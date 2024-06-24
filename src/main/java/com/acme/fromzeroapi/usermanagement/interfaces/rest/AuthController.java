package com.acme.fromzeroapi.usermanagement.interfaces.rest;

import com.acme.fromzeroapi.usermanagement.domain.model.queries.GetUserByEmailQuery;
import com.acme.fromzeroapi.usermanagement.domain.services.UserCommandService;
import com.acme.fromzeroapi.usermanagement.domain.services.UserQueryService;
import com.acme.fromzeroapi.usermanagement.interfaces.rest.resources.*;
import com.acme.fromzeroapi.usermanagement.interfaces.rest.transform.AuthenticatedUsedResourcerFromEntityAssembler;
import com.acme.fromzeroapi.usermanagement.interfaces.rest.transform.DeveloperCommandFromSignUpDeveloperResourceAssembler;
import com.acme.fromzeroapi.usermanagement.interfaces.rest.transform.EnterpriseCommandFromSignUpEnterpriseResourceAssembler;
import com.acme.fromzeroapi.usermanagement.interfaces.rest.transform.SignInCommandFromResourceAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/api/auth")
@Tag(name = "Auth", description = "Operations related to users")
public class AuthController {
    private final UserCommandService userCommandService;
    private final UserQueryService userQueryService;

    public AuthController(UserCommandService userCommandService, UserQueryService userQueryService) {
        this.userCommandService = userCommandService;
        this.userQueryService = userQueryService;
    }

//    @Operation(summary = "Get all users")
//    @GetMapping
//    public ResponseEntity<List<User>> getAllUsers(){
//        return ResponseEntity.ok(userQueryService.handle(new GetAllUsersQuery()));
//    }
//    @Operation(summary = "Get user by id")
//    @GetMapping("/{id}")
//    public ResponseEntity<User> getUserById(@PathVariable Long id) {
//        return userQueryService.handle(new GetUserByIdQuery(id))
//                .map(ResponseEntity::ok)
//                .orElse(ResponseEntity.notFound().build());
//    }
    @Operation(summary = "Get user by email")
    @GetMapping("/email/{email}")
    public ResponseEntity<UserResource> getUserByEmail(@PathVariable String email) {
        var getUserByEmailQuery=new GetUserByEmailQuery(email);
        var user = this.userQueryService.handle(getUserByEmailQuery);
        if(user.isEmpty())return ResponseEntity.notFound().build();
        var resource = UserResourceFromEntityAssembler.toResourceFromEntity(user.get());
        return ResponseEntity.ok(resource);
    }

    @Operation(summary = "Create Developer")
    @PostMapping("/register-developer")
    public ResponseEntity<UserResource> createDeveloper(@RequestBody SignUpDeveloperResource resource) {
        var command = DeveloperCommandFromSignUpDeveloperResourceAssembler.toCommandFromResource(resource);
        var user = userCommandService.handle(command);

        if (user.isEmpty()){
            return ResponseEntity.badRequest().build();
        }
        var userResource = UserResourceFromEntityAssembler.toResourceFromEntity(user.get());
        return new ResponseEntity<>(userResource, HttpStatus.CREATED);
    }
    @Operation(summary = "Create Enterprise")
    @PostMapping("/register-enterprise")
    public ResponseEntity<UserResource> createEnterprise(@RequestBody SignUpEnterpriseResource resource) {
        var registerCommand = EnterpriseCommandFromSignUpEnterpriseResourceAssembler.toCommandFromResource(resource);
        var user = userCommandService.handle(registerCommand);

        if (user.isEmpty()){
            return ResponseEntity.badRequest().build();
        }
        var userResource = UserResourceFromEntityAssembler.toResourceFromEntity(user.get());
        return new ResponseEntity<>(userResource, HttpStatus.CREATED);
    }

    @Operation(summary = "sign in")
    @PostMapping("/sign-in")
    public ResponseEntity<AuthenticateUserResource> signIn(@RequestBody SignInResource signInResource){
        var signInCommand = SignInCommandFromResourceAssembler.toCommandFromResource(signInResource);
        var authenticatedUser = userCommandService.handle(signInCommand);

        if(authenticatedUser.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        var authenticatedUserResource = AuthenticatedUsedResourcerFromEntityAssembler.toResourceFromEntity(
                authenticatedUser.get().getLeft());

        return ResponseEntity.ok(authenticatedUserResource);
    }
}

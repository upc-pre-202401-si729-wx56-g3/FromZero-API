package com.acme.fromzeroapi.auth.interfaces.rest;

import com.acme.fromzeroapi.auth.domain.model.aggregates.User;
import com.acme.fromzeroapi.auth.domain.model.commands.CreateUserCommand;
import com.acme.fromzeroapi.auth.domain.model.commands.SignUpDeveloperCommand;
import java.util.Optional;
import com.acme.fromzeroapi.auth.domain.model.queries.GetAllUsersQuery;
import com.acme.fromzeroapi.auth.domain.model.queries.GetUserByEmailQuery;
import com.acme.fromzeroapi.auth.domain.model.queries.GetUserByIdQuery;
import com.acme.fromzeroapi.auth.domain.services.UserCommandService;
import com.acme.fromzeroapi.auth.domain.services.UserQueryService;
import com.acme.fromzeroapi.auth.interfaces.rest.resources.SignUpDeveloperResource;
import com.acme.fromzeroapi.auth.interfaces.rest.transform.DeveloperCommandFromSignUpDeveloperResourceAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/v1/api/users")
@Tag(name = "Users", description = "Operations related to users")
public class UserController {
    private final UserCommandService userCommandService;
    private final UserQueryService userQueryService;

    public UserController(UserCommandService userCommandService, UserQueryService userQueryService) {
        this.userCommandService = userCommandService;
        this.userQueryService = userQueryService;
    }

    @Operation(summary = "Get all users")
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(){
        return ResponseEntity.ok(userQueryService.handle(new GetAllUsersQuery()));
    }
    @Operation(summary = "Get user by id")
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        return userQueryService.handle(new GetUserByIdQuery(id))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @Operation(summary = "Get user by email")
    @GetMapping("/email/{email}")
    public ResponseEntity<User> getUserByEmail(@PathVariable String email) {
        return userQueryService.handle(new GetUserByEmailQuery(email))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @Operation(summary = "Create Developer")
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody SignUpDeveloperResource resource) {
        SignUpDeveloperCommand command = DeveloperCommandFromSignUpDeveloperResourceAssembler.toCommandFromResource(resource);
        Optional<User> user = userCommandService.handle(command);

        if (user.isPresent()) {
            return ResponseEntity.ok(user.get());
        } else {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to register developer");
        }
    }
}

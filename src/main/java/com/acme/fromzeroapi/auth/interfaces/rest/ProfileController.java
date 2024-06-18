package com.acme.fromzeroapi.auth.interfaces.rest;

import com.acme.fromzeroapi.auth.domain.model.aggregates.Developer;
import com.acme.fromzeroapi.auth.domain.model.aggregates.Enterprise;
import com.acme.fromzeroapi.auth.domain.model.queries.GetAllDevelopersAsyncQuery;
import com.acme.fromzeroapi.auth.domain.model.queries.GetDeveloperByUserIdAsyncQuery;
import com.acme.fromzeroapi.auth.domain.model.queries.GetEnterpriseByUserIdAsyncQuery;
import com.acme.fromzeroapi.auth.domain.services.ProfileQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/api/profiles")
@Tag(name = "Profiles", description = "Operations related to profiles")
public class ProfileController {
    private final ProfileQueryService profileQueryService;

    public ProfileController(ProfileQueryService profileQueryService) {
        this.profileQueryService = profileQueryService;
    }

    @Operation(summary = "Get all developers")
    @GetMapping("/developers")
    public ResponseEntity<List<Developer>> getAllDevelopers() {
        return ResponseEntity.ok(profileQueryService.handle(new GetAllDevelopersAsyncQuery()));
    }

    @Operation(summary = "Get developer by id")
    @GetMapping("/developers/{userId}")
    public ResponseEntity<Developer> getDeveloperById(@PathVariable Long userId) {
        var getDeveloperByUserIdQuery = new GetDeveloperByUserIdAsyncQuery(userId);
        var developer = profileQueryService.handle(getDeveloperByUserIdQuery);
        if (developer.isEmpty()) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(developer.get());
    }

    @Operation(summary = "Get enterprise by id")
    @GetMapping("/enterprises/{userId}")
    public ResponseEntity<Enterprise> getEnterpriseById(@PathVariable Long userId) {
        var getEnterpriseByUserID = new GetEnterpriseByUserIdAsyncQuery(userId);
        var enterprise = profileQueryService.handle(getEnterpriseByUserID);
        if (enterprise.isEmpty()) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(enterprise.get());
    }
}

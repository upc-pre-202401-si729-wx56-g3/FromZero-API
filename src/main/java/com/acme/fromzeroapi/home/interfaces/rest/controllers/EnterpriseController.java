package com.acme.fromzeroapi.home.interfaces.rest.controllers;

import com.acme.fromzeroapi.home.domain.model.aggregates.Enterprise;
import com.acme.fromzeroapi.home.domain.model.queries.GetAllEnterprisesQuery;
import com.acme.fromzeroapi.home.domain.model.queries.GetEnterpriseByCountryQuery;
import com.acme.fromzeroapi.home.domain.model.queries.GetEnterpriseByIdQuery;
import com.acme.fromzeroapi.home.domain.model.queries.GetEnterpriseByRucQuery;
import com.acme.fromzeroapi.home.domain.services.EnterpriseCommandService;
import com.acme.fromzeroapi.home.domain.services.EnterpriseQueryService;
import com.acme.fromzeroapi.home.interfaces.rest.resources.CreateEnterpriseResource;
import com.acme.fromzeroapi.home.interfaces.rest.resources.EnterpriseResource;
import com.acme.fromzeroapi.home.interfaces.rest.transform.CreateEnterpriseCommandFromResourceAssembler;
import com.acme.fromzeroapi.home.interfaces.rest.transform.EnterpriseSourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("v1/api/enterprises")
@Tag(name= "Enterprises", description = "Operations related to enterprises")
public class EnterpriseController {
    private final EnterpriseCommandService enterpriseCommandService;
    private final EnterpriseQueryService enterpriseQueryService;

    public EnterpriseController(EnterpriseCommandService enterpriseCommandService, EnterpriseQueryService enterpriseQueryService) {
        this.enterpriseCommandService = enterpriseCommandService;
        this.enterpriseQueryService = enterpriseQueryService;
    }

    @Operation(summary = "Create a new enterprise")
    @PostMapping
    public ResponseEntity<EnterpriseResource> createEnterprise(@RequestBody CreateEnterpriseResource resource){
        Optional<Enterprise> enterpriseResource = enterpriseCommandService
                .handle(CreateEnterpriseCommandFromResourceAssembler.toCommandFromSource(resource));

        return enterpriseResource.map(enterprise -> new ResponseEntity<>(
                        EnterpriseSourceFromEntityAssembler.toSourceFromEntity(enterprise), CREATED))
                .orElseGet(()->ResponseEntity.badRequest().build());
    }

    @Operation(summary = "Get all enterprise")
    @GetMapping
    public ResponseEntity<List<EnterpriseResource>> getAllEnterprises() {
        var getAllEnterprises = new GetAllEnterprisesQuery();
        var enterprises = enterpriseQueryService.handle(getAllEnterprises);

        var enterprisesResources = enterprises.stream().map(
                EnterpriseSourceFromEntityAssembler::toSourceFromEntity).toList();

        return ResponseEntity.ok(enterprisesResources);
    }

    @Operation(summary = "Get enterprise by country")
    @GetMapping("/country/{country}")
    public ResponseEntity<List<EnterpriseResource>> getEnterpriseByCountry(@PathVariable String country){
        var getEnterpriseByCountryQuery = new GetEnterpriseByCountryQuery(country);
        var enterprise = enterpriseQueryService.handle(getEnterpriseByCountryQuery);
        if(enterprise.isEmpty()) return ResponseEntity.badRequest().build();
        var enterpriseResource = enterprise.stream().map(
                EnterpriseSourceFromEntityAssembler::toSourceFromEntity).toList();
        return ResponseEntity.ok(enterpriseResource);
    }

    @Operation(summary = "Get enterprise by id")
    @GetMapping("/{enterpriseId}")
    public ResponseEntity<EnterpriseResource> getEnterpriseById(@PathVariable Long enterpriseId){
        var getEnterpriseByIdQuery = new GetEnterpriseByIdQuery(enterpriseId);
        var enterprise = enterpriseQueryService.handle(getEnterpriseByIdQuery);
        if (enterprise.isEmpty()) return ResponseEntity.badRequest().build();
        var enterpriseResource = EnterpriseSourceFromEntityAssembler.toSourceFromEntity(enterprise.get());
        return ResponseEntity.ok(enterpriseResource);
    }

    @Operation(summary = "Get enterprise by ruc")
    @GetMapping("/ruc/{ruc}")
    public ResponseEntity<EnterpriseResource> getEnterpriseByRuc(@PathVariable String ruc){
        var getEnterpriseByRucQuery = new GetEnterpriseByRucQuery(ruc);
        var enterprise = enterpriseQueryService.handle(getEnterpriseByRucQuery);
        if(enterprise.isEmpty()) return ResponseEntity.badRequest().build();
        var enterpriseResource = EnterpriseSourceFromEntityAssembler.toSourceFromEntity(enterprise.get());
        return ResponseEntity.ok(enterpriseResource);
    }
}
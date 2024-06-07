package com.acme.fromzeroapi.home.interfaces.rest.controllers;

import com.acme.fromzeroapi.home.domain.model.aggregates.Developer;
import com.acme.fromzeroapi.home.domain.model.queries.GetAllDevelopersQuery;
import com.acme.fromzeroapi.home.domain.model.queries.GetDeveloperByCountryQuery;
import com.acme.fromzeroapi.home.domain.model.queries.GetDeveloperByIdQuery;
import com.acme.fromzeroapi.home.domain.model.queries.GetDeveloperBySpecialityQuery;
import com.acme.fromzeroapi.home.domain.services.DeveloperCommandService;
import com.acme.fromzeroapi.home.domain.services.DeveloperQueryService;
import com.acme.fromzeroapi.home.interfaces.rest.resources.CreateDeveloperResource;
import com.acme.fromzeroapi.home.interfaces.rest.resources.DeveloperResource;
import com.acme.fromzeroapi.home.interfaces.rest.transform.CreateDeveloperCommandFromResourceAssembler;
import com.acme.fromzeroapi.home.interfaces.rest.transform.DeveloperSourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("v1/api/developers")
@Tag(name= "Developers", description = "Operations related to developers")
public class DeveloperController {

    private final DeveloperCommandService developerCommandService;
    private final DeveloperQueryService developerQueryService;

    public DeveloperController(DeveloperCommandService developerCommandService, DeveloperQueryService developerQueryService){
        this.developerCommandService = developerCommandService;
        this.developerQueryService = developerQueryService;
    }

    @Operation(summary = "Create a new developers")
    @PostMapping
    public ResponseEntity<DeveloperResource> createDeveloper(@RequestBody CreateDeveloperResource resource) {
        Optional<Developer> desarrolladorResource = developerCommandService
                .handle(CreateDeveloperCommandFromResourceAssembler.toCommandFromSource(resource));

        return desarrolladorResource.map(desarrollador -> new ResponseEntity<>(
                DeveloperSourceFromEntityAssembler.toResourceFromSource(desarrollador), CREATED))
                .orElseGet(()->ResponseEntity.badRequest().build());
    }

    @Operation(summary = "Get all developers")
    @GetMapping
    private ResponseEntity<List<DeveloperResource>> getAllDesarrolladores(){
        var getAllDesarrolladores = new GetAllDevelopersQuery();
        var desarrolladores = developerQueryService.handle(getAllDesarrolladores);

        var desarrolladoresResources = desarrolladores.stream().map(
                DeveloperSourceFromEntityAssembler::toResourceFromSource).toList();

        return ResponseEntity.ok(desarrolladoresResources);
    }

    @Operation(summary = "Get a developer by id")
    @GetMapping("/{developerId}")
    public ResponseEntity<DeveloperResource> getDeveloperById(@PathVariable Long developerId) {
        var getDeveloperByIdQuery = new GetDeveloperByIdQuery(developerId);
        var developer = developerQueryService.handle(getDeveloperByIdQuery);
        if (developer.isEmpty()) return ResponseEntity.badRequest().build();
        var developerResource = DeveloperSourceFromEntityAssembler.toResourceFromSource(developer.get());
        return ResponseEntity.ok(developerResource);
    }

    @Operation(summary = "Get developer by country")
    @GetMapping("/country/{country}")
    public ResponseEntity<List<DeveloperResource>> getDeveloperByCountry(@PathVariable String country){
        var getDeveloperByCountryQuery = new GetDeveloperByCountryQuery(country);
        var developer = developerQueryService.handle(getDeveloperByCountryQuery);
        if(developer.isEmpty()) return ResponseEntity.badRequest().build();
        var developerResource = developer.stream().map(
                DeveloperSourceFromEntityAssembler::toResourceFromSource).toList();
        return ResponseEntity.ok(developerResource);
    }

    @Operation(summary = "Get developer by speciality")
    @GetMapping("/speciality/{specialties}")
    public ResponseEntity<DeveloperResource> getDeveloperBySpeciality(@PathVariable String specialties){
        var getDeveloperBySpecialityQuery = new GetDeveloperBySpecialityQuery(specialties);
        var developer = developerQueryService.handle(getDeveloperBySpecialityQuery);
        if(developer.isEmpty()) return ResponseEntity.badRequest().build();
        var developerResource = DeveloperSourceFromEntityAssembler.toResourceFromSource(developer.get());
        return ResponseEntity.ok(developerResource);
    }
}
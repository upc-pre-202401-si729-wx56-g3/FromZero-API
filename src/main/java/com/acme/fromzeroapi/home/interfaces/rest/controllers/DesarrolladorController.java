package com.acme.fromzeroapi.home.interfaces.rest.controllers;

import com.acme.fromzeroapi.home.domain.model.aggregates.Desarrollador;
import com.acme.fromzeroapi.home.domain.model.queries.GetAllDesarrolladoresQuery;
import com.acme.fromzeroapi.home.domain.services.DesarrolladorCommandService;
import com.acme.fromzeroapi.home.domain.services.DesarrolladorQueryService;
import com.acme.fromzeroapi.home.interfaces.rest.resources.CreateDesarrolladorResource;
import com.acme.fromzeroapi.home.interfaces.rest.resources.DesarrolladorResource;
import com.acme.fromzeroapi.home.interfaces.rest.transform.CreateDesarrolladorCommandFromResourceAssembler;
import com.acme.fromzeroapi.home.interfaces.rest.transform.DesarrolladorSourceFromEntityAssembler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("api/v1/desarrolladores")
public class DesarrolladorController {
    private final DesarrolladorCommandService desarrolladorCommandService;
    private final DesarrolladorQueryService desarrolladorQueryService;

    public DesarrolladorController(DesarrolladorCommandService desarrolladorCommandService, DesarrolladorQueryService desarrolladorQueryService){
        this.desarrolladorCommandService = desarrolladorCommandService;
        this.desarrolladorQueryService = desarrolladorQueryService;
    }

    @PostMapping
    public ResponseEntity<DesarrolladorResource> createDesarrollador(@RequestBody CreateDesarrolladorResource resource) {
        Optional<Desarrollador> desarrolladorResource =desarrolladorCommandService
                .handle(CreateDesarrolladorCommandFromResourceAssembler.toCommandFromSource(resource));

        return desarrolladorResource.map(desarrollador -> new ResponseEntity<>(
                DesarrolladorSourceFromEntityAssembler.toResourceFromSource(desarrollador), CREATED))
                .orElseGet(()->ResponseEntity.badRequest().build());
    }

    @GetMapping
    private ResponseEntity<List<DesarrolladorResource>> getAllDesarrolladores(){
        var getAllDesarrolladores = new GetAllDesarrolladoresQuery();
        var desarrolladores = desarrolladorQueryService.handle(getAllDesarrolladores);

        var desarrolladoresResources = desarrolladores.stream().map(
                DesarrolladorSourceFromEntityAssembler::toResourceFromSource).toList();

        return ResponseEntity.ok(desarrolladoresResources);
    }
}

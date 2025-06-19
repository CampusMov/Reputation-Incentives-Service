package com.campusmov.platform.reputationincentivesservice.reputationincentives.interfaces.rest.controllers;

import com.campusmov.platform.reputationincentivesservice.reputationincentives.domain.model.queries.GetAllValorationsByUserIdQuery;
import com.campusmov.platform.reputationincentivesservice.reputationincentives.domain.services.ValorationCommandService;
import com.campusmov.platform.reputationincentivesservice.reputationincentives.domain.services.ValorationQueryService;
import com.campusmov.platform.reputationincentivesservice.reputationincentives.interfaces.rest.resources.CreateValorationResource;
import com.campusmov.platform.reputationincentivesservice.reputationincentives.interfaces.rest.resources.ValorationResource;
import com.campusmov.platform.reputationincentivesservice.reputationincentives.interfaces.rest.transform.CreateValorationCommandFromResourceAssembler;
import com.campusmov.platform.reputationincentivesservice.reputationincentives.interfaces.rest.transform.ValorationResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/reputation-incentives/valorations")
@Tag(name = "valoration", description = "Valoration Management Endpoints")
public class ValorationController {
    private final ValorationCommandService valorationCommandService;
    private final ValorationQueryService valorationQueryService;

    public ValorationController(ValorationCommandService valorationCommandService, ValorationQueryService valorationQueryService) {
        this.valorationCommandService = valorationCommandService;
        this.valorationQueryService = valorationQueryService;
    }

    @PostMapping("")
    @Operation(summary = "create valoration", description = "Create a new valoration", operationId = "create-valoration")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Valoration created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    public ResponseEntity<ValorationResource> createValoration(@RequestBody CreateValorationResource createvalorationResource) {
        var command = CreateValorationCommandFromResourceAssembler.toCommandFromResource(createvalorationResource);
        var valoration = valorationCommandService.handle(command);
        var valorationResource = ValorationResourceFromEntityAssembler.toResourceFromEntity(valoration.get());
        return ResponseEntity.ok(valorationResource);
    }

    @GetMapping("{userId}")
    @Operation(summary = "get valorations by user", description = "Retrieves valorations for a specific user", operationId = "get-valorations-by-user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Valorations retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    public ResponseEntity<Collection<ValorationResource>> getAllValorationsByUserId(@PathVariable String userId) {
        var query = new GetAllValorationsByUserIdQuery(userId);
        var valorations = valorationQueryService.handle(query);
        var valorationResources = valorations.stream()
                .map(ValorationResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(valorationResources);
    }


}

package com.campusmov.platform.reputationincentivesservice.reputationincentives.interfaces.rest;

import com.campusmov.platform.reputationincentivesservice.reputationincentives.domain.services.ValorationCommandService;
import com.campusmov.platform.reputationincentivesservice.reputationincentives.interfaces.rest.resources.CreateValorationResource;
import com.campusmov.platform.reputationincentivesservice.reputationincentives.interfaces.rest.resources.ValorationResource;
import com.campusmov.platform.reputationincentivesservice.reputationincentives.interfaces.rest.transform.CreateValorationCommandFromResourceAssembler;
import com.campusmov.platform.reputationincentivesservice.reputationincentives.interfaces.rest.transform.ValorationResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reputation-incentives/valorations")
@Tag(name = "valoration", description = "Valoration Management Endpoints")
public class ValorationController {
    private final ValorationCommandService valorationCommandService;

    public ValorationController(ValorationCommandService valorationCommandService) {
        this.valorationCommandService = valorationCommandService;
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
}

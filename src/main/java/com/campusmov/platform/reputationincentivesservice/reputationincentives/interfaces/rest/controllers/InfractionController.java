package com.campusmov.platform.reputationincentivesservice.reputationincentives.interfaces.rest.controllers;

import com.campusmov.platform.reputationincentivesservice.reputationincentives.domain.model.queries.GetAllPenaltiesByUserIdQuery;
import com.campusmov.platform.reputationincentivesservice.reputationincentives.domain.services.InfractionTrackerCommandService;
import com.campusmov.platform.reputationincentivesservice.reputationincentives.domain.services.PenaltyQueryService;
import com.campusmov.platform.reputationincentivesservice.reputationincentives.interfaces.rest.resources.CreateInfractionTrackerResource;
import com.campusmov.platform.reputationincentivesservice.reputationincentives.interfaces.rest.resources.PenaltyResource;
import com.campusmov.platform.reputationincentivesservice.reputationincentives.interfaces.rest.transform.CreateInfractionTrackerCommandFromResourceAssembler;
import com.campusmov.platform.reputationincentivesservice.reputationincentives.interfaces.rest.transform.PenaltyResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/reputation-incentives/infractions")
@Tag(name = "infraction", description = "Infraction Management Endpoints")
public class InfractionController {
    private final InfractionTrackerCommandService infractionTrackerCommandService;
    private final PenaltyQueryService penaltyQueryService;

    public InfractionController(InfractionTrackerCommandService infractionTrackerCommandService, PenaltyQueryService penaltyQueryService) {
        this.infractionTrackerCommandService = infractionTrackerCommandService;
        this.penaltyQueryService = penaltyQueryService;
    }

    @PostMapping("increase")
    @Operation(summary = "increase infraction counter", description = "Increases or creates infraction counter", operationId = "increase-infraction")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Infraction updated successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    public boolean increaseInfractionTracker(@RequestBody CreateInfractionTrackerResource createInfractionTrackerResource) {
        var command = CreateInfractionTrackerCommandFromResourceAssembler.toCommandFromResource(createInfractionTrackerResource);
        var infractionTracker = infractionTrackerCommandService.handle(command);
        return infractionTracker.isPresent();
    }

    @PostMapping("reset")
    @Operation(summary = "reset infraction counter", description = "Resets infraction counter", operationId = "reset-infraction")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Infraction updated successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    public boolean resetInfractionTracker(@RequestBody CreateInfractionTrackerResource createInfractionTrackerResource) {
        var command = CreateInfractionTrackerCommandFromResourceAssembler.toCommandFromResource(createInfractionTrackerResource);
        var infractionTracker = infractionTrackerCommandService.handleReset(command);
        return infractionTracker.isPresent();
    }

    @GetMapping("{userId}")
    @Operation(summary = "get penalties by user", description = "Retrieves penalties for a specific user", operationId = "get-penalties-by-user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Penalties retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    public ResponseEntity<Collection<PenaltyResource>> getAllPenaltiesByUserId(@PathVariable String userId) {
        var query = new GetAllPenaltiesByUserIdQuery(userId);
        var penalties = penaltyQueryService.handle(query);
        var penaltyResources = penalties.stream()
                .map(PenaltyResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(penaltyResources);

    }


}

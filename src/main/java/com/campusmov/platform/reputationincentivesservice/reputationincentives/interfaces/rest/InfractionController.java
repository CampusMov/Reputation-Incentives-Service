package com.campusmov.platform.reputationincentivesservice.reputationincentives.interfaces.rest;

import com.campusmov.platform.reputationincentivesservice.reputationincentives.domain.services.InfractionTrackerCommandService;
import com.campusmov.platform.reputationincentivesservice.reputationincentives.interfaces.rest.resources.CreateInfractionTrackerResource;
import com.campusmov.platform.reputationincentivesservice.reputationincentives.interfaces.rest.transform.CreateInfractionTrackerCommandFromResourceAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reputation-incentives/infractions")
@Tag(name = "infraction", description = "Infraction Management Endpoints")
public class InfractionController {
    private final InfractionTrackerCommandService infractionTrackerCommandService;

    public InfractionController(InfractionTrackerCommandService infractionTrackerCommandService) {
        this.infractionTrackerCommandService = infractionTrackerCommandService;
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
        var infractionTracker = infractionTrackerCommandService.handle(command);
        return infractionTracker.isPresent();
    }



}

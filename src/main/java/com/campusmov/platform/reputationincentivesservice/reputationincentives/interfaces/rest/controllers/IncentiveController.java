package com.campusmov.platform.reputationincentivesservice.reputationincentives.interfaces.rest.controllers;

import com.campusmov.platform.reputationincentivesservice.reputationincentives.domain.services.IncentiveCommandService;
import com.campusmov.platform.reputationincentivesservice.reputationincentives.interfaces.rest.resources.CreateIncentiveResource;
import com.campusmov.platform.reputationincentivesservice.reputationincentives.interfaces.rest.transform.CreateIncentiveCommandFromResourceAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reputation-incentives/incentives")
@Tag(name = "incentive", description = "Incentive Management Endpoints")
public class IncentiveController {
    private final IncentiveCommandService incentiveCommandService;

    public IncentiveController(IncentiveCommandService incentiveCommandService) {
        this.incentiveCommandService = incentiveCommandService;
    }

    @PostMapping("")
    @Operation(summary = "create incentive", description = "Create a new incentive", operationId = "create-incentive")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Incentive created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    public boolean createIncentive(@RequestBody CreateIncentiveResource createIncentiveResource) {
        var command = CreateIncentiveCommandFromResourceAssembler.toCommandFromResource(createIncentiveResource);
        var incentive = incentiveCommandService.handle(command);
        return incentive.isPresent();
    }
}

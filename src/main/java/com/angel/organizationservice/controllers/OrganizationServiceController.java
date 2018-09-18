package com.angel.organizationservice.controllers;

import com.angel.organizationservice.model.Organization;
import com.angel.organizationservice.services.OrganizationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value="v1/organizations")
public class OrganizationServiceController {

    private final OrganizationService organizationService;

    Logger logger = LoggerFactory.getLogger(OrganizationServiceController.class);

    @Autowired
    public OrganizationServiceController(OrganizationService organizationService) {
        this.organizationService = organizationService;
    }

    @RequestMapping(value="/{organizationId}",method = RequestMethod.GET)
    public Optional<Organization> getOrganization(@PathVariable("organizationId") String organizationId) {
        logger.error("NAJDI", "org povikano");
        return organizationService.getOrg(organizationId);
    }

    @RequestMapping(value="/{organizationId}",method = RequestMethod.PUT)
    public void updateOrganization( @PathVariable("organizationId") String orgId, @RequestBody Organization org) {
        organizationService.updateOrg( org );
    }

    @RequestMapping(value="/{organizationId}",method = RequestMethod.POST)
    public void saveOrganization(@RequestBody Organization org) {
        organizationService.saveOrg( org );
    }

    @RequestMapping(value="/{organizationId}",method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteOrganization( @PathVariable("organizationId") String orgId,  @RequestBody Organization org) {
        organizationService.deleteOrg( org );
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<Organization> getAll() {
        return organizationService.getAll();
    }

}

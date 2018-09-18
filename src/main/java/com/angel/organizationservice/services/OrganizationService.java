package com.angel.organizationservice.services;

import com.angel.organizationservice.events.source.SimpleSourceBean;
import com.angel.organizationservice.model.Organization;
import com.angel.organizationservice.repository.OrganizationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class OrganizationService {

    final OrganizationRepository organizationRepository;
    private Logger logger = LoggerFactory.getLogger("asd");
    private SimpleSourceBean simpleSourceBean;

    @Autowired
    public OrganizationService(OrganizationRepository organizationRepository, SimpleSourceBean simpleSourceBean) {
        this.organizationRepository = organizationRepository;
        this.simpleSourceBean = simpleSourceBean;
    }

    public Optional<Organization> getOrg(String organizationId) {
        logger.error("Getting data for organization {}", organizationId);
        return organizationRepository.findById(organizationId);
    }

    public void saveOrg(Organization org) {
        org.setId(UUID.randomUUID().toString());
        organizationRepository.save(org);
        simpleSourceBean.publishOrgChange("SAVE", org.getId());
    }

    public void updateOrg(Organization org) {
        organizationRepository.save(org);
        simpleSourceBean.publishOrgChange("UPDATE", org.getId());
    }

    public void deleteOrg(Organization org) {
        organizationRepository.delete(org);
        simpleSourceBean.publishOrgChange("DELETE", org.getId());
    }

    public List<Organization> getAll() {
        return (List<Organization>) organizationRepository.findAll();
    }
}

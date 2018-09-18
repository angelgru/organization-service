package com.angel.organizationservice.repository;

import com.angel.organizationservice.model.Organization;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrganizationRepository extends CrudRepository<Organization, String>{
}

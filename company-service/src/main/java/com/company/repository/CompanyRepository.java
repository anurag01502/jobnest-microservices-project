package com.company.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.company.model.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {

    boolean existsByCompanyNameIgnoreCase(String companyName);

    @Query("""
        SELECT DISTINCT c
        FROM Company c
        JOIN c.locations cl
        WHERE
            (:companyName IS NULL OR
             LOWER(c.companyName) LIKE LOWER(CONCAT('%', :companyName, '%')))
        AND
            (:state IS NULL OR
             LOWER(cl.state) = LOWER(:state))
        AND
            (:city IS NULL OR
             LOWER(cl.city) = LOWER(:city))
        AND
            (:country IS NULL OR
             LOWER(cl.country) = LOWER(:country))
        """)
    Page<Company> searchCompanyBasedOnFilters(
            @Param("companyName") String companyName,
            @Param("state") String state,
            @Param("city") String city,
            @Param("country") String country,
            Pageable pageable
    );
}
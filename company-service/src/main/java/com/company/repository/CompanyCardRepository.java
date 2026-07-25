package com.company.repository;

import com.company.model.CompanyCardInfo;
import com.company.model.Company;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;



public interface CompanyCardRepository extends JpaRepository<Company, Long> {
    @Query("""
            SELECT
                c.companyName AS companyName,
                cl.city AS location,
                cs.averageRating AS ratings,
                cs.totalClients AS totalClients
            FROM Company c
            LEFT JOIN c.companyLocation cl
            LEFT JOIN c.companyStatistics cs
            """)
    Page<CompanyCardInfo> getCompanyCards(Pageable pageable);
}

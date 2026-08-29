package com.company.repository;

import com.company.model.VerificationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VerificationStatusRepository
        extends JpaRepository<VerificationStatus, Long> {

}
package com.company.rowmapper;

import com.company.dto.VerificationStatusDTO;
import com.company.model.VerificationStatus;

public class VerificationStatusRowmapper {

    private VerificationStatusRowmapper() {
        // Utility class
    }

    public static VerificationStatusDTO toDto(VerificationStatus model) {

        if (model == null) {
            return null;
        }

        VerificationStatusDTO dto = new VerificationStatusDTO();

        dto.setCompanyId(model.getCompanyId());
        dto.setStatus(model.getStatus());
        dto.setVerifiedAt(model.getVerifiedAt());
        dto.setVerifiedBy(model.getVerifiedBy());

        return dto;
    }

    public static VerificationStatus toModel(VerificationStatusDTO dto) {

        if (dto == null) {
            return null;
        }

        VerificationStatus model = new VerificationStatus();

        model.setCompanyId(dto.getCompanyId());
        model.setStatus(dto.getStatus());
        model.setVerifiedAt(dto.getVerifiedAt());
        model.setVerifiedBy(dto.getVerifiedBy());

        return model;
    }
}
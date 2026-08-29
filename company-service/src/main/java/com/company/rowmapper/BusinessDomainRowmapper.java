package com.company.rowmapper;

import com.company.dto.BusinessDomainDTO;
import com.company.model.BusinessDomain;

public class BusinessDomainRowmapper {

    private BusinessDomainRowmapper() {
        // Utility class
    }

    public static BusinessDomainDTO toDto(BusinessDomain model) {

        if (model == null) {
            return null;
        }

        BusinessDomainDTO dto = new BusinessDomainDTO();

        dto.setDomainId(model.getDomainId());
        dto.setDomainName(model.getDomainName());

        return dto;
    }

    public static BusinessDomain toModel(BusinessDomainDTO dto) {

        if (dto == null) {
            return null;
        }

        BusinessDomain model = new BusinessDomain();

        model.setDomainId(dto.getDomainId());
        model.setDomainName(dto.getDomainName());

        return model;
    }
}
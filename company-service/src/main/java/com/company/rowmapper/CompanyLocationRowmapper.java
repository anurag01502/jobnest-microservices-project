package com.company.rowmapper;

import com.company.dto.CompanyLocationDTO;
import com.company.model.CompanyLocation;

public class CompanyLocationRowmapper {

    private CompanyLocationRowmapper() {
        // Utility class
    }

    public static CompanyLocationDTO toDto(CompanyLocation model) {

        if (model == null) {
            return null;
        }

        CompanyLocationDTO dto = new CompanyLocationDTO();

        dto.setLocationId(model.getLocationId());
        dto.setLatitude(model.getLatitude());
        dto.setLongitude(model.getLongitude());
        dto.setAddress(model.getAddress());
        dto.setCity(model.getCity());
        dto.setState(model.getState());
        dto.setCountry(model.getCountry());
        dto.setPostalCode(model.getPostalCode());

        return dto;
    }

    public static CompanyLocation toModel(CompanyLocationDTO dto) {

        if (dto == null) {
            return null;
        }

        CompanyLocation model = new CompanyLocation();

        model.setLocationId(dto.getLocationId());
        model.setLatitude(dto.getLatitude());
        model.setLongitude(dto.getLongitude());
        model.setAddress(dto.getAddress());
        model.setCity(dto.getCity());
        model.setState(dto.getState());
        model.setCountry(dto.getCountry());
        model.setPostalCode(dto.getPostalCode());

        return model;
    }
}
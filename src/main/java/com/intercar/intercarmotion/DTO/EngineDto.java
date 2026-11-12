package com.intercar.intercarmotion.DTO;

import com.intercar.intercarmotion.Entity.FuelType;

public record EngineDto(
        Long id,
        String name,
        FuelType fuelType,
        Integer horsepower,
        Integer displacementCc,
        Integer torqueNm,
        String transmission,
        Double acceleration0100
) {
}

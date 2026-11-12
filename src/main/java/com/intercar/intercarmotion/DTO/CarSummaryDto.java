package com.intercar.intercarmotion.DTO;

public record CarSummaryDto(
        Long id,
        String name,
        String brandName,
        String segment,
        Integer yearFrom,
        Integer yearTo,
        String primaryImageUrl
) {
}

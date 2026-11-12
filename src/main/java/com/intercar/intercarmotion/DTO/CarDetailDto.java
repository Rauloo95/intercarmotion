package com.intercar.intercarmotion.DTO;

import java.util.List;

public record CarDetailDto(
        Long id,
        String name,
        String brandName,
        String segment,
        Integer yearFrom,
        Integer yearTo,
        List<String> imageUrls,
        List<EngineDto> engines
) {
}

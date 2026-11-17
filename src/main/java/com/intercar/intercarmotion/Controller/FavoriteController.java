package com.intercar.intercarmotion.Controller;



import com.intercar.intercarmotion.DTO.CarSummaryDto;
import com.intercar.intercarmotion.Service.FavoriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/favorites")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class FavoriteController {

    private final FavoriteService favoriteService;

    @GetMapping
    public ResponseEntity<List<CarSummaryDto>> getMyFavorites(Authentication authentication) {
        return ResponseEntity.ok(favoriteService.getUserFavorites(authentication.getName()));
    }

    @PostMapping("/{carId}")
    public ResponseEntity<?> toggleFavorite(@PathVariable Long carId, Authentication authentication) {
        boolean added = favoriteService.toggleFavorite(authentication.getName(), carId);
        return ResponseEntity.ok(Map.of("favorite", added, "carId", carId));
    }
}

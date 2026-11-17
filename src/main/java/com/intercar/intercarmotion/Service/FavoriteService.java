package com.intercar.intercarmotion.Service;

import com.intercar.intercarmotion.Entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.intercar.intercarmotion.DTO.CarSummaryDto;
import com.intercar.intercarmotion.Entity.CarImage;
import com.intercar.intercarmotion.Entity.CarModel;
import com.intercar.intercarmotion.Repository.CarModelRepository;
import com.intercar.intercarmotion.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FavoriteService {
    private final UserRepository userRepository;
    private final CarModelRepository carModelRepository;

    @Transactional(readOnly = true)
    public List<CarSummaryDto> getUserFavorites(String userEmail) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        return user.getFavoriteCars().stream().map(car -> {
            String primaryUrl = car.getImages().stream()
                    .filter(CarImage::isPrimary)
                    .map(CarImage::getUrl)
                    .findFirst()
                    .orElse(car.getImages().isEmpty() ? null : car.getImages().get(0).getUrl());

            return new CarSummaryDto(
                    car.getId(),
                    car.getName(),
                    car.getBrand() != null ? car.getBrand().getName() : "Sin marca",
                    car.getSegment(),
                    car.getYearFrom(),
                    car.getYearTo(),
                    primaryUrl
            );
        }).toList();
    }

    @Transactional
    public boolean toggleFavorite(String userEmail, Long carId) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        CarModel car = carModelRepository.findById(carId)
                .orElseThrow(() -> new RuntimeException("Coche no encontrado"));

        boolean isAdded;
        if (user.getFavoriteCars().contains(car)) {
            user.getFavoriteCars().remove(car);
            isAdded = false;
        } else {
            user.getFavoriteCars().add(car);
            isAdded = true;
        }

        userRepository.save(user);
        return isAdded;
    }
}

package com.intercar.intercarmotion.Controller;


import com.intercar.intercarmotion.DTO.CarDetailDto;
import com.intercar.intercarmotion.DTO.CarSummaryDto;
import com.intercar.intercarmotion.DTO.EngineDto;
import com.intercar.intercarmotion.Entity.CarImage;
import com.intercar.intercarmotion.Entity.CarModel;
import com.intercar.intercarmotion.Repository.CarModelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cars")
@CrossOrigin(origins = "*")
public class CarController {

    private final CarModelRepository carModelRepository;

    public CarController(CarModelRepository carModelRepository) {
        this.carModelRepository = carModelRepository;
    }

    @GetMapping
    public ResponseEntity<List<CarSummaryDto>> getCars(@RequestParam(required = false) String query) {
        List<CarModel> cars;

        if (query != null && !query.trim().isEmpty()) {
            cars = carModelRepository.findByNameContainingIgnoreCaseOrBrandNameContainingIgnoreCase(query.trim(), query.trim());
        } else {
            cars = carModelRepository.findAll();
        }

        List<CarSummaryDto> response = cars.stream().map(car -> {
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

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarDetailDto> getCarById(@PathVariable Long id) {
        return carModelRepository.findById(id)
                .map(car -> {
                    List<String> images = car.getImages().stream().map(CarImage::getUrl).toList();
                    List<EngineDto> engines = car.getEngines().stream().map(e -> new EngineDto(
                            e.getId(),
                            e.getName(),
                            e.getFuelType(),
                            e.getHorsepower(),
                            e.getDisplacementCc(),
                            e.getTorqueNm(),
                            e.getTransmission(),
                            e.getAcceleration0100()
                    )).toList();

                    return ResponseEntity.ok(new CarDetailDto(
                            car.getId(),
                            car.getName(),
                            car.getBrand() != null ? car.getBrand().getName() : "Sin marca",
                            car.getSegment(),
                            car.getYearFrom(),
                            car.getYearTo(),
                            images,
                            engines
                    ));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<CarModel> createCar(@RequestBody CarModel car) {
        if (car.getEngines() != null) {
            car.getEngines().forEach(e -> e.setCarModel(car));
        }
        if (car.getImages() != null) {
            car.getImages().forEach(img -> img.setCarModel(car));
        }
        return ResponseEntity.ok(carModelRepository.save(car));
    }
}
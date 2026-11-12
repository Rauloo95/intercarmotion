package com.intercar.intercarmotion.Repository;



import com.intercar.intercarmotion.Entity.CarModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CarModelRepository extends JpaRepository<CarModel, Long> {
    List<CarModel> findByNameContainingIgnoreCaseOrBrandNameContainingIgnoreCase(String name, String brandName);
}

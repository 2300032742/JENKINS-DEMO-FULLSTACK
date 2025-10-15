package com.klef.devops.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.klef.devops.model.Watch;

@Repository
public interface WatchRepository extends JpaRepository<Watch, Integer> {
    Watch findByBrand(String brand);
    Watch findByModel(String model);
}

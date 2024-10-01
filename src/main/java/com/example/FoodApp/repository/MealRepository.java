package com.example.FoodApp.repository;

import com.example.FoodApp.model.Meal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MealRepository extends JpaRepository<Meal,Long> {
    Meal findByName(String name);
}

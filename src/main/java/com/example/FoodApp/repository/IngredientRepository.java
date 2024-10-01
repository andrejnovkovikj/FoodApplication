package com.example.FoodApp.repository;

import com.example.FoodApp.model.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient,Long> {
    Ingredient findByName(String name);
}

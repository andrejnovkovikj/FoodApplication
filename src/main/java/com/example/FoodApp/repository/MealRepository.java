package com.example.FoodApp.repository;

import com.example.FoodApp.model.Meal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MealRepository extends JpaRepository<Meal,Long> {
    Meal findByName(String name);
    @Query("SELECT m FROM Meal m JOIN m.ingredients i WHERE i.id IN :ingredientIds GROUP BY m.id HAVING COUNT(i.id) = :count")
    List<Meal> findMealsByIngredients(@Param("ingredientIds") List<Long> ingredientIds, @Param("count") Long count);

}

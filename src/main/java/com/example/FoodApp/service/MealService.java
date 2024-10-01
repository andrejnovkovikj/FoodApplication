package com.example.FoodApp.service;

import com.example.FoodApp.model.Ingredient;
import com.example.FoodApp.model.Meal;
import com.example.FoodApp.repository.MealRepository;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;

import java.util.List;

public interface MealService {

    Meal findById(Long id);
    Meal findByName(String name);
    List<Meal> listAll();
    Meal create(String name,String mealImage_url,String recipe,String ingredients_recipe,int calories,List<Ingredient> ingredients);
    Meal update(Long id,String name,String mealImage_url,String recipe,String ingredients_recipe,int calories,List<Ingredient> ingredients);
    Meal delete(Long id);

}

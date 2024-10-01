package com.example.FoodApp.service;

import com.example.FoodApp.model.Ingredient;
import com.example.FoodApp.model.IngredientEnum;
import com.example.FoodApp.model.Meal;

import java.util.List;

public interface IngredientService {
    Ingredient findById(Long id);
    Ingredient findByName(String name);
    List<Ingredient> listAll();
    Ingredient create (String name, String image_url, IngredientEnum type);
    Ingredient update(Long id,String name, String image_url, IngredientEnum type);
    Ingredient delete(Long id);
}
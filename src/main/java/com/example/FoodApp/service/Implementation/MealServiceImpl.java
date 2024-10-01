package com.example.FoodApp.service.Implementation;

import com.example.FoodApp.model.Ingredient;
import com.example.FoodApp.model.Meal;
import com.example.FoodApp.model.exceptions.InvalidMealIdException;
import com.example.FoodApp.repository.MealRepository;
import com.example.FoodApp.service.MealService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class MealServiceImpl implements MealService {
    private final MealRepository mealRepository;

    public MealServiceImpl(MealRepository mealRepository) {
        this.mealRepository = mealRepository;
    }

    @Override
    public Meal findById(Long id) {
        return this.mealRepository.findById(id).orElseThrow(InvalidMealIdException::new);
    }

    @Override
    public Meal findByName(String name) {
        return this.mealRepository.findByName(name);
    }

    @Override
    public List<Meal> listAll() {
        return this.mealRepository.findAll();
    }

    @Override
    public Meal create(String name, String mealImage_url, String recipe,String ingredients_recipe, int calories, List<Ingredient> ingredients) {
        Meal meal = new Meal(name,mealImage_url,recipe,ingredients_recipe,calories,ingredients);
        return this.mealRepository.save(meal);
    }

    @Override
    public Meal update(Long id, String name, String mealImage_url, String recipe,String ingredients_recipe, int calories, List<Ingredient> ingredients) {
        Meal meal = findById(id);
        meal.setName(name);
        meal.setMealImage_url(mealImage_url);
        meal.setRecipe(recipe);
        meal.setIngredients_recipe(ingredients_recipe);
        meal.setCalories(calories);
        meal.setIngredients(ingredients);
        return this.mealRepository.save(meal);

    }

    @Override
    public Meal delete(Long id) {
        Meal meal = findById(id);
        this.mealRepository.delete(meal);
        return meal;
    }
}

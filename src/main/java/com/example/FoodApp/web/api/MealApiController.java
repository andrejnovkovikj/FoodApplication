package com.example.FoodApp.web.api;

import com.example.FoodApp.model.Meal;
import com.example.FoodApp.service.IngredientService;
import com.example.FoodApp.service.MealService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/meals")
@CrossOrigin(origins = {"http://localhost:3000","https://food-application-frontend.onrender.com"})
public class MealApiController {

    private final MealService mealService;
    private final IngredientService ingredientService;

    public MealApiController(MealService mealService, IngredientService ingredientService) {
        this.mealService = mealService;
        this.ingredientService = ingredientService;
    }

    @GetMapping
    public ResponseEntity<List<Meal>> showMeals() {
        List<Meal> meals = mealService.listAll();
        return ResponseEntity.ok(meals);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Meal> getMealById(@PathVariable Long id) {
        Meal meal = mealService.findById(id);
        return meal != null ? ResponseEntity.ok(meal) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Meal> addMeal(@RequestBody Meal meal) {
        Meal createdMeal = mealService.create(
                meal.getName(),
                meal.getMealImage_url(),
                meal.getRecipe(),
                meal.getIngredients_recipe(),
                meal.getCalories(),
                meal.getIngredients()
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(createdMeal);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Meal> editMeal(@PathVariable Long id, @RequestBody Meal meal) {
        Meal updatedMeal = mealService.update(
                id,
                meal.getName(),
                meal.getMealImage_url(),
                meal.getRecipe(),
                meal.getIngredients_recipe(),
                meal.getCalories(),
                meal.getIngredients()
        );
        return ResponseEntity.ok(updatedMeal);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMeal(@PathVariable Long id) {
        mealService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/filter")
    public ResponseEntity<List<Meal>> getMealsByIngredients(@RequestBody List<Long> ingredientIds) {
        List<Meal> meals = mealService.findMealsByIngredients(ingredientIds);
        return ResponseEntity.ok(meals);
    }

}
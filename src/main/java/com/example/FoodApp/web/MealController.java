package com.example.FoodApp.web;

import com.example.FoodApp.model.Ingredient;
import com.example.FoodApp.model.IngredientEnum;
import com.example.FoodApp.model.Meal;
import com.example.FoodApp.service.IngredientService;
import com.example.FoodApp.service.MealService;
import jakarta.persistence.*;
import org.apache.logging.log4j.util.PerformanceSensitive;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping
public class MealController {

    private final MealService mealService;
    private final IngredientService ingredientService;

    public MealController(MealService mealService, IngredientService ingredientService) {
        this.mealService = mealService;
        this.ingredientService = ingredientService;
    }

    @GetMapping(path = {"/meals"})
    public String showMeals(Model model){
        List<Meal> meals = mealService.listAll();
        model.addAttribute("meals", meals);
        return "meals.html";

    }
    @GetMapping(path = {"/add-meal-form"})
    public String addMealPage(Model model){
        List<Ingredient> ingredients = ingredientService.listAll();
        model.addAttribute("ingredients", ingredients);
        return "add-meal";
    }
    @PostMapping(path = {"/add-meal"})
    public String addMeal (@RequestParam String name,
                           @RequestParam String mealImage_url,
                           @RequestParam String recipe,
                           @RequestParam String ingredients_recipe,
                           @RequestParam int calories,
                           @RequestParam List<Ingredient> ingredients){
        mealService.create(name,mealImage_url,recipe,ingredients_recipe,calories,ingredients);
        return "redirect:/meals";
    }
    @PostMapping(path = {"/meals/delete/{id}"})
    public String deleteMeal(@PathVariable Long id){
        mealService.delete(id);
        return "redirect:/meals";
    }
    @GetMapping(path = {"/meals/edit/{id}"})
    public String showEditMealForm(@PathVariable Long id,Model model){
        Meal meal = mealService.findById(id);
        List<Ingredient> ingredients = ingredientService.listAll();
        model.addAttribute("meal",meal);
        model.addAttribute("ingredients",ingredients);
        return "edit-meal";
    }
    @PostMapping(path = {"/meals/edit/{id}"})
    public String editMeal(@PathVariable Long id,@RequestParam String name,
                           @RequestParam String mealImage_url,
                           @RequestParam String recipe,
                           @RequestParam String ingredients_recipe,
                           @RequestParam int calories,
                           @RequestParam List<Ingredient> ingredients){
        mealService.update(id, name, mealImage_url, recipe,ingredients_recipe, calories, ingredients);
        return "redirect:/meals";
    }



}

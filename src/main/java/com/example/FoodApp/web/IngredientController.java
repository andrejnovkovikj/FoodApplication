package com.example.FoodApp.web;

import com.example.FoodApp.model.Ingredient;
import com.example.FoodApp.model.IngredientEnum;
import com.example.FoodApp.model.Meal;
import com.example.FoodApp.service.IngredientService;
import com.example.FoodApp.service.MealService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping
public class IngredientController {

    private final MealService mealService;
    private final IngredientService ingredientService;

    public IngredientController(MealService mealService, IngredientService ingredientService) {
        this.mealService = mealService;
        this.ingredientService = ingredientService;
    }
    @GetMapping(path = {"/ingredients"})
    public String showIngredients(Model model){
        List <Ingredient> ingredients = ingredientService.listAll();
        model.addAttribute("ingredients",ingredients);
        return "ingredients";
    }
    @GetMapping(path = {"/add-ingredient-form"})
    public String addIngredientPage(Model model){
        model.addAttribute("bodyContent", "add-ingredient");
        return "add-ingredient";  // Return view name, without .html
    }

    @PostMapping(path = {"/add-ingredient"})
    public String addIngredient(@RequestParam String name,
                                @RequestParam String image_url,
                                @RequestParam IngredientEnum type){
        this.ingredientService.create(name, image_url, type);
        return "redirect:/home";
    }
    @PostMapping(path = {"ingredients/delete/{id}"})
    public String deleteIngredient(@PathVariable Long id){
        Ingredient ingredient = ingredientService.delete(id);
        return "redirect:/ingredients";
    }
    @GetMapping(path = {"/ingredients/edit/{id}"})
    public String showEditIngredientForm(@PathVariable Long id, Model model) {
        Ingredient ingredient = ingredientService.findById(id);
        model.addAttribute("ingredient", ingredient);
        return "edit-ingredient";
    }
    @PostMapping(path = {"/ingredients/edit/{id}"})
    public String editIngredient(@PathVariable Long id,
                                 @RequestParam String name,
                                 @RequestParam String image_url,
                                 @RequestParam("type") IngredientEnum type) {
        ingredientService.update(id, name, image_url, type);
        return "redirect:/ingredients";
    }
}

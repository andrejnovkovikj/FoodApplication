package com.example.FoodApp.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Meal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String mealImage_url;
    private String recipe;
    private int calories;
    @ManyToMany
    @JoinTable(
            name = "meal_ingredient",
            joinColumns = @JoinColumn(name = "meal_id"),
            inverseJoinColumns = @JoinColumn(name = "ingredient_id")
    )
    private List<Ingredient> ingredients;

    public Meal(){}

    public Meal(Long id, String name, String mealImage_url, String recipe, int calories, List<Ingredient> ingredients) {
        this.id = id;
        this.name = name;
        this.mealImage_url = mealImage_url;
        this.recipe = recipe;
        this.calories = calories;
        this.ingredients = ingredients;
    }

    public Meal(String name, String mealImage_url, String recipe, int calories, List<Ingredient> ingredients) {
        this.name = name;
        this.mealImage_url = mealImage_url;
        this.recipe = recipe;
        this.calories = calories;
        this.ingredients = ingredients;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMealImage_url() {
        return mealImage_url;
    }

    public void setMealImage_url(String mealImage_url) {
        this.mealImage_url = mealImage_url;
    }

    public String getRecipe() {
        return recipe;
    }

    public void setRecipe(String recipe) {
        this.recipe = recipe;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }
}

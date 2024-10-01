package com.example.FoodApp.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String image_url;
    @Enumerated(EnumType.STRING)
    private IngredientEnum type;


    public Ingredient(Long id, String name, String image_url, IngredientEnum type) {
        this.id = id;
        this.name = name;
        this.image_url = image_url;
        this.type = type;
    }

    public Ingredient(String name, String image_url, IngredientEnum type) {
        this.name = name;
        this.image_url = image_url;
        this.type = type;
    }

    public Ingredient(){}
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

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public IngredientEnum getType() {
        return type;
    }

    public void setType(IngredientEnum type) {
        this.type = type;
    }
}

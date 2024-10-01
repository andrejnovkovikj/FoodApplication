package com.example.FoodApp.service.Implementation;

import com.example.FoodApp.model.Ingredient;
import com.example.FoodApp.model.IngredientEnum;
import com.example.FoodApp.model.Meal;
import com.example.FoodApp.model.exceptions.InvalidIngredientIdException;
import com.example.FoodApp.repository.IngredientRepository;
import com.example.FoodApp.service.IngredientService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class IngredientServiceImpl implements IngredientService {
    private final IngredientRepository ingredientRepository;

    public IngredientServiceImpl(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }


    @Override
    public Ingredient findById(Long id) {
        return this.ingredientRepository.findById(id).orElseThrow(InvalidIngredientIdException::new);
    }

    @Override
    public Ingredient findByName(String name) {
        return this.ingredientRepository.findByName(name);
    }

    @Override
    public List<Ingredient> listAll() {
        return this.ingredientRepository.findAll();
    }

    @Override
    public Ingredient create(String name, String image_url, IngredientEnum type) {
        Ingredient ingredient = new Ingredient(name,image_url,type);
        return this.ingredientRepository.save(ingredient);
    }

    @Override
    public Ingredient update(Long id, String name, String image_url, IngredientEnum type) {
        Ingredient ingredient = findById(id);
        ingredient.setName(name);
        ingredient.setImage_url(image_url);
        ingredient.setType(type);
        return this.ingredientRepository.save(ingredient);
    }

    @Override
    public Ingredient delete(Long id) {
         Ingredient ingredient = findById(id);
         this.ingredientRepository.delete(ingredient);
         return ingredient;
    }
}

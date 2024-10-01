package com.example.FoodApp.web.api;

import com.example.FoodApp.model.Ingredient;
import com.example.FoodApp.model.IngredientEnum;
import com.example.FoodApp.service.IngredientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ingredients")
@CrossOrigin(origins = {"http://localhost:3000","https://food-application-frontend.onrender.com"})
public class IngredientApiController {
    private final IngredientService ingredientService;

    public IngredientApiController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @GetMapping
    public ResponseEntity<List<Ingredient>> showIngredients(){
        List<Ingredient> ingredients = ingredientService.listAll();
        return ResponseEntity.ok(ingredients);

    }
    @GetMapping("/{id}")
    public ResponseEntity<Ingredient> showIngredient(@PathVariable Long id){
        Ingredient ingredient = ingredientService.findById(id);
        return ingredient != null ? ResponseEntity.ok(ingredient) : ResponseEntity.notFound().build();
    }
    @PostMapping
    public ResponseEntity<Ingredient> addIngredient(@RequestBody Ingredient ingredient){
        Ingredient newIngredient = ingredientService.create(
                ingredient.getName(),
                ingredient.getImage_url(),
                ingredient.getType()
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(newIngredient);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Ingredient> editIngredient(@PathVariable Long id,@RequestBody Ingredient ingredient){
        Ingredient editedIngredient = ingredientService.update(
                id,
                ingredient.getName(),
                ingredient.getImage_url(),
                ingredient.getType()
        );
        return ResponseEntity.ok(editedIngredient);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Ingredient> deleteIngredient(@PathVariable Long id){
        ingredientService.delete(id);
        return ResponseEntity.noContent().build();
    }

}

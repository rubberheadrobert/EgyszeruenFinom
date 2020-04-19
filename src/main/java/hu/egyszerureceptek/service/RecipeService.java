package hu.egyszerureceptek.service;

import hu.egyszerureceptek.model.Recipe;
import hu.egyszerureceptek.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecipeService {
    @Autowired
    private RecipeRepository repo;

    public List<Recipe> listAll(){
        return repo.findAll();
    }

    public void save(Recipe recipe){
        repo.save(recipe);
    }

    public Recipe get(int id){
        return repo.findById(id).get();
    }

    public void delete(int id){
        repo.deleteById(id);
    }
}

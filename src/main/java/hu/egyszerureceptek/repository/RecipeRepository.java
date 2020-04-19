package hu.egyszerureceptek.repository;

import hu.egyszerureceptek.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipeRepository extends JpaRepository<Recipe,Integer> {
}

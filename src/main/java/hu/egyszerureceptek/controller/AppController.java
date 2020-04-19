package hu.egyszerureceptek.controller;

import hu.egyszerureceptek.model.Recipe;
import hu.egyszerureceptek.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

@Controller
public class AppController {

    public static String uploadDirectory = System.getProperty("user.dir") + "/src/main/resources/static/uploads";

    @Autowired
    private RecipeService service;

    @RequestMapping("/")
    public String viewHomePage(Model model){
        Recipe[] recipeList = new Recipe[3];
        for (int i = 1; i <= recipeList.length ; i++) {
            recipeList[i-1] = service.get(i);
        }
        model.addAttribute("recipeList",recipeList);
        return "index";
    }

    @RequestMapping("/recipes")
    public String viewRecipes(Model model){
        List<Recipe> recipeList = service.listAll();
        model.addAttribute("recipeList", recipeList);

        return "recipes";
    }

    @RequestMapping("/edit-recipes")
    public String editRecipes(Model model){
        List<Recipe> recipeList = service.listAll();
        model.addAttribute("recipeList", recipeList);
        return "edit-recipe-db";
    }

    @RequestMapping("/recipe/{id}")
    public String viewRecipe(@PathVariable(name = "id") int id, Model model){
        Recipe recipe= service.get(id);
        model.addAttribute("recipe", recipe);

        return "recipe";
    }

    @RequestMapping("/edit/{id}")
    public String editRecipe(@PathVariable(name = "id") int id, Model model){
        Recipe recipe= service.get(id);
        model.addAttribute("recipe", recipe);

        return "edit-recipe";
    }

    @RequestMapping("/login")
    public String loginPage(){
        return "login";
    }

    @RequestMapping("/logout-success")
    public String logout(){
        return "index";
    }

    @RequestMapping("/add-recipe")
    public String addRecipe(){
        return "add-recipe";
    }

    @RequestMapping(value = "/upload-edited", method=RequestMethod.POST)
    public String saveProduct(@ModelAttribute("recipe") Recipe recipe, Model model){

        service.save(recipe);

        model.addAttribute("msg", "Sikeresen megváltoztatta a fájlt: ");
        return "upload-status-view";
    }

    @RequestMapping("/upload")
    public String upload(@RequestParam String name, @RequestParam String description,
        @RequestParam MultipartFile image, @RequestParam String chefname, Model model){
        String fileName = image.getOriginalFilename();
        StringBuilder sb = new StringBuilder();
        sb.append(image.getOriginalFilename());
        sb.insert(0,chefname + "_");

        Path fileNameAndPath = Paths.get(uploadDirectory, sb.toString());

        try{
            Files.write(fileNameAndPath, image.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        String str = fileNameAndPath.toString();
        System.out.println("Before splitting:" + str);
        String[] strArray = str.split("\\\\");
        System.out.println("After splitting: " + Arrays.toString(strArray) );

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(strArray[strArray.length-2] + "\\" + strArray[strArray.length-1]);

        System.out.println(stringBuilder);

        Recipe recipe = new Recipe(name, description, stringBuilder.toString(), chefname);

        service.save(recipe);

        model.addAttribute("msg", "Sikeresen feltöltötte a következő fájlt: " + fileName.toString());
        return "upload-status-view";
    }
}

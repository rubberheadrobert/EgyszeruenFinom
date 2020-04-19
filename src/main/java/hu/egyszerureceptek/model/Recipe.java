package hu.egyszerureceptek.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
@Entity
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String description;
    private String image;
    private String chefname;

    protected Recipe(){
        super();
    }

    public Recipe(String name, String description, String image, String chefname) {
        this.name = name;
        this.description = description;
        this.image = image;
        this.chefname = chefname;
    }
}

package my.apps.web;

/**
 * Created by camelia on 14/02/2017.
 */
public class Recipes {

    private Long id;
    private String type;
    private String name;
    private String ingredients;
    private String instructions;
    private String duration;


    public Recipes(String type, String name, String ingredients, String instructions, String duration) {
        this.type = type;
        this.name = name;
        this.ingredients = ingredients;
        this.instructions = instructions;
        this.duration = duration;
    }


    public String getType() {

        return this.type;
    }

    public String getName() {

        return this.name;
    }

    public String getIngredients() {

        return this.ingredients;
    }

    public String getInstructions() {

        return this.instructions;
    }

    public String getDuration() {

        return this.duration;
    }


    public Long getId() {

        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    @Override
    public String toString() {
        return "Recipe{" +
                "Type= '" + type + '\'' +
                ", Name= '" + name + '\'' +
                ", Ingredients= '" + ingredients + '\'' +
                ", Instructions= '" + instructions + '\'' +
                ", Duration= '" + duration + '\'' +
                '}';
    }
}


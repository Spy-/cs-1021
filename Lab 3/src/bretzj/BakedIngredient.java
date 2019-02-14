/*
 * Course: CS1021
 * Winter 2018-2019
 * Lab 3
 * Name: John Bretz
 * Created: 6 Dec 2018
 */
package bretzj;

/**
 * Class for a baked ingredient
 */
public class BakedIngredient implements Ingredient {

    private Ingredient ingredient;
    private double expansionFactor;

    /**
     * Constructor for a baked ingredient
     * @param ingredient the ingredient required
     * @param expansionFactor the ingredients expansion factor
     */
    public BakedIngredient(Ingredient ingredient, double expansionFactor) {
        this.ingredient = ingredient;
        this.expansionFactor = expansionFactor;
    }

    /**
     * returns number of calories
     *
     * @return calories
     */
    public double getCalories() {
        return ingredient.getCalories();
    }

    /**
     * returns the number of cups
     *
     * @return cups
     */
    public double getCups() {
        return ingredient.getCups() * expansionFactor;
    }

    /**
     * returns the name of the ingredient
     *
     * @return name
     */
    public String getName() {
        return "Baked " + ingredient.getName();
    }

    /**
     * returns True if the ingredient is dry
     *
     * @return dry
     */
    public boolean isDry() {
        return true;
    }

    /**
     * prints the ingredients recipe
     */
    public void printRecipe() {
        System.out.println("====================================================");
        System.out.println(this.getName());
        System.out.println("====================================================");
        System.out.println("Ingredient to be baked: " + ingredient.getName());
        System.out.println("Cups: " + this.getCups() + " Cups");
        System.out.println("Energy: " + (int)this.getCalories() + " Calories\n");

        ingredient.printRecipe();
    }
}

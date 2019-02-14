/*
 * Course: CS1021
 * Winter 2018-2019
 * Lab 3
 * Name: John Bretz
 * Created: 6 Dec 2018
 */
package bretzj;

import java.util.ArrayList;

/**
 * Class for a mix
 */
public class Mix implements Ingredient {

    private ArrayList<Ingredient> ingredients = new ArrayList<>();
    private String name;

    /**
     * Constructor for a mix
     * @param name the mix's name
     */
    public Mix(String name) {
        this.name = name;
    }

    /**
     * Adds an igredient to the mix
     * @param ingredient the ingredient to add
     */
    public void addIngredient(Ingredient ingredient) {
        this.ingredients.add(ingredient);
    }

    /**
     * returns number of calories
     *
     * @return calories
     */
    public double getCalories() {
        double calories = 0;
        for (Ingredient i : ingredients) {
            calories += i.getCalories();
        }
        return calories;
    }

    /**
     * returns the number of cups
     *
     * @return cups
     */
    public double getCups() {
        double cups = 0;
        for (Ingredient i : ingredients) {
            cups += i.getCups();
        }
        return cups;
    }

    /**
     * returns the name of the ingredient
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * returns true if the mix has a dry ingredient
     * @return dry
     */
    public boolean hasDryIngredient() {
        for (Ingredient i : ingredients) {
            if (i.isDry()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns true if the mix has a wet ingredient
     * @return
     */
    public boolean hasWetIngredient() {
        for (Ingredient i : ingredients) {
            if (!i.isDry()) {
                return true;
            }
        }
        return false;
    }

    /**
     * returns True if the ingredient is dry
     *
     * @return dry
     */
    public boolean isDry() {
        return !this.hasWetIngredient();
    }

    /**
     * prints the ingredients recipe
     */
    public void printRecipe() {
        System.out.println("====================================================");
        System.out.println(this.getName());
        System.out.println("====================================================");

        if (this.hasDryIngredient()) {
            System.out.println("Dry Ingredients:");
            for (Ingredient i : ingredients) {
                if (i.isDry()) {
                    System.out.println("\t" + i.getName());
                }
            }
        }

        if (this.hasWetIngredient()) {
            System.out.println("\nWet Ingredients:");
            for (Ingredient i : ingredients) {
                if (!i.isDry()) {
                    System.out.println("\t" + i.getName());
                }
            }
        }

        System.out.println("\nCups: " + this.getCups() + " cups");
        System.out.println("Calories: " + (int)this.getCalories() + " calories\n");

        for (Ingredient i : ingredients) {
            i.printRecipe();
        }
    }
}

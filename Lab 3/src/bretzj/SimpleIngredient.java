/*
 * Course: CS1021
 * Winter 2018-2019
 * Lab 3
 * Name: John Bretz
 * Created: 6 Dec 2018
 */
package bretzj;

/**
 * Allows creation for a simple ingredient
 */
public class SimpleIngredient implements Ingredient {

    private double calories;
    private double cups;
    private boolean isDry;
    private String name;

    /**
     * Constructor for a simple ingredient
     * @param name name of the ingredient
     * @param calories calories ingredient has
     * @param cups volume of ingredient
     * @param isDry is the ingredient dry?
     */
    public SimpleIngredient(String name, double calories, double cups, boolean isDry) {
        this.calories = calories;
        this.cups = cups;
        this.isDry = isDry;
        this.name = name;
    }

    /**
     * returns number of calories
     *
     * @return calories
     */
    public double getCalories() {
        return calories;
    }

    /**
     * returns the number of cups
     *
     * @return cups
     */
    public double getCups() {
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
     * returns True if the ingredient is dry
     *
     * @return dry
     */
    public boolean isDry() {
        return isDry;
    }

    /**
     * prints the ingredients recipe
     */
    public void printRecipe() {
        System.out.println("====================================================");
        System.out.println(this.getName());
        System.out.println("====================================================");
        if (this.getCups() < 1) {
            System.out.println("Cups: " + this.getCups() + " Cups");
        } else {
            System.out.println("Cups: " + (int)this.getCups() + " Cups");
        }
        System.out.println("Energy: " + (int)this.getCalories() + " Calories\n");
    }
}

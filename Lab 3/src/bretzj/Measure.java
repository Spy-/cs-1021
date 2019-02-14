/*
 * Course: CS1021
 * Winter 2018-2019
 * Lab 3
 * Name: John Bretz
 * Created: 6 Dec 2018
 */
package bretzj;


/**
 * Class for a measure
 */
public class Measure implements Ingredient {

    private int numerator;
    private int denominator;
    private Ingredient measuredIngredient;

    /**
     * Constructor
     * @param cups the number of cups as an int
     * @param ingredient the ingredient object
     */
    public Measure(int cups, Ingredient ingredient) {
        this.numerator = cups;
        this.denominator = 1;
        this.measuredIngredient = ingredient;
    }

    /**
     * Constructor
     * @param numerator the numerator of a fraction
     * @param denominator the denominator of a fraction
     * @param ingredient the ingredient object
     */
    public Measure(int numerator, int denominator, Ingredient ingredient) {
        this.numerator = numerator;
        this.denominator = denominator;
        this.measuredIngredient = ingredient;
    }

    /**
     * returns number of calories
     *
     * @return calories
     */
    public double getCalories() {
        return numerator/(double)denominator * (measuredIngredient.getCalories()/
                measuredIngredient.getCups());
    }

    /**
     * returns the number of cups
     *
     * @return cups
     */
    public double getCups() {
        return numerator / (double)denominator;
    }

    /**
     * returns the name of the ingredient
     *
     * @return name
     */
    public String getName() {
        if (denominator != 1) {
            return this.numerator + "/" + this.denominator + " Cups " +
                    measuredIngredient.getName();
        } else {
            return this.numerator + " Cups " + measuredIngredient.getName();
        }
    }

    /**
     * returns True if the ingredient is dry
     *
     * @return dry
     */
    public boolean isDry() {
        return measuredIngredient.isDry();
    }

    /**
     * prints the ingredients recipe
     */
    public void printRecipe() {
        System.out.println("====================================================");
        System.out.println(this.getName());
        System.out.println("====================================================");

        System.out.println("Measured Ingredient: " + measuredIngredient.getName());
        if (denominator != 1) {
            System.out.println("Cups: " + this.numerator + "/" + this.denominator +
                    " Cups (" + this.getCups() + " cups)");
        } else {
            System.out.println("Cups: " + this.numerator + " cups");
        }
        System.out.println("Calories: " + (int)this.getCalories() + " calories\n");

        measuredIngredient.printRecipe();
    }
}
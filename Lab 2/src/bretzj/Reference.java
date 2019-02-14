/*
 * Course: CS1021
 * Winter 2018
 * Lab: Lab2
 * Name: John Bretz
 * Created: 12-11-18
 */
package bretzj;

import java.util.Scanner;

/**
 * Master class for all reference types
 */
public class Reference {
    private static int instanceCount = 0;
    private String author;
    private final String myUniqueID;
    private int publicYear;
    private String title;

    /**
     * Main Constructor for the reference type
     * @param author the author
     * @param title the title
     * @param publicationYear year the piece was published
     */
    public Reference(String author, String title, int publicationYear) {
        this.author = author;
        this.title = title;
        this.publicYear = publicationYear;
        this.myUniqueID = "REF" + instanceCount;
        this.instanceCount += 1;
    }

    /**
     * Default constructor for a reference object
     */
    public Reference() {
        this(null, null, -1);
    }

    /**
     * Will prompt the user on how to update this reference
     * @param in a scanner
     */
    public void promptForUpdate(Scanner in) {
        System.out.println("Override me!");
    }

    /**
     * Returns the name of the author
     * @return the author's name
     */
    public String getAuthor() {
        return this.author;
    }

    /**
     * Returns the uniqueID of the reference
     * @return the uniqueID
     */
    public String getMyUniqueID() {
        return this.myUniqueID;
    }

    /**
     * Returns the publication year of the reference
     * @return the publication year
     */
    public int getPublicationYear() {
        return this.publicYear;
    }

    /**
     * Returns the title of the reference
     * @return the name of the title
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * Allows the author to change
     * @param author the updated name of the author
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * Allows the publication year to change
     * @param year the year must be positive
     */
    public void setPublicationYear(int year) {
        if (year > 0) {
            this.publicYear = year;
        }
    }

    /**
     * Allows the title to change
     * @param title the title
     */
    public void setTitle(String title) {
        this.title = title;
    }
}

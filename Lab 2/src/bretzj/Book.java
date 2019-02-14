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
 * class for a Book reference object
 */
public class Book extends Reference {

    private String publisher;

    /**
     * Constructor for a book reference object
     * @param author the name of the author
     * @param title the title of the book
     * @param year the year that the book was published
     * @param publisher the name of the book's publisher
     */
    public Book(String author, String title, int year, String publisher) {
        super(author, title, year);
        this.publisher = publisher;
    }

    /**
     * Returns the name of the publisher
     * @return publisher
     */
    public String getPublisher() {
        return this.publisher;
    }

    /**
     * Allows the publisher name to be changed
     * @param publisher the publisher's name
     */
    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    /**
     * Prompts the user on how to update a book reference
     * @param in a scanner
     */
    @Override
    public void promptForUpdate(Scanner in) {
        // get new author
        System.out.println("Enter the updated author of the reference");
        this.setAuthor(in.nextLine());
        // get new title
        System.out.println("Enter the updated title of the reference");
        this.setTitle(in.nextLine());
        // get new copyright year
        System.out.println("Enter the updated copyright year for the reference.");
        while(!in.hasNextInt()) {
            in.next();
            System.out.println("Enter the updated copyright year for the reference");
        }
        this.setPublicationYear(in.nextInt());
        in.nextLine();
        // get new publisher
        System.out.println("Enter the updated publisher for the book");
        this.setPublisher(in.nextLine());
    }

    /**
     * Returns the Book's data as a String
     * @return string
     */
    @Override
    public String toString() {
        String text = "@BOOK { " + this.getMyUniqueID()      + ",\n";
        text += "author = \""    + this.getAuthor()          + "\",\n";
        text += "title = \""     + this.getTitle()           + "\",\n";
        text += "publisher = \"" + this.getPublisher()       + "\",\n";
        text += "year = \""      + this.getPublicationYear() + "\"\n}";
        return text;
    }
}

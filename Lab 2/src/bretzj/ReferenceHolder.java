/*
 * Course: CS1021
 * Winter 2018
 * Lab: Lab2
 * Name: John Bretz
 * Created: 12-11-18
 */
package bretzj;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class that holds all of the created references
 */
public class ReferenceHolder {

    private ArrayList<Reference> references = new ArrayList<>();

    /**
     * Default constructor for ReferenceHolder
     */
    public ReferenceHolder() { }

    /**
     * Allows the addition of a book reference to the references list
     * @param book a book object
     */
    public void addReference(Book book) {
        references.add(book);
    }

    /**
     * Allows the addition of a article reference to the references list
     * @param article a article object
     */
    public void addReference(Article article) {
        references.add(article);
    }

    /**
     * Allows the editing of a reference based on it's uniqueID
     * @param uniqueID the uniqueID of the reference
     * @param in a scanner
     */
    public void updateReference(String uniqueID, Scanner in) {
        for (Reference ref : references) {
            if (ref.getMyUniqueID().equals(uniqueID)) {
                ref.promptForUpdate(in);
            }
        }
    }

    /**
     * the to string method
     * @return the string output
     */
    @Override
    public String toString() {
        String output = "";
        for (Reference r : references) {
            output += r.toString() + "\n";
        }
        return output;
    }
}

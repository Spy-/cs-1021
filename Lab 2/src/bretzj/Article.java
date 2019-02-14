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
 * class for an Article reference object
 */
public class Article extends Reference {

    private int startingPage;
    private int endingPage;
    private String journal;

    /**
     * Constructor for an article object
     * @param author the authors name
     * @param title the title of the article
     * @param year the year it was published
     * @param journal the name of the journal it is from
     * @param start the starting page
     * @param end the ending page
     */
    public Article(String author, String title, int year, String journal, int start, int end) {
        super(author, title, year);
        this.startingPage = start;
        this.endingPage = end;
        this.journal = journal;
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
        // get new journal
        System.out.println("Enter the updated journal of the reference");
        this.setJournal(in.nextLine());
        // get new starting page
        System.out.println("Enter the updated starting page of the reference");
        while(!in.hasNextInt()) {
            in.next();
            System.out.println("Enter the updated starting page of the reference");
        }
        this.setStartingPage(in.nextInt());
        in.nextLine();
        // get new ending page
        System.out.println("Enter the updated ending page of the reference");
        while(!in.hasNextInt()) {
            in.next();
            System.out.println("Enter the updated ending page of the reference");
        }
        this.setEndingPage(in.nextInt());
        in.nextLine();
        // get new copyright year
        System.out.println("Enter the updated copyright year for the reference.");
        while(!in.hasNextInt()) {
            in.next();
            System.out.println("Enter the updated copyright year for the reference");
        }
        this.setPublicationYear(in.nextInt());
        in.nextLine();
    }

    /**
     * Returns the Articles's data as a String
     * @return string
     */
    @Override
    public String toString() {
        String text = "@ARTICLE { " + this.getMyUniqueID()      + ",\n";
        text += "author = \""       + this.getAuthor()          + "\",\n";
        text += "title = \""        + this.getTitle()           + "\",\n";
        text += "journal = \""      + this.getJournal()         + "\",\n";
        text += "pages = \""        + this.getStartingPage()    + "-" + this.getEndingPage() + "\",\n";
        text += "year = \""         + this.getPublicationYear() + "\"\n}";
        return text;
    }

    /**
     * Returns the starting page for the article reference
     * @return starting page
     */
    public int getStartingPage() {
        return this.startingPage;
    }

    /**
     * Returns the ending page for the article reference
     * @return ending page
     */
    public int getEndingPage() {
        return this.endingPage;
    }

    /**
     * Returns the name of the journal that the article was published in
     * @return journal
     */
    public String getJournal() {
        return this.journal;
    }

    /**
     * Allows the starting page to be changed for a reference
     * @param startingPage the new starting page
     */
    public void setStartingPage(int startingPage) {
        if (startingPage > 0 && startingPage <= endingPage) {
            this.startingPage = startingPage;
        }
    }

    /**
     * Allows the ending page to be changed for a reference
     * @param endingPage the new ending page
     */
    public void setEndingPage(int endingPage) {
        if (endingPage >= this.startingPage) {
            this.endingPage = endingPage;
        }
    }

    /**
     * Allows the name of the publishing journal to be changed for a reference
     * @param journal the new journal name
     */
    public void setJournal(String journal) {
        this.journal = journal;
    }
}

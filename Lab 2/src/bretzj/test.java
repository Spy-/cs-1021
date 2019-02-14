package bretzj;

import java.util.Scanner;

public class test {
    public static void main(String[] args) {
        ReferenceHolder references = new ReferenceHolder();
        Scanner in = new Scanner(System.in);

        Book book = new Book("John Williams", "Star Wars: The Musical Score", 1977, "20th Century Fox");
        Article article = new Article("Carl Sagan", "A Celebration of Isaac Asimov: A Man for the Universe", 1992, "The Skeptical Inquirer", 5, 7);

        references.addReference(book);
        references.addReference(article);

        System.out.println(references.toString());
    }
}

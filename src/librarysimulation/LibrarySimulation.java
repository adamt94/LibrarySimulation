/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package librarysimulation;

import java.util.Random;
import librarysimulation.LibraryBook.status;

/**
 *
 * @author ypf12pxu
 */
public class LibrarySimulation {

    public static void main(String[] args) {

        /*      LibraryBook book = new LibraryBook("Lewis and Loftus", "Java Software", 234);
         System.out.println(book.toString());*/
        LibrarySimulation.runSimulation(LibrarySimulation.generateBookStock(), 30);
    }

    public static LibraryBook[] generateBookStock() {
        String[] authorsList = {"Lewis and Loftus", "Mitrani", "Goodrich",
            "Lippman", "Gross", "Baase", "Maclane", "Dahlquist",
            "Stimson", "Knuth", "Hahn", "Cormen and Leiserson",
            "Menzes", "Garey and Johnson"};
        String[] titlesList = {"Java Software Solutions", "Simulation",
            "Data Structures", "C++ Primer", "Graph Theory",
            "Computer Algorithms", "Algebra", "Numerical Methods",
            "Cryptography", "Semi-Numerical Algorithms",
            "Essential MATLAB", "Introduction to Algorithms",
            "Handbook of Applied Cryptography",
            "Computers and Intractability"};
        int[] pagesList = {832, 185, 695, 614, 586, 685, 590, 573, 475,
            685, 301, 1175, 820, 338};

        int n = authorsList.length;
        LibraryBook[] bookStock = new LibraryBook[n];
        for (int i = 0; i < n; i++) {
            bookStock[i] = new LibraryBook(authorsList[i],
                    titlesList[i], pagesList[i]);
        }
// set library classification for half of the LIbraryBooks
        for (int i = 0; i < n; i = i + 2) {
            bookStock[i].setClassification("QA" + (99 - i));
        }
// set approx. two thirds of LIbraryBooks in test data as
// lending books
        for (int i = 0; i < 2 * n / 3; i++) {
            bookStock[i].setAsForLending();
        }
// set approx. one third of LibraryBooks in test data as
// reference-only
        for (int i = 2 * n / 3; i < n; i++) {
            bookStock[i].setAsReferenceOnly();
        }
        return bookStock;
    }

    public static String[] runSimulation(LibraryBook[] bookStock, int numberOFevents) {
        //gets the books length
        int n = bookStock.length;
        int rbook;
        Random random = new Random();
        //generates random number between bookstock length loops around 
        for (int i = 0; i <= numberOFevents; i++) {
            rbook = random.nextInt(n);
            //check if classification is null
            if (bookStock[rbook].getClassification() == null) {
                bookStock[rbook].setClassification("QA" + (99 - n));
                System.out.println(i + " " + bookStock[rbook].getClassification() + " " + bookStock[rbook].getloans() + " BOOK IS CLASSIFIED");
            //check if classification is not null and book is ref only
            } else if (bookStock[rbook].getClassification() != null && bookStock[rbook].getStatus() == status.REFERENCE_ONLY) {
                bookStock[rbook].setAsForLending();
                System.out.println(i + " " + bookStock[rbook].getClassification() + " " + bookStock[rbook].getloans() + " REFERENCE ONLY BOOK");
            } else if (bookStock[rbook].getClassification() != null && bookStock[rbook].isAvailable() == true) {
                bookStock[rbook].borrowBook();
                System.out.println(i + " " + bookStock[rbook].getClassification() + " " + bookStock[rbook].getloans() + " BOOK IS LOANED OUT");
            } else if (bookStock[rbook].getStatus() == status.ON_LOAN && bookStock[rbook].returnBook() == true) {
                bookStock[rbook].returnBook();
                System.out.println(i + " " + bookStock[rbook].getClassification() + " " + bookStock[rbook].getloans() + " BOOK IS RETURNED");
            } else if (bookStock[rbook].getStatus() == status.ON_LOAN && bookStock[rbook].resrveBook()==true) {
                
                System.out.println(i + " " + bookStock[rbook].getClassification() + " " + bookStock[rbook].getBorrowed() + " RESERVATION PLACED FOR ON LOAN BOOK");
            } else if (bookStock[rbook].getStatus() == status.ON_LOAN && bookStock[rbook].resrveBook() ==false) {
                bookStock[rbook].setAsForLending();
                System.out.println(i + " " + bookStock[rbook].getClassification() + " " + bookStock[rbook].getBorrowed() + " BOOK IS ON LOAN BUT CANNOT BE RESERVED");
            }

        }
        return null;
    }
}

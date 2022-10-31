/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.polsl.jktab.Views;

import java.util.Scanner;
import pl.polsl.jktab.Models.Listing;

/**
 *
 * @author JK
 * @version f1.0L
 */
public class ListingView {
    /**
     * Prints into console short listing info.
     * Used to print out listings in bulk as it only takes a single line, 
     * Static, so it can be used in TabController
     * @param title listing title
     * @param price listing price <strong>as string</strong>
     * @param negotiable if true will add aditional text after price
     */
    public static void printShort(String title, String price, boolean negotiable) {
        System.out.print(title);
        System.out.print(" | ");
        System.out.print(price);
        System.out.print("$ ");
        if(negotiable) {
            System.out.println(" Negotiable");
        }
    }
    
    /**
     * Prints into console detailed listing info
     * Takes up a lot of space prints everything,
     * Static, so it can be used in TabController
     * @param title listing title
     * @param details listing details
     * @param price listing price <strong>as string</strong>
     * @param negotiable if true will add aditional text after price
     * @param username listing author name
     * @param contact author contact info
     */    
    public static void printDetails(String title, String details, String price, boolean negotiable, String username, String contact) {
        System.out.println(title);
        System.out.print("\nDescryption: ");
        System.out.println(details);
        System.out.print("Listed for ");
        System.out.print(price);
        if(negotiable) {
            System.out.println(", Negotiable");
        }
        System.out.print("\nListed by ");
        System.out.print(username);
        System.out.print(" - ");
        System.out.println(contact);
    }
    
    /**
     * Creates new listing model object based on user console input
     * @param authorUname current user name, passed so it can be added to listing
     * @param authorContact current user contact info, passed so it can be added to listing
     * @return newly created Listing instance
     */
    public static Listing createListing(String authorUname, String authorContact) {
        System.out.println("Insert title");
        Scanner sc = new Scanner(System.in);
        String title = sc.nextLine();
        System.out.println("Insert price");
        float price = Float.parseFloat(sc.nextLine());
        System.out.println("Insert descryption");
        String details = sc.nextLine();
        System.out.println("Is the price negotiable?");
        boolean negotiable = sc.nextLine().equals("Y");
        return new Listing(title, price, details, negotiable, authorUname, authorContact);
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.polsl.jktab.Views;

import java.util.List;
import java.util.Scanner;
import pl.polsl.jktab.Models.Listing;

/**
 * Tab class view, used as a user interface via console
 * @author JK
 * @version f1.0
 */
public class TabView {
    /**
     * Console scanner as private field,
     * as it is used in different methods that utilize console readings
     */
    private final Scanner sc = new Scanner(System.in);
    
    /**
     * Prints out entire arrayList of lisitngs,
     * uses Listing view static method
     * @see pl.polsl.jktab.Controllers.TabController
     * @param listings arrayList of Listing instaces to be printed
     */
    public static void printListings(List<Listing> listings) {
        for(int i = 0; i < listings.size(); ++i) {
            System.out.print((i + 1) + ". ");
            String price = String.valueOf(listings.get(i).getPrice());
            ListingView.printShort(listings.get(i).getTitle(), price, listings.get(i).isNegotiable());
        }
    }
    
    /**
     * Asks user to input listing number, used to select which details to print out
     * @return user input <strong>as int</strong>, decremented as its used as list index
     */
    public int listingIndex() {
        System.out.println("\nTo get more listing details, insert listing number");
        try {
            int input = Integer.parseInt(this.sc.nextLine());
            return input - 1;
        } catch(NumberFormatException e) {
           return -1;
        }
    }
    
    /**
     * Asks user to input his username, used if the username wasnt provided correctly in args
     * @see pl.polsl.jktab.Controllers.TabController
     * @return user input string
     */
    public String requestUsername() {
        System.out.println("\nInvalid number of arguments, please specify your username");
        return this.sc.nextLine();
    }
    
    /**
     * Asks user to input his contact info, used if the contact wasnt provided correctly in args
     * @see pl.polsl.jktab.Controllers.TabController
     * @return user input string
     */
    public String requestContact() {
        System.out.println("\nInvalid number of arguments, please specify your contact method");
        return this.sc.nextLine();
    }
    
    /**
     * Asks user to input given string
     * @param strReq Expected string
     * @param msg Console message to be displaed eg. "Pls input A"
     * @return true if strReq was inputted, false if any other str recieved
     */
    public boolean requestStr(String strReq, String msg) {
        System.out.println(msg);
        String input = this.sc.nextLine();
        return strReq != null && input.equals(strReq);
    }
    
    /**
     * Prints out error message to console
     * @param msg 
     */
    public void handleErrMsg(String msg) {
        System.out.println(msg);
    }
    
    /**
     * Asks user for his address via console input
     * @return inputted address
     */
    public String requestAddress() {
        System.out.println("Please type in recepient address and press enter");
        return this.sc.nextLine();
    }
    
    /**
     * Prints out Paczkomaty code
     * @param code code to be printed
     */
    public void printCode(String code) {
        System.out.print("Your generated code is: ");
        System.out.println(code);
    }
}

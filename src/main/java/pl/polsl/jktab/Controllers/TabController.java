/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.polsl.jktab.Controllers;

import java.util.List;
import pl.polsl.jktab.Models.Listing;
import pl.polsl.jktab.Models.ListingAccessException;
import pl.polsl.jktab.Models.Tab;
import pl.polsl.jktab.Views.ListingView;
import pl.polsl.jktab.Views.TabView;

/**
 * Main app controller, suitable for console interface
 * @author JK
 * @version f1.0
 */
public class TabController {
    private final Tab model;
    private final TabView view;
    
    /**
     * 2 argument constructor, puts together MVC elements
     * @param _model Tab model
     * @param _view Tab view
     */
    public TabController(Tab _model, TabView _view) {
        this.model = _model;
        this.view = _view;
    }
    
    /**
     * Method used to start the Tab app.
     * It works in an loop and runs different methods based on user input recieved form view
     */
    public void startup() {
        List<Listing> listings = this.model.getListings();
        do {
            if(this.view.requestStr("I", "\nWelcome to TAB, Insert \"L\" to see avilable listings or \"I\" to insert your own")) {

                this.model.addListing(ListingView.createListing(this.model.getUsername(), this.model.getContact()), true);
          
            } else {
                this.view.printListings(this.model.getListings());
                int index = this.view.listingIndex();
                
                if(index < 0 || (index > listings.size() - 1)) {
                    this.view.handleErrMsg("Incorrect index!");
                } else {
                    String price = String.valueOf(listings.get(index).getPrice());
                    ListingView.printDetails(listings.get(index).getTitle(), listings.get(index).getDesc(),
                            price, listings.get(index).isNegotiable(), listings.get(index).getAuthorUname(), listings.get(index).getAuthorContact());

                    if(this.view.requestStr("D", "If you wish to delete this listing, insert \"D\"")) {
                        try {
                            this.model.removeListing(index, this.model.getUsername(), true);
                        } catch(ListingAccessException e) {
                            this.view.handleErrMsg(e.getMessage());
                        }
                    }
                    if(this.view.requestStr("P", "Listing sold and ready to ship? Insert \"P\" to gerate Paczkomaty code")) {
                        try {
                            String code = listings.get(index).generateCode(this.model.getUsername(), this.view.requestAddress());
                            this.view.printCode(code);
                        } catch(ListingAccessException e) {
                            this.view.handleErrMsg(e.getMessage());
                        }
                    }
                    if(this.view.requestStr("C", "Insert \"C\" to close all of your listings")) {
                        this.model.closeUserListings();
                    }
                }
            }
        } while(!this.view.requestStr("Q", "If you wish to quit the app, input \"Q\""));
    }
    
    /**
     * If incorrect (different than 2) number of args was passed, requests the arguments using view methods
     * Otherwise it loads them from array
     * @param args list of  arguments, passed from Main method
     * @see pl.polsl.jktab.Jktab
     */
    public void handleUserArgs(String[] args) {        
        if(args.length == 2) {
            this.model.setUsername(args[0]);
            this.model.setContact(args[1]);
        } else {
            this.model.setUsername(this.view.requestUsername());
            this.model.setContact(this.view.requestContact());
        }
    }
    
}

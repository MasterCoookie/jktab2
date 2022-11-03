/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.polsl.jktab.Models;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author JK
 * @version f2.0
 */
public class Tab {
    /**
     * ArrayList of all user-added listings
     * @see pl.polsl.jktab.Models.Listing
     */
    private List<Listing> listings = new ArrayList<Listing>();

    /**
     * Basic 0 argument constructor,
     * It loads listing from file
     */
    public Tab() {
        this.deserializeListings();
    }
    
    /**
     * 1 argument constructor, used for testing
     * @param listings listings array to init with
     */
    public Tab(ArrayList<Listing> listings) {
        this.deserializeListings();
    }
 
    /**
     * Username of current user
     */
    private String username;
    /**
     * Contact info, added to the end of a listing
     */
    private String contact;

    public void setUsername(String username) {
        this.username = username;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getUsername() {
        return username;
    }

    public String getContact() {
        return contact;
    }

    public List<Listing> getListings() {
        return listings;
    }

    public void setListings(List<Listing> listings) {
        this.listings = listings;
    }
    /**
     * adds listing to listings ArrayList
     * Rewrites the listings_list file to save added instance using 
     * serializeListings method
     * 
     * @param listing instance to be added to list
     * @param serialize should the class serialize its listings after addition?
     * @see pl.polsl.jktab.Models.Tab#serializeListings()
     */
    public void addListing(Listing listing, boolean serialize) {
        this.listings.add(listing);
        if(serialize) {
            this.serializeListings();
        }
    }
    
    /**
     * Removes listing from Listings arrayList.
     * @param index index at which the deletion will take place
     * @param username Current user name, used to validate access
     * @param serialize should the class serialize its listings after deletion?
     * @throws ListingAccessException thrown when user tries to delete someone elses listing
     */
    public void removeListing(int index, String username, boolean serialize)
    throws ListingAccessException {
        if(!this.listings.get(index).getAuthorUname().equals(username)) {
            throw new ListingAccessException("You cannot delete this listing!");
        } else {
            this.listings.remove(index);
            if(serialize) {
                this.serializeListings();
            }
        }
    }
    
    /**
     * serializes entire arrayList into file named listings_list
     */
    private void serializeListings() {
        try {
            FileOutputStream outputStream = new FileOutputStream("listings_list");
        
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);

            objectOutputStream.writeObject(this.listings);
            objectOutputStream.close();
            outputStream.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * reads (deserializes) listing_list file and puts its contents into
     * listings arrayList
     */
    private void deserializeListings(){
        try {
            FileInputStream inputStream = new FileInputStream("listings_list");
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);

            this.listings = (ArrayList) objectInputStream.readObject();

            objectInputStream.close();
            inputStream.close();
        } catch (IOException | ClassNotFoundException ioe) {
          ioe.printStackTrace();
          this.listings = new ArrayList<Listing>();
        }
    }
    
    /**
     * changes all current user lisings status to CLOSED, serializes data after
     */
    public void closeUserListings() {
        this.listings.forEach(listing -> {
            if(listing.getAuthorUname().equals(this.username)) {
                listing.closeListing();
            }
        });
        this.serializeListings();
    }
    
    /**
     * Gets all listings names posted by current user
     * @return list of users listings names
     */
    public List<String> getUserListingsNames() {
        List<String> names = this.listings.stream()
                .filter(listing -> listing.getAuthorUname().equals(this.username))
                .map(listing -> listing.getTitle())
                .collect(Collectors.toList());
        return names;
    }
}

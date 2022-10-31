/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */

import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import pl.polsl.jktab.Models.Listing;
import pl.polsl.jktab.Models.ListingAccessException;
import pl.polsl.jktab.Models.Tab;

/**
 * @version p2.0
 * @author SuperStudent
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TabTest {
    
    private Tab tab;
    private final String username = "JK";
    private final String username2 = "JK2";
    
    @BeforeAll
    public void setUpClass() {
        var testListing = new Listing("Title1", 1.11f, "deschere", false, username, "123456789");
        var testListing2 = new Listing("Title2", 2.22f, "deschere2", true, username2, "323456789");
        ArrayList<Listing> listings = new ArrayList<Listing>();
        listings.add(testListing);
        listings.add(testListing2);
        this.tab = new Tab(listings);
    }
    
    @Test
    public void serializationTests() {
        
    }
    
    @Test
    public void addListingTest() {
        this.tab.addListing(null);
        
        var testListing3 = new Listing("Title3", 3.33f, "deschere3", false, username, "423456789");
        this.tab.addListing(testListing3);
    }
    
    @ParameterizedTest
    @ValueSource(ints = {-1, 0, 99999})
    public void testRemoveListing(int index) {       
        try {
            this.tab.removeListing(index, username2);
        } catch(ListingAccessException e) {
            fail("ListingAccessException occurred");
        }
        
    }
    
    @Test
    public void testRemoveListingException() {
        try {
            this.tab.removeListing(0, username2);
        }catch(ListingAccessException e) {
            fail("ListingAccessException occurred");
        }
    }
}

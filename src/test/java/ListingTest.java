/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import pl.polsl.jktab.Models.Listing;
import pl.polsl.jktab.Models.ListingAccessException;

/**
 * @version p2.0
 * @author SuperStudent
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ListingTest {
    
    private Listing testListing;
    private final String username = "JK";
    private final String username2 = "JK2";
    private final String contact = "123456789";
    
    @BeforeAll
    public void setUpClass() {
        this.testListing = new Listing("Title1", 1.11f, "deschere", false, this.username, this.contact);
    }
    
    @Test
    public void testGenerateCodeException() {
        try {
            this.testListing.generateCode(this.username2, "address");
            fail("Should throw ListingAccessException");
        } catch(ListingAccessException e) {
        }
    }
    
    @ParameterizedTest
    @CsvSource({"address,TABTJ1.11-address", "address spaced,TABTJ1.11-address-spaced", " ,TABTJ1.11-"})
    public void testGenerateCode(String input, String expected) {
        try {
            String code = this.testListing.generateCode(this.username, input);
            assertEquals(expected, code, "Invalid code");
        } catch(ListingAccessException e) {
            fail("ListingAccessException occurred");
        } catch(NullPointerException np) {
            fail("NullPointerException occurred");
        }
    }
}

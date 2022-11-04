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
import org.junit.jupiter.params.provider.ValueSource;
import pl.polsl.jktab.Models.Listing;
import pl.polsl.jktab.Models.ListingAccessException;

/**
 * @version f2.0
 * @author JK
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ListingTest {
    
    private Listing testListing;
    private final String username = "JK";
    private final String username2 = "JK2";
    private final String contact = "123456789";
    
    /**
     * Prepares listing before each test
     */
    @BeforeAll
    public void setUpClass() {
        this.testListing = new Listing("Title1", 1.11f, "deschere", false, this.username, this.contact);
    }
    
    /**
     * intentionally generates ListingAccessException by attempting to generate code for another user
     * @see pl.polsl.jktab.Models.ListingAccessException
     */
    @Test
    public void testGenerateCodeException() {
        try {
            this.testListing.generateCode(this.username2, "address");
            fail("Should throw ListingAccessException");
        } catch(ListingAccessException e) {
        }
    }
    
    /**
     * attempts to generate multiple correct codes based on CSV source
     * @param input codes generation data
     * @param expected expected codes generated by tested method
     */
    @ParameterizedTest
    @CsvSource({
        "short,TABTJ1.11-short",
        "s h o r t,TABTJ1.11-s-h-o-r-t",
        "address,TABTJ1.11-address",
        "address spaced,TABTJ1.11-address-spaced",
        "address ending with space ,TABTJ1.11-address-ending-with-space",
        "address with  double space,TABTJ1.11-address-with--double-space",
        "address a little bit longer to check maximum siz,TABTJ1.11-address-a-little-bit-longer-to-check-maximum-siz"
    })
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
    
    /**
     * Tests generateCode Listing method ivalid inputs,
     * tests too short and too long inputs
     * @param inputStr list of input strings
     */
    @ParameterizedTest
    @ValueSource(strings = {
        "a",
        "aa",
        "t e s t",
        "address a little bit longer to check maximum size"  
    })
    public void testGenerateInvalidCode(String inputStr) {
        try {
            String code = this.testListing.generateCode(this.username, inputStr);
            assertEquals("INVALID", code, "Invalid code");
        } catch(ListingAccessException e) {
            fail("ListingAccessException occurred");
        } catch(NullPointerException np) {
            fail("NullPointerException occurred");
        }
    }
}

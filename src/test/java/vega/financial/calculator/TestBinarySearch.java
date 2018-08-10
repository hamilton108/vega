package vega.financial.calculator;

import oahu.financial.Derivative;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestBinarySearch {
    @Test
    void testFindBounds() {
        BinarySearch binarySearch = new BinarySearch();
        OptionPricing pricing = new DefaultOptionPricing(Derivative.OptionType.CALL, 100, 100, 0.5);
        BinarySearchBounds bounds = binarySearch.findBounds(pricing, 0.4, 12.0);
        assertEquals(0, bounds.getStart(), 0.1, "Bounds start value");
        assertEquals(0.4, bounds.getEnd(), 0.1, "Bounds end value");
        BinarySearchBounds bounds2 = binarySearch.findBounds(pricing, 0.1, 12.0);
        assertEquals(0.1, bounds2.getStart(), 0.1, "Bounds 2 start value");
        assertEquals(0.4, bounds2.getEnd(), 0.1, "Bounds 2 end value");
    }
    @Test
    void testBinarySearchCall() {
        BinarySearch binarySearch = new BinarySearch();
        OptionPricing pricing = new DefaultOptionPricing(Derivative.OptionType.CALL, 100, 100, 0.5);
        double result = binarySearch.find(pricing,0.4, 12,0.1);
        assertEquals(0.39, result, 0.1, "Binary search call implied volatility");
    }
    @Test
    void testBinarySearchPut() {
        BinarySearch binarySearch = new BinarySearch();
        OptionPricing pricing = new DefaultOptionPricing(Derivative.OptionType.PUT, 100, 100, 0.5);
        double result = binarySearch.find(pricing,0.4, 12,0.1);
        assertEquals(0.48, result, 0.1, "Binary search put implied volatility");
    }
}

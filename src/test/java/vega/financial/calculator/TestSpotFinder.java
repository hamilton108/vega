package vega.financial.calculator;

import oahu.financial.Derivative;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestSpotFinder {
    @Test
    public void testSpotForCallPrice() {
        testSpotFinder(Derivative.OptionType.CALL, 107.53, "SpotFinder Call price");
    }

    @Test
    public void testSpotForPutPrice() {
        testSpotFinder(Derivative.OptionType.PUT, 88, "SpotFinder Put price");
    }

    private void testSpotFinder(Derivative.OptionType ot, double v, String msg) {
        OptionPricing optionPricing = new DefaultOptionPricing(ot);
        SpotFinder spotFinder = new SpotFinder(optionPricing,100,0.5,0.2);
        BinarySearch binarySearch = new BinarySearch();
        double result = binarySearch.find(spotFinder, 100, 12, 0.05);
        assertEquals(v, result, 0.15, msg);
    }
}

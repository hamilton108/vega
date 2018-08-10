package vega.financial.calculator;

import oahu.financial.Derivative;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class TestOptionPricing {

    @Test
    void testCallPrice() {
        OptionPricing optionPricing = new DefaultOptionPricing(Derivative.OptionType.CALL);
        double call = optionPricing.apply(100,100,0.5,0.2);
        assertEquals(6.878, call, 0.1,"Call price");
    }

    @Test
    void testPutPrice() {
        OptionPricing optionPricing = new DefaultOptionPricing(Derivative.OptionType.PUT);
        double put = optionPricing.apply(100,100,0.5,0.2);
        assertEquals(4.415, put, 0.1,"Call price");
    }
}

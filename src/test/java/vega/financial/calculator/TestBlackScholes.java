package vega.financial.calculator;

import oahu.financial.Derivative;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class TestBlackScholes {

    @Test
    void testCallPrice() {
        BlackScholes blackScholes = new BlackScholes();
        double call = blackScholes.callPrice(100,100,0.5,0.2);
        assertEquals(6.79, call, 0.1, "Call Price");
    }
    @Test
    void testPutPrice() {
        BlackScholes blackScholes = new BlackScholes();
        double put = blackScholes.putPrice(100,100,0.5,0.2);
        assertEquals(4.38, put, 0.1, "Put Price");
    }

}

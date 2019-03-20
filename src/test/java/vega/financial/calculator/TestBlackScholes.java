package vega.financial.calculator;

import org.assertj.core.api.Assertions;
import org.assertj.core.data.Offset;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class TestBlackScholes {

    @Test
    public void testCallPrice() {
        BlackScholes blackScholes = new BlackScholes();
        double call = blackScholes.callPrice(100,100,0.5,0.2);
        Assertions.assertThat(call).isCloseTo(6.79, Offset.offset(0.1));
    }
    @Test
    public void testPutPrice() {
        BlackScholes blackScholes = new BlackScholes();
        double put = blackScholes.putPrice(100,100,0.5,0.2);
        Assertions.assertThat(put).isCloseTo(4.38, Offset.offset(0.1));
    }
}


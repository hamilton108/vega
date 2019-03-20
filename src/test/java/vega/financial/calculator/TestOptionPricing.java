package vega.financial.calculator;

import oahu.financial.Derivative;
import org.assertj.core.api.Assertions;
import org.assertj.core.data.Offset;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class TestOptionPricing {

    @Test
    public void testCallPrice() {
        OptionPricing optionPricing = new DefaultOptionPricing(Derivative.OptionType.CALL);
        double call = optionPricing.apply(100,100,0.5,0.2);
        Assertions.assertThat(call).isCloseTo(6.878, Offset.offset(0.1));
    }

    @Test
    public void testPutPrice() {
        OptionPricing optionPricing = new DefaultOptionPricing(Derivative.OptionType.PUT);
        double put = optionPricing.apply(100,100,0.5,0.2);
        Assertions.assertThat(put).isCloseTo(4.415, Offset.offset(0.1));
    }
}


package vega.financial.calculator;

import org.assertj.core.api.Assertions;
import org.assertj.core.data.Offset;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import vega.financial.StockOptionType;

@SpringBootTest(classes = {vega.financial.calculator.DefaultOptionPricing.class})
public class TestOptionPricing {

    @Test
    public void testCallPrice() {
        OptionPricing optionPricing = new DefaultOptionPricing(StockOptionType.CALL);
        double call = optionPricing.apply(100,100,0.5,0.2);
        Assertions.assertThat(call).isCloseTo(6.878, Offset.offset(0.1));
    }

    @Test
    public void testPutPrice() {
        OptionPricing optionPricing = new DefaultOptionPricing(StockOptionType.PUT);
        double put = optionPricing.apply(100,100,0.5,0.2);
        Assertions.assertThat(put).isCloseTo(4.415, Offset.offset(0.1));
    }
}


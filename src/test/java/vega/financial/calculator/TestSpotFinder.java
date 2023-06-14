package vega.financial.calculator;

import org.assertj.core.data.Offset;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import org.assertj.core.api.Assertions;
import vega.financial.StockOptionType;

@SpringBootTest(classes = {vega.financial.calculator.DefaultOptionPricing.class})
public class TestSpotFinder {

    @Test
    public void testSpotForCallPrice() {
        testSpotFinder(StockOptionType.CALL, 107.53, "SpotFinder Call price");
    }

    @Test
    public void testSpotForPutPrice() {
        testSpotFinder(StockOptionType.PUT, 88, "SpotFinder Put price");
    }

    private void testSpotFinder(StockOptionType ot, double v, String msg) {
        OptionPricing optionPricing = new DefaultOptionPricing(ot);
        SpotFinder spotFinder = new SpotFinder(optionPricing,100,0.5,0.2);
        BinarySearch binarySearch = new BinarySearch();
        double result = binarySearch.find(spotFinder, 100, 12, 0.05);
        Assertions.assertThat(result).describedAs(msg).isCloseTo(v, Offset.offset(0.15));
    }
}

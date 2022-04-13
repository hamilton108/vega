package vega.financial.calculator;

import org.assertj.core.data.Offset;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import org.assertj.core.api.Assertions;
import vega.financial.StockOption.OptionType;

@RunWith(SpringRunner.class)
public class TestSpotFinder {

    @Test
    public void testSpotForCallPrice() {
        testSpotFinder(OptionType.CALL, 107.53, "SpotFinder Call price");
    }

    @Test
    public void testSpotForPutPrice() {
        testSpotFinder(OptionType.PUT, 88, "SpotFinder Put price");
    }

    private void testSpotFinder(OptionType ot, double v, String msg) {
        OptionPricing optionPricing = new DefaultOptionPricing(ot);
        SpotFinder spotFinder = new SpotFinder(optionPricing,100,0.5,0.2);
        BinarySearch binarySearch = new BinarySearch();
        double result = binarySearch.find(spotFinder, 100, 12, 0.05);
        Assertions.assertThat(result).describedAs(msg).isCloseTo(v, Offset.offset(0.15));
    }
}

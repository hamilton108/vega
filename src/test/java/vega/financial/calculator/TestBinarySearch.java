package vega.financial.calculator;

import org.assertj.core.api.Assertions;
import org.assertj.core.data.Offset;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import vega.financial.StockOptionType;

@SpringBootTest(classes =
        {
                vega.financial.calculator.BinarySearch.class,
                vega.financial.calculator.DefaultOptionPricing.class,
        })
public class TestBinarySearch {

    @Test
    public void testFindBounds() {
        BinarySearch binarySearch = new BinarySearch();
        OptionPricing pricing = new DefaultOptionPricing(StockOptionType.CALL, 100, 100, 0.5);
        BinarySearchBounds bounds = binarySearch.findBounds(pricing, 0.4, 12.0);
        Assertions.assertThat(bounds.getStart()).isCloseTo(0, Offset.offset(0.1));
        Assertions.assertThat(bounds.getEnd()).isCloseTo(0.4, Offset.offset(0.1));
        BinarySearchBounds bounds2 = binarySearch.findBounds(pricing, 0.1, 12.0);
    }

    @Test
    public void testBinarySearchPut() {
        BinarySearch binarySearch = new BinarySearch();
        OptionPricing pricing = new DefaultOptionPricing(StockOptionType.PUT, 100, 100, 0.5);
        double result = binarySearch.find(pricing,0.4, 12,0.1);
        Assertions.assertThat(result).isCloseTo(0.48, Offset.offset(0.1));
    }
}

/*
public class TestBinarySearch {

    @Test
    void testBinarySearchCall() {
        BinarySearch binarySearch = new BinarySearch();
        OptionPricing pricing = new DefaultOptionPricing(Derivative.OptionType.CALL, 100, 100, 0.5);
        double result = binarySearch.find(pricing,0.4, 12,0.1);
        assertEquals(0.39, result, 0.1, "Binary search call implied volatility");
    }
}
*/

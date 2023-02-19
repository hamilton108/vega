package vega.financial.calculator;

import org.assertj.core.api.Assertions;
import org.assertj.core.data.Offset;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;
import static vega.financial.StockOption.OptionType.CALL;
import static vega.financial.StockOption.OptionType.PUT;

@RunWith(SpringRunner.class)
public class TestBlackScholes {

    private BlackScholes blackScholes;
    @Before
    public void init() {
        blackScholes = new BlackScholes();
    }
    @Test
    public void testCallPrice() {
        double call = blackScholes.callPrice(100,100,0.5,0.2);
        Assertions.assertThat(call).isCloseTo(6.79, Offset.offset(0.1));
    }
    @Test
    public void testPutPrice() {
        double put = blackScholes.putPrice(100,100,0.5,0.2);
        Assertions.assertThat(put).isCloseTo(4.38, Offset.offset(0.1));
    }
    @Test
    public void testStockPriceFor() {
        double stockPrice = blackScholes.stockPriceFor2(CALL, 12.0, 100.0, 200, 0.2, 110);
        Assertions.assertThat(stockPrice).isCloseTo(107.0, Offset.offset(0.5));
        stockPrice = blackScholes.stockPriceFor2(PUT, 12.0, 100.0, 200, 0.2, 110);
        Assertions.assertThat(stockPrice).isCloseTo(88.0, Offset.offset(0.5));

    }
}


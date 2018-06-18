package vega.financial.calculator;

import oahu.financial.DerivativePrice;
import oahu.financial.OptionCalculator;

public class BlackScholes implements OptionCalculator {
    @Override
    public double delta(DerivativePrice d) {
        return 0;
    }

    @Override
    public double spread(DerivativePrice d) {
        return 0;
    }

    @Override
    public double breakEven(DerivativePrice d) {
        return 0;
    }

    @Override
    public double stockPriceFor(double optionPrice, DerivativePrice o) {
        return 0;
    }

    @Override
    public double iv(DerivativePrice d, int priceType) {
        return 0;
    }

    @Override
    public double ivCall(double spot, double strike, double yearsExpiry, double optionPrice) {
        return 0;
    }

    @Override
    public double ivPut(double spot, double strike, double yearsExpiry, double optionPrice) {
        return 0;
    }

    @Override
    public double callPrice(double spot, double strike, double yearsExpiry, double sigma) {
        return 0;
    }

    @Override
    public double putPrice(double spot, double strike, double yearsExpiry, double sigma) {
        return 0;
    }
}

package vega.financial.calculator;

import static oahu.financial.Derivative.OptionType;

import oahu.exceptions.BinarySearchException;
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
        BinarySearch binarySearch = new BinarySearch();
        OptionPricing fn = new DefaultOptionPricing(OptionType.CALL, spot, strike, yearsExpiry);
        return binarySearch.find(fn, 0.4, optionPrice, 0.05);
    }

    @Override
    public double ivPut(double spot, double strike, double yearsExpiry, double optionPrice) {
        BinarySearch binarySearch = new BinarySearch();
        OptionPricing fn = new DefaultOptionPricing(OptionType.PUT, spot, strike, yearsExpiry);
        return binarySearch.find(fn, 0.4, optionPrice, 0.05);
    }

    @Override
    public double callPrice(double spot, double strike, double yearsExpiry, double sigma) {
        OptionPricing optionPrice = new DefaultOptionPricing(OptionType.CALL);
        return optionPrice.apply(spot, strike, yearsExpiry, sigma);
    }

    @Override
    public double putPrice(double spot, double strike, double yearsExpiry, double sigma) {
        OptionPricing optionPrice = new DefaultOptionPricing(OptionType.PUT);
        return optionPrice.apply(spot, strike, yearsExpiry, sigma);
    }

}

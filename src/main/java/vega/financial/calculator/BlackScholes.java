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
        OptionPricing optionPriceFn = new DefaultOptionPricing(OptionType.CALL, spot, strike, yearsExpiry);
        return 0;
    }

    @Override
    public double ivPut(double spot, double strike, double yearsExpiry, double optionPrice) {
        return 0;
    }

    @Override
    public double callPrice(double spot, double strike, double yearsExpiry, double sigma) {
        OptionPricing optionPrice = new DefaultOptionPricing(OptionType.CALL);
        return optionPrice.apply(spot,strike,yearsExpiry,sigma);
    }

    @Override
    public double putPrice(double spot, double strike, double yearsExpiry, double sigma) {
        OptionPricing optionPrice = new DefaultOptionPricing(OptionType.PUT);
        return optionPrice.apply(spot,strike,yearsExpiry,sigma);
    }

    private boolean isWithinTolerance(double value, double target, double tolerance) {
        return (Math.abs(value-target)) < tolerance;
    }
    private BinarySearchBounds findBounds(OptionPricing fn, double startValue, double target) {
        double r = fn.apply(startValue);
        double r1 = fn.apply(startValue+1);
        boolean isIncr = r1 > r;
        return isIncr ? findBoundsIncr(fn,startValue,target, r) :
                findBoundsDec(fn,startValue,target, r);
    }
    private BinarySearchBounds findBoundsDec(OptionPricing fn, double startValue, double target, double startResult) {
        if (startResult >= target) {
            return new BinarySearchBounds(0.0, startValue, startResult);
        }
        double s = startValue * 2.0;
        while (true) {
            if (s == Double.POSITIVE_INFINITY) {
                throw new BinarySearchException("Infinity");
            }
            double r2 = fn.apply(s);
            if (r2 >= target) {
                return new BinarySearchBounds(startValue, s, r2);
            }
            s = s*2.0;
        }
    }
    private BinarySearchBounds findBoundsIncr(OptionPricing fn, double startValue, double target, double startResult) {
        return null;
    }
}

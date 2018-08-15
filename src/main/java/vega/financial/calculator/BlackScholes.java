package vega.financial.calculator;

import static oahu.financial.Derivative.OptionType;

import oahu.exceptions.BinarySearchException;
import oahu.financial.Derivative;
import oahu.financial.DerivativePrice;
import oahu.financial.OptionCalculator;

public class BlackScholes implements OptionCalculator {
    double daysInAYear = 365.0;

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
        double result = 0;
        if (o.getIvBuy().isPresent()) {
            double iv = o.getIvBuy().get();
            double x = o.getDerivative().getX();
            double t = o.getDays() / daysInAYear;
            OptionPricing pricing = new DefaultOptionPricing(o.getDerivative().getOpType());
            SpotFinder spotFinder = new SpotFinder(pricing,x,t,iv);
            double startValue = o.getStockPrice().getCls();
            BinarySearch binarySearch = new BinarySearch();
            result = binarySearch.find(spotFinder, startValue, optionPrice, 0.05);
        }
        return result;
    }

    @Override
    public double iv(DerivativePrice d, int priceType) {
        double price = priceType == Derivative.BUY ? d.getBuy() : d.getSell();
        double tm = d.getDerivative().getDays() / daysInAYear;
        OptionPricing fn = new DefaultOptionPricing(
                d.getDerivative().getOpType(),
                d.getStockPrice().getCls(),
                d.getDerivative().getX(),
                tm);

        BinarySearch binarySearch = new BinarySearch();
        return binarySearch.find(fn, 0.4, price, 0.05);
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

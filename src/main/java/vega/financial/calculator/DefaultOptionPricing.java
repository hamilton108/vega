package vega.financial.calculator;

import cern.jet.random.Normal;
import cern.jet.random.engine.MersenneTwister;
import vega.financial.StockOption.OptionType;

public class DefaultOptionPricing implements OptionPricing {

    private final OptionType optionType;
    private final Normal normal = new Normal(0.0, 1.0, new MersenneTwister());
    private double interestRate = 0.05;
    private double _spot = 0.0;
    private double _x = 0.0;
    private double _t = 0.0;

    public DefaultOptionPricing(OptionType ot) {
        this.optionType = ot;
    }
    public DefaultOptionPricing(OptionType ot, double spot, double x, double t) {
        this.optionType = ot;
        _spot = spot;
        _x = x;
        _t = t;
    }

    private double calcD1(double spot, double x, double t, double sigma) {
        double a = Math.log(spot / x);
        double b = t * ((sigma * (sigma / 2.0)) + interestRate);
        double c = sigma * (Math.sqrt(t));
        return (a+b) / c;
    }
    private double calcD2(double d1, double t, double sigma) {
        return d1 - (sigma * (Math.sqrt(t)));
    }

    @Override
    public double apply(double spot, double x, double t, double sigma) {
        double d1x = calcD1(spot,x,t,sigma);
        double d2x = calcD2(d1x,t,sigma);
        double d1 = optionType == OptionType.CALL ? d1x : -d1x;
        double d2 = optionType == OptionType.CALL ? d2x : -d2x;
        double cdf1 = normal.cdf(d1);
        double cdf2 = normal.cdf(d2);
        double xp = Math.exp(t * (-interestRate));
        double a = spot * cdf1;
        double b =  x * xp * cdf2;
        return optionType == OptionType.CALL ? a - b : Math.max(x - spot, b - a);
    }

    @Override
    public double apply(double value) {
        return apply(_spot,_x,_t, value);
    }
}

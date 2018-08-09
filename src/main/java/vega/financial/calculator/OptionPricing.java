package vega.financial.calculator;

public interface OptionPricing {
    double apply(double spot, double x, double t, double sigma);
    double apply(double sigma);
}

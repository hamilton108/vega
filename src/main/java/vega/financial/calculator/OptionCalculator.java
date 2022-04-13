/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vega.financial.calculator;

import vega.financial.StockOptionPrice;

public interface OptionCalculator {

    double delta(StockOptionPrice d);
    double spread(StockOptionPrice d);
    double breakEven(StockOptionPrice d);
    double stockPriceFor(double optionPrice, StockOptionPrice o);
    double iv(StockOptionPrice d, int priceType);

    double ivCall(double spot, double strike, double yearsExpiry, double optionPrice);
    double ivPut(double spot, double strike, double yearsExpiry, double optionPrice);
    double callPrice(double spot, double strike, double yearsExpiry, double sigma);
    double putPrice(double spot, double strike, double yearsExpiry, double sigma);
}

package vega.filters.ehlers;

import vega.filters.Common;
import vega.filters.Filter;
import vega.functional.Func5;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by rcs on 03.06.15.
 *
 */
public class CyberCycle implements Filter {

    private double alpha;

    public CyberCycle(long days) {
        alpha = Common.calcAlpha(days);
    }

    @Override
    public List<Double> calculate(List<Double> data) {

        //List<Double> datax = (List<Double>)data;
        //List<Double> smooth = Common.calcSmooth(datax);
        List<Double> result = new ArrayList<>();

        double curHead = 0.0;

        result.add(curHead);

        double f1 = 1.0 - (alpha * 0.5);
        double f12 = f1 * f1;
        double f2 = 1.0 - alpha;
        double f22 = f2 * f2;

        for (int i = 1; i < 20; ++i) {
            double resultValue = ave(data.get(i-1),
                                     data.get(i),
                                     curHead,
                                     f1,
                                     f2);
            curHead = resultValue;
            result.add(resultValue);
        }

        double a = result.get(result.size() - 2);
        double b = result.get(result.size() - 1);

        Func5<Double,Double,Double,Double,Double,Double> ccFn = createCalcCycleFn(f12,f2,f22);

        for (int i = 20; i < data.size(); ++i) {
            double c = data.get(i-2);
            double d = data.get(i-1);
            double e = data.get(i);
            double cycleVal = ccFn.apply(a,b,c,d,e);
            result.add(cycleVal);
            a = b;
            b = cycleVal;
        }
        
        return result;
    }

    private double ave(double a,
                       double b,
                       double ccValue,
                       double f1,
                       double f2) {
        double c = f2 * ccValue;
        double d = f1 * (b - a);
        return c + d;
    }
    private Func5<Double,Double,Double,Double,Double,Double>
    createCalcCycleFn(double f12,
                      double f2,
                      double f22) {
        return (a,b,c,d,e) -> {
            double dataVal = f12 * (e + c - (2.0 * d));
            double cycleVal = (2.0 * f2 * b) - (f22 * a);
            return dataVal + cycleVal;
        };
    }
}

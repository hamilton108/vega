package vega.financial;

import java.time.LocalDate;

public interface StockOption {
    public static int BUY = 1;
    public static int SELL = 2;

    /*
    public static int CALL = 1;
    public static int PUT = 2;
    public static int OPTYPE_UNDEF = 3;
    //*/

    public static enum OptionType { CALL, PUT, UNDEF };

    public static enum LifeCycle { FROM_HTML, SAVED_TO_DATABASE, FROM_DATABASE };

    OptionType getOpType();
    double getX();
    long getDays();

    /*
    Stock getStock();
    OptionType getOpType();
    LifeCycle getLifeCycle();
    String getOpTypeStr();
    double getX();
    String getTicker();
    int getOid();
    void setOid(int oid);
    LocalDate getExpiry();
    String getSeries();
    long getDays();
     */
}

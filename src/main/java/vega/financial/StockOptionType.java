package vega.financial;

public enum StockOptionType {
    CALL("c"),
    PUT("p"),
    UNDEF("undef");

    private final String value;

    StockOptionType(String stockOptionType) {
        this.value = stockOptionType;
    }

    public String getValue() {
        return value;
    }
}

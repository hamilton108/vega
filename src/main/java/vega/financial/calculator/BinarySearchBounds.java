package vega.financial.calculator;

public class BinarySearchBounds {
    private final double start;
    private final double end;
    private final double endResult;
    public BinarySearchBounds(double start, double end, double endResult) {
        this.start = start;
        this.end = end;
        this.endResult = endResult;
    }

    public double getStart() {
        return start;
    }

    public double getEnd() {
        return end;
    }

    public double getEndResult() {
        return endResult;
    }
}

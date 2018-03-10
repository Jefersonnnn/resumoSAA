package model;

import java.util.List;

public class DrawPrintResult {
    String type;
    List<PrintResult> printResults;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<PrintResult> getPrintResults() {
        return printResults;
    }

    public void setPrintResults(List<PrintResult> printResults) {
        this.printResults = printResults;
    }
}

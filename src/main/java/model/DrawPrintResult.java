package model;

import java.util.List;

public class DrawPrintResult {
    String equipment;
    String type;
    List<PrintResult> printResults;

    public void setEquipment(String equipment){
        this.equipment = equipment;
    }

    public String getEquipment(){
        return this.equipment;
    }

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

package model;

import java.time.LocalDateTime;

public class PrintResult {
    LocalDateTime data;
    Double medida;

    public PrintResult(LocalDateTime data, double medida) {
        this.data = data;
        this.medida = medida;
    }

    public PrintResult() {
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public Double getMedida() {
        return medida;
    }

    public void setMedida(Double medida) {
        this.medida = medida;
    }
}

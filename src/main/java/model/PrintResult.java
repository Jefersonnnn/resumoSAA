package model;

import java.time.LocalDateTime;

public class PrintResult {
    LocalDateTime data;
    String medida;

    public PrintResult(LocalDateTime data, String medida) {
        this.data = data;
        this.medida = medida;
    }

    public PrintResult(String medida) {
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

    public String getMedida() {
        return medida;
    }

    public void setMedida(String medida) {
        this.medida = medida;
    }
}

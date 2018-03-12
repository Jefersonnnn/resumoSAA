package model;

import java.time.LocalDateTime;
import java.util.List;

public class Equipment {

    private String fileName;
    private String       instalacao;
    private String       dipositivo;
    private String       periodo;
    private String       ponto;
    private List<Double> medida;
    private List<LocalDateTime>   data;

    public Equipment() {}

    public Equipment(String fileName, String instalacao, String dipositivo, String periodo, String ponto, List<Double> medida, List<LocalDateTime> data) {
        this.fileName = fileName;
        this.instalacao = instalacao;
        this.dipositivo = dipositivo;
        this.periodo = periodo;
        this.ponto = ponto;
        this.medida = medida;
        this.data = data;
    }

    public String getPonto() {
        return ponto;
    }

    public void setPonto(String ponto) {
        this.ponto = ponto;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getInstalacao() {
        return instalacao;
    }

    public void setInstalacao(String instalacao) {
        this.instalacao = instalacao;
    }

    public String getDipositivo() {
        return dipositivo;
    }

    public void setDipositivo(String dipositivo) {
        this.dipositivo = dipositivo;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public List<Double> getMedida() {
        return medida;
    }

    public void setMedida(List<Double> medida) {
        this.medida = medida;
    }

    public List<LocalDateTime> getData() {
        return data;
    }

    public void setData(List<LocalDateTime> data) {
        this.data = data;
    }
}

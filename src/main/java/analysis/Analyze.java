package analysis;

import model.DrawPrintResult;
import model.Equipment;
import model.PrintResult;
import utils.DrawTable;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


/**
 * Sem valores negativos
 * Sem valores muito acima (analisar %)
 */
public class Analyze {

    private DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("hh:mm");
    private DecimalFormat df = new DecimalFormat("0.###");
    private Equipment equipment;
    private List<DrawPrintResult> drawPrintResults;

    public Analyze(Equipment equipment, List<String> optionsAnalyze) {
        this.equipment = equipment;
        this.drawPrintResults = new ArrayList<>();

        for (int i = 1; i < optionsAnalyze.size(); i++) {
            switch (optionsAnalyze.get(i).toUpperCase()) {
                case "FP":
                    searchFactor();
                    break;
                case "MN":
                    minimumNight();
                    break;
                case "MIND":
                    minimumDaily();
                    break;
                case "MAXD":
                    maximumDaily();
                    break;
                case "MED":
                    average();
                    break;
            }
        }

        if (drawPrintResults.size() > 0 && drawPrintResults != null)
            if (optionsAnalyze.get(0).toUpperCase().equals("H"))
                DrawTable.drawTableH(drawPrintResults, equipment);
            else
                DrawTable.drawTableV(drawPrintResults, equipment);

    }

    /**
     * Fator de pesquisa (FP) é calculado com a minima norturna dividido pela média diaria.
     * O valor deverá ser algo em torno de 0 e 1 (acima de 0,3 provavel vazamento na região)
     * Excluindo valor abaixo de 0.
     */
    private void searchFactor() {
        LocalDateTime start = equipment.getData().get(0);
        LocalDateTime finish = equipment.getData().get(equipment.getData().size() - 1);
        LocalDateTime dataMedida;
        double sum = 0, minimaN = 9999, media, fp;
        int qtdMedidasValidas = 0;
        List<PrintResult> results = new ArrayList<>();

        for (LocalDateTime dia = start; !dia.isAfter(finish); dia = dia.plusDays(1)) {
            dataMedida = dia;
            for (int i = 0; i < equipment.getMedida().size(); i++) {
                if (dia.format(dateFormatter).equals(equipment.getData().get(i).format(dateFormatter))) {
                    //Minima noturna fica entre a 00:00h e 05:00h
                    if (equipment.getData().get(i).getHour() < 5 && equipment.getMedida().get(i) >= 0) {
                        if (equipment.getMedida().get(i) < minimaN) {
                            minimaN = equipment.getMedida().get(i);
                        }
                    }
                    if (equipment.getMedida().get(i) >= 0) {
                        sum += equipment.getMedida().get(i);
                        qtdMedidasValidas++;
                    }
                }
            }

            media = sum / qtdMedidasValidas;
            fp = minimaN / media;

            if (minimaN == 9999)
                results.add(new PrintResult(dataMedida, "fora do horário para mínima noturna"));
            else
                results.add(new PrintResult(dataMedida, String.valueOf(df.format(fp))));

            //Reseta os valores
            minimaN = 9999;
            media = 0;
            fp = 0;
            qtdMedidasValidas = 0;
            sum = 0;
        }

        DrawPrintResult dp = new DrawPrintResult();
        dp.setPrintResults(results);
        dp.setType("FP");
        drawPrintResults.add(dp);
    }

    private void minimumNight() {
        LocalDateTime start = equipment.getData().get(0);
        LocalDateTime finish = equipment.getData().get(equipment.getData().size() - 1);
        List<PrintResult> results = new ArrayList<>();
        double minimaN = 9999;
        LocalDateTime horaMinima = equipment.getData().get(0);

        for (LocalDateTime dia = start; !dia.isAfter(finish); dia = dia.plusDays(1)) {

            for (int i = 0; i < equipment.getMedida().size(); i++) {
                if (dia.format(dateFormatter).equals(equipment.getData().get(i).format(dateFormatter))) {
                    //Minima noturna fica entre a 00:00h e 05:00h
                    if (equipment.getData().get(i).getHour() < 5 && equipment.getMedida().get(i) >= 0) {
                        if (equipment.getMedida().get(i) < minimaN) {
                            minimaN = equipment.getMedida().get(i);
                            horaMinima = equipment.getData().get(i);
                        }
                    }
                }
            }

            if (horaMinima == null)
                horaMinima = dia;

            if (minimaN == 9999)
                results.add(new PrintResult(horaMinima, "fora do horário para mínima noturna"));
            else
                results.add(new PrintResult(horaMinima, df.format(minimaN)));

            //Reseta os valores
            minimaN = 9999;
            horaMinima = null;
        }

        DrawPrintResult dp = new DrawPrintResult();
        dp.setPrintResults(results);
        dp.setType("MN");
        drawPrintResults.add(dp);
    }

    private void maximumDaily() {
        LocalDateTime start = equipment.getData().get(0);
        LocalDateTime finish = equipment.getData().get(equipment.getData().size() - 1);
        List<PrintResult> results = new ArrayList<>();
        double maximaD = 0;
        LocalDateTime horaMaxima = equipment.getData().get(0);

        for (LocalDateTime dia = start; !dia.isAfter(finish); dia = dia.plusDays(1)) {
            for (int i = 0; i < equipment.getMedida().size(); i++) {
                if (dia.format(dateFormatter).equals(equipment.getData().get(i).format(dateFormatter))) {
                    if (equipment.getMedida().get(i) >= 0) {
                        if (equipment.getMedida().get(i) > maximaD) {
                            maximaD = equipment.getMedida().get(i);
                            horaMaxima = equipment.getData().get(i);
                        }
                    }
                }
            }

            if (horaMaxima == null)
                horaMaxima = dia;

            if (maximaD == 0)
                results.add(new PrintResult(horaMaxima, "ERRO"));
            else
                results.add(new PrintResult(horaMaxima, df.format(maximaD) + ";" + horaMaxima.format(dateTimeFormatter)));

            //Reseta os valores
            maximaD = 0;
            horaMaxima = null;
        }

        DrawPrintResult dp = new DrawPrintResult();
        dp.setPrintResults(results);
        dp.setType("Máxima Diária");
        drawPrintResults.add(dp);
    }

    private void minimumDaily() {
        LocalDateTime start = equipment.getData().get(0);
        LocalDateTime finish = equipment.getData().get(equipment.getData().size() - 1);
        List<PrintResult> results = new ArrayList<>();
        double minimaD = 9999;
        LocalDateTime horaMinima = equipment.getData().get(0);

        for (LocalDateTime dia = start; !dia.isAfter(finish); dia = dia.plusDays(1)) {

            for (int i = 0; i < equipment.getMedida().size(); i++) {
                if (dia.format(dateFormatter).equals(equipment.getData().get(i).format(dateFormatter))) {
                    if (equipment.getMedida().get(i) >= 0) {
                        if (equipment.getMedida().get(i) < minimaD) {
                            minimaD = equipment.getMedida().get(i);
                            horaMinima = equipment.getData().get(i);
                        }
                    }
                }
            }

            if (horaMinima == null)
                horaMinima = dia;

            if (minimaD == 9999)
                results.add(new PrintResult(horaMinima, "ERRO"));
            else
                results.add(new PrintResult(horaMinima, df.format(minimaD) + ";" + horaMinima.format(dateTimeFormatter)));

            //Reseta os valores
            minimaD = 9999;
            horaMinima = null;
        }

        DrawPrintResult dp = new DrawPrintResult();
        dp.setPrintResults(results);
        dp.setType("Minima Diária");
        drawPrintResults.add(dp);
    }

    private void average() {
        LocalDateTime start = equipment.getData().get(0);
        LocalDateTime finish = equipment.getData().get(equipment.getData().size() - 1);
        LocalDateTime dataMedida;
        double sum = 0, media;
        int qtdMedidasValidas = 0;
        List<PrintResult> results = new ArrayList<>();

        for (LocalDateTime dia = start; !dia.isAfter(finish); dia = dia.plusDays(1)) {
            dataMedida = dia;
            for (int i = 0; i < equipment.getMedida().size(); i++) {
                if (dia.format(dateFormatter).equals(equipment.getData().get(i).format(dateFormatter))) {
                    if (equipment.getMedida().get(i) >= 0) {
                        sum += equipment.getMedida().get(i);
                        qtdMedidasValidas++;
                    }
                }
            }

            media = sum / qtdMedidasValidas;

            results.add(new PrintResult(dataMedida, String.valueOf(df.format(media))));

            //Reseta os valores
            media = 0;
            qtdMedidasValidas = 0;
            sum = 0;
        }

        DrawPrintResult dp = new DrawPrintResult();
        dp.setPrintResults(results);
        dp.setType("Média diária");
        drawPrintResults.add(dp);
    }
}

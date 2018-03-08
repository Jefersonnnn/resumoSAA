package analises;

import com.sun.javaws.Main;
import model.Equipamento;
import model.PrintResult;
import utils.DrawTable;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Sem valores negativos
 * Sem valores muito acima (analisar %)
 */
public class AnaliseFP implements Analise {

    private static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static DecimalFormat df = new DecimalFormat("0.##");

    /**
     * Fator de pesquisa (FP) é calculado com a minima norturna dividido pela média diaria.
     * O valor deverá ser algo em torno de 0 e 1 (acima de 0,3 provavel vazamento na região)
     * Excluindo valor abaixo de 0.
     *
     * @param equipamento
     */
    public static void fatorPesquisa(Equipamento equipamento) {
        LocalDateTime start = equipamento.getData().get(0);
        LocalDateTime finish = equipamento.getData().get(equipamento.getData().size() - 1);
        double sum = 0;
        List<PrintResult> results = new ArrayList<>();

        System.out.println(equipamento.getDipositivo());
        for (LocalDateTime dia = start; !dia.isAfter(finish); dia = dia.plusDays(1)) {
            System.out.println(dia.format(dateTimeFormatter));

            for (int i = 0; i < equipamento.getMedida().size(); i++) {
                if (dia.getDayOfMonth() == equipamento.getData().get(i).getDayOfMonth()) {
                    sum += equipamento.getMedida().get(i);
                }
            }
            results.add(new PrintResult());
            sum = 0;
        }

        System.out.println("Media: " + sum);
        DrawTable.drawTableV(results, equipamento);
    }

    public static void minimaNot(Equipamento equipamento, String formatoImp) {
        LocalDateTime start = equipamento.getData().get(0);
        LocalDateTime finish = equipamento.getData().get(equipamento.getData().size() - 1);
        List<PrintResult> results = new ArrayList<>();
        double minimaN = 9999;
        LocalDateTime horaMinima = equipamento.getData().get(0);

        for (LocalDateTime dia = start; !dia.isAfter(finish); dia = dia.plusDays(1)) {
            System.out.println(dia);

            for (int i = 0; i < equipamento.getMedida().size(); i++) {
                if (dia.getYear() == equipamento.getData().get(i).getYear()) {
                    if (dia.getMonth() == equipamento.getData().get(i).getMonth()) {
                        if (dia.getDayOfMonth() == equipamento.getData().get(i).getDayOfMonth()) {
                            if (equipamento.getData().get(i).getHour() < 5 && equipamento.getMedida().get(i) >= 0) {
                                if (equipamento.getMedida().get(i) < minimaN) {
                                    minimaN = equipamento.getMedida().get(i);
                                    horaMinima = equipamento.getData().get(i);
                                }
                            }
                        }
                    }
                }
            }

            //TODO: Mudar mininaN para receber string, pois se no dia nao ter valor entre 00:00 < 05:00 mostrar msg de erro
            if (horaMinima != null && minimaN != 9999)
                results.add(new PrintResult(horaMinima, minimaN));

            //Reseta os valores
            minimaN = 9999;
            horaMinima = null;
        }

        if (formatoImp.toUpperCase().equals("H"))
            DrawTable.drawTableH(results, equipamento);
        else if (formatoImp.toUpperCase().equals("V"))
            DrawTable.drawTableV(results,equipamento);
    }

    public static void maximaDiaria() {

    }

    public static void minimaDiaria() {

    }
}

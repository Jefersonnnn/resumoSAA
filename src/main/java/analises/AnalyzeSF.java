package analises;

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
public class AnalyzeSF implements Analise {

    private static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static DecimalFormat df = new DecimalFormat("0.##");

    /**
     * Fator de pesquisa (FP) é calculado com a minima norturna dividido pela média diaria.
     * O valor deverá ser algo em torno de 0 e 1 (acima de 0,3 provavel vazamento na região)
     * Excluindo valor abaixo de 0.
     *
     * @param equipment
     */
    public static void fatorPesquisa(Equipment equipment) {
        LocalDateTime start = equipment.getData().get(0);
        LocalDateTime finish = equipment.getData().get(equipment.getData().size() - 1);
        double sum = 0;
        List<PrintResult> results = new ArrayList<>();

        System.out.println(equipment.getDipositivo());
        for (LocalDateTime dia = start; !dia.isAfter(finish); dia = dia.plusDays(1)) {
            System.out.println(dia.format(dateTimeFormatter));

            for (int i = 0; i < equipment.getMedida().size(); i++) {
                if (dia.getDayOfMonth() == equipment.getData().get(i).getDayOfMonth()) {
                    sum += equipment.getMedida().get(i);
                }
            }
            results.add(new PrintResult());
            sum = 0;
        }

        System.out.println("Media: " + sum);
        DrawTable.drawTableV(results, equipment);
    }

    public static void minimaNot(Equipment equipment, String formatoImp) {
        LocalDateTime start = equipment.getData().get(0);
        LocalDateTime finish = equipment.getData().get(equipment.getData().size() - 1);
        List<PrintResult> results = new ArrayList<>();
        double minimaN = 9999;
        LocalDateTime horaMinima = equipment.getData().get(0);

        for (LocalDateTime dia = start; !dia.isAfter(finish); dia = dia.plusDays(1)) {
            System.out.println(dia);

            for (int i = 0; i < equipment.getMedida().size(); i++) {
                if (dia.getYear() == equipment.getData().get(i).getYear()) {
                    if (dia.getMonth() == equipment.getData().get(i).getMonth()) {
                        if (dia.getDayOfMonth() == equipment.getData().get(i).getDayOfMonth()) {
                            if (equipment.getData().get(i).getHour() < 5 && equipment.getMedida().get(i) >= 0) {
                                if (equipment.getMedida().get(i) < minimaN) {
                                    minimaN = equipment.getMedida().get(i);
                                    horaMinima = equipment.getData().get(i);
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
            DrawTable.drawTableH(results, equipment);
        else if (formatoImp.toUpperCase().equals("V"))
            DrawTable.drawTableV(results, equipment);
    }

    public static void maximaDiaria() {

    }

    public static void minimaDiaria() {

    }
}

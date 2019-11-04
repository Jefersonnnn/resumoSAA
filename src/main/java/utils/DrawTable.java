package utils;

import model.DrawPrintResult;
import model.Equipment;
import model.PrintResult;

import java.io.File;
import java.io.FileWriter;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class DrawTable {

    public static void drawTableH(List<DrawPrintResult> results, Equipment equipment) {
        StringBuilder table = new StringBuilder();

        table.append(getHeader())
                .append("<body>")
                .append("<table><tr>")
                .append("<h2>").append(equipment.getDipositivo()).append("</h2>")
                .append("<th>Data</td>");

        for (PrintResult result : results.get(0).getPrintResults()) {
            table.append("<td>").append(result.getData().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
                    .append("</td>");
        }

        for (DrawPrintResult result : results) {
            table.append("</tr><tr><th>").append(result.getType()).append("</th>");
            for (PrintResult printResult : result.getPrintResults()) {
                table.append("<td>").append(String.valueOf(printResult.getMedida()).replace('.', ',')).append("</td>");
            }
            table.append("</tr>");
        }
        saveTableInDotHtml(String.valueOf(table), equipment);
    }

    public static void drawTableV(List<DrawPrintResult> results, Equipment equipment) {
        StringBuilder table = new StringBuilder();

        table.append(getHeader())
                .append("<body>")
                .append("<h2>").append(equipment.getDipositivo()).append("</h2>")
                .append("<table>" +
                        "<tr>")
                .append("<th>Data</th>")
                .append("<th>Tipo</th>")
                .append("<th>Equipamento</th>")
                .append("<th>Valor</th>")
        ;

        /*
        for (DrawPrintResult result : results) {
            table.append("<th>").append(result.getType()).append("</th>");
        }*/

        table.append("</tr>");

        for (int i = 0; i < results.get(0).getPrintResults().size(); i++) {
            table.append("<tr>");

            for (int j = 0; j < results.size(); j++) {
                table.append("<td>").append(results.get(0).getPrintResults().get(i).getData().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                table.append("<td>").append(results.get(j).getType());
                table.append("<td>").append(equipment.getDipositivo());
                table.append("<td>").append(results.get(j).getPrintResults().get(i). getMedida().replace('.', ','));
                table.append("</tr>");
            }

        }


        saveTableInDotHtml(String.valueOf(table), equipment);
    }

    private static void saveTableInDotHtml(String table, Equipment equipment) {
        FileWriter fw;
        try {
            File localSave = new File(System.getProperty("user.dir") + "\\dataResults\\");
            if (!localSave.exists())
                localSave.mkdir();
            if (equipment.getDipositivo() == null || equipment.getDipositivo().equals(""))
                fw = new FileWriter(localSave + "\\" + equipment.getFileName() + ".html");
            else
                fw = new FileWriter(localSave + "\\" + Utils.removeSpecialCharacters(equipment.getDipositivo())+ " - " + Utils.removeSpecialCharacters(equipment.getPonto()) + ".html");
            fw.write(table);
            fw.flush();
            fw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String getHeader() {
        return "<!DOCTYPE html><html><head><style>" +
                "table{font-family: arial, sans-serif;border-collapse: collapse;width: 100%}" +
                "td, th{border: 1px solid #dddddd;text-align:left;padding:8px;}" +
                "tr:nth-child(even){background-color: #dddddd;}</style></head>";
    }
}

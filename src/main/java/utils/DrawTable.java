package utils;

import model.Equipment;
import model.PrintResult;

import java.io.File;
import java.io.FileWriter;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class DrawTable {

    public static void drawTableH(List<PrintResult> results, Equipment equipment) {
        StringBuilder table = new StringBuilder();

        table.append("<!DOCTYPE html><html><head><style>")
                .append("table{font-family: arial, sans-serif;border-collapse: collapse;width: 100%}")
                .append("td, th{border: 1px solid #dddddd;text-align:left;padding:8px;}")
                .append("tr:nth-child(even){background-color: #dddddd;}")
                .append("</style></head>")
                .append("<body>")
                .append("<table><tr>")
                .append("<h2>").append(equipment.getDipositivo()).append("</h2>")
                .append("<th>Data</td>");

        for (PrintResult result : results) {
            table.append("<td>").append(result.getData().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
                    .append("</td>");
        }
        table.append("</tr><tr><th>Medida</th>");
        for (PrintResult result : results) {
            table.append("<td>").append(String.valueOf(result.getMedida()).replace('.',',')).append("</td>");
        }

        table.append("</tr>");

        save(String.valueOf(table), equipment.getDipositivo());
    }

    public static void drawTableV(List<PrintResult> results, Equipment equipment) {
        StringBuilder table = new StringBuilder();

        table.append("<!DOCTYPE html><html><head><style>")
                .append("table{font-family: arial, sans-serif;border-collapse: collapse;width: 100%}")
                .append("td, th{border: 1px solid #dddddd;text-align:left;padding:8px;}")
                .append("tr:nth-child(even){background-color: #dddddd;}")
                .append("</style></head>")
                .append("<body>")
                .append("<h2>").append(equipment.getDipositivo()).append("</h2>")

                .append("<table><tr>")
                .append("<th>Data</th><th>Medida</th></tr>");
        for (int i = 0; i < results.size(); i++) {
            table.append("<tr><td>").append(results.get(i).getData().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))).append("</td>")
                    .append("<td>").append(String.valueOf(results.get(i).getMedida()).replace('.',',')).append("</td></tr>");
        }
        save(String.valueOf(table), equipment.getDipositivo());
    }

    //TODO: Corrigir erro ao salvar com : (Remover Caracteres especiais)
    public static void save(String table, String name) {
        try {
            File localSave = new File(System.getProperty("user.dir") + "\\saida\\");
            System.getProperty("user.dir");
            if(!localSave.exists()) {
                localSave.mkdir();
            }
            FileWriter fw = new FileWriter(localSave + "\\" + name + ".html");
            fw.write(table);
            fw.flush();
            fw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

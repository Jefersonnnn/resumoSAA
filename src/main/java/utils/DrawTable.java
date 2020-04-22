package utils;

import model.DrawPrintResult;

import java.io.File;
import java.io.FileWriter;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class DrawTable {

    public static void drawTableH(ArrayList<List<DrawPrintResult>> results) {
//        StringBuilder table = new StringBuilder();
//
//        table.append(getHeader())
//                .append("<body>")
//                .append("<table><tr>")
//                .append("<h2>").append(results.get(0).getEquipment()).append("</h2>")
//                .append("<th>Data</td>");
//
//        for (PrintResult result : results.get(0).getPrintResults()) {
//            table.append("<td>").append(result.getData().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
//                    .append("</td>");
//        }
//
//        for (DrawPrintResult result : results) {
//            table.append("</tr><tr><th>").append(result.getType()).append("</th>");
//            for (PrintResult printResult : result.getPrintResults()) {
//                table.append("<td>").append(String.valueOf(printResult.getMedida()).replace('.', ',')).append("</td>");
//            }
//            table.append("</tr>");
//        }
//        saveTableInDotHtml(String.valueOf(table));
    }

    public static void drawTableV(ArrayList<List<DrawPrintResult>> results) {
        StringBuilder table = new StringBuilder();

        table.append(getHeader())
                .append("<body>")
                .append("<h2>").append("Copiar e colar no banco de dados").append("</h2>")
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

        for (int i = 0; i < results.size();i++){
            for (int j =0; j < results.get(i).size();j++){
                for (int x = 0; x < results.get(i).get(j).getPrintResults().size(); x++) {
                    table.append("<tr>");

                    for (DrawPrintResult result : results.get(i)) {
                        table.append("<td>").append(results.get(i).get(j).getPrintResults().get(x).getData().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                        table.append("<td>").append(result.getType());
                        table.append("<td>").append(result.getEquipment());
                        table.append("<td>").append(result.getPrintResults().get(i).getMedida().replace('.', ','));
                        table.append("</tr>");
                    }

                }
            }

        }




        saveTableInDotHtml(String.valueOf(table));
    }

    private static void saveTableInDotHtml(String table) {
        FileWriter fw;
        try {
            File localSave = new File(System.getProperty("user.dir") + "\\dataResults\\");
            if (!localSave.exists())
                localSave.mkdir();

            fw = new FileWriter(localSave + "\\ResultadosTelemetria.html");
//            if (equipment.getDipositivo() == null || equipment.getDipositivo().equals(""))
//                fw = new FileWriter(localSave + "\\ResultadosTelemetria.html");
//            else
//                fw = new FileWriter(localSave + "\\" + Utils.removeSpecialCharacters(equipment.getDipositivo())+ " - " + Utils.removeSpecialCharacters(equipment.getPonto()) + ".html");
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

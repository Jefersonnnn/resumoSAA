package core;

import analysis.Analyze;
import model.DrawPrintResult;
import model.Equipment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import utils.Utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

//Excel 2007+

public class OpenExcel implements Callable<List<DrawPrintResult>> {

    private File file;
    private Equipment equipment;
    private List<DrawPrintResult> drawPrintResults;
    private List<String> tmpOpt;

    public OpenExcel(File file, List<String> tmp) {
        this.file = file;
        this.tmpOpt = tmp;
    }


    /**
     * Abre o arquivo especificado em uma Thread
     *
     * @return Equipment
     */
    @Override
    public List<DrawPrintResult> call() throws Exception {
        List<LocalDateTime> datas = new ArrayList<>();
        List<Double> medidas = new ArrayList<>();
        Equipment equipment = new Equipment();

        try {
            FileInputStream arquivo = new FileInputStream(file);
            equipment.setFileName(file.getName());

            if (file.getName().endsWith(".xls")) {
                System.out.println("Suporte aos arquivos .xls (Excel 2003 ou anterior) foram descontinuados");
            } else {
                Workbook wb = new XSSFWorkbook(arquivo);
                Sheet sheet = wb.getSheetAt(0);
                arquivo.close();
                wb.close();

                System.out.println("Lendo o arquivo " + file.getName());
                for (Row row : sheet) {

                    //Pega as 4 primeiras linhas do arquivo excel
                    //0 Descartado
                    //1 Instalação
                    //2 Dispostivo da instalação
                    //3 Periodo da instalação
                    switch (row.getRowNum()) {
                        case 1:
                            equipment.setInstalacao(Utils.removeSpecialCharacters(row.getCell(1).getStringCellValue()).trim());
                            break;
                        case 2:
                            equipment.setDipositivo(Utils.removeSpecialCharacters(row.getCell(1).getStringCellValue()).trim());
                            break;
                        case 3:
                            equipment.setPeriodo(row.getCell(1).getStringCellValue());
                            break;
                        case 5:
                            try {
                                if (row.getCell(1).getStringCellValue() != null) {
                                    equipment.setPonto(row.getCell(1).getStringCellValue());
                                } else if (row.getCell(2).getStringCellValue() != null) {
                                    equipment.setPonto(row.getCell(2).getStringCellValue());
                                } else {
                                    equipment.setPonto("PONTO");
                                }
                            } catch (NullPointerException e) {
                                equipment.setPonto("PONTO");
                            }
                            break;
                    }

                    if (row.getRowNum() > 6 && row.getRowNum() < sheet.getLastRowNum()) {

                        if (row.getCell(0) != null && row.getCell(2) != null) {
                            medidas.add(row.getCell(0).getNumericCellValue());
                            datas.add(LocalDateTime.ofInstant(row.getCell(2).getDateCellValue().toInstant(), ZoneId.of("America/Sao_Paulo")));
                        }
                    }
                }

                equipment.setData(datas);
                equipment.setMedida(medidas);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo Excel não encontrado: " + file.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (OutOfMemoryError e){
            System.out.println("Memória insuficiente!");
        } catch (Exception e) {
            System.out.println("Erro não listado!");
            e.printStackTrace();
        }

        this.equipment = equipment;
        Analyze analyze = new Analyze();
        System.out.println("Finalizado a leitura do arquivo " + file.getName() + "["+equipment.getInstalacao()+"]");

        return analyze.Analyze(equipment,tmpOpt);
    }

    @Override
    public String toString() {
        return equipment.getFileName();
    }

}

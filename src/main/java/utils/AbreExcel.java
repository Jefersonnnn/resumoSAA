package utils;

import model.Equipment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

//Excel 2007+

public class AbreExcel implements Runnable {

    private File file;
    private onExcelRead mListener;
    private Equipment equipment;

    public AbreExcel(File file, onExcelRead listener) {
        this.file = file;
        this.mListener = listener;
    }

    /**
     * Abre o arquivo especificado em uma Thread
     *
     * @return Equipment
     */
    @Override
    public void run() {
        List<LocalDateTime> datas = new ArrayList<>();
        List<Double> medidas = new ArrayList<>();
        Equipment equipment = new Equipment();

        try {
            FileInputStream arquivo = new FileInputStream(file);
            equipment.setFileName(file.getName());

            if (file.getName().endsWith(".xls")) {
                System.out.println("Suporte aos arquivos .xls (Excel 2003 ou anterior) foram descontinuados");
//                HSSFWorkbook workbook = new HSSFWorkbook(arquivo);
//                HSSFSheet sheetEquipamento = workbook.getSheetAt(0);
            } else {

                Workbook wb = new XSSFWorkbook(arquivo);
                Sheet sheet = wb.getSheetAt(0);

                System.out.println("Analisando..." + file.getName() + "...");
                Iterator<Row> rowIterator = sheet.iterator();
                while (rowIterator.hasNext()) {

                    Row row = rowIterator.next();
                    //Pega as 4 primeiras linhas do arquivo excel
                    //0 Descartado
                    //1 Instalação
                    //2 Dispostivo da instalação
                    //3 Periodo da instalação
                    switch (row.getRowNum()) {
                        case 1:
                            equipment.setInstalacao(row.getCell(1).getStringCellValue());
                            break;
                        case 2:
                            equipment.setDipositivo(row.getCell(1).getStringCellValue());
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

                arquivo.close();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("Arquivo Excel não encontrado: " + file.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            System.out.println("Finalizado: " + file.getName() + " :)");
        }
        mListener.onFinish(equipment);
    }

    public interface onExcelRead {
        void onFinish(Equipment equipment);
    }

    @Override
    public String toString() {
        return equipment.getFileName();
    }

}

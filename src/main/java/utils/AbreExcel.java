package utils;

import model.Equipment;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class AbreExcel implements Runnable {

    private File file;
    private onExcelRead mListener;

    public AbreExcel(File file, onExcelRead listener) {
        this.file = file;
        this.mListener = listener;
    }

    /**
     * Abre o arquivo especificado em uma Thread
     *
     * @param file
     * @return Equipment
     */
    @Override
    public void run() {
        List<LocalDateTime> datas = new ArrayList<>();
        List<Double> medidas = new ArrayList<>();
        Equipment equipment = new Equipment();

        try {
            FileInputStream arquivo = new FileInputStream(file);

            HSSFWorkbook workbook = new HSSFWorkbook(arquivo);
            HSSFSheet sheetEquipamento = workbook.getSheetAt(0);

            Iterator<Row> rowIterator = sheetEquipamento.iterator();

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
                }

                if (row.getRowNum() > 6 && row.getRowNum() < sheetEquipamento.getLastRowNum()) {

                    if (row.getCell(0) != null && row.getCell(2) != null) {
                        medidas.add(row.getCell(0).getNumericCellValue());
                        datas.add(LocalDateTime.ofInstant(row.getCell(2).getDateCellValue().toInstant(), ZoneId.of("America/Sao_Paulo")));
                    }
                }
            }

            equipment.setData(datas);
            equipment.setMedida(medidas);

            arquivo.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("Arquivo Excel não encontrado: " + file.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("FINISH " + this.toString());
        mListener.onFinish(equipment);
    }

    public interface onExcelRead {
        void onFinish(Equipment equipment);
    }

}

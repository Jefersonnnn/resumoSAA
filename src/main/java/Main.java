import analises.AnaliseFP;
import utils.AbreExcel;

import java.io.File;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String formatoImp;
        do {
            System.out.println("Formato de Impressão [V][H]:");
            formatoImp = scanner.next();

        }while (!formatoImp.toUpperCase().equals("V") && !formatoImp.toUpperCase().equals("H"));

        //Diretorio contendo os arquivos .xls para serem analisados
        File pasta = new File("in");

        //Inicia o tempo para o calculo de execucao das threads
        long inicioT = System.currentTimeMillis();
        //////////////////////////////////////////////////////

        //Verifica se a pasta existe
        if (pasta.exists()) {
            if (pasta.listFiles().length <= 0) {
                System.err.println("Não existe arquivos para analise!");
                scanner.next();
                System.exit(0);
            } else {
                //Lista todos os arquivos da pasta
                File[] arquivos = pasta.listFiles();
                AbreExcel[] abrirArquivos = new AbreExcel[arquivos.length];
                Thread[] threads = new Thread[arquivos.length];

                for (int i = 0; i < arquivos.length; i++) {
                    abrirArquivos[i] = new AbreExcel(arquivos[i]);
                    threads[i] = new Thread(abrirArquivos[i]);
                    threads[i].start();
                    try {
                        threads[i].join();
                    } catch (InterruptedException e) {
                        System.err.println("Erro na threads " + threads[i].getName() + "\n");
                        e.printStackTrace();
                    }
                }

                for (AbreExcel arquivo : abrirArquivos) {
                    AnaliseFP.minimaNot(arquivo.getEquipamento(), formatoImp);
                }

            }
        } else {
            System.err.println("ERROR: Pasta '/in' não foi criada");
            scanner.next();
        }

        /////////////////////////////////////////////
        //Calcula o tempo de execucao das threads
        System.out.printf("Execucao em: %d", (System.currentTimeMillis()) - inicioT);
        /////////////////////////////////////////////

    }
}

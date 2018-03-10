import analysis.Analyze;
import core.Core;
import utils.AbreExcel;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        //Diretorio contendo os arquivos .xls para serem analisados
        File pasta = new File("DataEntry");

        Scanner scanner = new Scanner(System.in);
        List<String> optionsAnalysis = new ArrayList<>();
        Core.loadInitialConfiguration();
        do {
            if (!pasta.exists()) {
                pasta.mkdir();
            }
        } while (!pasta.exists());

        if (pasta.listFiles().length <= 0) {
            System.err.println("Não existe arquivos para análise!");
            System.err.println("Favor adicionar os arquivos (.xls) na pasta \"DataEntry\"");
            scanner.nextLine();
            System.exit(0);
        } else {

            do {
                optionsAnalysis.clear();
                System.out.println("Formato de Impressão [V][H]:");
                optionsAnalysis.add(0, scanner.next());
            }
            while (!optionsAnalysis.get(0).toUpperCase().equals("V") && !optionsAnalysis.get(0).toUpperCase().equals("H"));
            System.out.println("Digite as opcoes para análise [MN]Minima Noturna | [FP]Fator de Pesquisa | [MinD]Minima Diaria | [MaxD]Maxima Diaria:");
            scanner.nextLine();
            String teste = scanner.nextLine();
            optionsAnalysis.addAll(Arrays.asList(teste.split(" ")));
            scanner.close();


            //Lista todos os arquivos da pasta
            File[] arquivos = pasta.listFiles();
            AbreExcel[] abrirArquivos = new AbreExcel[arquivos.length];
            Thread[] threads = new Thread[arquivos.length];

            for (int i = 0; i < arquivos.length; i++) {
                final List<String> tmp = optionsAnalysis;
                abrirArquivos[i] = new AbreExcel(arquivos[i], equipment -> new Analyze(equipment, tmp));
                threads[i] = new Thread(abrirArquivos[i]);
                threads[i].start();
            }
        }
    }
}

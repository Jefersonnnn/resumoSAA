import analysis.Analyze;
import core.Core;
import utils.AbreExcel;
import utils.ConsoleUtils;
import utils.GithubUpdate;
import utils.HttpClientExample;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {

        //Baixa os Excel
        HttpClientExample getExcels = new HttpClientExample();

        //Verica novas atualizacoes
        if(GithubUpdate.newUpdate())
            System.out.println(ConsoleUtils.ANSI_PURPLE + "NOVA ATUALIZAÇÃO EM: github.com/Jefersonnnn/resumoSAA/releases" + ConsoleUtils.ANSI_RESET);

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
            System.out.println("Digite as opções para análise:\n-> [MN]   Mínima Noturna\n" +
                                                                "-> [FP]   Fator de Pesquisa\n" +
                                                                "-> [MinD] Mínima Diária\n" +
                                                                "-> [MaxD] Máxima Diária\n" +
                                                                "-> [MED]  Média Diária");
            scanner.nextLine();
            String teste = scanner.nextLine();
            optionsAnalysis.addAll(Arrays.asList(teste.split(" ")));

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

        scanner.nextLine();
        scanner.close();
    }
}

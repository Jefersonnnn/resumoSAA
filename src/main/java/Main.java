import analysis.Analyze;
import core.Core;
import model.DrawPrintResult;
import utils.AbreExcel;
import utils.ConsoleUtils;
import utils.DrawTable;
import utils.GithubUpdate;

import java.io.File;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {

        //Verica novas atualizacoes
        if (GithubUpdate.newUpdate())
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

        if (Objects.requireNonNull(pasta.listFiles()).length <= 0) {
            System.err.println("Não existe arquivos para análise!");
            System.err.println("Favor adicionar os arquivos (.xls) na pasta \"DataEntry\"");
            scanner.nextLine();
            System.exit(0);
        } else {

            //Forçar o uso da opção Vertical V
            do {
                optionsAnalysis.clear();
                System.out.println("Formato de Impressão [V][H]:");
                optionsAnalysis.add(0, scanner.next());
            } while (!optionsAnalysis.get(0).toUpperCase().equals("V") && !optionsAnalysis.get(0).toUpperCase().equals("H"));

            System.out.println("Digite as opções para análise:\n-> [MN]   Mínima Noturna\n" +
                    "-> [FP]   Fator de Pesquisa\n" +
                    "-> [MinD] Mínima Diária\n" +
                    "-> [MaxD] Máxima Diária\n" +
                    "-> [MED]  Média Diária");
            scanner.nextLine();
            String optForAnalyses = scanner.nextLine();
            optionsAnalysis.addAll(Arrays.asList(optForAnalyses.split(" ")));

            //Lista todos os arquivos da pasta
            File[] arquivos = pasta.listFiles();
            AbreExcel[] abrirArquivos = new AbreExcel[arquivos.length];
            Thread[] threads = new Thread[arquivos.length];
            Analyze analises = new Analyze();

            final List<String> tmp = optionsAnalysis;
            for (int i = 0; i < arquivos.length; i++) {
                if (arquivos[i].getName().endsWith(".xls")) {
                    System.out.println("ATENÇÃO [" + arquivos[i].getName() + "] Suporte aos arquivos .xls (Excel 2003 ou anterior) foram descontinuados.");
                } else {

                    //abrirArquivos[i] = new AbreExcel(arquivos[i], equipment -> new Analyze(equipment, tmp));
                    abrirArquivos[i] = new AbreExcel(arquivos[i], tmp);
                    threads[i] = new Thread(abrirArquivos[i]);
                    threads[i].start();
                }
            }

            for (int i = 0; i < arquivos.length; i++) {
                threads[i].join();
            }

            //Salvar o resultado em um arquivo .html
            ArrayList<List<DrawPrintResult>> printResults = new ArrayList<>();

            for (int i = 0; i < arquivos.length; i++) {
                printResults.add(abrirArquivos[i].returnDrawPrintResults());
            }

            if (printResults.size() > 0)
                if (tmp.get(0).toUpperCase().equals("H"))
                    DrawTable.drawTableH(printResults);
                else
                    DrawTable.drawTableV(printResults);
        }

        System.out.println("\nPressione qualquer tecla para sair..\n");
        scanner.nextLine();
        scanner.close();


    }
}

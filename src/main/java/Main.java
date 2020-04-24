import core.Core;
import model.DrawPrintResult;
import utils.AbreExcel;
import utils.ConsoleUtils;
import utils.DrawTable;
import utils.GithubUpdate;

import java.io.File;
import java.util.*;

public class Main {

//    private static final int MAX_THREADS = 4;
//    private static final Semaphore semaforo = new Semaphore(MAX_THREADS);

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


            System.out.println("\nAguarde... Iniciando...\n");
            //Lista todos os arquivos da pasta

            ArrayList<File> arquivos = new ArrayList<>(Arrays.asList(pasta.listFiles()));
            AbreExcel[] abrirArquivos = new AbreExcel[arquivos.size()];
            Thread[] threads = new Thread[arquivos.size()];

            final List<String> tmp = optionsAnalysis;

            int iArq = 0;
            int qtdArqProce = 0;
            while (arquivos.size() != 0) {
                if (qtdArqProce==10){
                    for (int i = 0; i < iArq; i++) {
                        threads[i].join();
                    }
                    qtdArqProce = 0;
                }
                if (arquivos.get(0).getName().endsWith(".xls")) {
                    System.out.println("ATENÇÃO [" + arquivos.get(0).getName() + "] Suporte aos arquivos .xls (Excel 2003 ou anterior) foram descontinuados.");
                } else {
                    //abrirArquivos[iArq] = new AbreExcel(arquivos.get(0), qtdArqProce--);
                    abrirArquivos[iArq] = new AbreExcel(arquivos.get(0), tmp);
                    threads[iArq] = new Thread(abrirArquivos[iArq]);
                    threads[iArq].start();
                    iArq++;
                    qtdArqProce++;
                }
                arquivos.remove(0);
            }


            for (int i = 0; i < abrirArquivos.length; i++) {
                threads[i].join();
            }
            //Salvar o resultado em um arquivo .html
            ArrayList<List<DrawPrintResult>> printResults = new ArrayList<>();

            for (int i = 0; i < abrirArquivos.length; i++) {
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

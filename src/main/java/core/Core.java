package core;

import model.DrawPrintResult;

import java.io.File;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Core {

    private final File pasta;
    private boolean _lowConfig;

    public Core(boolean low_config) {
        _lowConfig = low_config;

        //Diretorio contendo os arquivos .xls a serem analisados
        pasta = new File("DataEntry");
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        List<String> optionsAnalysis = new ArrayList<>();

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
            } while (!optionsAnalysis.get(0).toUpperCase().equals("V") && !optionsAnalysis.get(0).equalsIgnoreCase("H"));


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
            ArrayList<File> excelFiles = new ArrayList<>(Arrays.asList(Objects.requireNonNull(pasta.listFiles())));
            List<OpenExcel> openFilesTasks = new ArrayList<>(Collections.emptyList());

            //Adiciona os arquivos na lista de tarefas.
            for (File arquivo : excelFiles) {
                if (arquivo.getName().endsWith(".xls")) {
                    System.out.println("ATENÇÃO [" + excelFiles.get(0).getName() + "] Suporte aos arquivos .xls (Excel 2003 ou anterior) foram descontinuados.");
                } else {
                    openFilesTasks.add(new OpenExcel(arquivo, optionsAnalysis));
                }
            }

            //Pool de threads
            ExecutorService threadPool;
            if(_lowConfig){
                threadPool = Executors.newFixedThreadPool(2);
            }else {
                threadPool = Executors.newFixedThreadPool(3);
            }
            ExecutorCompletionService<List<DrawPrintResult>> completionService = new ExecutorCompletionService<>(threadPool);

            //Executa as tarefas
            for (OpenExcel task : openFilesTasks){
                completionService.submit(task);
            }

            System.out.println("Tarefas iniciadas, aguardando conclusão");

            //Aguarda e salva os resultados
            ArrayList<List<DrawPrintResult>> printResults = new ArrayList<>();

            for (int i =0; i < openFilesTasks.size(); i++){
                try{
                    printResults.add(completionService.take().get());
                }catch (InterruptedException | ExecutionException ex){
                    ex.printStackTrace();
                }
            }

            /*
              Attempts to stop all actively executing tasks, halts the
              processing of waiting tasks, and returns a list of the tasks
              that were awaiting execution.
             */
            threadPool.shutdown();

            //Salvar o resultado em um arquivo .html
            if (printResults.size() > 0)
                if (optionsAnalysis.get(0).equalsIgnoreCase("H"))
                    DrawTable.drawTableH(printResults);
                else
                    DrawTable.drawTableV(printResults);
        }
    }
}

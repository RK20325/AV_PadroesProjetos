import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main {
    // A escolha do provedor fica somente na Main, como permite o enunciado.
    private static CloudFactory chooseFactory(String provider, AwsFactory aws, AzureFactory azure) {
        switch (provider.toUpperCase()) {
            case "AWS": return aws;
            case "AZURE": return azure;
            default: throw new IllegalArgumentException("Provedor desconhecido: " + provider);
        }
    }

    public static void main(String[] args) {
        AwsFactory awsFactory = new AwsFactory();
        AzureFactory azureFactory = new AzureFactory();

        ProvisioningService awsService = new ProvisioningService(chooseFactory("AWS", awsFactory, azureFactory));
        ProvisioningService azureService = new ProvisioningService(chooseFactory("AZURE", awsFactory, azureFactory));

        ExecutorService executor = Executors.newFixedThreadPool(4);
        List<Future<?>> tasks = new ArrayList<>();

        // Quatro solicitações: duas AWS, uma Azure e uma recusada por orçamento.
        tasks.add(executor.submit(() -> awsService.provision(
                new ProvisioningRequest("Ambiente-AWS-1", "AWS", 2, 500))));
        tasks.add(executor.submit(() -> awsService.provision(
                new ProvisioningRequest("Ambiente-AWS-2", "AWS", 1, 100))));
        tasks.add(executor.submit(() -> azureService.provision(
                new ProvisioningRequest("Ambiente-Azure", "Azure", 3, 250))));
        tasks.add(executor.submit(() -> awsService.provision(
                new ProvisioningRequest("Ambiente-Fora-do-Orcamento", "AWS", 20, 100))));

        try {
            for (Future<?> task : tasks) task.get();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("A execução foi interrompida.");
        } catch (java.util.concurrent.ExecutionException e) {
            System.err.println("Erro durante o provisionamento: " + e.getCause());
        } finally {
            executor.shutdown();
        }

        AuditLog.getInstance().printHistory();
    }
}

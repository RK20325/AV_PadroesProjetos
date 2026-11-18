import java.util.ArrayList;
import java.util.List;

// Recebe uma única Abstract Factory. Não conhece classes concretas dos provedores.
public class ProvisioningService {
    private final CloudFactory factory;

    public ProvisioningService(CloudFactory factory) {
        this.factory = factory;
    }

    public CloudEnvironment provision(ProvisioningRequest request) {
        AppConfig config = AppConfig.getInstance();
        AuditLog audit = AuditLog.getInstance();

        if (!factory.getProviderName().equalsIgnoreCase(request.getProvider())) {
            String reason = "A fábrica não corresponde ao provedor solicitado";
            audit.register(request.getEnvironmentName(), request.getProvider(), "RECUSADO", reason);
            System.out.println("Provisionamento recusado: " + reason);
            return null;
        }
        if (request.getVirtualMachineCount() < 0 || request.getStorageGb() < 0) {
            String reason = "As quantidades de recursos não podem ser negativas";
            audit.register(request.getEnvironmentName(), request.getProvider(), "RECUSADO", reason);
            System.out.println("Provisionamento recusado: " + reason);
            return null;
        }

        // O custo é calculado antes da criação: uma solicitação recusada não cria recursos.
        double estimatedCost = request.getVirtualMachineCount() * factory.getVmHourlyCost() * 730
                + request.getStorageGb() * factory.getStorageCostPerGb()
                + factory.getQueueMonthlyCost();

        if (estimatedCost > config.getMonthlyBudget()) {
            String reason = String.format("Orçamento excedido (R$ %.2f > R$ %.2f)",
                    estimatedCost, config.getMonthlyBudget());
            audit.register(request.getEnvironmentName(), request.getProvider(), "RECUSADO", reason);
            System.out.println("Provisionamento recusado para " + request.getEnvironmentName() + ": " + reason);
            return null;
        }

        List<VirtualMachine> machines = new ArrayList<>();
        for (int i = 0; i < request.getVirtualMachineCount(); i++) {
            machines.add(factory.createVirtualMachine());
        }
        ObjectStorage storage = factory.createObjectStorage(request.getStorageGb());
        MessageQueue queue = factory.createMessageQueue();

        CloudEnvironment environment = new CloudEnvironment(
                request.getEnvironmentName(), factory.getProviderName(), config.getDefaultRegion(),
                machines, storage, queue);
        audit.register(request.getEnvironmentName(), request.getProvider(), "ACEITO",
                String.format("Custo mensal estimado: R$ %.2f", estimatedCost));
        environment.printSummary();
        return environment;
    }
}

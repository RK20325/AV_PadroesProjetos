import java.util.List;

public class CloudEnvironment {
    private final String name;
    private final String provider;
    private final String region;
    private final List<VirtualMachine> virtualMachines;
    private final ObjectStorage storage;
    private final MessageQueue queue;

    public CloudEnvironment(String name, String provider, String region,
                            List<VirtualMachine> virtualMachines,
                            ObjectStorage storage, MessageQueue queue) {
        this.name = name;
        this.provider = provider;
        this.region = region;
        this.virtualMachines = virtualMachines;
        this.storage = storage;
        this.queue = queue;
    }

    public double calculateMonthlyCost() {
        double total = 0.0;
        for (VirtualMachine vm : virtualMachines) total += vm.getHourlyCost() * 730;
        total += storage.getGigabytes() * storage.getCostPerGb();
        total += queue.getMonthlyCost();
        return total;
    }

    public void printSummary() {
        System.out.println("\n=== Ambiente provisionado ===");
        System.out.println("Nome: " + name);
        System.out.println("Provedor: " + provider);
        System.out.println("Região: " + region);
        for (VirtualMachine vm : virtualMachines) System.out.println("Máquina virtual: " + vm.getId());
        System.out.println("Armazenamento: " + storage.getId());
        System.out.println("Fila: " + queue.getId());
        System.out.printf("Custo mensal estimado: R$ %.2f%n", calculateMonthlyCost());
    }
}

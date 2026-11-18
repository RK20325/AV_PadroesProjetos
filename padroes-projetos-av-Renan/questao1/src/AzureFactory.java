import java.util.concurrent.atomic.AtomicInteger;

public class AzureFactory implements CloudFactory {
    private final AtomicInteger vmSequence = new AtomicInteger(1);
    private final AtomicInteger storageSequence = new AtomicInteger(1);
    private final AtomicInteger queueSequence = new AtomicInteger(1);

    public String getProviderName() { return "Azure"; }
    public VirtualMachine createVirtualMachine() {
        return new AzureVirtualMachine("vm-" + vmSequence.getAndIncrement());
    }
    public ObjectStorage createObjectStorage(int gigabytes) {
        return new AzureObjectStorage("blob://" + storageSequence.getAndIncrement(), gigabytes);
    }
    public MessageQueue createMessageQueue() {
        return new AzureMessageQueue("sb-" + queueSequence.getAndIncrement());
    }
    public double getVmHourlyCost() { return 0.12; }
    public double getStorageCostPerGb() { return 0.02; }
    public double getQueueMonthlyCost() { return 0.50; }
}

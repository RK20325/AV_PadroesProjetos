import java.util.concurrent.atomic.AtomicInteger;

public class AwsFactory implements CloudFactory {
    private final AtomicInteger vmSequence = new AtomicInteger(1);
    private final AtomicInteger storageSequence = new AtomicInteger(1);
    private final AtomicInteger queueSequence = new AtomicInteger(1);

    public String getProviderName() { return "AWS"; }
    public VirtualMachine createVirtualMachine() {
        return new AwsVirtualMachine("i-" + vmSequence.getAndIncrement());
    }
    public ObjectStorage createObjectStorage(int gigabytes) {
        return new AwsObjectStorage("s3://" + storageSequence.getAndIncrement(), gigabytes);
    }
    public MessageQueue createMessageQueue() {
        return new AwsMessageQueue("sqs-" + queueSequence.getAndIncrement());
    }
    public double getVmHourlyCost() { return 0.10; }
    public double getStorageCostPerGb() { return 0.023; }
    public double getQueueMonthlyCost() { return 0.40; }
}

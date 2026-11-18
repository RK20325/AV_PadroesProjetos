public interface CloudFactory {
    String getProviderName();
    VirtualMachine createVirtualMachine();
    ObjectStorage createObjectStorage(int gigabytes);
    MessageQueue createMessageQueue();
    double getVmHourlyCost();
    double getStorageCostPerGb();
    double getQueueMonthlyCost();
}

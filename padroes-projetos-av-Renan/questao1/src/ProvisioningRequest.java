public class ProvisioningRequest {
    private final String environmentName;
    private final String provider;
    private final int virtualMachineCount;
    private final int storageGb;

    public ProvisioningRequest(String environmentName, String provider,
                               int virtualMachineCount, int storageGb) {
        this.environmentName = environmentName;
        this.provider = provider;
        this.virtualMachineCount = virtualMachineCount;
        this.storageGb = storageGb;
    }

    public String getEnvironmentName() { return environmentName; }
    public String getProvider() { return provider; }
    public int getVirtualMachineCount() { return virtualMachineCount; }
    public int getStorageGb() { return storageGb; }
}

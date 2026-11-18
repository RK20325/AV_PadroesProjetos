public class AzureVirtualMachine implements VirtualMachine {
    private final String id;
    public AzureVirtualMachine(String id) { this.id = id; }
    public String getId() { return id; }
    public double getHourlyCost() { return 0.12; }
}

public class AwsVirtualMachine implements VirtualMachine {
    private final String id;
    public AwsVirtualMachine(String id) { this.id = id; }
    public String getId() { return id; }
    public double getHourlyCost() { return 0.10; }
}

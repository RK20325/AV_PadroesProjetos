public class AzureMessageQueue implements MessageQueue {
    private final String id;
    public AzureMessageQueue(String id) { this.id = id; }
    public String getId() { return id; }
    public double getMonthlyCost() { return 0.50; }
}

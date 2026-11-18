public class AwsMessageQueue implements MessageQueue {
    private final String id;
    public AwsMessageQueue(String id) { this.id = id; }
    public String getId() { return id; }
    public double getMonthlyCost() { return 0.40; }
}

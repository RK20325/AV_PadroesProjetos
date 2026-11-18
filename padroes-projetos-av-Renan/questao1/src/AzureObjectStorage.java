public class AzureObjectStorage implements ObjectStorage {
    private final String id;
    private final int gigabytes;
    public AzureObjectStorage(String id, int gigabytes) { this.id = id; this.gigabytes = gigabytes; }
    public String getId() { return id; }
    public int getGigabytes() { return gigabytes; }
    public double getCostPerGb() { return 0.02; }
}

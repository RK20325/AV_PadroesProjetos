public class ExpressDelivery extends ServiceDecorator {
    public ExpressDelivery(InvoiceComponent component) { super(component); }
    protected String getServiceName() { return "Entrega expressa"; }
    protected double getServiceCost() { return 450.00; }
}

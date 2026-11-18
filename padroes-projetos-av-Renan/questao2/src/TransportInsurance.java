public class TransportInsurance extends ServiceDecorator {
    public TransportInsurance(InvoiceComponent component) { super(component); }
    protected String getServiceName() { return "Seguro de transporte"; }
    protected double getServiceCost() { return getSaleValue() * 0.015; }
}

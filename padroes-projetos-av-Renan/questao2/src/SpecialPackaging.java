public class SpecialPackaging extends ServiceDecorator {
    public SpecialPackaging(InvoiceComponent component) { super(component); }
    protected String getServiceName() { return "Embalagem especial"; }
    protected double getServiceCost() { return 180.00; }
}

public class AuthenticityCertificate extends ServiceDecorator {
    public AuthenticityCertificate(InvoiceComponent component) { super(component); }
    protected String getServiceName() { return "Certificado de autenticidade"; }
    protected double getServiceCost() { return 300.00; }
}

import java.util.ArrayList;
import java.util.List;

// Decorator: acrescenta uma linha e um custo, sem alterar o componente envolvido.
public abstract class ServiceDecorator implements InvoiceComponent {
    protected final InvoiceComponent component;

    public ServiceDecorator(InvoiceComponent component) { this.component = component; }
    protected abstract String getServiceName();
    protected abstract double getServiceCost();

    public List<String> getLines() {
        List<String> lines = new ArrayList<>(component.getLines());
        lines.add(String.format("%s: R$ %.2f", getServiceName(), getServiceCost()));
        return lines;
    }

    public double getSaleValue() { return component.getSaleValue(); }
    public double getTotal() { return component.getTotal() + getServiceCost(); }
}

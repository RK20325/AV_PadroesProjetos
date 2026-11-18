public class PropertyCommission implements CommissionStrategy {
    public double calculate(double saleValue) { return saleValue * 0.03 + 1500.00; }
}

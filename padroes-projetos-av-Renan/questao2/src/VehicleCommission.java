public class VehicleCommission implements CommissionStrategy {
    public double calculate(double saleValue) { return Math.min(saleValue * 0.08, 5000.00); }
}

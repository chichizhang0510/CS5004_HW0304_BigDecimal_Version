package student;

public class PayStub implements IPayStub{
    private String name;
    private double payAfterTax;
    private double tax;
    private double ytdEarnings;
    private double ytdTaxesPaid;

    public PayStub(String name, double payAfterTax, double tax, double ytdEarnings, double ytdTaxesPaid) {
        this.name = name;
        this.payAfterTax = payAfterTax;
        this.tax = tax;
        this.ytdEarnings = ytdEarnings;
        this.ytdTaxesPaid = ytdTaxesPaid;
    }

    @Override
    public double getPay() {
        return payAfterTax;
    }

    @Override
    public double getTaxesPaid() {
        return tax;
    }

    @Override
    public String toCSV() {
        return name + "," + String.format("%.2f", payAfterTax) + "," +
                String.format("%.2f", tax) + "," +
                String.format("%.2f", ytdEarnings) + "," +
                String.format("%.2f", ytdTaxesPaid);
    }
}

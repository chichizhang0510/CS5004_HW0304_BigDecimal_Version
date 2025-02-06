package student;

public class SalaryEmployee implements IEmployee{
    String name;
    String id;
    double payRate;
    double ytdEarnings;
    double ytdTaxesPaid;
    double pretaxDeductions;

    public SalaryEmployee(String name, String id, double payRate, double ytdEarnings, double ytdTaxesPaid, double pretaxDeductions) {
        this.name = name;
        this.id = id;
        this.payRate = payRate;
        this.ytdEarnings = ytdEarnings;
        this.ytdTaxesPaid = ytdTaxesPaid;
        this.ytdTaxesPaid = ytdTaxesPaid;
        this.pretaxDeductions = pretaxDeductions;
    }

    public String getName() {
        return name;
    }

    @Override
    public String getID() {
        return this.id;
    }

    public double getPayRate() {
        return payRate;
    }

    public double getYTDEarnings() {
        return ytdEarnings;
    }

    public double getYTDTaxesPaid() {
        return ytdTaxesPaid;
    }

    public double getPretaxDeductions() {
        return pretaxDeductions;
    }

    @Override
    public IPayStub runPayroll(double hoursWorked) {
        if (hoursWorked < 0) {
            return null;
        }

        double totalPay = payRate / 24;
        double payShouldTax = totalPay - pretaxDeductions;
        double taxes = payShouldTax * 0.2265;
        double payAfterTax = payShouldTax - taxes;

        return new PayStub(name, payAfterTax, taxes, ytdEarnings + payAfterTax, +ytdTaxesPaid + taxes);
    }

    @Override
    public String toCSV() {
        return "";
    }

    @Override
    public String getEmployeeType() {
        return "SALARY";
    }
}

package student;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class SalaryEmployee extends Employee {

    public SalaryEmployee(String name, String id, double payRate,
                          double ytdEarnings, double ytdTaxesPaid, double pretaxDeductions) {
        super(name, id, payRate, ytdEarnings, ytdTaxesPaid, pretaxDeductions);
    }

    @Override
    public String getEmployeeType() {
        return "SALARY";
    }

    @Override
    protected double calculateGrossPay(double hoursWorked) {
        return payRate / 24;  // 薪资固定，每月支付两次
    }

    @Override
    public IPayStub runPayroll(double hoursWorked) {
        if (hoursWorked < 0) {
            return null;
        }

        // ✅ 使用 BigDecimal 计算
        BigDecimal totalPay = BigDecimal.valueOf(calculateGrossPay(hoursWorked));
        BigDecimal taxablePay = totalPay.subtract(BigDecimal.valueOf(pretaxDeductions));
        BigDecimal taxes = taxablePay.multiply(new BigDecimal("0.2265"));
        BigDecimal payAfterTax = taxablePay.subtract(taxes);

        // ✅ 只将 ytdEarnings 和 ytdTaxesPaid 变为 BigDecimal
        BigDecimal newYTDEarnings = BigDecimal.valueOf(ytdEarnings).add(payAfterTax);
        BigDecimal newYTDTaxesPaid = BigDecimal.valueOf(ytdTaxesPaid).add(taxes);

        // ✅ 计算完成后转换回 double 以保持兼容性
        ytdEarnings = newYTDEarnings.doubleValue();
        ytdTaxesPaid = newYTDTaxesPaid.doubleValue();
        System.out.println(name  + " " + totalPay + " " + taxes + " " + payAfterTax + " " + newYTDEarnings + " " + newYTDTaxesPaid);

        return new PayStub(name, payAfterTax.doubleValue(), taxes.doubleValue(), ytdEarnings, ytdTaxesPaid);
    }
}

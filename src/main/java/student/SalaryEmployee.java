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
        return payRate / 24;  // 固定薪资，每月支付两次
    }

    @Override
    public IPayStub runPayroll(double hoursWorked) {
        if (hoursWorked < 0) {
            return null;
        }

        // ✅ 计算总工资（保持高精度）
        BigDecimal totalPay = BigDecimal.valueOf(calculateGrossPay(hoursWorked))
                .setScale(10, RoundingMode.HALF_UP);

        // ✅ 计算应税工资（确保高精度）
        BigDecimal taxablePay = totalPay.subtract(BigDecimal.valueOf(pretaxDeductions))
                .setScale(10, RoundingMode.HALF_UP);

        // ✅ 计算税款（确保高精度）
        BigDecimal taxes = taxablePay.multiply(new BigDecimal("0.2265"))
                .setScale(10, RoundingMode.HALF_UP);

        // ✅ 计算税后收入（先保持高精度，然后立即转换为 2 位小数）
        BigDecimal payAfterTax = taxablePay.subtract(taxes)
                .setScale(2, RoundingMode.HALF_UP);

        BigDecimal payAfterTax1 = taxablePay.subtract(taxes)
                .setScale(2, RoundingMode.DOWN); // 这里就确保它是最终正确的小数点

        // ✅ 计算年初至今收入和税款（确保精度）
        BigDecimal prevYTDEarnings = BigDecimal.valueOf(ytdEarnings);
        BigDecimal prevYTDTaxesPaid = BigDecimal.valueOf(ytdTaxesPaid);

        BigDecimal newYTDEarnings = prevYTDEarnings.add(payAfterTax1)
                .setScale(2, RoundingMode.DOWN); // 这里也立刻转换为 2 位小数
        BigDecimal newYTDTaxesPaid = prevYTDTaxesPaid.add(taxes)
                .setScale(2, RoundingMode.HALF_UP); // 同样确保 2 位小数

        // ✅ 更新 ytdEarnings 和 ytdTaxesPaid
        ytdEarnings = newYTDEarnings.doubleValue();
        ytdTaxesPaid = newYTDTaxesPaid.doubleValue();

        System.out.println(name + " TotalPay: " + totalPay + " Taxes: " + taxes + " NetPay: " + payAfterTax +
                " YTD Earnings: " + newYTDEarnings + " YTD Taxes: " + newYTDTaxesPaid);

        // ✅ **最终四舍五入后传递给 PayStub**
        return new PayStub(name, payAfterTax.doubleValue(), taxes.doubleValue(), newYTDEarnings.doubleValue(), newYTDTaxesPaid.doubleValue());
    }

}

package student;

/**
 * Represents a salaried employee who is paid a fixed amount per pay period.
 * Implements the {@link IEmployee} interface to define employee behavior.
 */
public class SalaryEmployee implements IEmployee {
    /** The name of the employee. */
    private String name;

    /** The unique identifier (ID) of the employee. */
    private String id;

    /** The annual salary of the employee. */
    private double payRate;

    /** The year-to-date (YTD) earnings of the employee. */
    private double ytdEarnings;

    /** The year-to-date (YTD) taxes paid by the employee. */
    private double ytdTaxesPaid;

    /** The pretax deductions of the employee. */
    private double pretaxDeductions;

    /**
     * Constructs a new {@code SalaryEmployee} with the specified details.
     *
     * @param name The name of the employee.
     * @param id The unique ID of the employee.
     * @param payRate The annual salary.
     * @param ytdEarnings The year-to-date earnings.
     * @param ytdTaxesPaid The year-to-date taxes paid.
     * @param pretaxDeductions The pretax deductions.
     */
    public SalaryEmployee(String name, String id,
                          double payRate, double ytdEarnings,
                          double ytdTaxesPaid, double pretaxDeductions) {
        this.name = name;
        this.id = id;
        this.payRate = payRate;
        this.ytdEarnings = ytdEarnings;
        this.ytdTaxesPaid = ytdTaxesPaid;
        this.ytdTaxesPaid = ytdTaxesPaid;
        this.pretaxDeductions = pretaxDeductions;
    }

    /**
     * Gets the name of the employee.
     *
     * @return The employee's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the ID of the employee.
     *
     * @return The employee's ID.
     */
    @Override
    public String getID() {
        return this.id;
    }

    /**
     * Gets the annual salary of the employee.
     *
     * @return The employee's annual salary.
     */
    public double getPayRate() {
        return payRate;
    }

    /**
     * Gets the year-to-date (YTD) earnings of the employee.
     *
     * @return The employee's YTD earnings.
     */
    public double getYTDEarnings() {
        return ytdEarnings;
    }

    /**
     * Gets the year-to-date (YTD) taxes paid by the employee.
     *
     * @return The employee's YTD taxes paid.
     */
    public double getYTDTaxesPaid() {
        return ytdTaxesPaid;
    }

    /**
     * Gets the pretax deductions for the employee.
     *
     * @return The pretax deductions amount.
     */
    public double getPretaxDeductions() {
        return pretaxDeductions;
    }

    /**
     * Runs payroll for the current pay period, calculating gross pay, deductions, and taxes.
     * - If hours worked is negative, payroll is skipped and returns {@code null}.
     * - Salaried employees are paid a fixed amount, which is their annual salary divided by 24.
     * - Taxes are calculated as 22.65% of the taxable pay (after pretax deductions).
     *
     * @param hoursWorked The number of hours worked in the pay period (ignored for salaried employees).
     * @return A {@link PayStub} containing the payroll details, or {@code null} if hoursWorked < 0.
     */
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

    /**
     * Converts the employee's information to a CSV-formatted string.
     * The format follows: "SALARY,name,id,payRate,pretaxDeductions,YTDEarnings,YTDTaxesPaid".
     *
     * @return A CSV string representing the employee.
     */
    @Override
    public String toCSV() {
        return "";
    }

    /**
     * Gets the employee type as a string.
     *
     * @return "SALARY" indicating this employee is a salaried employee.
     */
    @Override
    public String getEmployeeType() {
        return "SALARY";
    }
}

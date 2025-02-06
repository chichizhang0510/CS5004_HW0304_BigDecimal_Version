package student;

/**
 * Represents a time card for an employee, recording the number of hours worked.
 * Implements the {@link ITimeCard} interface to define time card behavior.
 */
public class TimeCard implements ITimeCard{
    /** The unique identifier (ID) of the employee associated with this time card. */
    private String id;

    /** The number of hours worked by the employee in the current pay period. */
    private double hoursWorked;

    /**
     * Constructs a new {@code TimeCard} with the specified details.
     *
     * @param id The unique ID of the employee.
     * @param hoursWorked The number of hours worked in the current pay period.
     */
    public TimeCard(String id, double hoursWorked) {
        this.id = id;
        this.hoursWorked = hoursWorked;
    }

    /**
     * Gets the ID of the employee associated with this time card.
     *
     * @return The employee's ID.
     */
    @Override
    public String getEmployeeID() {
        return id;
    }

    /**
     * Gets the number of hours worked by the employee for the current pay period.
     *
     * @return The hours worked.
     */
    @Override
    public double getHoursWorked() {
        return hoursWorked;
    }

    /**
     * Converts the time card details to a string representation.
     *
     * @return A string representing the time card in the format:
     *         "TimeCard{employeeID='id', hoursWorked=hoursWorked}".
     */
    @Override
    public String toString() {
        return "TimeCard{"
                + "employeeID='" + id + '\''
                + ", hoursWorked=" + hoursWorked
                + '}';
    }
}

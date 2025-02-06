package student;

public class TimeCard implements ITimeCard{
    private String id;
    private double hoursWorked;

    public TimeCard(String id, double hoursWorked) {
        this.id = id;
        this.hoursWorked = hoursWorked;
    }

    @Override
    public String getEmployeeID() {
        return id;
    }

    @Override
    public double getHoursWorked() {
        return hoursWorked;
    }

    @Override
    public String toString() {
        return "TimeCard{" +
                "employeeID='" + id + '\'' +
                ", hoursWorked=" + hoursWorked +
                '}';
    }
}

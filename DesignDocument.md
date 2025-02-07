# Payroll Generator Design Document


This document is meant to provide a tool for you to demonstrate the design process. You need to work on this before you code, and after have a finished product. That way you can compare the changes, and changes in design are normal as you work through a project. It is contrary to popular belief, but we are not perfect our first attempt. We need to iterate on our designs to make them better. This document is a tool to help you do that.


## (INITIAL DESIGN): Class Diagram

```mermaid
classDiagram
    class IEmployee {
        <<interface>>
        +String getName()
        +String getID()
        +double getPayRate()
        +String getEmployeeType()
        +double getYTDEarnings()
        +double getYTDTaxesPaid()
        +double getPretaxDeductions()
        +IPayStub runPayroll(double hoursWorked)
        +String toCSV()
    }
    
    class HourlyEmployee {
        -String name
        -String id
        -double payRate
        -double ytdEarnings
        -double ytdTaxesPaid
        -double pretaxDeductions
        +String getID()
        +String getName()
        +double getPayRate()
        +double getYTDEarnings()
        +double getYTDTaxesPaid()
        +double getPretaxDeductions()
        +String getEmployeeType()
        +IPayStub runPayroll(double hoursWorked)
        +String toCSV()
    }
    
    class SalaryEmployee {
        -String name
        -String id
        -double payRate
        -double ytdEarnings
        -double ytdTaxesPaid
        -double pretaxDeductions
        +String getID()
        +String getName()
        +double getPayRate()
        +double getYTDEarnings()
        +double getYTDTaxesPaid()
        +double getPretaxDeductions()
        +String getEmployeeType()
        +IPayStub runPayroll(double hoursWorked)
        +String toCSV()
    }
    
    class IPayStub {
        <<interface>>
        +double getPay()
        +double getTaxesPaid()
        +String toCSV()
    }
    
    class ITimeCard{
    		<<interface>>
    		+String getEmployeeID()
    		+double getHoursWorked()
    }
    
    
    
    class PayStub {
    		-String name
        -double payAfterTax
        -double tax
        -double ytdEarnings
        -double ytdTaxesPaid
        +double getPay()
        +double getTaxesPaid()
        +String toCSV()
    }
    
    class TimeCard {
        -String id
        -double hoursWorked
        +String getEmployeeID()
        +double getHoursWorked()
    }
    
    class Builder {
    		-Builder()
    		+static IEmployee buildEmployeeFromCSV(String csv)
    		+static ITimeCard buildTimeCardFromCSV(String csv)
    }
    
    class FileUtil {
    		+static String EMPLOYEE_HEADER
    		+static String PAY_STUB_HEADER
    		+static List<String> readFileToList(String file)
    		+static void writeFile(String outFile, List<String> lines)
    		+static void writeFile(String outFile, List<String> lines, boolean backup)
    }
    
    class PayrollGenerator {
        -static String DEFAULT_EMPLOYEE_FILE
        -static String DEFAULT_PAYROLL_FILE
        -static String DEFAULT_TIME_CARD_FILE
        -PayrollGenerator()
        +static void main(String[] args)
    }
    
    class Arguments {
        -String employeeFile
        -String payrollFile
        -String timeCards
    		+Arguments()
        +String getEmployeeFile()
        +String getPayrollFile()
        +String getTimeCards()
        +void printHelp()
        +static Arguments process(String[] args)
    }

    IEmployee <|-- HourlyEmployee
    IEmployee <|-- SalaryEmployee
    IPayStub <|-- PayStub
    ITimeCard <|-- TimeCard
    
    Builder --> HourlyEmployee : "creates"
		Builder --> SalaryEmployee : "creates"
		Builder --> TimeCard : "creates"

    PayrollGenerator --> Builder : "uses"
    PayrollGenerator --> Arguments : "uses"
```




## (INITIAL DESIGN): Tests to Write - Brainstorm

Write a test (in english) that you can picture for the class diagram you have created. This is the brainstorming stage in the TDD process. 

> [!TIP]
> As a reminder, this is the TDD process we are following:
> 1. Figure out a number of tests by brainstorming (this step)
> 2. Write **one** test
> 3. Write **just enough** code to make that test pass
> 4. Refactor/update  as you go along
> 5. Repeat steps 2-4 until you have all the tests passing/fully built program

You should feel free to number your brainstorm. 

1. Test that the HourlyEmployee class properly returns name from getName().
2. Test that the HourlyEmployee class properly returns id from getID().
3. Test that the HourlyEmployee class properly returns payRate from getPayRate().
4. Test that the HourlyEmployee class properly returns YTDEarnings from getYTDEarnings().
5. Test that the HourlyEmployee class properly returns YTDTaxesPaid from getYTDTaxesPaid().
6. Test that the HourlyEmployee class properly returns pretaxDeductions from getPretaxDeductions().
7. Test that the HourlyEmployee class correctly identifies as "HOURLY" from getEmployeeType().
8. Test that the SalaryEmployee class properly returns name from getName().
9. Test that the SalaryEmployee class properly returns id from getID().
10. Test that the SalaryEmployee class properly returns payRate from getPayRate().
11. Test that the SalaryEmployee class properly returns YTDEarnings from getYTDEarnings().
12. Test that the SalaryEmployee class properly returns YTDTaxesPaid from getYTDTaxesPaid().
13. Test that the SalaryEmployee class properly returns pretaxDeductions from getPretaxDeductions().
14. Test that the SalaryEmployee class correctly identifies as "SALARY" from getEmployeeType().
15. Test that HourlyEmployee.runPayroll(hoursWorked) calculates gross pay correctly for ≤40 hours.
16. Test that HourlyEmployee.runPayroll(hoursWorked) calculates gross pay correctly for >40 hours (overtime pay included).
17. Test that HourlyEmployee.runPayroll(hoursWorked) applies pretax deductions correctly.
18. Test that HourlyEmployee.runPayroll(hoursWorked) calculates correct taxes (22.65%).
19. Test that HourlyEmployee.runPayroll(hoursWorked) correctly calculates net pay.
20. Test that SalaryEmployee.runPayroll(hoursWorked) correctly calculates gross pay as (annual salary / 24 pay periods).
21. Test that SalaryEmployee.runPayroll(hoursWorked) applies pretax deductions correctly.
22. Test that SalaryEmployee.runPayroll(hoursWorked) calculates correct taxes (22.65%).
23. Test that SalaryEmployee.runPayroll(hoursWorked) correctly calculates net pay.
24. Test that runPayroll(hoursWorked) skips payroll calculation if hoursWorked < 0.


## (FINAL DESIGN): Class Diagram

Go through your completed code, and update your class diagram to reflect the final design. Make sure you check the file in the browser on github.com to make sure it is rendering correctly. It is normal that the two diagrams don't match! Rarely (though possible) is your initial design perfect. 

> [!WARNING]
> If you resubmit your assignment for manual grading, this is a section that often needs updating. You should double check with every resubmit to make sure it is up to date.

```mermaid
classDiagram
    class IEmployee {
        <<interface>>
        +String getName()
        +String getID()
        +double getPayRate()
        +String getEmployeeType()
        +double getYTDEarnings()
        +double getYTDTaxesPaid()
        +double getPretaxDeductions()
        +IPayStub runPayroll(double hoursWorked)
        +String toCSV()
    }
    
    class HourlyEmployee {
        -String name
        -String id
        -double payRate
        -double ytdEarnings
        -double ytdTaxesPaid
        -double pretaxDeductions
        +String getID()
        +String getName()
        +double getPayRate()
        +double getYTDEarnings()
        +double getYTDTaxesPaid()
        +double getPretaxDeductions()
        +String getEmployeeType()
        +IPayStub runPayroll(double hoursWorked)
        +String toCSV()
    }
    
    class SalaryEmployee {
        -String name
        -String id
        -double payRate
        -double ytdEarnings
        -double ytdTaxesPaid
        -double pretaxDeductions
        +String getID()
        +String getName()
        +double getPayRate()
        +double getYTDEarnings()
        +double getYTDTaxesPaid()
        +double getPretaxDeductions()
        +String getEmployeeType()
        +IPayStub runPayroll(double hoursWorked)
        +String toCSV()
    }
    
    class IPayStub {
        <<interface>>
        +double getPay()
        +double getTaxesPaid()
        +String toCSV()
    }
    
    class ITimeCard{
    		<<interface>>
    		+String getEmployeeID()
    		+double getHoursWorked()
    }
    
    
    
    class PayStub {
    		-String name
        -double payAfterTax
        -double tax
        -double ytdEarnings
        -double ytdTaxesPaid
        +double getPay()
        +double getTaxesPaid()
        +String toCSV()
    }
    
    class TimeCard {
        -String id
        -double hoursWorked
        +String getEmployeeID()
        +double getHoursWorked()
    }
    
    class Builder {
    		-Builder()
    		+static IEmployee buildEmployeeFromCSV(String csv)
    		+static ITimeCard buildTimeCardFromCSV(String csv)
    }
    
    class FileUtil {
    		+static String EMPLOYEE_HEADER
    		+static String PAY_STUB_HEADER
    		+static List<String> readFileToList(String file)
    		+static void writeFile(String outFile, List<String> lines)
    		+static void writeFile(String outFile, List<String> lines, boolean backup)
    }
    
    class PayrollGenerator {
        -static String DEFAULT_EMPLOYEE_FILE
        -static String DEFAULT_PAYROLL_FILE
        -static String DEFAULT_TIME_CARD_FILE
        -PayrollGenerator()
        +static void main(String[] args)
    }
    
    class Arguments {
        -String employeeFile
        -String payrollFile
        -String timeCards
    		+Arguments()
        +String getEmployeeFile()
        +String getPayrollFile()
        +String getTimeCards()
        +void printHelp()
        +static Arguments process(String[] args)
    }

    IEmployee <|-- HourlyEmployee
    IEmployee <|-- SalaryEmployee
    IPayStub <|-- PayStub
    ITimeCard <|-- TimeCard
    
    Builder --> HourlyEmployee : "creates"
		Builder --> SalaryEmployee : "creates"
		Builder --> TimeCard : "creates"

    PayrollGenerator --> Builder : "uses"
    PayrollGenerator --> Arguments : "uses"
```



## (FINAL DESIGN): Reflection/Retrospective

> [!IMPORTANT]
> The value of reflective writing has been highly researched and documented within computer science, from learning new information to showing higher salaries in the workplace. For this next part, we encourage you to take time, and truly focus on your retrospective.

Take time to reflect on how your design has changed. Write in *prose* (i.e. do not bullet point your answers - it matters in how our brain processes the information). Make sure to include what were some major changes, and why you made them. What did you learn from this process? What would you do differently next time? What was the most challenging part of this process? For most students, it will be a paragraph or two. 

>During the project, my design changed a lot as I worked through the implementation. 
>
>At first, I focused on just creating employees and calculating their payroll. But after testing, I saw that some parts of the system needed to be improved. Through this process, I learned that designing a program takes many steps, and the first idea is not always the best. Writing tests first helped me find problems early, especially with tax deductions and pretax benefits. If I could do this again, I would spend more time planning how to handle file input and data validation, because small mistakes in reading files caused a lot of errors. The hardest part was fixing small floating-point errors in tax calculations, which made test results slightly different from what was expected.
>
>Overall, I realized that good software design needs changes and improvements. No program is perfect at the start, and testing and refactoring make it better. If I had more time, I would add a feature to check salary fairness, helping employers find pay gaps. This project helped me improve my Java skills and taught me more about writing clean code, organizing a program well, and testing properly.

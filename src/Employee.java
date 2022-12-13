public class Employee {


    private int id;
    static int counter;
    private String employeeName;
    private int department;
    private int salary;

    public Employee(String employeeName, int department, int salary) {
        this.employeeName = employeeName;
        this.department = department;
        this.salary = salary;
        id = ++counter;
    }

    public String getEmployeeName() {

        return this.employeeName;
    }

    public int getDepartment() {
        return this.department;
    }

    public int getSalary() {
        return this.salary;
    }

    public int getId() {
        return this.id;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public void setDepartment(int department) {
        this.department = department;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Id: " + id + " ФИО: " + employeeName + " Отдел: " + department + " Зарплата: " + salary;
    }

}

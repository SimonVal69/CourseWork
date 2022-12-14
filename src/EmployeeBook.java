public class EmployeeBook {

    private final Employee[] employee;

    public EmployeeBook(int employeeNumbers) {
        employee = new Employee[employeeNumbers];
        generateEmployees(employeeNumbers);
    }

    public void generateEmployees(int employeeNumbers) {
        for (int i = 0; i < employeeNumbers; i++) {
            employee[i] = new Employee(EmployeeGenerator.generateNameEmployee(), EmployeeGenerator.generateDepartment(), EmployeeGenerator.generateSalary());
        }
    }

    public StringBuilder printEmployees(int dept) {
        StringBuilder employees = new StringBuilder();
        if (dept == 0) {
            for (Employee value : employee) {
                employees.append(value).append("\n");
            }
        } else {
            for (Employee value : employee) {
                if (value.getDepartment() == dept) {
                    employees.append("Id: ").append(value.getId()).append(" ФИО: ").append(value.getEmployeeName()).append(" Зарплата: ").append(value.getSalary()).append("\n");
                }
            }
        }
        return employees;
    }

    public int totalSalaryPerMonth(int dept) {
        int sum = 0;
        if (dept == 0) {
            for (Employee value : employee) {
                sum += value.getSalary();
            }
        } else {
            for (Employee value : employee) {
                if (value.getDepartment() == dept) {
                    sum += value.getSalary();
                }
            }
        }
        return sum;
    }

    public String findMinSalary(int dept) {
        int min = Integer.MAX_VALUE;
        int index = 0;
        if (dept == 0) {
            for (int i = 0; i < employee.length; i++) {
                if (employee[i].getSalary() < min) {
                    min = employee[i].getSalary();
                    index = i;
                }
            }
        } else {
            for (int i = 0; i < employee.length; i++) {
                if (employee[i].getDepartment() == dept) {
                    if (employee[i].getSalary() < min) {
                        min = employee[i].getSalary();
                        index = i;
                    }
                }
            }
        }
        return employee[index].getEmployeeName() + " - " + min;
    }

    public String findMaxSalary(int dept) {
        int max = 0;
        int index = 0;
        if (dept == 0) {
            for (int i = 0; i < employee.length; i++) {
                if (employee[i].getSalary() > max) {
                    max = employee[i].getSalary();
                    index = i;
                }
            }
        } else {
            for (int i = 0; i < employee.length; i++) {
                if (employee[i].getDepartment() == dept) {
                    if (employee[i].getSalary() > max) {
                        max = employee[i].getSalary();
                        index = i;
                    }
                }
            }
        }
        return employee[index].getEmployeeName() + " - " + max;
    }

    public StringBuilder printEmployeesNames() {
        StringBuilder names = new StringBuilder();
        for (Employee value : employee) {
            names.append(value.getEmployeeName()).append("\n");
        }
        return names;
    }

    public void indexSalary(int index, int dept) {
        if (dept == 0) {
            for (Employee value : employee) {
                value.setSalary(value.getSalary() + value.getSalary() * index / 100);
            }
        } else {
            for (Employee value : employee) {
                if (value.getDepartment() == dept) {
                    value.setSalary(value.getSalary() + value.getSalary() * index / 100);
                }
            }
        }
    }

    public StringBuilder analyzeSalary(int sum) {
        StringBuilder salariesLess = new StringBuilder();
        StringBuilder salariesMore = new StringBuilder();
        for (Employee value : employee) {
            if (value.getSalary() < sum) {
                salariesLess.append("Id: ").append(value.getId()).append(" ФИО: ").append(value.getEmployeeName()).append(" Зарплата: ").append(value.getSalary()).append("\n");
            }
            if (value.getSalary() >= sum) {
                salariesMore.append("Id: ").append(value.getId()).append(" ФИО: ").append(value.getEmployeeName()).append(" Зарплата: ").append(value.getSalary()).append("\n");
            }
        }
        return salariesLess.append("+").append(salariesMore);
    }

    public String dismissEmployee(int id) {
        String name = "";
        for (Employee value : employee) {
            if (value.getId() == id) {
                name = value.getEmployeeName();
                value.setId(0);
                value.setEmployeeName("");
                value.setDepartment(0);
                value.setSalary(0);
                break;
            }
        }
        return name;
    }

    public String addNewEmployee() {
        String name = "";
        for (int i = 0; i < employee.length; i++) {
            if (employee[i].getId() == 0) {
                employee[i] = new Employee(EmployeeGenerator.generateNameEmployee(), EmployeeGenerator.generateDepartment(), EmployeeGenerator.generateSalary());
                name = employee[i] + "\n";
                break;
            }
        }
        return name;
    }

    public String changeEmployeeParameters(String employeeName, int department, int salary) {
        String name = "";
        for (Employee value : employee) {
            if (employeeName.equals(value.getEmployeeName())) {
                value.setDepartment(department);
                value.setSalary(salary);
                name = value + "\n";
                break;
            }
        }
        return name;
    }

    public int countEmployeesInDepartment(int dept) {
        int countEmp = 0;
        for (Employee value : employee) {
            if (value.getDepartment() == dept) {
                countEmp++;
            }
        }
        return countEmp;
    }

}

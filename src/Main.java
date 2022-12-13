import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    static Scanner in = new Scanner(System.in);
    static int employeeNumbers = 30;       //Количество сотрудников

    public static void main(String[] args) {
        int dept = 0;                   //Переменная, отвечающая за признак: 0 - все отделы, 1 - выбранный отдел
        EmployeeBook employeeBook = new EmployeeBook(employeeNumbers);
        System.out.println("\nСписок всех сотрудников:");
        System.out.println(employeeBook.printEmployees(dept));      //Вывод в консоль списка всех сотрудников
        System.out.println("\nСумма затрат на зарплаты в месяц: " + employeeBook.totalSalaryPerMonth(dept));  //Вывод в консоль общих затрат на зарплату
        System.out.println("\nСотрудник с минимальной зарплатой: " + employeeBook.findMinSalary(dept));     //Вывод в консоль сотрудника с наименьшей зарплатой
        System.out.println("\nСотрудник с максимальной зарплатой: " + employeeBook.findMaxSalary(dept));    //Вывод в консоль сотрудника с наибольшей зарплатой
        System.out.println("\nСреднее значение зарплат: " + employeeBook.totalSalaryPerMonth(dept) / (float) employeeNumbers);  //Вывод в консоль значения средней зарплаты
        System.out.println("\nФИО всех сотрудников:\n" + employeeBook.printEmployeesNames());       //Вывод в консоль списка всех ФИО сотрудников
        indexSalary(employeeBook, dept);        //Индексация зарплаты у всех сотрудников
        actionsWithDepartment(employeeBook);    //Некоторые действия с сотрудниками отдела
        analyzeSalary(employeeBook);            //Сравнение зарплат сотрудников с заданной цифрой
        dismissEmployee(employeeBook);          //Увольнение сотрудника
        addNewEmployee(employeeBook);           //Приём нового сотрудника
        changeEmployee(employeeBook);           //Изменение параметров сотрудника
        printFilteredInfo(employeeBook);        //Вывод в консоль списка всех сотрудников упорядоченных по отделам
        in.close();
    }

    public static void indexSalary(EmployeeBook employeeBook, int dept) {
        String input;
        int indexValue;
        do {
            System.out.print("\nВведите процент, на который будет изменена зарплата у сотрудников (любое целое число в диапазоне 0..999): ");
            input = in.nextLine();
        } while (checkInput(input, 1));     //Проверка на правильность ввода
        indexValue = Integer.parseInt(input);
        employeeBook.indexSalary(indexValue, dept);
        System.out.println("\nСписок сотрудников:");
        System.out.println(employeeBook.printEmployees(dept));
    }

    public static void actionsWithDepartment(EmployeeBook employeeBook) {
        String input;
        do {
            System.out.print("\nВведите номер отдела (1..5): ");
            input = in.nextLine();
        } while (checkInput(input, 2));     //Проверка на правильность ввода
        int dept = Integer.parseInt(input);
        System.out.println("\nСотрудник с минимальной зарплатой в отделе №" + dept + ": " + employeeBook.findMinSalary(dept));
        System.out.println("\nСотрудник с максимальной зарплатой в отделе №" + dept + ": " + employeeBook.findMaxSalary(dept));
        System.out.println("\nСумма затрат на зарплату по отделу №" + dept + ": " + employeeBook.totalSalaryPerMonth(dept));
        System.out.println("\nСредняя зарплата по отделу №" + dept + ": " + employeeBook.totalSalaryPerMonth(dept) / (float) employeeBook.countEmployeesInDepartment(dept));
        indexSalary(employeeBook, dept);
    }

    public static void analyzeSalary(EmployeeBook employeeBook) {
        StringBuilder salariesLessAndMore;
        String input;
        int index;
        do {
            System.out.print("\nВведите сумму для сравнения зарплат (любое целое число в диапазоне 0..999999): ");
            input = in.nextLine();
        } while (checkInput(input, 3));     //Проверка на правильность ввода
        int sum = Integer.parseInt(input);
        salariesLessAndMore = employeeBook.analyzeSalary(sum);
        index = salariesLessAndMore.indexOf("+");
        if (index != 0) {
            System.out.println("\nВсе сотрудники с зарплатой меньше " + sum + " :");
            System.out.println(salariesLessAndMore.substring(0, index - 1));
            System.out.println("\nВсе сотрудники с зарплатой больше или равно " + sum + " :");
            System.out.println(salariesLessAndMore.substring(index + 1));
        } else {
            System.out.println("\nНет сотрудников с зарплатой меньше " + sum);
            System.out.println("\nВсе сотрудники с зарплатой больше или равно " + sum + " :");
            System.out.println(salariesLessAndMore.substring(1));
        }
    }

    public static void dismissEmployee(EmployeeBook employeeBook) {
        String input;
        do {
            System.out.print("\nДля увольнения сотрудника введите его id: ");
            input = in.nextLine();
        } while (checkInput(input, 4));     //Проверка на правильность ввода
        int id = Integer.parseInt(input);
        System.out.println("\nСотрудник " + employeeBook.dismissEmployee(id) + " был уволен");
        employeeNumbers--;
    }

    public static void addNewEmployee(EmployeeBook employeeBook) {
        System.out.println("\nБыл принят новый сотрудник: " + employeeBook.addNewEmployee());
        employeeNumbers++;
    }

    public static void changeEmployee(EmployeeBook employeeBook) {
        String input;
        do {
            System.out.print("\nВведите полное ФИО сотрудника в формате 'Фамилия Имя Отчество': ");
            input = in.nextLine();
        } while (checkInput(input, 5));     //Проверка на правильность ввода
        String name = input;
        do {
            System.out.print("\nВведите новый номер отдела: ");
            input = in.nextLine();
        } while (checkInput(input, 2));     //Проверка на правильность ввода
        int dept = Integer.parseInt(input);
        do {
            System.out.print("\nВведите новую зарплату для сотрудника: ");
            input = in.nextLine();
        } while (checkInput(input, 3));     //Проверка на правильность ввода
        int sal = Integer.parseInt(input);
        System.out.println("\nДанные сотрудника изменены: " + employeeBook.changeEmployeeParameters(name, dept, sal));
    }

    public static void printFilteredInfo(EmployeeBook employeeBook) {
        System.out.println("\nСписок всех сотрудников с разделением по отделам.");
        for (int i = 1; i <= 5; i++) {                                  //Количество отделов по умолчанию равно 5
            System.out.println("\nСотрудники отдела №" + i + ":");
            System.out.println(employeeBook.printEmployees(i));
        }
    }

    public static boolean checkInput(String input, int choiceOfCase) {      //Проверка на правильность ввода
        String regex = "";
        switch (choiceOfCase) {
            case 1 -> regex = "^(0|[1-9][0-9]{0,2})$";                      //Проверка процента индексации зарплаты
            case 2 -> regex = "^[1-5]";                                     //Проверка номера отдела
            case 3 -> regex = "^(0|[1-9][0-9]{0,5})$";                      //Проверка суммы зарплаты
            case 4 -> regex = "^([1-9][0-9]?)$";                            //Проверка id
            case 5 -> regex = "^([А-ЯЁ][а-яА-ЯёЁ]{2,}\\s[А-Я][а-яА-ЯёЁ]{2,}\\s[А-Я][а-яА-ЯёЁ]{2,})";    //Проверка ФИО
        }
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        if (!matcher.matches()) {
            System.out.println("Неправильный ввод, попробуйте снова!");
        }
        return !matcher.matches();
    }
}


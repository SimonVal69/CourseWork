import java.util.Random;

class EmployeeGenerator {

    final private static String[] maleNames = {"Иван", "Пётр", "Сергей", "Дмитрий", "Олег"};
    final private static String[] femaleNames = {"Ирина", "Ольга", "Мария", "Елена", "Татьяна"};
    final private static String[] maleSurnames = {"Иванович", "Петрович", "Сергеевич", "Дмитриевич", "Олегович"};
    final private static String[] femaleSurnames = {"Ивановна", "Петровна", "Сергеевна", "Дмитриевна", "Олеговна"};
    final private static String[] maleFamily = {"Иванов", "Петров", "Сергеев", "Дмитриев", "Сидоров"};
    final private static String[] femaleFamily = {"Иванова", "Петрова", "Сергеева", "Дмитриева", "Сидорова"};

    private EmployeeGenerator() {
    }

    public static String generateNameEmployee() {
        Random random = new Random();
        StringBuilder name = new StringBuilder();
        if (random.nextInt(2) == 0) {
            name.append(maleFamily[random.nextInt(5)]).append(" ");
            name.append(maleNames[random.nextInt(5)]).append(" ");
            name.append(maleSurnames[random.nextInt(5)]);
        } else {
            name.append(femaleFamily[random.nextInt(5)]).append(" ");
            name.append(femaleNames[random.nextInt(5)]).append(" ");
            name.append(femaleSurnames[random.nextInt(5)]);
        }
        return String.valueOf(name);
    }

    public static int generateDepartment() {
        Random random = new Random();
        return random.nextInt(1, 6);
    }

    public static int generateSalary() {
        Random random = new Random();
        return random.nextInt(50_000, 150_001);
    }

}

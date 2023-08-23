
import java.util.Scanner;


public class Main {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        while (true){
            System.out.println(calculate(scanner.nextLine()));
        }

    }


    static String calculate(String inputString) throws Exception {

        String[] sArray = inputString.split("\"*\"\\s*([-+*/])");
        if (sArray.length != 2) throw new Exception();
        String operand1 = sArray[0].trim() + "\"";
        String operand2 = sArray[1].trim();
        char operator = inputString.charAt(inputString.indexOf(sArray[1]) - 1);

        if (!operand1.startsWith("\"") || !operand1.endsWith("\"")) throw new Exception();
        String result;

        switch (operator) {
            case '+':

                result = "\"" + removeKavichki(operand1) + removeKavichki(operand2) + "\"";
                break;
            case '-':
                result = operand1.replace(removeKavichki(operand2), "");
                break;
            case '*':
                int repeatCount = parseNumber(operand2);
                result = repeatString(operand1, repeatCount);
                break;
            case '/':
                int divideFactor = parseNumber(operand2);
                result = divideString(operand1, divideFactor);
                break;
            default:
                throw new IllegalArgumentException("Неподдерживаемая операция: " + operator);
        }

        if (result.length() > 40) {
            result = result.substring(0, 40) + "...";
        }

        return result;
    }

    private static int parseNumber(String operand) {
        try {
            return Integer.parseInt(operand);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Некорректное число: " + operand);
        }


    }

    private static String repeatString(String str, int count) {
        if (count <= 0) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < count; i++) {
            sb.append(removeKavichki(str));
        }

        return "\"" + sb + "\"";
    }

    private static String divideString(String str, int factor) {
        if (factor <= 0) {
            throw new IllegalArgumentException("Делитель должен быть положительным числом");
        }

        int length = str.length();
        int newLength = length / factor;

        return str.substring(0, newLength) + "\"";
    }

    private static String removeKavichki(String input){
        return input.replaceAll("\"","");
    }


}

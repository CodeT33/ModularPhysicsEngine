import java.util.Scanner;

public class InputManager {


    public String getConsoleInput() {
        Scanner inputScanner = new Scanner(System.in);
        System.out.println("Enter DPL-code: ");
        StringBuilder codeBuilder = new StringBuilder();
        while (true) {
            String line = inputScanner.nextLine();
            codeBuilder.append(line);
            String trimmed = codeBuilder.toString().replaceAll("\\s+$", "");
            if (!trimmed.isEmpty() && trimmed.charAt(trimmed.length() - 1) == ';') {
                break;
            }
            codeBuilder.append(System.lineSeparator());
        }
        String code = codeBuilder.toString().replaceAll("\\r?\\n", " ");
        //System.out.println(code);
        return code;
    }


}


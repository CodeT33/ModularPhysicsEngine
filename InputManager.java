import java.util.Scanner;

public class InputManager {

    public String getConsoleInput() {
        Scanner inputScanner = new Scanner(System.in);
        System.out.println("Enter DPL-code: ");
        String code = inputScanner.nextLine();
        return code;
    }


}

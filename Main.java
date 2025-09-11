import java.util.*;

public class Main {

    public static void main(String[] args) {

        InputManager inputManager = new InputManager();
        String code = inputManager.getConsoleInput();

        Tokenizer tokenizer = new Tokenizer(code);
        var tokens = tokenizer.tokenize();

        Parser parser = new Parser(tokens);

        Engine engine = new Engine();
        Executor executor = new Executor(engine);

        while (true) {
            Object cmd = parser.parseNextCommand();
            if (cmd instanceof VariableCall) {
                executor.execute((VariableCall) cmd);
            } else if (cmd instanceof ModuleCall) {
                executor.execute((ModuleCall) cmd);
            } else if (cmd instanceof UnitConvertingCall) {
                executor.execute((UnitConvertingCall) cmd);
            } else if (cmd instanceof OutputCommand) {
                executor.execute((OutputCommand) cmd);
                break;
            }
        }
    }
}









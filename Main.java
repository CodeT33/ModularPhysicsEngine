import java.util.*;

public class Main {

    public static void main(String[] args) {

        InputManager inputManager = new InputManager();
        String code = inputManager.getConsoleInput();

        Tokenizer tokenizer = new Tokenizer(code);
        var tokens = tokenizer.tokenize();

        Parser parser = new Parser(tokens);

        //List<ModuleCall> calls = parser.parseModuleCalls();
        //OutputCommand out = parser.parseOutputCommand();

        Engine engine = new Engine();
        Executor executor = new Executor(engine);

        while (true) {
            Object cmd = parser.parseNextCommand();
            if (cmd instanceof VariableCall) {
                executor.execute((VariableCall) cmd);
            } else if (cmd instanceof ModuleCall) {
                executor.execute((ModuleCall) cmd);
            } else if (cmd instanceof OutputCommand) {
                executor.execute((OutputCommand) cmd);
                break;
            }
        }


        /*
        for (ModuleCall call : calls) {
            //System.out.println(calls);
            executor.execute(call);
        }

        executor.execute(out);
        //System.out.println(out);
        //executor.printAllVariables();
*/

    }
}









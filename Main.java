import java.util.*;

public class Main {

    public static void main(String[] args) {

        String code = "";
        InputManager inputManager = new InputManager();
        code = inputManager.getConsoleInput();

        Tokenizer tokenizer = new Tokenizer(code);
        var tokens = tokenizer.tokenize();

        Parser parser = new Parser(tokens);
        List<ModuleCall> calls = parser.parseModuleCalls();
        OutputCommand out = parser.parseOutputCommand();

        Engine engine = new Engine();
        Executor executor = new Executor(engine);

        for (ModuleCall call : calls) {
            //System.out.println(calls);
            executor.execute(call);
        }

        executor.execute(out);
        //System.out.println(out);
        //executor.printAllVariables();


    }
}









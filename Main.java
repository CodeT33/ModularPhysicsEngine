import java.util.*;

public class Main {

    public static void main(String[] args) {

        /*
        Engine engine = new Engine();

        PM_OhmsLaw ohm = new PM_OhmsLaw();
        ohm.inputs.put("current", 50.0);
        ohm.inputs.put("resistance", 80.0);

        engine.addModule(ohm);

        PM_PowerLaw power = new PM_PowerLaw();
        power.inputs.put("current", 130.0);
        power.inputs.put("voltage", ohm.outputs.get("voltage"));

        engine.addModule(power);

        engine.execute();

        engine.displayOutputs();


        String code = """
            USE OhmsLaw WITH current = 50, resistance = 80 OUT output_voltage
            THEN OUTPUT output_voltage VIA Display;
            """;

        Tokenizer tokenizer = new Tokenizer(code);
        var tokens = tokenizer.tokenize();

        Parser parser = new Parser(tokens);
        ModuleCall call = parser.parseModuleCall();
        OutputCommand out = parser.parseOutputCommand();

        System.out.println(call);
        System.out.println(out);

*/
                String code = """
            USE OhmsLaw WITH current = 50, resistance = 80 OUT output_voltage
            THEN OUTPUT output_voltage VIA Display;
            """;

                Tokenizer tokenizer = new Tokenizer(code);
                var tokens = tokenizer.tokenize();

                Parser parser = new Parser(tokens);
                ModuleCall call = parser.parseModuleCall();
                OutputCommand out = parser.parseOutputCommand();

                Engine engine = new Engine();
                Executor executor = new Executor(engine);

                executor.execute(call);
                executor.execute(out);
            }
        }








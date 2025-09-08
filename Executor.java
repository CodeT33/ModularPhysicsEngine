import java.util.*;

class Executor {
    private final Map<String, Double> variables = new HashMap<>();
    private final Engine engine;

    public Executor(Engine engine) {
        this.engine = engine;
    }

    public void execute(ModuleCall call) {
        PM_Blueprint module = createModule(call.moduleName);

        for (var entry : call.inputs.entrySet()) {
            module.inputs.put(entry.getKey(), entry.getValue());
        }

        engine.addModule(module);
        engine.execute();

        for (String out : call.outputs) {
            if (module.outputs.containsKey(out)) {
                variables.put(out, module.outputs.get(out));
            } else {
                throw new RuntimeException("Output " + out + " not found in module " + call.moduleName);
            }
        }
    }

    public void execute(OutputCommand cmd) {

        Map<String, Double> displayMap = new HashMap<>();

        for (String out : cmd.outputs) {
            displayMap.put(out, variables.get(out));
        }

        PM_Display display = new PM_Display(displayMap);
        engine.addModule(display);
        engine.execute();
    }

    private PM_Blueprint createModule(String moduleName) {
        switch (moduleName) {
            case "OhmsLaw": return new PM_OhmsLaw();
            case "PowerLaw": return new PM_PowerLaw();
            default: throw new RuntimeException("Unknown module: " + moduleName);
        }
    }
}
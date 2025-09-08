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
            Object value = entry.getValue();
            Double resolvedValue;
            if (value instanceof String) {
                String varName = (String) value;
                resolvedValue = variables.get(varName);
                if (resolvedValue == null) {
                    throw new RuntimeException("Variable " + varName + " not found");
                }
            } else if (value instanceof Double) {
                resolvedValue = (Double) value;
            } else {
                throw new RuntimeException("Unsupported type: " + value);
            }
            module.inputs.put(entry.getKey(), resolvedValue);
        }

        engine.addModule(module, call.outputs);
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

        Map<String, Double> displayMap = new LinkedHashMap<>();

        for (String varName : cmd.displayVariables) {
            Double value = variables.get(varName);
            if (value == null) {
                throw new RuntimeException("Variable " + varName + " not found");
            }
            displayMap.put(varName, value);
        }

        PM_Display display = new PM_Display(displayMap);
        engine.addModule(display, cmd.outputs);
        engine.execute();
    }

    private PM_Blueprint createModule(String moduleName) {
        switch (moduleName) {
            case "Round": return new PM_Round();
            case "OhmsLaw": return new PM_OhmsLaw();
            case "PowerLaw": return new PM_PowerLaw();
            case "CapacitanceLaw": return new PM_CapacitanceLaw();
            case "NewtonTwo": return new PM_NewtonTwo();
            case "KineticEnergy": return new PM_KineticEnergy();
            default: throw new RuntimeException("Unknown module: " + moduleName);
        }
    }

    public void printAllVariables() {
        System.out.println("Globale Variablen:");
        for (Map.Entry<String, Double> entry : variables.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

}
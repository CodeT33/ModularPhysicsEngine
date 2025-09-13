import PhysicModules.*;

import java.util.*;

class Executor {
    private final Map<String, Double> variables = new HashMap<>();

    public void initializeConstants() {
        variables.put("MATH_PI", Math.PI);
        variables.put("MATH_E", Math.E);
        variables.put("UNIV_GAS_CONST", 8.314);//in J*K^(-1)*mol^(-1)
        variables.put("STANDARD_GRAVITY", 9.80665);//in m/s^2
        variables.put("SPEED_OF_LIGHT", 299792458.0);//in m/s
        //variables.put("", 0.0);
    }

    private final Engine engine;

    public Executor(Engine engine) {
        this.engine = engine;
    }

    public void execute(UnitConvertingCall call) {
        Double value;
        if (!Objects.equals(call.valueVarName, "empty")) {
            value = variables.get(call.valueVarName);
            if (value == null) {
                throw new RuntimeException("Variable " + call.valueVarName + " not found");
            }
        } else {
            value = call.value;
        }

        Double converted = PM_UnitConverter.convert(value, call.startUnit, call.endUnit);
        variables.put(call.outputVar, converted);
    }

    public void execute(VariableCall call) {
        Double value;
        if (!Objects.equals(call.valueVarName, "empty")) {
            value = variables.get(call.valueVarName);
            if (value == null) {
                throw new RuntimeException("Variable " + call.valueVarName + " not found");
            }
        } else {
            value = call.value;
        }
        variables.put(call.varName, value);
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
        return switch (moduleName) {
            case "Round" -> new PM_Round();
            case "OhmsLaw" -> new PM_OhmsLaw();
            case "PowerLaw" -> new PM_PowerLaw();
            case "PowerLawExt" -> new PM_PowerLawExt();
            case "CapacitanceLaw" -> new PM_CapacitanceLaw();
            case "NewtonTwo" -> new PM_NewtonTwo();
            case "KineticEnergy" -> new PM_KineticEnergy();
            case "WorkLaw" -> new PM_WorkLaw();
            case "MomentumLaw" -> new PM_MomentumLaw();
            case "PressureLaw" -> new PM_PressureLaw();
            case "DensityLaw" -> new PM_DensityLaw();
            case "HookesLaw" -> new PM_HookesLaw();
            case "SpecificHeat" -> new PM_SpecificHeat();
            case "IdealGasLaw" -> new PM_IdealGasLaw();
            case "FreqWavelengthRel" -> new PM_FreqWavelengthRel();
            //Add new PModules here
            default -> throw new RuntimeException("Unknown module: " + moduleName);
        };
    }

    public void printAllVariables() {
        System.out.println("Globale Variablen:");
        for (Map.Entry<String, Double> entry : variables.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

}
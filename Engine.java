import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Engine {

    private List<PM_Blueprint> modules = new ArrayList<>();
    private Map<String, Double> context = new HashMap<>();

    public void addModule(PM_Blueprint module) {
        modules.add(module);
    }

    public void execute() {
        for (PM_Blueprint module : modules) {
            for (String inputName : module.inputs.keySet()) {
                if (module.inputs.get(inputName) == null && context.containsKey(inputName)) {
                    module.inputs.put(inputName, context.get(inputName));
                }
            }
            module.compute();
            context.putAll(module.outputs);
        }
    }

    public void displayOutputs() {
        Map<String, Double> allOutputs = new HashMap<>();
        for (PM_Blueprint module : modules) {
            allOutputs.putAll(module.outputs);
        }

        PM_Display display = new PM_Display();
        display.setContext(allOutputs);
        display.compute();
    }

}

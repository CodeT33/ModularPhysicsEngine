package PhysicModules;

import java.util.*;

public class PM_Display extends PM_Blueprint {

    public PM_Display(Map<String, Double> outputsToShow) {
        this.outputs.putAll(outputsToShow);
    }

    @Override
    public void compute(List<String> outputVariableKeys) {
        System.out.println("\n-----");
        if (outputs.isEmpty()) {
            System.out.println("Nothing to display");
            return;
        }
        for (Map.Entry<String, Double> entry : outputs.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
        System.out.println("-----");
    }
}

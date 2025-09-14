package PhysicModules;

import java.util.List;

public class PM_Divide extends PM_Blueprint {

    @Override
    public void compute(List<String> outputKeys) {

        Double numerator = null;
        Double denominator = null;

        if (inputs.containsKey("numerator")) {
            numerator = inputs.get("numerator");
        } else if (inputs.containsKey("n")) {
            numerator = inputs.get("n");
        } else {
            throw new RuntimeException("No numerator provided (expected 'numerator' or 'n')");
        }

        if (inputs.containsKey("denominator")) {
            denominator = inputs.get("denominator");
        } else if (inputs.containsKey("d")) {
            denominator = inputs.get("d");
        } else {
            throw new RuntimeException("No denominator provided (expected 'd' or 'n')");
        }

        if (denominator == 0) {
            throw new RuntimeException("You are not allowed to divide by " + denominator);
        }

        results = List.of(numerator/denominator);
        output(outputKeys);
    }
}

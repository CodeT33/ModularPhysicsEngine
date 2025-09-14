package PhysicModules;

import java.util.List;

public class PM_Addition extends PM_Blueprint {

    @Override
    public void compute(List<String> outputKeys) {

        Double n = inputs.get("number1");
        Double d = inputs.get("number2");

        results = List.of(n+d);
        output(outputKeys);
    }
}

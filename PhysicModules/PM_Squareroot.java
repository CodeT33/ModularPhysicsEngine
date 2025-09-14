package PhysicModules;

import java.util.List;
import static java.lang.Math.sqrt;

public class PM_Squareroot extends PM_Blueprint {

    @Override
    public void compute(List<String> outputKeys) {

        Double base = inputs.get("base");

        results = List.of(sqrt(base));
        output(outputKeys);
    }
}

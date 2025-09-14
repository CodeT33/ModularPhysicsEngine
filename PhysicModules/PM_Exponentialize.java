package PhysicModules;

import static java.lang.Math.pow;

import java.util.List;

public class PM_Exponentialize extends PM_Blueprint {

    @Override
    public void compute(List<String> outputKeys) {

        Double base = inputs.get("base");
        Double exponent = inputs.get("exponent");

        results = List.of(pow(base, exponent));
        output(outputKeys);
    }
}

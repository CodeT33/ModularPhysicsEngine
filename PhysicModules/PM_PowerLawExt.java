package PhysicModules;

import java.util.List;

import static java.lang.Math.sqrt;
import static java.lang.Math.pow;

public class PM_PowerLawExt extends PM_Blueprint {

    @Override
    public void compute(List<String> outputKeys) {

        Double power = inputs.get("power");
        Double resistance = inputs.get("resistance");
        Double current = inputs.get("current");

        if (power == null && current != null && resistance != null) {
            power = pow(current, 2) * resistance;
            results = List.of(power);

        } else if (power != null && current != null && resistance == null) {
            resistance = power / pow(current, 2);
            results = List.of(resistance);

        } else if (power != null && current == null && resistance != null) {
            current = sqrt(power / resistance);
            results = List.of(current);
        }
        output(outputKeys);
    }
}

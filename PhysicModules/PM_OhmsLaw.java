package PhysicModules;

import java.util.List;

public class PM_OhmsLaw extends PM_Blueprint {

    @Override
    public void compute(List<String> outputKeys) {

        Double voltage = inputs.get("voltage");
        Double resistance = inputs.get("resistance");
        Double current = inputs.get("current");

        if (voltage == null && current != null && resistance != null) {
            voltage = current * resistance;
            results = List.of(voltage);

        } else if (voltage != null && current != null && resistance == null) {
            resistance = voltage / current;
            results = List.of(resistance);

        } else if (voltage != null && current == null && resistance != null) {
            current = voltage / resistance;
            results = List.of(current);
        }
        output(outputKeys);
    }
}

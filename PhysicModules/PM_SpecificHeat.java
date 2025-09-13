package PhysicModules;

import java.util.List;

public class PM_SpecificHeat extends PM_Blueprint {

    @Override
    public void compute(List<String> outputKeys) {

        Double heat = inputs.get("heat");
        Double mass = inputs.get("mass");
        Double temperature = inputs.get("temperature");
        Double heat_capacity = inputs.get("heat_capacity");

        if (heat == null && temperature != null && mass != null && heat_capacity != null) {
            heat = mass * heat_capacity * temperature;
            results = List.of(heat);

        } else if (heat != null && temperature != null && mass == null && heat_capacity != null) {
            mass = heat / (heat_capacity * temperature);
            results = List.of(mass);

        } else if (heat != null && temperature == null && mass != null && heat_capacity != null) {
            temperature = heat / (mass * heat_capacity);
            results = List.of(temperature);

        } else if (heat != null && temperature != null && mass != null && heat_capacity == null) {
            heat_capacity = heat / (mass * temperature);
            results = List.of(heat_capacity);
        }
        output(outputKeys);
    }
}

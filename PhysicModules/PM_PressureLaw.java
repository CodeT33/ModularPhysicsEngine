package PhysicModules;

import java.util.List;

public class PM_PressureLaw extends PM_Blueprint {

    @Override
    public void compute(List<String> outputKeys) {

        Double force = inputs.get("force");
        Double pressure = inputs.get("pressure");
        Double area = inputs.get("area");

        if (force == null && area != null && pressure != null) {
            force = area * pressure;
            results = List.of(force);

        } else if (force != null && area != null && pressure == null) {
            pressure = force / area;
            results = List.of(pressure);

        } else if (force != null && area == null && pressure != null) {
            area = force / pressure;
            results = List.of(area);
        }
        output(outputKeys);
    }
}
// pressure force/ area

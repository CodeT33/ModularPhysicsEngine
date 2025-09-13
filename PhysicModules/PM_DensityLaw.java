package PhysicModules;

import java.util.List;

public class PM_DensityLaw extends PM_Blueprint {

    @Override
    public void compute(List<String> outputKeys) {

        Double density = inputs.get("density");
        Double mass = inputs.get("mass");
        Double volume = inputs.get("volume");

        if (density == null && volume != null && mass != null) {
            density = mass / volume;
            results = List.of(density);

        } else if (density != null && volume != null && mass == null) {
            mass = density * volume;
            results = List.of(mass);

        } else if (density != null && volume == null && mass != null) {
            volume = mass / density;
            results = List.of(volume);
        }
        output(outputKeys);
    }
}

import java.util.List;
import static java.lang.Math.sqrt;
import static java.lang.Math.pow;

public class PM_KineticEnergy extends PM_Blueprint {

    @Override
    public void compute(List<String> outputKeys) {

        Double energy = inputs.get("energy");
        Double mass = inputs.get("mass");
        Double velocity = inputs.get("velocity");

        List<Double> results = List.of();

        if (energy == null && velocity != null && mass != null) {
            energy = 0.5 * mass * pow(velocity, 2);
            results = List.of(energy);

        } else if (energy != null && velocity != null && mass == null) {
            mass = (2.0 * energy) / (pow(velocity, 2));
            results = List.of(mass);

        } else if (energy != null && velocity == null && mass != null) {
            velocity = sqrt((energy * 2.0) / mass);
            results = List.of(velocity);
        }

        for (int i = 0; i < outputKeys.size(); i++) {
            if (i < results.size() && results.get(i) != null) {
                outputs.put(outputKeys.get(i), results.get(i));
            }
        }
    }
}

import java.util.List;

public class PM_NewtonTwo extends PM_Blueprint {

    @Override
    public void compute(List<String> outputKeys) {

        Double force = inputs.get("force");
        Double mass = inputs.get("mass");
        Double acceleration = inputs.get("acceleration");

        if (force == null && mass != null && acceleration != null) {
            force = mass * acceleration;
            results = List.of(force);

        } else if (force != null && mass != null && acceleration == null) {
            acceleration = force / mass;
            results = List.of(acceleration);

        } else if (force != null && mass == null && acceleration != null) {
            mass = force / acceleration;
            results = List.of(mass);
        }
        output(outputKeys);
    }
}

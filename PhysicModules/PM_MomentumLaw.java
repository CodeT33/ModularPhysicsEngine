package PhysicModules;

import java.util.List;

public class PM_MomentumLaw extends PM_Blueprint {


    @Override
    public void compute(List<String> outputKeys) {

        Double mass = inputs.get("mass");
        Double velocity = inputs.get("velocity");
        Double force = inputs.get("force");

        if (mass == null && force != null && velocity != null) {
            mass = force * velocity;
            results = List.of(mass);

        } else if (mass != null && force != null && velocity == null) {
            velocity = mass / force;
            results = List.of(velocity);

        } else if (mass != null && force == null && velocity != null) {
            force = mass / velocity;
            results = List.of(force);
        }
        output(outputKeys);
    }
}

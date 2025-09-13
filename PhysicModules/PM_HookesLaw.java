package PhysicModules;

import java.util.List;

public class PM_HookesLaw extends PM_Blueprint {

    @Override
    public void compute(List<String> outputKeys) {

        Double force = inputs.get("force");
        Double spring_constant = inputs.get("spring_constant");
        Double length = inputs.get("length");

        if (force == null && length != null && spring_constant != null) {
            force = spring_constant * length;
            results = List.of(force);

        } else if (force != null && length != null && spring_constant == null) {
            spring_constant = force / length;
            results = List.of(spring_constant);

        } else if (force != null && length == null && spring_constant != null) {
            length = force / spring_constant;
            results = List.of(length);
        }
        output(outputKeys);
    }
}

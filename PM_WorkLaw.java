import java.util.List;

public class PM_WorkLaw extends PM_Blueprint {

    @Override
    public void compute(List<String> outputKeys) {

        Double force = inputs.get("force");
        Double length = inputs.get("length");
        Double energy = inputs.get("energy");

        if (energy == null && length != null && force != null) {
            energy = length * force;
            results = List.of(energy);

        } else if (energy != null && length != null && force == null) {
            force = energy / length;
            results = List.of(force);

        } else if (energy != null && length == null && force != null) {
            length = energy / force;
            results = List.of(length);
        }
        output(outputKeys);
    }
}


import java.util.List;

public class PM_OhmsLaw extends PM_Blueprint {

    @Override
    public void compute(List<String> outputVariableKeys) {

        Double V = inputs.get("voltage");
        Double R = inputs.get("resistance");
        Double I = inputs.get("current");

        if (V == null && I != null && R != null) {
            V = I * R;

        } else if (V != null && I != null && R == null) {
            R = V / I;

        } else if (V != null && I == null && R != null) {
            I = V / R;

        }

        List<Double> results = List.of(V, R, I);

        for (int i = 0; i < outputVariableKeys.size(); i++) {
            if (i < results.size() && results.get(i) != null) {
                outputs.put(outputVariableKeys.get(i), results.get(i));
            }
        }
    }
}

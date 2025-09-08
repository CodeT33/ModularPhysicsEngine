import java.util.List;

public class PM_PowerLaw extends PM_Blueprint {

    @Override
    public void compute(List<String> outputVariableKeys) {

        Double V = inputs.get("voltage");
        Double P = inputs.get("power");
        Double I = inputs.get("current");

        if (V == null && I != null && P != null) {
            V = P / I;

        } else if (V != null && I != null && P == null) {
            P = V * I;

        } else if (V != null && I == null && P != null) {
            I = P / V;

        }

        List<Double> results = List.of(V, P, I);

        for (int i = 0; i < outputVariableKeys.size(); i++) {
            if (i < results.size() && results.get(i) != null) {
                outputs.put(outputVariableKeys.get(i), results.get(i));
            }
        }
    }
}

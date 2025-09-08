import java.util.List;

public class PM_OhmsLaw extends PM_Blueprint {

    @Override
    public void compute(List<String> outputKeys) {

        Double V = inputs.get("voltage");
        Double R = inputs.get("resistance");
        Double I = inputs.get("current");

        List<Double> results = List.of();

        if (V == null && I != null && R != null) {
            V = I * R;
            results = List.of(V, V, R, I);

        } else if (V != null && I != null && R == null) {
            R = V / I;
            results = List.of(R, V, R, I);

        } else if (V != null && I == null && R != null) {
            I = V / R;
            results = List.of(I, V, R, I);

        } 

        for (int i = 0; i < outputKeys.size(); i++) {
            if (i < results.size() && results.get(i) != null) {
                outputs.put(outputKeys.get(i), results.get(i));
            }
        }
    }
}

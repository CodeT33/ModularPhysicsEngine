import java.util.*;

abstract public class PM_Blueprint {

    protected Map<String, Double> inputs = new HashMap<>();
    protected Map<String, Double> outputs = new HashMap<>();

    List<Double> results = List.of();

    public abstract void compute(List<String> outputKeys);

    public void output(List<String> outputKeys) {
        for (int i = 0; i < outputKeys.size(); i++) {
            if (i < results.size() && results.get(i) != null) {
                outputs.put(outputKeys.get(i), results.get(i));
            }
        }
    }

}


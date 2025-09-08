import java.util.*;

abstract public class PM_Blueprint {

    protected Map<String, Double> inputs = new HashMap<>();
    protected Map<String, Double> outputs = new HashMap<>();

    public void setInputs(String name, double value) {
        inputs.put(name, value);
    }

    public double getOutputs(String name) {
        return outputs.get(name);
    }

    public abstract void compute(List<String> outputVariableKeys);
}


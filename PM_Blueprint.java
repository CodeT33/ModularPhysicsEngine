import java.util.*;

abstract public class PM_Blueprint {

    protected Map<String, Double> inputs = new HashMap<>();
    protected Map<String, Double> outputs = new HashMap<>();

    public abstract void compute(List<String> outputKeys);
}


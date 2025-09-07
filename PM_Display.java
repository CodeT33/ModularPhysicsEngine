import java.util.Map;

public class PM_Display extends PM_Blueprint {

    private Map<String, Double> context;

    public void setContext(Map<String, Double> context) {
        this.context = context;
    }

    @Override
    public void compute() {
        if (context == null || context.isEmpty()) {
            System.out.println("Nothing to display.");
            return;
        }
        for (Map.Entry<String, Double> entry : context.entrySet()) {
            System.out.println(entry.getKey() + " = " + entry.getValue());
        }
    }
}

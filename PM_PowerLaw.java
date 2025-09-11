import java.util.List;

public class PM_PowerLaw extends PM_Blueprint {

    @Override
    public void compute(List<String> outputKeys) {

        Double voltage = inputs.get("voltage");
        Double power = inputs.get("power");
        Double current = inputs.get("current");

        if (voltage == null && current != null && power != null) {
            voltage = power / current;
            results = List.of(voltage);

        } else if (voltage != null && current != null && power == null) {
            power = voltage * current;
            results = List.of(power);

        } else if (voltage != null && current == null && power != null) {
            current = power / voltage;
            results = List.of(current);
        }
        output(outputKeys);
    }
}

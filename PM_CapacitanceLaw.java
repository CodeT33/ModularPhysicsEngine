import java.util.List;

public class PM_CapacitanceLaw extends PM_Blueprint {

    @Override
    public void compute(List<String> outputKeys) {

        Double charge = inputs.get("charge");
        Double capacitance = inputs.get("capacitance");
        Double voltage = inputs.get("voltage");

        List<Double> results = List.of();

        if (charge == null && capacitance != null && voltage != null) {
            charge = capacitance * voltage;
            results = List.of(charge);

        } else if (charge != null && capacitance != null && voltage == null) {
            voltage = charge / capacitance;
            results = List.of(voltage);

        } else if (charge != null && capacitance == null && voltage != null) {
            capacitance = charge / voltage;
            results = List.of(capacitance);
        }

        for (int i = 0; i < outputKeys.size(); i++) {
            if (i < results.size() && results.get(i) != null) {
                outputs.put(outputKeys.get(i), results.get(i));
            }
        }
    }
}

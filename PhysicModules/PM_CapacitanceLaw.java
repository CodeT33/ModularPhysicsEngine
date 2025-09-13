package PhysicModules;

import java.util.List;

public class PM_CapacitanceLaw extends PM_Blueprint {

    @Override
    public void compute(List<String> outputKeys) {

        Double charge = inputs.get("charge");
        Double capacitance = inputs.get("capacitance");
        Double voltage = inputs.get("voltage");

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
        output(outputKeys);
    }
}

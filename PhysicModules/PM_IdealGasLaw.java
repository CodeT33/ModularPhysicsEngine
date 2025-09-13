package PhysicModules;

import java.util.List;

public class PM_IdealGasLaw extends PM_Blueprint {

    @Override
    public void compute(List<String> outputKeys) {

        Double pressure = inputs.get("pressure");
        Double volume = inputs.get("volume");
        Double amount_of_substance = inputs.get("amount_of_substance");
        Double gas_constant = inputs.get("gas_constant");
        Double temperature = inputs.get("temperature");

        if (pressure == null && amount_of_substance != null && volume != null && gas_constant != null && temperature != null) {
            pressure = (amount_of_substance * gas_constant * temperature) / volume;
            results = List.of(pressure);

        } else if (pressure != null && amount_of_substance != null && volume == null && gas_constant != null && temperature != null) {
            volume = (amount_of_substance * gas_constant * temperature) / pressure;
            results = List.of(volume);

        } else if (pressure != null && amount_of_substance == null && volume != null && gas_constant != null && temperature != null) {
            amount_of_substance = (pressure * volume) / (gas_constant * temperature);
            results = List.of(amount_of_substance);

        } else if (pressure != null && amount_of_substance != null && volume != null && gas_constant == null && temperature != null) {
            gas_constant = (pressure * volume) / (amount_of_substance * temperature);
            results = List.of(gas_constant);

        } else if (pressure != null && amount_of_substance != null && volume != null && gas_constant != null && temperature == null) {
            temperature = (pressure * volume) / (amount_of_substance * gas_constant);
            results = List.of(temperature);
        }
        output(outputKeys);
    }
}

package PhysicModules;

import java.util.List;

public class PM_FreqWavelengthRel extends PM_Blueprint {

    @Override
    public void compute(List<String> outputKeys) {

        Double wavelength = inputs.get("wavelength");
        Double speed_of_light = inputs.get("speed_of_light");
        Double frequency = inputs.get("frequency");

        if (wavelength == null && frequency != null && speed_of_light != null) {
            wavelength = speed_of_light / frequency;
            results = List.of(wavelength);

        } else if (wavelength != null && frequency != null && speed_of_light == null) {
            speed_of_light = wavelength * frequency;
            results = List.of(speed_of_light);

        } else if (wavelength != null && frequency == null && speed_of_light != null) {
            frequency = speed_of_light / wavelength;
            results = List.of(frequency);
        }
        output(outputKeys);
    }
}

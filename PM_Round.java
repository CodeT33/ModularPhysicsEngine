import java.util.List;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class PM_Round extends PM_Blueprint {

    public static Double round(Double number, Double decs) {
        int dec = (decs != null) ? decs.intValue() : 2;
        if (dec < 0) throw new RuntimeException("Decimals must be non-negative");
        return BigDecimal.valueOf(number)
                .setScale(dec, RoundingMode.HALF_UP)
                .doubleValue();
    }

    @Override
    public void compute(List<String> outputKeys) {

        Double value = inputs.get("value");
        Double decimals = inputs.get("decimals");

        if (value == null) {
            throw new RuntimeException("Input 'value' is missing");
        }
        if (decimals == null) {
            throw new RuntimeException("Input 'decimals' is missing");
        }

        List<Double> results = List.of();

        results = List.of(round(value, decimals));

        outputs.put(outputKeys.getFirst(), results.getFirst());


    }
}










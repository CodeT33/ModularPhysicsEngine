import java.util.HashMap;

public class Main {

    public static void main(String[] args) {

        Engine engine = new Engine();

        PM_OhmsLaw ohm = new PM_OhmsLaw();
        ohm.inputs.put("voltage", 100.0);
        ohm.inputs.put("resistance", 10.0);

        engine.addModule(ohm);

        PM_PowerLaw power = new PM_PowerLaw();
        power.inputs.put("current", ohm.outputs.get("current"));
        power.inputs.put("voltage", 100.0);

        engine.addModule(power);

        engine.execute();

        engine.displayOutputs();

    }
}

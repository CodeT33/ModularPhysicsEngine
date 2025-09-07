public class PM_OhmsLaw extends PM_Blueprint {

    @Override
    public void compute() {

        Double V = inputs.get("voltage");
        Double R = inputs.get("resistance");
        Double I = inputs.get("current");

        if (V == null && I != null && R != null) {

            V = I * R;
            outputs.put("voltage", V);

        } else if (V != null && I != null && R == null) {

            R = V / I;
            outputs.put("resistance", R);

        } else if (V != null && I == null && R != null) {

            I = V / R;
            outputs.put("current", I);

        }
    }
}

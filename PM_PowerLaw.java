public class PM_PowerLaw extends PM_Blueprint {

    @Override
    public void compute() {

        Double V = inputs.get("voltage");
        Double P = inputs.get("power");
        Double I = inputs.get("current");

        if (V == null && I != null && P != null) {

            V = P / I;
            outputs.put("voltage", V);

        } else if (V != null && I != null && P == null) {

            P = V * I;
            outputs.put("power", P);

        } else if (V != null && I == null && P != null) {

            I = P / V;
            outputs.put("current", I);

        }
    }
}

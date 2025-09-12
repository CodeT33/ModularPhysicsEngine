public class UnitConverter {
    public static double convert(double value, String unit, String endUnit) {


        class ExponentHelper {
            /**
             * This function outputs an exponent in relation to the prefix of SI-meter
             */
            int getExponent(String u) {
                u = u.toLowerCase();
                String prefix;

                if (u.endsWith("meter")) {
                    prefix = u.substring(0, u.length() - "meter".length());

                } else if (u.endsWith("gram")) {
                    prefix = u.substring(0, u.length() - "gram".length());

                } else if (u.endsWith("ampere")) {
                    prefix = u.substring(0, u.length() - "ampere".length());

                } else if (u.endsWith("mole")) {
                    prefix = u.substring(0, u.length() - "mole".length());

                } else if (u.endsWith("candela")) {
                    prefix = u.substring(0, u.length() - "candela".length());

                } else {
                        throw new RuntimeException("Unknown unit: " + unit);
                }

                switch (prefix) {
                    case "quecto":
                    case "qu":
                        return -30;
                    case "ronto":
                    case "r":
                        return -27;
                    case "yocto":
                    case "y":
                        return -24;
                    case "zepto":
                    case "z":
                        return -21;
                    case "atto":
                    case "a":
                        return -18;
                    case "femto":
                    case "f":
                        return -15;
                    case "pico":
                    case "p":
                        return -12;
                    case "nano":
                    case "n":
                        return -9;
                    case "micro":
                    case "mic":
                        return -6;
                    case "milli":
                    case "m":
                        return -3;
                    case "centi":
                    case "c":
                        return -2;
                    case "deci":
                    case "d":
                        return -1;
                    case "":
                        return 0; // plainunit
                    case "deca":
                    case "da":
                        return 1;
                    case "hecto":
                    case "h":
                        return 2;
                    case "kilo":
                    case "k":
                        return 3;
                    case "mega":
                    case "M":
                        return 6;
                    case "giga":
                    case "G":
                        return 9;
                    case "tera":
                    case "T":
                        return 12;
                    case "peta":
                    case "P":
                        return 15;
                    case "exa":
                    case "E":
                        return 18;
                    case "zetta":
                    case "Z":
                        return 21;
                    case "yotta":
                    case "Y":
                        return 24;
                    case "ronna":
                    case "R":
                        return 27;
                    case "quetta":
                        return 30;
                    default:
                        throw new IllegalArgumentException("Unknown unit: " + u);
                }
            }
        }

        if (
                (unit.contains("meter") && endUnit.contains("meter")) ||
                (unit.contains("gram") && endUnit.contains("gram")) ||
                (unit.contains("ampere") && endUnit.contains("ampere")) ||
                (unit.contains("mole") && endUnit.contains("mole")) ||
                (unit.contains("candela") && endUnit.contains("candela"))
        ) {
            ExponentHelper helper = new ExponentHelper();

            int exp1 = helper.getExponent(unit);
            int exp2 = helper.getExponent(endUnit);

            return value * Math.pow(10, exp1 - exp2);
        } else {
            throw new RuntimeException("Unknown unit: " + unit);
        }
    }
}

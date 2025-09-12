public class UnitConverter {

    public static double convert(double value, String unit, String endUnit) {

        class ExponentHelper {

            private String getPrefix(String u) {
                if (u.endsWith("_")) {
                    return u.substring(0, u.length() - "_".length());

                } else if (u.endsWith("meter")) {
                    return u.substring(0, u.length() - "meter".length());

                } else if (u.endsWith("m")) {
                    return u.substring(0, u.length() - "m".length());

                } else if (u.endsWith("gram")) {
                    return u.substring(0, u.length() - "gram".length());

                } else if (u.endsWith("g")) {
                    return u.substring(0, u.length() - "g".length());

                } else if (u.endsWith("ampere")) {
                    return u.substring(0, u.length() - "ampere".length());

                } else if (u.endsWith("mole")) {
                    return u.substring(0, u.length() - "mole".length());

                } else if (u.endsWith("candela")) {
                    return u.substring(0, u.length() - "candela".length());

                } else if (u.endsWith("ohms")) {
                    return u.substring(0, u.length() - "ohms".length());

                } else if (u.endsWith("volts")) {
                    return u.substring(0, u.length() - "volts".length());

                } else if (u.endsWith("feet")) {
                    return u.substring(0, u.length() - "feet".length());

                } else {
                    throw new RuntimeException("Unknown SI unit: " + u);
                }
            }

            private String getSuffix(String u, String prefix) {
                return u.substring(prefix.length());
            }

            private int getExponent(String u) {
                //u = u.toLowerCase();
                String prefix = getPrefix(u);

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

        ExponentHelper helper = new ExponentHelper();

        String prefixUnit = helper.getPrefix(unit);
        String prefixEndUnit = helper.getPrefix(endUnit);
        String suffixUnit = helper.getSuffix(unit, prefixUnit);
        String suffixEndUnit = helper.getSuffix(endUnit, prefixEndUnit);

        int exp1 = helper.getExponent(unit);
        int exp2 = helper.getExponent(endUnit);

        if (suffixUnit.equals(suffixEndUnit)) {

            return value * Math.pow(10, exp1 - exp2);

        } else if (unit.contains("feet") && (endUnit.endsWith("meter")) || endUnit.endsWith("m")) {
            return 0.3048 * value * Math.pow(10, exp2);

        } else if ((unit.contains("meter") || unit.contains("m")) && endUnit.contains("feet")) {
            return 3.2808399 * value * Math.pow(10, exp1);

        } else {
            throw new RuntimeException("\'" + unit + "' in not the same unit as '" + endUnit + "\'");
        }
    }
}


//suffix at the end
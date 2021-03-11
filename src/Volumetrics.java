
public class Volumetrics {
    public static final int BBLS = 7758;  // barrels (42 gallon) per acre-foot
    public static final int MCF = 43560;  // cubic feet of gas per acre-foot

    // Prohibit instantiation
    private Volumetrics() {}

    public static double oilInPlace(double area, int thickness,
                                        double oilSaturation, double porosity,
                                        double volumeFactor) {

        double value = BBLS * area * thickness * oilSaturation * porosity;
        value /= volumeFactor;

        return Math.round(value * 100.0) / 100.0;
    }

    public static double gasInPlace(double area, int thickness,
                                    double gasSaturation, double porosity,
                                    double volumeFactor) {

        double value = MCF * area * thickness * gasSaturation * gasSaturation * porosity;
        value /= volumeFactor;

        return Math.round(value * 100.0) / 100.0;
    }

    // Generally only used to evaluate potential for water disposal, or as an academic exercise
    public static double waterInPlace(double area, int thickness,
                                      double porosity) {

        double value = BBLS * area * thickness * porosity;

        return Math.round(value * 100.0) / 100.0;
    }
}

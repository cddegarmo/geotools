package appclasses;

public class Petrophysics {

    private Petrophysics() {}

    /**
     * Calculates water saturation of a reservoir via Archie's equation.
     * @param n   saturation exponent
     * @param rw  formation water resistivity
     * @param phi porosity (in decimal)
     * @param m   cementation exponent
     * @param rf  formation resistivity
     * @return double water saturation, in range 0 to 1.
     */
    public static double waterSaturation(double n, double rw, double phi,
                                         double m, double rf) {
        double value = rw / (Math.pow(phi, m) * rf);
        if (value < 0)
            throw new IllegalArgumentException("Negative parameters are invalid for this calculation");
        value = Math.pow(value, (1 / n));

        double result = Math.round(value * 100) / 100.0;
        if (result < 0 || result > 1)
            throw new IllegalArgumentException("Check parameters for validity.");
        return result;
    }
}

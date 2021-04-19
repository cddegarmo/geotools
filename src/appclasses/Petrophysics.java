public class Petrophysics {

    private Petrophysics() {}

    /**
     * Calculates water saturation of a reservoir via Archie's equation.
     * @param n   saturation exponent
     * @param rw  formation water resistivity
     * @param phi porosity
     * @param m   cementation exponent
     * @param rf  formation resistivity
     * @return double water saturation, in range 0 to 1.
     */
    public static double waterSaturation(double n, double rw, double phi,
                                         double m, double rf) {
        double value = rw / (Math.pow(phi, m) * rf);
        value = Math.pow(value, (1 / n));

        return Math.round(value * 100) / 100.0;
    }
}

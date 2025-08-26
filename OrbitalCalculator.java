public class OrbitalCalculator {
    public double gravityForce(double mass1, double mass2, double distance) {
        double G = 6.67430e-11; // Gravitational constant
        return G * (mass1 * mass2) / (distance * distance);
    }

    public double escapeVelocity(double mass, double radius) {
        double G = 6.67430e-11; // Gravitational constant
        return Math.sqrt((2 * G * mass) / radius);
    }

    public double orbitalVelocity(double mass, double radius) {
        double G = 6.67430e-11; // Gravitational constant
        return Math.sqrt((G * mass) / radius);
    } 

    public double orbitalPeriod(double mass, double radius) {
        double G = 6.67430e-11; // Gravitational constant
        return Math.sqrt((4 * Math.PI * Math.PI) * (radius * radius * radius) / (G * mass));
    }

    public static void main(String[] args) {
        OrbitalCalculator calculator = new OrbitalCalculator();
        double massEarth = 5.972e24; // in kg
        double massMoon = 7.348e22; // in kg
        double distance = 3.844e8; // in meters (average distance from Earth to Moon)
        double radiusEarth = 6.371e6; // in meters

        double force = calculator.gravityForce(massEarth, massMoon, distance);
        double escapeVel = calculator.escapeVelocity(massEarth, radiusEarth);
        double orbitalVel = calculator.orbitalVelocity(massEarth, distance);
        double orbitalPer = calculator.orbitalPeriod(massEarth, distance);

        System.out.println("Gravitational Force between Earth and Moon: " + force + " N");
        System.out.println("Escape Velocity from Earth: " + escapeVel + " m/s");
        System.out.println("Orbital Velocity at Moon's distance: " + orbitalVel + " m/s");
        System.out.println("Orbital Period at Moon's distance: " + orbitalPer + " s");
    }

}

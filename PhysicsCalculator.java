
/**
 * A class to perform satellite and planetary calculations, demonstrating various
 * method types and modifiers.
 */
public class PhysicsCalculator {

    // A public, static, final constant.
    // - public: Accessible everywhere.
    // - static: Belongs to the class, not an instance.
    // - final: The value cannot be changed.
    public static final double GRAVITATIONAL_CONSTANT = 6.67430e-11; // N m^2 / kg^2

    // An instance variable. It is private to encapsulate it.
    private String planetName;

    // This is a constructor. It's called when we create an object of the class.
    public PhysicsCalculator(String planetName) {
        this.planetName = planetName;
    }

    // This is an OVERLOADED constructor. It has the same name as the one above
    // but takes different parameters (in this case, no parameters).
    public PhysicsCalculator() {
        this.planetName = "Unknown Planet";
    }

    /**
     * A public static method. It can be called directly on the class without
     * creating an object.
     * It returns a double value.
     */
    public static double calculateGravitationalForce(double mass1, double mass2, double radius) {
        if (radius <= 0) {
            return 0; // Prevent division by zero
        }
        return (GRAVITATIONAL_CONSTANT * mass1 * mass2) / (radius * radius);
    }

    /**
     * A public instance method. It needs an object to be called.
     * It returns a double value.
     */
    public double calculateEscapeVelocity(double planetMass, double planetRadius) {
        if (planetRadius <= 0) {
            return 0;
        }
        // Calls a private helper method for logging.
        logCalculation("Calculating escape velocity for " + this.planetName);
        return Math.sqrt((2 * GRAVITATIONAL_CONSTANT * planetMass) / planetRadius);
    }

    /**
     * Calculates the orbital speed of a satellite.
     */
    public double calculateOrbitalSpeed(double planetMass, double orbitalRadius) {
        if (orbitalRadius <= 0) {
            return 0;
        }
        return Math.sqrt((GRAVITATIONAL_CONSTANT * planetMass) / orbitalRadius);
    }

    /**
     * Calculates the orbital period of a satellite.
     */
    public double calculateOrbitalPeriod(double planetMass, double orbitalRadius) {
        if (orbitalRadius <= 0 || planetMass <= 0) {
            return 0;
        }
        return 2 * Math.PI * Math.sqrt(Math.pow(orbitalRadius, 3) / (GRAVITATIONAL_CONSTANT * planetMass));
    }

    /**
     * A public method with a void return type. It performs an action (printing)
     * but does not return a value.
     */
    public void displayResults(double force, double velocity) {
        System.out.println("\n--- Basic Results for " + this.planetName + " ---");
        System.out.printf("Gravitational Force: %.2e Newtons\n", force);
        System.out.printf("Escape Velocity: %.2f m/s\n", velocity);
        displayDisclaimer(); // Calling another instance method
    }

    /**
     * This is an OVERLOADED method. It has the same name as the method above
     * but a different parameter list. It prints a more complete report.
     */
    public void displayResults(double force, double velocity, double orbitalSpeed, double orbitalPeriod) {
        System.out.println("\n--- Full Report for " + this.planetName + " ---");
        System.out.printf("Gravitational Force: %.2e Newtons\n", force);
        System.out.printf("Escape Velocity: %.2f m/s\n", velocity);
        System.out.printf("Orbital Speed: %.2f m/s\n", orbitalSpeed);
        System.out.printf("Orbital Period: %.2f seconds (%.2f hours)\n", orbitalPeriod, orbitalPeriod / 3600);
        displayDisclaimer(); // Calling another instance method
    }

    /**
     * A final method. A subclass of PhysicsCalculator could not override this method.
     */
    public final void displayDisclaimer() {
        System.out.println("Disclaimer: Calculations are based on idealized models.");
    }

    /**
     * A private helper method. It can only be called from within this class.
     * It has a void return type.
     */
    private void logCalculation(String message) {
        // In a real app, this might write to a file. Here, we just print.
        System.out.println("[LOG] " + message);
    }

    // The main method is always public, static, and void.
    public static void main(String[] args) {
        // Define inputs based on the notes.md context
        double planetMass = 5.972e24; // kg (Earth)
        double planetRadius = 6.371e6; // m (Earth)
        double satMass = 1000; // kg
        double satAltitude = 5.49e5; // m
        double orbitalRadius = planetRadius + satAltitude;

        // Calling a static method without creating an object
        double force = PhysicsCalculator.calculateGravitationalForce(planetMass, satMass, orbitalRadius);

        // Creating an instance (object) of the class
        PhysicsCalculator earthCalculator = new PhysicsCalculator("Earth");

        // Calculate all values
        double escapeVelocity = earthCalculator.calculateEscapeVelocity(planetMass, planetRadius);
        double orbitalSpeed = earthCalculator.calculateOrbitalSpeed(planetMass, orbitalRadius);
        double orbitalPeriod = earthCalculator.calculateOrbitalPeriod(planetMass, orbitalRadius);

        // Call the new, overloaded displayResults method
        earthCalculator.displayResults(force, escapeVelocity, orbitalSpeed, orbitalPeriod);
    }
}
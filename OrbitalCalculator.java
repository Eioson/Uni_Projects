import java.util.Scanner;

public class OrbitalCalculator {
    private static final double G = 6.67430e-11; // Gravitational constant

    // Method to calculate the distance between the center of the planet and the satellite
    public double distPC (double radiusPlanet, double altitude) {
        return radiusPlanet + altitude; // Orbital radius = planet radius + altitude
    }

    // Method to calculate gravitational force between two masses
    public double gravityForce(double massPlanet, double massSatellite, double distance) {
        return (G * massPlanet * massSatellite) / Math.pow(distance, 2);
    }

    // Method to calculate escape velocity from the planet's surface
    public double escapeVelocity(double mass, double Radius) {
        return Math.sqrt((2 * (G * mass)) / Radius);
    }

    // Method to calculate orbital velocity at a given radius
    public double orbitalVelocity(double mass, double radius) {
        return Math.sqrt((G * mass) / radius);
    } 

    /*  Overloaded method to calculate orbital velocity at a given altitude above the planet's surface
    Overloaded methods have the same name but different parameters (type, number, or both).
    Which method is called is determined by the arguments inputted during the method call.
    */
    public double orbitalVelocity(double centralBodyMass, double centralBodyRadius, double altitude) {
        double orbitalRadius = centralBodyRadius + altitude;
        return orbitalVelocity(centralBodyMass, orbitalRadius);
    }

    // Method to calculate orbital period using the given radius and mass
    public double orbitalPeriod(double mass, double radius) {
        // Input validation to prevent division by zero or square root of a negative number.
        if (mass <= 0 || radius < 0) {
            return 0; 
        }// Or throw an exception, depending on desired error handling.
        
        // Using Math.pow (power) for clarity when dealing with exponents.
        return ((2 * Math.PI * radius) / (G * mass));
    }

    /*  Overloaded method to calculate orbital period at a given altitude above the planet's surface 
     * Ment to calculate for the same parameters as the orbitalVelocity method
     * Shows and error if orbitalRadius isnt defined
    */
    public double orbitalPeriod(double centralBodyMass, double centralBodyRadius, double altitude) {
        double orbitalRadius = centralBodyRadius + altitude;
        return orbitalPeriod(centralBodyMass, orbitalRadius); 
        // ^ This automatically calls the other orbitalPeriod method and assigns the results to the arguments
    }

    public void output(double force, double velocity, double orbitalVel, double orbitalPer) {
        System.out.println("--- Results ---"
        + "\nGravitational Force: " + force + " N"
        + "\nOrbital Period: " + orbitalPer + " seconds"
        + "\nOrbital Velocity: " + orbitalVel + " m/s"
        + "\nEscape Velocity: " + velocity + " m/s (from the planet's surface)"
        + "\n--- End of Calculations ---" + "\n");   

        /* Made this in case proffessor wants a specific decimal point output
         * System.out.println("--- Results ---"
        + "\nGravitational Force: " + String.format("%.2f", force) + " N"
        + "\nOrbital Period: " + String.format("%.2f", orbitalPer) + " seconds"
        + "\nOrbital Velocity: " + String.format("%.2f", orbitalVel) + " m/s"
        + "\nEscape Velocity: " + String.format("%.2f", velocity) + " m/s (from the planet's surface)"
        + "\n--- End of Calculations ---" + "\n");
         */
    }

    public static void main(String[] sigma) {
            try(Scanner sc = new Scanner(System.in)) {

            OrbitalCalculator calculator = new OrbitalCalculator();

            System.out.print("Enter the mass of the planet (in kilograms): ");
            double massPlanet = sc.nextDouble(); // in kg, Planet's mass, user-defined
            if (massPlanet <= 0) {
                System.out.println("Error: Planet mass must be greater than zero.");
                return; // Exit the program if the mass is invalid
            }

            System.out.print("Enter the radius of the planet (in meters): ");
            double radiusPlanet = sc.nextDouble(); // in meters, Planet's radius, user-defined
            if (radiusPlanet <= 0) {
                System.out.println("Error: Planet radius must be greater than zero.");
                return; // Exit the program if the radius is invalid
            }

            System.out.print("Enter the mass of the satellite (in kilograms): ");
            double massSatellite = sc.nextDouble(); // in kg, Satellite's mass, user-defined
            if (massSatellite <= 0) {
                System.out.println("Error: Satellite mass must be greater than zero.");
                return; // Exit the program if the mass is invalid
            }

            System.out.print("Enter the distance between the planet and the satellite/orbital altitude (in meters): ");
            double distancePS = sc.nextDouble(); // in meters, Distance from the planet to the satellite AKA orbital altitude, user-defined
            if (distancePS <= 0) {
                System.out.println("Error: Distance must be greater than zero.");
                return; // Exit the program if the distance is invalid
            }

            //Calculations and Output
            double distance = calculator.distPC(radiusPlanet, distancePS); // distance = Orbital Radius = planet radius + altitude
            double force = calculator.gravityForce(massPlanet, massSatellite, distance); // force = Gravitational Force between the planet and the satellite
            double escapeVel = calculator.escapeVelocity(massPlanet, radiusPlanet); // escapeVel = Escape Velocity from the planet's surface
            double orbitalVelSat = calculator.orbitalVelocity(massPlanet, distance); // orbitalVelSat = Orbital Velocity of the satellite
            double orbitalPeriodSat = calculator.orbitalPeriod(massPlanet, distancePS); // orbitalPeriodSat = Orbital Period of the satellite

            calculator.output(force, escapeVel, orbitalVelSat, orbitalPeriodSat);
            sc.close();
        }
    }
}

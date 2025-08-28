import java.text.DecimalFormat; // Scanner class for user input
import java.util.Scanner; // DecimalFormat class for formatting output


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
    public double escapeVelocity(double mass, double Radius) { // Radius = radius of the planet
        return Math.sqrt((2 * (G * mass)) / Radius);
    }

    // Method to calculate orbital velocity at a given radius
    public double orbitalVelocity(double mass, double radius) { // radius = orbital radius from the planet's center
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

        double orbitalVelocity = orbitalVelocity(mass, radius);
        
        // Using Math.pow (power) for clarity when dealing with exponents.
        return ((2 * Math.PI * radius) / orbitalVelocity);
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

        DecimalFormat df = new DecimalFormat("0.000e0"); /*/ Scientific notation with 3 digits past the decimal 
        for the gravitational force. Zeros are placeholders for the digits*/

        df.setPositivePrefix(""); // Removes '+' sign for positive numbers
        df.setNegativePrefix("-"); // Adds '-' sign for negative numbers

        DecimalFormat velFormat = new DecimalFormat("0.000000"); /* Fixed 6 digits past the decimal notation format for 
        the orbital and escape velocities. Zeros are placeholders for the digits*/

        DecimalFormat perFormat = new DecimalFormat("0.000"); /* Fixed 3 digits past the decimal notation format for 
        the periods. Zeros are placeholders for the digits*/

        System.out.println("\n--- Results ---" + "\n"
        + "\n\tGravitational Force: " + df.format(force)  + " N" // 3 digits past the decimal, e = scientific notation
        + "\n\tOrbital Period: " + perFormat.format(orbitalPer) + " seconds" // 3 digits past the decimal, f = fixed/decimal notation
        + "\n\tOrbital Velocity: " + velFormat.format(orbitalVel) + " m/s" // 6 digits past the decimal, f = fixed/decimal notation
        + "\n\tEscape Velocity: " + velFormat.format(velocity) + " m/s (from the planet's surface)\n" /*/ 6 digits past the decimal, 
        f = fixed/decimal notation */
        + "\n--- End of Calculation ---" + "\n"); // New lines for better readability
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
            double orbitalAlt = sc.nextDouble(); // in meters, Distance from the planet to the satellite AKA orbital altitude, user-defined
            
            if (orbitalAlt <= 0) {
                System.out.println("Error: Distance must be greater than zero.");
                return; // Exit the program if the distance is invalid
            }

            //Calculations and Output
            double orbitalRad = calculator.distPC(radiusPlanet, orbitalAlt); // distance = Orbital Radius = planet radius + altitude

            double force = calculator.gravityForce(massPlanet, massSatellite, orbitalRad); // force = Gravitational Force between the planet and the satellite

            double escapeVel = calculator.escapeVelocity(massPlanet, radiusPlanet); // escapeVel = Escape Velocity from the planet's surface

            double orbitalVelSat = calculator.orbitalVelocity(massPlanet, orbitalRad); // orbitalVelSat = Orbital Velocity of the satellite

            double orbitalPeriodSat = calculator.orbitalPeriod(massPlanet, orbitalRad); // orbitalPeriodSat = Orbital Period of the satellite = 2π * orbital radius / orbital velocity

            calculator.output(force, escapeVel, orbitalVelSat, orbitalPeriodSat);
            sc.close();
        }
    }
}

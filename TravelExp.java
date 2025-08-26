import java.util.Scanner;   // Imports the Scanner class

public class TravelExp {

    // Method that calculates the Total Fuel needed for the journey
    public double totalFuel(double distance, double consumption) {
        double totalFuel = distance * consumption; // totalFuel = Total Fuel
        return totalFuel;
    }

    // Method to calculate the predicted cost for fuel for the journey.
    public double fuelCost(double totalFuel, double PpL) {
        double FC = totalFuel * PpL; // FC = Fuel Cost, PpL = Price per Liter
        return FC;
    }

    // Method that calculates the cost of fuel per KM travelled
    public double costPerKM(double dailyRate, double distance) {
        double cpk = dailyRate / distance; // cpk = cost per KM
        return cpk;
    }

    // Method that prints all the results in a nicely formatted way
    public void finish(double TF, double FC, double cpk) {
        System.out.println("\nResults: "  /*  I dunno why this still appears as a warning, nor do I know how to 
                                            fix it, but it works*/
        + "\n"
        + "\n\tTotal Fuel: " + TF + " Liters"
        + "\n\tFuel Cost: " + FC + " Doubloons" // Chose "Doubloons" as the currency for funny reasons :>
        + "\n\tCost Per KM: " + cpk + " Doubloons"
        + "\n");
    }

    // Main method that runs the program
    public static void main(String[] args) {

        try (Scanner sc = new Scanner(System.in)) {
        System.out.print("\nEnter the trip distance: ");
        double distance = sc.nextDouble();
        System.out.print("Enter the fuel consumption per km: ");
        double consumption = sc.nextDouble();
        System.out.print("Enter the daily rate: ");
        double dailyRate = sc.nextDouble();
        sc.close();
        

        /*  Creates an instance of the TravelExp class to use it's methods 
         * since apparently Java doesn't support calling non-static methods from a static context
         * thus we need to create an object of the class to call them.
        */
        TravelExp Exp1 = new TravelExp();
        double TF = Exp1.totalFuel(distance, consumption);
        double FC = Exp1.fuelCost(Exp1.totalFuel(distance, consumption), dailyRate);
        double cpk = Exp1.costPerKM(dailyRate, distance);
        Exp1.finish(TF, FC, cpk);
        }
    }
}

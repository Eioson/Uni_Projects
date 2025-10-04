import java.util.ArrayList;

public class ArrayTest {
    public static void main(String[] sigma) {
    
        /* int n=11, m=11;
        
        int[][] arr= new int[n][m];


        for (int i=1; i<m; i++){
            for (int j=1; j<n; j++){
                arr[i][j]=i*j;
            }
        }

        for (int i = 1; i<n; i++){
            for (int j = 1; j<m; j++){
                System.out.printf("%3d", arr[i][j]); // Credits to Less
            }
            System.out.println();
        } */

        
        ArrayList<String> cars = new ArrayList<String>();
        cars.add("mazda");
        cars.add("bmw");
        cars.add("benz");     

        /*
        //for (int i = 0; i < cars.size(); i++){
        for (String a : cars){ // Iterates over each item in the list, grabbint it's value and assigning it to variable 'a'
            // String a = cars.get(i);
            System.out.println(a);
        }*/

        cars.forEach(c -> System.out.print(c + ", "));
        // for each c, print c and a comma

    }
}

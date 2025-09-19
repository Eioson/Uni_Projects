
public class ArrayTest {
    public static void main(String[] sigma) {
        
        int n=10, m=10;
        
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
        }
    }
}

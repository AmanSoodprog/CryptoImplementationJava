import java.util.Scanner;

public class Q1 {
    // Following function encrypts the message
    static void encrypt(int cipherMatrix[][], int keyMatrix[][], int messageVector[][]) {
        int x, i, j;
        for (i = 0; i < 3; i++) {
            for (j = 0; j < 1; j++) {
                cipherMatrix[i][j] = 0;

                for (x = 0; x < 3; x++) {
                    cipherMatrix[i][j] += keyMatrix[i][x] * messageVector[x][j];
                }

                cipherMatrix[i][j] = cipherMatrix[i][j] % 26;
            }
        }
    }

    // Function to implement Hill Cipher
    static void HillCipher(String message, int[][] keyMatrix) {
        int[][] messageVector = new int[3][1];

        // Generate vector for the message
        for (int i = 0; i < 3; i++)
            messageVector[i][0] = (message.charAt(i)) % 65;

        int[][] cipherMatrix = new int[3][1];

        // Following function generates the encrypted vector
        encrypt(cipherMatrix, keyMatrix, messageVector);

        String cipherText = "";

        // Generate the encrypted text from the encrypted vector
        for (int i = 0; i < 3; i++)
            cipherText += (char) (cipherMatrix[i][0] + 65);

        // Finally print the ciphertext
        System.out.print("Ciphertext: " + cipherText);
    }

    // Driver code
    public static void main(String[] args) {
        // Get the message to be encrypted
        String message = "ACT";

        // Get the key matrix from user input
        int[][] keyMatrix = new int[3][3];
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the key matrix (3x3):");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                keyMatrix[i][j] = scanner.nextInt();
            }
        }

        HillCipher(message, keyMatrix);
    }
}

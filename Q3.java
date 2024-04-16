public class Q3 {
    public static void main(String[] args) {
        // Example plaintext in hexadecimal
        String plaintextHex = "133457799BBCDFF1";
        
        // Convert the plaintext from hexadecimal to binary
        String plaintextBinary = hexToBinary(plaintextHex);
        
        // Generate the subkeys
        String[] subkeys = generateSubkeys(plaintextBinary);
        
        // Print the subkeys
        for (int i = 0; i < subkeys.length; i++) {
            System.out.println("Subkey " + (i + 1) + ": " + subkeys[i]);
        }
    }
    
    // Method to convert a hexadecimal string to binary string
    public static String hexToBinary(String hex) {
        String binary = "";
        String binaryDigit;
        
        for (int i = 0; i < hex.length(); i++) {
            binaryDigit = Integer.toBinaryString(Integer.parseInt(hex.substring(i, i + 1), 16));
            while (binaryDigit.length() < 4) {
                binaryDigit = "0" + binaryDigit;
            }
            binary += binaryDigit;
        }
        
        return binary;
    }
    
    // Method to generate the subkeys
    public static String[] generateSubkeys(String plaintextBinary) {
        // Permutation table for the initial key permutation
        int[] initialKeyPermutation = { 57, 49, 41, 33, 25, 17, 9, 1, 58, 50, 42, 34, 26, 18,
                10, 2, 59, 51, 43, 35, 27, 19, 11, 3, 60, 52, 44, 36, 63, 55, 47, 39, 31,
                23, 15, 7, 62, 54, 46, 38, 30, 22, 14, 6, 61, 53, 45, 37, 29, 21, 13, 5,
                28, 20, 12, 4 };

        // Placeholder code
        String[] subkeys = new String[16];
        for (int i = 0; i < 16; i++) {
            // Perform the initial key permutation
            String permutedKey = permuteKey(plaintextBinary, initialKeyPermutation);

            // Split the permuted key into left and right halves
            String leftHalf = permutedKey.substring(0, 28);
            String rightHalf = permutedKey.substring(28);

            // Perform the key schedule
            leftHalf = shiftLeft(leftHalf, i + 1);
            rightHalf = shiftLeft(rightHalf, i + 1);

            // Combine the left and right halves
            String combinedKey = leftHalf + rightHalf;

            // Perform the subkey permutation
            int[] subkeyPermutation = { 14, 17, 11, 24, 1, 5, 3, 28, 15, 6, 21, 10, 23, 19,
                    12, 4, 26, 8, 16, 7, 27, 20, 13, 2, 41, 52, 31, 37, 47, 55, 30, 40,
                    51, 45, 33, 48, 44, 49, 39, 56, 34, 53, 46, 42, 50, 36, 29, 32 };

            subkeys[i] = permuteKey(combinedKey, subkeyPermutation);
        }

        return subkeys;
    }

    // Method to permute the key based on a permutation table
    public static String permuteKey(String key, int[] permutationTable) {
        StringBuilder permutedKey = new StringBuilder();
        for (int i = 0; i < permutationTable.length; i++) {
            permutedKey.append(key.charAt(permutationTable[i] - 1));
        }
        return permutedKey.toString();
    }

    // Method to perform a left circular shift on a string
    public static String shiftLeft(String str, int shift) {
        return str.substring(shift) + str.substring(0, shift);
    }
}

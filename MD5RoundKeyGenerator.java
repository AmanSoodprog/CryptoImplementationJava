public class MD5RoundKeyGenerator {

    private static final int[] S = {
            7, 12, 17, 22,
            5, 9, 14, 20,
            4, 11, 16, 23,
            6, 10, 15, 21
    };

    private static final int[] K = {
            0xd76aa478, 0xe8c7b756, 0x242070db, 0xc1bdceee,
            0xf57c0faf, 0x4787c62a, 0xa8304613, 0xfd469501,
            0x698098d8, 0x8b44f7af, 0xffff5bb1, 0x895cd7be,
            0x6b901122, 0xfd987193, 0xa679438e, 0x49b40821,
            0xf61e2562, 0xc040b340, 0x265e5a51, 0xe9b6c7aa,
            0xd62f105d, 0x02441453, 0xd8a1e681, 0xe7d3fbc8,
            0x21e1cde6, 0xc33707d6, 0xf4d50d87, 0x455a14ed,
            0xa9e3e905, 0xfcefa3f8, 0x676f02d9, 0x8d2a4c8a,
            0xfffa3942, 0x8771f681, 0x6d9d6122, 0xfde5380c,
            0xa4beea44, 0x4bdecfa9, 0xf6bb4b60, 0xbebfbc70,
            0x289b7ec6, 0xeaa127fa, 0xd4ef3085, 0x04881d05,
            0xd9d4d039, 0xe6db99e5, 0x1fa27cf8, 0xc4ac5665,
            0xf4292244, 0x432aff97, 0xab9423a7, 0xfc93a039,
            0x655b59c3, 0x8f0ccc92, 0xffeff47d, 0x85845dd1,
            0x6fa87e4f, 0xfe2ce6e0, 0xa3014314, 0x4e0811a1,
            0xf7537e82, 0xbd3af235, 0x2ad7d2bb, 0xeb86d391
    };

    private static final int[] A = {0x01, 0x23, 0x45, 0x67};
    private static final int[] B = {0x89, 0xab, 0xcd, 0xef};
    private static final int[] C = {0xfe, 0xdc, 0xba, 0x98};
    private static final int[] D = {0x76, 0x54, 0x32, 0x10};

    public static void main(String[] args) {
        String input = "example"; // Replace this with your input string
        int[] paddedInput = padInput(input.getBytes());
        int[] X = new int[16];

        for (int i = 0; i < 16; i++) {
            int index = i * 4;
            X[i] = (paddedInput[index] & 0xFF) | ((paddedInput[index + 1] & 0xFF) << 8) |
                    ((paddedInput[index + 2] & 0xFF) << 16) | ((paddedInput[index + 3] & 0xFF) << 24);
        }

        int[] M = new int[16];
        for (int i = 0; i < 16; i++) {
            M[i] = X[i];
        }

        int[] result = new int[16];
        for (int i = 0; i < 16; i++) {
            int f = F(B[i], C[i], D[i]);
            result[i] = B[i] + Integer.rotateLeft(A[i] + f + M[i] + K[i], S[i]);
            A[i] = D[i];
            D[i] = C[i];
            C[i] = B[i];
            B[i] = result[i];
        }

        System.out.println("K1-K16 Round Keys:");
        for (int i = 0; i < 16; i++) {
            System.out.printf("K%d: %08X\n", i + 1, result[i]);
        }
    }

    private static int[] padInput(byte[] input) {
        int originalLength = input.length;
        int paddingLength = 64 - ((originalLength + 8) % 64);
        int newLength = originalLength + paddingLength + 8;
        byte[] paddedInput = new byte[newLength];

        // Copy input to paddedInput
        System.arraycopy(input, 0, paddedInput, 0, originalLength);

        // Append the bit '1' to the end of the message
        paddedInput[originalLength] = (byte) 0x80;

        // Append length in bits as 64-bit little-endian integer
        long bitLength = (long) originalLength * 8;
        for (int i = 0; i < 8; i++) {
            paddedInput[newLength - 8 + i] = (byte) ((bitLength >>> (i * 8)) & 0xFF);
        }

        int[] paddedIntArray = new int[newLength / 4];
        for (int i = 0; i < newLength; i += 4) {
            paddedIntArray[i / 4] = (paddedInput[i] & 0xFF) |
                    ((paddedInput[i + 1] & 0xFF) << 8) |
                    ((paddedInput[i + 2] & 0xFF) << 16) |
                    ((paddedInput[i + 3] & 0xFF) << 24);
        }

        return paddedIntArray;
    }

    private static int F(int x, int y, int z) {
        return (x & y) | (~x & z);
    }
}

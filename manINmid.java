import java.util.Scanner;
import java.math.BigInteger;
public class manINmid {
public static void main(String[] args) {
Scanner scanner = new Scanner(System.in);
System.out.print("Enter prime number 1: ");
BigInteger p = scanner.nextBigInteger();
System.out.print("Enter prime number 2: ");
BigInteger g = scanner.nextBigInteger();
System.out.print("Enter First person's private key: ");
BigInteger a = scanner.nextBigInteger();
System.out.print("Enter Second person's private key: ");
BigInteger b = scanner.nextBigInteger();
BigInteger pFake = p;
BigInteger gFake = g;
BigInteger aFake = BigInteger.valueOf(123);
BigInteger bFake = BigInteger.valueOf(456);
BigInteger A = gFake.modPow(a, pFake);
BigInteger B = gFake.modPow(b, pFake);
BigInteger interceptedA = A;
BigInteger interceptedB = B;
BigInteger secretKeyA = interceptedB.modPow(aFake, pFake);
BigInteger secretKeyB = interceptedA.modPow(bFake, pFake);
if (secretKeyA.equals(secretKeyB)) {
System.out.println("Intercepted shared secret key: " +
secretKeyA);
} else {
System.out.println("Error: Shared secret keys do not match!");
}
scanner.close();
}
}

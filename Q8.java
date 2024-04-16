import java.util.Scanner;
import java.math.BigInteger;
public class Q8 {
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
BigInteger A = g.modPow(a, p);
BigInteger B = g.modPow(b, p);
BigInteger secretKeyA = B.modPow(a, p);
BigInteger secretKeyB = A.modPow(b, p);
if (secretKeyA.equals(secretKeyB)) {
System.out.println("Shared secret key: " + secretKeyA);
} else {
System.out.println("Error: Shared secret keys do not match!");
}
scanner.close();
}
}
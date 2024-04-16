import java.util.*;
import java.math.BigInteger;
public class Q5 //RSA
{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int p=sc.nextInt();
        int q=sc.nextInt();
        int n=p*q;
        int phi=(p-1)*(q-1);

        int e=findSmallestValueWithGCD(phi);
        System.err.println(e);

        BigInteger base = BigInteger.valueOf(e);
        BigInteger modulus = BigInteger.valueOf(phi);
        
        // Find the modular inverse
        BigInteger power = base.modInverse(modulus);
        
        System.out.println("Power -1 mod b: " + power);

        //print pk and pk
        BigInteger m=BigInteger.valueOf(89);
        BigInteger modv=BigInteger.valueOf(n);
        BigInteger crypt=m.modPow(base,modv);
        System.out.println(crypt);
    }
    
    public static int findSmallestValueWithGCD(int number) {
        int result = 0;
        for (int i = 2; i < number; i++) {
            if (gcd(number, i) == 1) {
                result = i;
                break;
            }
        }
        return result;
    }
    public static int gcd(int a, int b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }
}
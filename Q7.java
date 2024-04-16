import java.util.*;
import java.math.*;
public class Q7 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int p=sc.nextInt();
        int q=sc.nextInt();
        int h=sc.nextInt(); //<p-1
        BigInteger bh=BigInteger.valueOf(h);
        int t=(p-1)/q;
        BigInteger pow=BigInteger.valueOf(t);
        BigInteger mod=BigInteger.valueOf(p);
        BigInteger g=bh.modPow(pow,mod);
        int k=sc.nextInt();
        BigInteger bk=BigInteger.valueOf(k);
        BigInteger bp=BigInteger.valueOf(p);
        int x=sc.nextInt();

        BigInteger r=(g.modPow(bk,bp)).modPow(BigInteger.valueOf(1),BigInteger.valueOf(q));

        int hm=sc.nextInt();
        
    }
}

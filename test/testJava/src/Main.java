import java.math.BigInteger;

public class Main {
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        BigInteger bigInteger=new BigInteger("1");
        System.out.println(bigInteger.shiftLeft(1000000));
        long endTime = System.currentTimeMillis();
        long elapsedTime = endTime - startTime;
        System.out.println(elapsedTime);
    }
}

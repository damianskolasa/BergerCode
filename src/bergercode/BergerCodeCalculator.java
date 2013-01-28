/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bergercode;

import java.math.BigInteger;
import java.util.Random;

/**
 *
 * @author damiank
 */
public class BergerCodeCalculator {

    public static int numBits = 8;

    public static long generateBergerCode(long data) {
        long inverted = data;
        inverted = ~inverted;
        int bergerCode = 0;
        for (bergerCode = 0; inverted != 0; inverted &= inverted - 1) {
            bergerCode++;
        }
        return bergerCode - (64 - numBits);
    }
    
    public static long totalWar(long data, double percent) {
        Double num = new Double(numBits + (numBits == 8 ? 4 : 5));
        num = num * (percent / 100);
        Random rnd = new Random(System.currentTimeMillis());
        BigInteger bi = new BigInteger(String.valueOf(data));
        for (int i = 0; i < num.intValue(); i++) {
            int b = rnd.nextInt(numBits + (numBits == 8 ? 4 : 5));
            bi = bi.flipBit(b);
        }
        return bi.longValue();
    }
    
    public static long embeddCode(long data, long code) {
        long embedded = data << (numBits == 8 ? 4 : 5);
        return embedded | code;
    }

    public static boolean validateMSG(long data) {
        long dt = data >>> (numBits == 8 ? 4 : 5);
        long cbg = generateBergerCode(dt);
        return embeddCode(dt, cbg) == data;
    }

    public static long makeOnes(long data, double percent) {
        Double num = new Double(numBits + (numBits == 8 ? 4 : 5));
        num = num * (percent / 100);
        Random rnd = new Random(System.currentTimeMillis());
        long bm = 1;
        for (int i = 0; i < num.intValue(); i++) {
            int b = rnd.nextInt(numBits + (numBits == 8 ? 4 : 5));
            data = data | (bm << b);
        }
        return data;
    }

    public static long makeZeros(long data, double percent) {
        Double num = new Double(numBits + (numBits == 8 ? 4 : 5));
        num = num * (percent / 100);
        Random rnd = new Random(System.currentTimeMillis());
        long bm = 1;
        for (int i = 0; i < num.intValue(); i++) {
            int b = rnd.nextInt(numBits + (numBits == 8 ? 4 : 5));
            data = data & ~(bm << b);
        }
        return data;
    }
}

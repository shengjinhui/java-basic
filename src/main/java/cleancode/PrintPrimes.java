package cleancode;

import com.sun.corba.se.impl.oa.poa.POAFactory;
import com.sun.org.apache.xpath.internal.WhitespaceStrippingElementMatcher;
import com.sun.tools.corba.se.idl.constExpr.Or;
import javafx.print.PageOrientation;
import sun.jvm.hotspot.debugger.Page;

import javax.annotation.Resource;
import javax.sound.midi.Soundbank;

/**
 * @Author: yichuan
 * @Date: 2020/4/17 5:49 下午
 * @Description: 初版
 */
public class PrintPrimes {
    public static void main(String[] args) {
        final int M = 1000;
        final int RR = 50;
        final int CC = 4;
        final int WW = 10;
        final int ORDMAX = 30;
        int P[] = new int[M + 1];
        int PAGENUMBER;
        int PAGEOFFSET;
        int ROWOFFSET;
        int C;
        int J;
        int K;
        boolean JPRIME;
        int ORD;
        int SQUARE;
        int N;
        int MULT[] = new int[ORDMAX + 1];

        J = 1;
        K = 1;
        P[1] = 2;
        ORD = 2;
        SQUARE = 9;

        while (K < M) {
            do {
                J = J + 2;
                if (J == SQUARE) {
                    ORD = ORD + 1;
                    SQUARE = P[ORD] * P[ORD];
                    MULT[ORD - 1] = J;
                }
                N = 2;
                JPRIME = true;
                while (N < ORD && JPRIME) {
                    while (MULT[N] < J) {
                        MULT[N] = MULT[N] + P[N] + P[N];
                    }
                    if (MULT[N] == J) {
                        JPRIME = false;
                    }
                    N = N + 1;
                }
            } while (!JPRIME);
            K = K + 1;
            P[K] = J;
        }
        {
            PAGENUMBER = 1;
            PAGEOFFSET = 1;
            while (PAGEOFFSET <= M) {
                System.out.println("The FIRST" + M + " Prime Numbers --- page " + PAGENUMBER);
                System.out.println("");
                for (ROWOFFSET = PAGEOFFSET; ROWOFFSET < PAGEOFFSET + RR; ROWOFFSET++) {
                    for (C = 0; C < CC; C++) {
                        if (ROWOFFSET + C * RR <= M) {
                            System.out.format("%10d", P[ROWOFFSET + C * RR]);
                        }
                        System.out.println("");
                    }
                    System.out.println("\f");
                    PAGENUMBER = PAGEOFFSET + 1;
                    PAGEOFFSET = PAGEOFFSET + RR * CC;
                }
            }
        }


    }
}

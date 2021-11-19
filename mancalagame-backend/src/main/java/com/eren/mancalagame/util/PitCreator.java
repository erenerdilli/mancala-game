package com.eren.mancalagame.util;

import java.util.ArrayList;
import java.util.List;

public class PitCreator {

    public static int[] createInitialPits(int pitCount, int stoneCount) {
        int[] pits = new int[pitCount];
        for (int i = 0; i < pitCount - 1; i++) {
            pits[i] = stoneCount;
        }
        pits[pitCount-1] = 0;

        return pits;
    }
}

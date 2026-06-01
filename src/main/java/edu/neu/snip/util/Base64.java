package edu.neu.snip.util;

public class Base64 {
    private static final String CHARS =
            "m3s7Kx2Pq9Ld5NrGjT0Yv4Wh8BzFcaR1eUiknou6OEIDgQbZJXACHwVfySMtp";
    private static final int BASE = 62;
    private static final long OFFSET = 3_521_614_606_208L;
    private static final long MAX_ID   = 214_818_490_978_687L;

    public static String encode(long id) {
        StringBuilder sb = new StringBuilder();
        // ensure length 8
        long val = id + OFFSET;
        while (val > 0) {
            sb.append(CHARS.charAt((int)(val % BASE)));
            val /= BASE;
        }
        return sb.reverse().toString();
    }

    public static long decode(String shortCode){
        long id = 0L;
        for(char c : shortCode.toCharArray()){
            id = id * 62 + CHARS.indexOf(c);
        }
        return id;
    }
}

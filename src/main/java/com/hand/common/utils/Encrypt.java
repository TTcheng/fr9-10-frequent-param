package com.hand.common.utils;

import org.apache.commons.lang3.StringUtils;

import java.math.BigInteger;

public class Encrypt {
    private static final int FOUR = 4;
    private static final int FIVE = 5;
    private static final int SIX = 6;
    private static final int MODU_31 = 31;
    private static final int RANDOM_PARA_1 = 1000000000;
    private static final int RANDOM_PARA_2 = 100000000;
    private static final int SALT_LENGTH = 8;
    private static final int PRAND_LENGTH = 10;
    private static final int ESCAPE_PARA_1 = 256;
    private static final int HEX_RADIX = 16;
    private static final int HEX_PARA_1 = 255;
    private static final int HEX_PARA_2 = -256;

    public Encrypt() {
    }

    public static String encrypt(String var0) {
        return encrypt(var0, (String)null);
    }

    public static String encrypt(String var0, String var1) {
        if (StringUtils.isEmpty(var0)) {
            return "";
        } else {
            var0 = EncryptCommonUtils.escape(var0);
            if (StringUtils.isEmpty(var1)) {
                var1 = "655";
            }

            var1 = EncryptCommonUtils.escape(var1);
            if (StringUtils.isEmpty(var1)) {
                return "";
            } else {
                StringBuilder var2 = new StringBuilder();

                for(int var3 = 0; var3 < var1.length(); ++var3) {
                    var2.append(var1.charAt(var3));
                }

                double var13 = Math.floor((double)(var2.length() / 5));
                String var5 = "" + var2.charAt((int)var13) + var2.charAt((int)(var13 * 2.0D)) + var2.charAt((int)(var13 * 3.0D)) + var2.charAt((int)(var13 * 4.0D)) + var2.charAt((int)(var13 * 5.0D));
                int var6 = (int)Math.ceil((double)var1.length() / 2.0D);
                BigInteger var7 = new BigInteger(String.valueOf((long)(Math.pow(2.0D, 31.0D) - 1.0D)));
                if (var5.length() < 2) {
                    return null;
                } else {
                    String var8 = String.valueOf(Math.round(Math.random() * 1.0E9D) % 100000000L);
                    var2.append(var8);

                    while(var2.length() > 10) {
                        var2 = new StringBuilder((new BigInteger(var2.substring(0, 10))).add(new BigInteger(var2.substring(10, var2.length()))).toString());
                    }

                    var2 = new StringBuilder((new BigInteger(String.valueOf(var5))).multiply(new BigInteger(var2.toString())).add(new BigInteger(var6 + "")).mod(var7).toString());
                    StringBuilder var10 = new StringBuilder();

                    for(int var11 = 0; var11 < var0.length(); ++var11) {
                        int var9 = var0.charAt(var11) ^ (int)Math.floor(Double.parseDouble(var2.toString()) / var7.doubleValue() * 255.0D);
                        String var12 = Integer.toHexString(var9 & 255 | -256).substring(6);
                        var10.append(var12);
                        var2 = new StringBuilder((new BigInteger(String.valueOf(var5))).multiply(new BigInteger(var2.toString())).add(new BigInteger(var6 + "")).mod(var7).toString());
                    }

                    StringBuilder var14 = new StringBuilder(Integer.toHexString(Integer.parseInt(var8)));

                    while(var14.length() < 8) {
                        var14.insert(0, "0");
                    }

                    var10.append(var14);
                    return var10.toString();
                }
            }
        }
    }
}

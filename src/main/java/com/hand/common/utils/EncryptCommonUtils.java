package com.hand.common.utils;

public class EncryptCommonUtils {
    private static final int ESCAPE_PARA_1 = 256;
    private static final int HEX_RADIX = 16;

    public EncryptCommonUtils() {
    }

    public static String escape(String var0) {
        StringBuffer var3 = new StringBuffer();
        var3.ensureCapacity(var0.length() * 6);

        for(int var1 = 0; var1 < var0.length(); ++var1) {
            char var2 = var0.charAt(var1);
            boolean var4 = Character.isLowerCase(var2) || Character.isUpperCase(var2);
            if (var2 >= 256) {
                var3.append("%u");
                var3.append(Integer.toString(var2, 16));
            } else if (!Character.isDigit(var2) && !var4) {
                var3.append("%");
                if (var2 < 16) {
                    var3.append("0");
                }

                var3.append(Integer.toString(var2, 16));
            } else {
                var3.append(var2);
            }
        }

        return var3.toString();
    }

    public static String unescape(String var0) {
        StringBuffer var1 = new StringBuffer();
        var1.ensureCapacity(var0.length());
        int var2 = 0;
        boolean var3 = false;

        while(var2 < var0.length()) {
            int var5 = var0.indexOf("%", var2);
            if (var5 == var2) {
                char var4;
                if (var0.charAt(var5 + 1) == 'u') {
                    var4 = (char)Integer.parseInt(var0.substring(var5 + 2, var5 + 6), 16);
                    var1.append(var4);
                    var2 = var5 + 6;
                } else {
                    var4 = (char)Integer.parseInt(var0.substring(var5 + 1, var5 + 3), 16);
                    var1.append(var4);
                    var2 = var5 + 3;
                }
            } else if (var5 == -1) {
                var1.append(var0.substring(var2));
                var2 = var0.length();
            } else {
                var1.append(var0.substring(var2, var5));
                var2 = var5;
            }
        }

        return var1.toString();
    }
}

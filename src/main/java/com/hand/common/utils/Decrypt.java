//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.hand.common.utils;

import java.math.BigInteger;

public class Decrypt {
    private String originalPassword;
    private String pwd;

    public String getOriginalPassword() {
        return this.originalPassword;
    }

    public void setOriginalPassword(String var1) {
        this.originalPassword = var1;
    }

    public String getPwd() {
        return this.pwd;
    }

    public void setPwd(String var1) {
        this.pwd = var1;
    }

    public Decrypt(String var1, String var2) {
        this.originalPassword = var1;
        this.pwd = var2;
    }

    public static String decrypt(String var0) {
        return decrypt(var0, (String)null);
    }

    public static String decrypt(String var0, String var1) {
        if (var0.equals("")) {
            return "";
        } else {
            if (var1 == null || var1.equals("")) {
                var1 = "655";
            }

            var1 = EncryptCommonUtils.escape(var1);
            if (var0 != null && var0.length() >= 8) {
                if (var1 != null && var1.length() > 0) {
                    String var2 = "";

                    int var3;
                    for(var3 = 0; var3 < var1.length(); ++var3) {
                        var2 = var2 + var1.charAt(var3);
                    }

                    var3 = (int)Math.floor((double)(var2.length() / 5));
                    String var4 = "" + var2.charAt(var3) + var2.charAt(var3 * 2) + var2.charAt(var3 * 3) + var2.charAt(var3 * 4);
                    if (var3 * 5 < var2.length()) {
                        var4 = var4 + var2.charAt(var3 * 5);
                    }

                    int var5 = (int)Math.round((double)var1.length() / 2.0D);
                    BigInteger var6 = new BigInteger((long)(Math.pow(2.0D, 31.0D) - 1.0D) + "");
                    int var7 = Integer.parseInt(var0.substring(var0.length() - 8, var0.length()), 16);
                    var0 = var0.substring(0, var0.length() - 8);

                    for(var2 = var2 + var7; var2.length() > 10; var2 = (new BigInteger(var2.substring(0, 10))).add(new BigInteger(var2.substring(10, var2.length()))).toString()) {
                    }

                    var2 = (new BigInteger(var4)).multiply(new BigInteger(var2)).add(new BigInteger(var5 + "")).mod(var6).toString();
                    String var8 = "";
                    String var9 = "";

                    for(int var10 = 0; var10 < var0.length(); var10 += 2) {
                        var8 = "" + (Integer.parseInt(var0.substring(var10, var10 + 2), 16) ^ (int)Math.floor(Double.parseDouble(var2) / var6.doubleValue() * 255.0D));
                        char var11 = (char)Integer.parseInt(var8);
                        var9 = var9 + var11;
                        var2 = (new BigInteger(var4)).multiply(new BigInteger(var2)).add(new BigInteger(var5 + "")).mod(var6).toString();
                    }

                    return EncryptCommonUtils.unescape(var9);
                } else {
                    return "";
                }
            } else {
                return "";
            }
        }
    }

    public static void main(String[] var0) {
        System.out.println(decrypt("cbc51c8f29eb03b08675", "655"));
    }
}

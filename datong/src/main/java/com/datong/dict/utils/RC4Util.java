package com.datong.dict.utils;

/* loaded from: classes.dex */
public class RC4Util {
    public static String KEY;

    public static String decryString(String data, String key) {
        try {
            return new String(RC4Base(HexString2Bytes(data), key));
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String encryString(String data, String key) {
        try {
            return toHexString(asString(encryByte(data, key)));
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String decryByte(byte[] data, String key) {
        if (data == null || key == null) {
            return null;
        }
        return asString(RC4Base(data, key));
    }

    public static byte[] encryByte(String data, String key) {
        if (data == null || key == null) {
            return null;
        }
        return RC4Base(data.getBytes(), key);
    }

    private static String asString(byte[] buf) {
        StringBuilder stringBuffer = new StringBuilder(buf.length);
        for (byte b : buf) {
            stringBuffer.append((char) b);
        }
        return stringBuffer.toString();
    }

    private static byte[] initKey(String aKey) {
        byte[] bytes = aKey.getBytes();
        byte[] bArr = new byte[256];
        for (int i = 0; i < 256; i++) {
            bArr[i] = (byte) i;
        }
        if (bytes == null || bytes.length == 0) {
            return null;
        }
        int i2 = 0;
        int i3 = 0;
        for (int i4 = 0; i4 < 256; i4++) {
            i3 = ((bytes[i2] & 255) + (bArr[i4] & 255) + i3) & 255;
            byte b = bArr[i4];
            bArr[i4] = bArr[i3];
            bArr[i3] = b;
            i2 = (i2 + 1) % bytes.length;
        }
        return bArr;
    }

    private static String toHexString(String s) {
        String str = "";
        for (int i = 0; i < s.length(); i++) {
            String hexString = Integer.toHexString(s.charAt(i) & 255);
            if (hexString.length() == 1) {
                hexString = '0' + hexString;
            }
            str = str + hexString;
        }
        return str;
    }

    private static byte[] HexString2Bytes(String src) {
        int length = src.length() / 2;
        byte[] bArr = new byte[length];
        byte[] bytes = src.getBytes();
        for (int i = 0; i < length; i++) {
            int i2 = i * 2;
            bArr[i] = uniteBytes(bytes[i2], bytes[i2 + 1]);
        }
        return bArr;
    }

    private static byte uniteBytes(byte src0, byte src1) {
        return (byte) (((char) (((char) Byte.decode("0x" + new String(new byte[]{src0})).byteValue()) << 4)) ^ ((char) Byte.decode("0x" + new String(new byte[]{src1})).byteValue()));
    }

    private static byte[] RC4Base(byte[] input, String mKkey) {
        byte[] initKey = initKey(mKkey);
        byte[] bArr = new byte[input.length];
        int i = 0;
        int i2 = 0;
        for (int i3 = 0; i3 < input.length; i3++) {
            i = (i + 1) & 255;
            i2 = ((initKey[i] & 255) + i2) & 255;
            byte b = initKey[i];
            initKey[i] = initKey[i2];
            initKey[i2] = b;
            bArr[i3] = (byte) (initKey[((initKey[i] & 255) + (initKey[i2] & 255)) & 255] ^ input[i3]);
        }
        return bArr;
    }
}

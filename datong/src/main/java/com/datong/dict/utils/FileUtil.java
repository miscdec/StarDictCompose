package com.datong.dict.utils;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.os.Looper;
import com.datong.dict.utils.FileUtil;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/* loaded from: classes.dex */
public class FileUtil {

    /* loaded from: classes.dex */
    public interface UnZipCallback {
        void completed();

        void error(String error);

        void progress(float progress);
    }

    /* loaded from: classes.dex */
    public interface ZipCallback {
        void completed();
    }

    public static void decryptUserInfo() {
        try {
            File file = new File(PathUtil.PATH_CURRENT_USER);
            if (file.exists()) {
                String readTextFile = readTextFile(file);
                if (!readTextFile.contains("username")) {
                    writeTextFile(file, RC4Util.decryString(readTextFile, RC4Util.KEY));
                }
            }
            File file2 = new File(PathUtil.PATH_ROOT_PRIVATE + "/databases/ParseOfflineStore");
            if (file2.exists()) {
                SQLiteDatabase openDatabase = SQLiteDatabase.openDatabase(file2.getPath(), null, 0);
                Cursor rawQuery = openDatabase.rawQuery("select * from ParseObjects where className = '_User'", null);
                if (rawQuery.moveToNext()) {
                    String string = rawQuery.getString(rawQuery.getColumnIndex("json"));
                    if (!string.contains("username")) {
                        String decryString = RC4Util.decryString(string, RC4Util.KEY);
                        openDatabase.execSQL("update ParseObjects set json = '" + decryString + "' where className = '_User'");
                    }
                }
                rawQuery.close();
                openDatabase.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void encryptUserInfo() {
        try {
            File file = new File(PathUtil.PATH_CURRENT_USER);
            if (file.exists()) {
                String readTextFile = readTextFile(file);
                if (readTextFile.contains("username")) {
                    writeTextFile(file, RC4Util.encryString(readTextFile, RC4Util.KEY));
                }
            }
            File file2 = new File(PathUtil.PATH_ROOT_PRIVATE + "/databases/ParseOfflineStore");
            if (file2.exists()) {
                SQLiteDatabase openDatabase = SQLiteDatabase.openDatabase(file2.getPath(), null, 0);
                Cursor rawQuery = openDatabase.rawQuery("select * from ParseObjects where className = '_User'", null);
                if (rawQuery.moveToNext()) {
                    String string = rawQuery.getString(rawQuery.getColumnIndex("json"));
                    if (string.contains("username")) {
                        String encryString = RC4Util.encryString(string, RC4Util.KEY);
                        openDatabase.execSQL("update ParseObjects set json = '" + encryString + "' where className = '_User'");
                    }
                }
                rawQuery.close();
                openDatabase.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:59:0x003e A[Catch: Exception -> 0x003a, TRY_LEAVE, TryCatch #1 {Exception -> 0x003a, blocks: (B:55:0x0036, B:59:0x003e), top: B:65:0x0036 }] */
    /* JADX WARN: Removed duplicated region for block: B:65:0x0036 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static String readTextFile(File file) throws IOException {
        FileInputStream fileInputStream;
        Exception e;
        BufferedReader bufferedReader;
        String str = null;
        try {
            fileInputStream = new FileInputStream(file);
            try {
                bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
                try {
                    StringBuffer stringBuffer = new StringBuffer();
                    while (true) {
                        String readLine = bufferedReader.readLine();
                        if (readLine == null) {
                            break;
                        }
                        stringBuffer.append(readLine);
                    }
                    str = stringBuffer.toString();
                } catch (Exception e2) {
                    e = e2;
                    e.printStackTrace();
                    str = "";
                    if (fileInputStream != null) {
                    }
                    if (bufferedReader != null) {
                    }
                    return str;
                }
            } catch (Exception e3) {
                e = e3;
                bufferedReader = null;
            }
        } catch (Exception e4) {
            fileInputStream = null;
            e = e4;
            bufferedReader = null;
        }
        if (fileInputStream != null) {
            try {
                fileInputStream.close();
            } catch (Exception e5) {
                e5.printStackTrace();
            }
        }
        if (bufferedReader != null) {
            bufferedReader.close();
        }
        return str;
    }

    /* JADX WARN: Removed duplicated region for block: B:58:0x0037 A[Catch: Exception -> 0x0033, TRY_LEAVE, TryCatch #2 {Exception -> 0x0033, blocks: (B:54:0x002f, B:58:0x0037), top: B:66:0x002f }] */
    /* JADX WARN: Removed duplicated region for block: B:66:0x002f A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:70:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static void writeTextFile(File file, String content) {
        BufferedWriter bufferedWriter;
        BufferedWriter bufferedWriter2;
        FileWriter fileWriter;
        FileWriter fileWriter2 = null;
        try {
            if (!file.exists()) {
                createNewFile(file);
            }
            fileWriter = new FileWriter(file, false);
            try {
                bufferedWriter2 = new BufferedWriter(fileWriter);
            } catch (Exception e) {
                e = e;
                bufferedWriter = null;
                fileWriter2 = fileWriter;
            }
        } catch (Exception e2) {
            e = e2;
            bufferedWriter = null;
        }
        try {
            bufferedWriter2.write(content);
            bufferedWriter2.flush();
        } catch (Exception e3) {
            fileWriter2 = fileWriter;
            bufferedWriter = bufferedWriter2;
            e = e3;
            e.printStackTrace();
            bufferedWriter2 = bufferedWriter;
            fileWriter = fileWriter2;
            if (fileWriter != null) {
            }
            if (bufferedWriter2 == null) {
            }
        }
        if (fileWriter != null) {
            try {
                fileWriter.close();
            } catch (Exception e4) {
                e4.printStackTrace();
                return;
            }
        }
        if (bufferedWriter2 == null) {
            bufferedWriter2.close();
        }
    }

    public static void createNewFile(File file) {
        try {
            file.getParentFile().mkdirs();
            file.createNewFile();
        } catch (Exception unused) {
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v0, types: [android.content.Context] */
    /* JADX WARN: Type inference failed for: r2v11, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r2v3 */
    /* JADX WARN: Type inference failed for: r2v5 */
    /* JADX WARN: Type inference failed for: r2v6, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r2v8, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r3v0, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r3v1 */
    /* JADX WARN: Type inference failed for: r3v3, types: [java.io.FileOutputStream] */
    /* JADX WARN: Type inference failed for: r3v6 */
    public static void copyFromAssets(Context context, String assetsName, String toPath, boolean isOverWrite) {
        Throwable th;
        FileOutputStream fileOutputStream;
        IOException e;
        File file = new File(toPath);
        if (file.exists() && !isOverWrite) {
            return;
        }
        try {
            try {
                try {
                    context = context.getResources().getAssets().open(assetsName);
                    try {
                        fileOutputStream = new FileOutputStream(file);
                    } catch (IOException e2) {
                        e = e2;
                        fileOutputStream = null;
                    } catch (Throwable th2) {
                        th = th2;
                        assetsName = 0;
                        if (context != 0) {
                            try {
                                context.close();
                            } catch (IOException e3) {
                                e3.printStackTrace();
                                throw th;
                            }
                        }
                        if (assetsName != 0) {
                            assetsName.close();
                        }
                        throw th;
                    }
                } catch (IOException e4) {
                    fileOutputStream = null;
                    e = e4;
                    context = 0;
                } catch (Throwable th3) {
                    assetsName = 0;
                    th = th3;
                    context = 0;
                }
                try {
                    byte[] bArr = new byte[10240];
                    while (true) {
                        int read = context.read(bArr);
                        if (read <= 0) {
                            break;
                        }
                        fileOutputStream.write(bArr, 0, read);
                    }
                    if (context != 0) {
                        context.close();
                    }
                    fileOutputStream.close();
                } catch (IOException e5) {
                    e = e5;
                    e.printStackTrace();
                    if (context != 0) {
                        context.close();
                    }
                    if (fileOutputStream != null) {
                        fileOutputStream.close();
                    }
                }
            } catch (Throwable th4) {
                th = th4;
            }
        } catch (IOException e6) {
            e6.printStackTrace();
        }
    }

    public static void zip(final String filePath, final String toPath, final ZipCallback callback) {
        new Thread(new Runnable() { // from class: com.datong.dict.utils.FileUtil$$ExternalSyntheticLambda9
            @Override // java.lang.Runnable
            public final void run() {
                FileUtil.lambda$zip$1(filePath, toPath, callback);
            }
        }).start();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:131:0x008b A[Catch: Exception -> 0x008f, TRY_ENTER, TryCatch #2 {Exception -> 0x008f, blocks: (B:102:0x004e, B:131:0x008b, B:135:0x0093, B:137:0x0098, B:139:0x009d), top: B:159:0x000d }] */
    /* JADX WARN: Removed duplicated region for block: B:135:0x0093 A[Catch: Exception -> 0x008f, TryCatch #2 {Exception -> 0x008f, blocks: (B:102:0x004e, B:131:0x008b, B:135:0x0093, B:137:0x0098, B:139:0x009d), top: B:159:0x000d }] */
    /* JADX WARN: Removed duplicated region for block: B:137:0x0098 A[Catch: Exception -> 0x008f, TryCatch #2 {Exception -> 0x008f, blocks: (B:102:0x004e, B:131:0x008b, B:135:0x0093, B:137:0x0098, B:139:0x009d), top: B:159:0x000d }] */
    /* JADX WARN: Removed duplicated region for block: B:139:0x009d A[Catch: Exception -> 0x008f, TRY_LEAVE, TryCatch #2 {Exception -> 0x008f, blocks: (B:102:0x004e, B:131:0x008b, B:135:0x0093, B:137:0x0098, B:139:0x009d), top: B:159:0x000d }] */
    /* JADX WARN: Removed duplicated region for block: B:149:0x00b0 A[Catch: Exception -> 0x00ac, TryCatch #5 {Exception -> 0x00ac, blocks: (B:145:0x00a8, B:149:0x00b0, B:151:0x00b5, B:153:0x00ba), top: B:160:0x00a8 }] */
    /* JADX WARN: Removed duplicated region for block: B:151:0x00b5 A[Catch: Exception -> 0x00ac, TryCatch #5 {Exception -> 0x00ac, blocks: (B:145:0x00a8, B:149:0x00b0, B:151:0x00b5, B:153:0x00ba), top: B:160:0x00a8 }] */
    /* JADX WARN: Removed duplicated region for block: B:153:0x00ba A[Catch: Exception -> 0x00ac, TRY_LEAVE, TryCatch #5 {Exception -> 0x00ac, blocks: (B:145:0x00a8, B:149:0x00b0, B:151:0x00b5, B:153:0x00ba), top: B:160:0x00a8 }] */
    /* JADX WARN: Removed duplicated region for block: B:160:0x00a8 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:173:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r2v0, types: [java.io.BufferedInputStream] */
    /* JADX WARN: Type inference failed for: r2v10, types: [java.io.BufferedInputStream] */
    /* JADX WARN: Type inference failed for: r2v11 */
    /* JADX WARN: Type inference failed for: r2v12 */
    /* JADX WARN: Type inference failed for: r2v13 */
    /* JADX WARN: Type inference failed for: r2v14 */
    /* JADX WARN: Type inference failed for: r2v6 */
    /* JADX WARN: Type inference failed for: r2v7, types: [java.io.BufferedInputStream] */
    /* JADX WARN: Type inference failed for: r7v0, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r7v1, types: [java.io.BufferedOutputStream] */
    /* JADX WARN: Type inference failed for: r7v10 */
    /* JADX WARN: Type inference failed for: r7v11, types: [java.io.BufferedOutputStream] */
    /* JADX WARN: Type inference failed for: r7v17 */
    /* JADX WARN: Type inference failed for: r7v19 */
    /* JADX WARN: Type inference failed for: r7v20, types: [java.io.BufferedOutputStream] */
    /* JADX WARN: Type inference failed for: r7v8 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static /* synthetic */ void lambda$zip$1(String str, String str2, final ZipCallback zipCallback) {
        ZipOutputStream zipOutputStream;
        ?? r2;
        FileInputStream fileInputStream;
        ZipOutputStream zipOutputStream2;
        ZipOutputStream zipOutputStream3;
        ZipOutputStream zipOutputStream4;
        ?? r22;
        Exception e;
        ?? r7;
        File file = new File(str);
        if (!file.exists()) {
            return;
        }
        try {
            try {
                try {
                    fileInputStream = new FileInputStream(file);
                    try {
                        r22 = new BufferedInputStream(fileInputStream);
                    } catch (Exception e2) {
                        e = e2;
                        zipOutputStream3 = null;
                        zipOutputStream = zipOutputStream3;
                        r22 = zipOutputStream3;
                        e = e;
                        r7 = zipOutputStream;
                        e.printStackTrace();
                        if (fileInputStream != null) {
                        }
                        if (r22 != 0) {
                        }
                        if (r7 != 0) {
                        }
                        if (zipOutputStream != null) {
                        }
                    } catch (Throwable th) {
                        th = th;
                        zipOutputStream2 = null;
                        zipOutputStream = zipOutputStream2;
                        zipOutputStream4 = zipOutputStream2;
                        th = th;
                        str2 = zipOutputStream;
                        r2 = zipOutputStream4;
                        if (fileInputStream != null) {
                        }
                        if (r2 != 0) {
                        }
                        if (str2 != 0) {
                        }
                        if (zipOutputStream != null) {
                        }
                        throw th;
                    }
                } catch (Exception e3) {
                    e = e3;
                    fileInputStream = null;
                    zipOutputStream3 = null;
                } catch (Throwable th2) {
                    th = th2;
                    fileInputStream = null;
                    zipOutputStream2 = null;
                }
                try {
                    zipOutputStream = new ZipOutputStream(new FileOutputStream((String) str2));
                    try {
                        r7 = new BufferedOutputStream(zipOutputStream);
                    } catch (Exception e4) {
                        r7 = 0;
                        e = e4;
                    } catch (Throwable th3) {
                        str2 = 0;
                        th = th3;
                        r2 = r22;
                        if (fileInputStream != null) {
                        }
                        if (r2 != 0) {
                        }
                        if (str2 != 0) {
                        }
                        if (zipOutputStream != null) {
                        }
                        throw th;
                    }
                } catch (Exception e5) {
                    e = e5;
                    zipOutputStream = null;
                    r22 = r22;
                    e = e;
                    r7 = zipOutputStream;
                    e.printStackTrace();
                    if (fileInputStream != null) {
                    }
                    if (r22 != 0) {
                    }
                    if (r7 != 0) {
                    }
                    if (zipOutputStream != null) {
                    }
                } catch (Throwable th4) {
                    th = th4;
                    zipOutputStream = null;
                    zipOutputStream4 = r22;
                    th = th;
                    str2 = zipOutputStream;
                    r2 = zipOutputStream4;
                    if (fileInputStream != null) {
                        try {
                            fileInputStream.close();
                        } catch (Exception e6) {
                            e6.printStackTrace();
                            throw th;
                        }
                    }
                    if (r2 != 0) {
                        r2.close();
                    }
                    if (str2 != 0) {
                        str2.close();
                    }
                    if (zipOutputStream != null) {
                        zipOutputStream.close();
                    }
                    throw th;
                }
                try {
                    zipOutputStream.putNextEntry(new ZipEntry(file.getName()));
                    byte[] bArr = new byte[1024];
                    while (true) {
                        int read = r22.read(bArr);
                        if (read != -1) {
                            r7.write(bArr, 0, read);
                        } else {
                            new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.datong.dict.utils.FileUtil$$ExternalSyntheticLambda6
                                @Override // java.lang.Runnable
                                public final void run() {
                                    FileUtil.lambda$zip$0(ZipCallback.this);
                                }
                            });
                            fileInputStream.close();
                            r22.close();
                            r7.close();
                            zipOutputStream.close();
                            return;
                        }
                    }
                } catch (Exception e7) {
                    e = e7;
                    e.printStackTrace();
                    if (fileInputStream != null) {
                        fileInputStream.close();
                    }
                    if (r22 != 0) {
                        r22.close();
                    }
                    if (r7 != 0) {
                        r7.close();
                    }
                    if (zipOutputStream != null) {
                        zipOutputStream.close();
                    }
                }
            } catch (Exception e8) {
                e8.printStackTrace();
            }
        } catch (Throwable th5) {
            th = th5;
        }
    }

    public static /* synthetic */ void lambda$zip$0(ZipCallback zipCallback) {
        if (zipCallback != null) {
            zipCallback.completed();
        }
    }

    public static void unZip(final String zipFilePath, final String outPath, final UnZipCallback callback) {
        new Thread(new Runnable() { // from class: com.datong.dict.utils.FileUtil$$ExternalSyntheticLambda7
            @Override // java.lang.Runnable
            public final void run() {
                FileUtil.lambda$unZip$5(zipFilePath, callback, outPath);
            }
        }).start();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0, types: [java.io.File] */
    /* JADX WARN: Type inference failed for: r0v1 */
    /* JADX WARN: Type inference failed for: r0v10 */
    /* JADX WARN: Type inference failed for: r0v7 */
    public static /* synthetic */ void lambda$unZip$5(String str, final UnZipCallback unZipCallback, String str2) {
        ZipInputStream zipInputStream;
        BufferedOutputStream file = new File(str);
        if (!file.exists()) {
            unZipCallback.error("not found zip file!");
            return;
        }
        BufferedInputStream bufferedInputStream = null;
        try {
            try {
                try {
                    ZipFile zipFile = new ZipFile((File) file);
                    zipInputStream = new ZipInputStream(new FileInputStream((File) file));
                    file = null;
                    while (true) {
                        try {
                            ZipEntry nextEntry = zipInputStream.getNextEntry();
                            if (nextEntry == null) {
                                break;
                            }
                            File file2 = new File(str2 + "/" + nextEntry.getName());
                            if (!file2.getParentFile().exists()) {
                                file2.getParentFile().mkdirs();
                            }
                            if (!file2.exists()) {
                                file2.createNewFile();
                            }
                            BufferedInputStream bufferedInputStream2 = new BufferedInputStream(zipFile.getInputStream(nextEntry));
                            try {
                                BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file2));
                                try {
                                    byte[] bArr = new byte[102400];
                                    int i = 0;
                                    while (true) {
                                        int read = bufferedInputStream2.read(bArr);
                                        i += read;
                                        if (read == -1) {
                                            break;
                                        }
                                        bufferedOutputStream.write(bArr, 0, read);
                                        final float size = (i * 100.0f) / ((float) nextEntry.getSize());
                                        new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.datong.dict.utils.FileUtil$$ExternalSyntheticLambda2
                                            @Override // java.lang.Runnable
                                            public final void run() {
                                                FileUtil.lambda$unZip$3(UnZipCallback.this, size);
                                            }
                                        });
                                    }
                                    new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.datong.dict.utils.FileUtil$$ExternalSyntheticLambda0
                                        @Override // java.lang.Runnable
                                        public final void run() {
                                            FileUtil.lambda$unZip$2(UnZipCallback.this);
                                        }
                                    });
                                    file = bufferedOutputStream;
                                    bufferedInputStream = bufferedInputStream2;
                                } catch (Exception e) {
                                    e = e;
                                    file = bufferedOutputStream;
                                    bufferedInputStream = bufferedInputStream2;
                                    e.printStackTrace();
                                    new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.datong.dict.utils.FileUtil$$ExternalSyntheticLambda5
                                        @Override // java.lang.Runnable
                                        public final void run() {
                                            FileUtil.lambda$unZip$4(UnZipCallback.this, e);
                                        }
                                    });
                                    if (bufferedInputStream != null) {
                                        bufferedInputStream.close();
                                    }
                                    if (file != null) {
                                        file.close();
                                    }
                                    if (zipInputStream != null) {
                                        zipInputStream.close();
                                        return;
                                    }
                                    return;
                                } catch (Throwable th) {
                                    th = th;
                                    file = bufferedOutputStream;
                                    bufferedInputStream = bufferedInputStream2;
                                    if (bufferedInputStream != null) {
                                        try {
                                            bufferedInputStream.close();
                                        } catch (Exception e2) {
                                            e2.printStackTrace();
                                            throw th;
                                        }
                                    }
                                    if (file != 0) {
                                        file.close();
                                    }
                                    if (zipInputStream != null) {
                                        zipInputStream.close();
                                    }
                                    throw th;
                                }
                            } catch (Exception e3) {
                                e = e3;
                            } catch (Throwable th2) {
                                th = th2;
                            }
                        } catch (Exception e4) {
                            e = e4;
                        }
                    }
                    if (bufferedInputStream != null) {
                        bufferedInputStream.close();
                    }
                    if (file != null) {
                        file.close();
                    }
                    zipInputStream.close();
                } catch (Exception e5) {
                    e = e5;
                    file = null;
                    zipInputStream = null;
                } catch (Throwable th3) {
                    th = th3;
                    file = 0;
                    zipInputStream = null;
                }
            } catch (Throwable th4) {
                th = th4;
            }
        } catch (Exception e6) {
            e6.printStackTrace();
        }
    }

    public static /* synthetic */ void lambda$unZip$2(UnZipCallback unZipCallback) {
        if (unZipCallback != null) {
            unZipCallback.completed();
        }
    }

    public static /* synthetic */ void lambda$unZip$3(UnZipCallback unZipCallback, float f) {
        unZipCallback.progress(f);
    }

    public static /* synthetic */ void lambda$unZip$4(UnZipCallback unZipCallback, Exception exc) {
        if (unZipCallback != null) {
            unZipCallback.error(exc.toString());
        }
    }

    public static void unZips(final String zipFilePath, final String toPath, final UnZipCallback callback) {
        new Thread(new Runnable() { // from class: com.datong.dict.utils.FileUtil$$ExternalSyntheticLambda8
            @Override // java.lang.Runnable
            public final void run() {
                FileUtil.lambda$unZips$9(toPath, zipFilePath, callback);
            }
        }).start();
    }

    public static /* synthetic */ void lambda$unZips$9(String str, String str2, final UnZipCallback unZipCallback) {
        File file = new File(str);
        if (!file.exists()) {
            file.mkdirs();
        }
        try {
            ZipFile zipFile = new ZipFile(new File(str2));
            Enumeration<? extends ZipEntry> entries = zipFile.entries();
            int i = 0;
            while (entries.hasMoreElements()) {
                i++;
                ZipEntry nextElement = entries.nextElement();
                String name = nextElement.getName();
                InputStream inputStream = zipFile.getInputStream(nextElement);
                String replaceAll = (str + name).replaceAll("\\*", "/");
                File file2 = new File(replaceAll.substring(0, replaceAll.lastIndexOf(47)));
                if (!file2.exists()) {
                    file2.mkdirs();
                }
                if (!new File(replaceAll).isDirectory()) {
                    FileOutputStream fileOutputStream = new FileOutputStream(replaceAll);
                    byte[] bArr = new byte[1024];
                    while (true) {
                        int read = inputStream.read(bArr);
                        if (read <= 0) {
                            break;
                        }
                        fileOutputStream.write(bArr, 0, read);
                    }
                    inputStream.close();
                    fileOutputStream.close();
                    final float size = ((i * 100.0f) / zipFile.size()) * 1.0f;
                    new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.datong.dict.utils.FileUtil$$ExternalSyntheticLambda3
                        @Override // java.lang.Runnable
                        public final void run() {
                            FileUtil.lambda$unZips$6(UnZipCallback.this, size);
                        }
                    });
                }
            }
            new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.datong.dict.utils.FileUtil$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    FileUtil.lambda$unZips$7(UnZipCallback.this);
                }
            });
        } catch (IOException e) {
            new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.datong.dict.utils.FileUtil$$ExternalSyntheticLambda4
                @Override // java.lang.Runnable
                public final void run() {
                    FileUtil.lambda$unZips$8(UnZipCallback.this, e);
                }
            });
        }
    }

    public static /* synthetic */ void lambda$unZips$6(UnZipCallback unZipCallback, float f) {
        if (unZipCallback != null) {
            unZipCallback.progress(f);
        }
    }

    public static /* synthetic */ void lambda$unZips$7(UnZipCallback unZipCallback) {
        if (unZipCallback != null) {
            unZipCallback.completed();
        }
    }

    public static /* synthetic */ void lambda$unZips$8(UnZipCallback unZipCallback, IOException iOException) {
        if (unZipCallback != null) {
            unZipCallback.error(iOException.toString());
        }
    }
}

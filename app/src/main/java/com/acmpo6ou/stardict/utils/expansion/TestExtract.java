package com.acmpo6ou.stardict.utils.expansion;

import android.util.Log;

import net.sf.sevenzipjbinding.ArchiveFormat;
import net.sf.sevenzipjbinding.ExtractAskMode;
import net.sf.sevenzipjbinding.ExtractOperationResult;
import net.sf.sevenzipjbinding.IArchiveExtractCallback;
import net.sf.sevenzipjbinding.IArchiveOpenCallback;
import net.sf.sevenzipjbinding.IInArchive;
import net.sf.sevenzipjbinding.ISequentialOutStream;
import net.sf.sevenzipjbinding.SevenZip;
import net.sf.sevenzipjbinding.SevenZipException;
import net.sf.sevenzipjbinding.impl.RandomAccessFileInStream;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class TestExtract {
    private static final String TAG = "TestExtract";

    public void testExtract(File file,ArchiveFormat format) {
        try {
            RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r");
            RandomAccessFileInStream inStream = new RandomAccessFileInStream(randomAccessFile);
            ArchiveOpenCallback callback = new ArchiveOpenCallback();
            IInArchive inArchive = SevenZip.openInArchive(format, inStream, callback);

            ArchiveExtractCallback extractCallback = new ArchiveExtractCallback();
            inArchive.extract(null, false, extractCallback);

            inArchive.close();
            inStream.close();
        } catch (FileNotFoundException e) {
            Log.e(TAG, e.getMessage());
        } catch (SevenZipException e) {
            Log.e(TAG, e.getMessage());
        } catch (IOException e) {
            Log.e(TAG, e.getMessage());
        }
    }


    private class ArchiveOpenCallback implements IArchiveOpenCallback {
        @Override
        public void setTotal(Long files, Long bytes) {

            Log.i(TAG, "Archive open, total work: " + files + " files, " + bytes + " bytes");
        }

        @Override
        public void setCompleted(Long files, Long bytes) {
            Log.i(TAG, "Archive open, completed: " + files + " files, " + bytes + " bytes");
        }
    }

    private class ArchiveExtractCallback implements IArchiveExtractCallback {
        @Override
        public ISequentialOutStream getStream(int index, ExtractAskMode extractAskMode) throws SevenZipException {
            Log.i(TAG, "Extract archive, get stream: " + index + " to: " + extractAskMode);
            return new SequentialOutStream();
        }

        @Override
        public void prepareOperation(ExtractAskMode extractAskMode) throws SevenZipException {
            Log.i(TAG, "Extract archive, prepare to: " + extractAskMode);
        }

        @Override
        public void setOperationResult(ExtractOperationResult extractOperationResult) throws SevenZipException {
            Log.i(TAG, "Extract archive, completed with: " + extractOperationResult);
            if (extractOperationResult != ExtractOperationResult.OK) {
                throw new SevenZipException(extractOperationResult.toString());
            }
        }

        @Override
        public void setTotal(long total) throws SevenZipException {
            Log.i(TAG, "Extract archive, work planned: " + total);
        }

        @Override
        public void setCompleted(long complete) throws SevenZipException {
            Log.i(TAG, "Extract archive, work completed: " + complete);
        }
    }

    private class SequentialOutStream implements ISequentialOutStream {
        @Override
        public int write(byte[] data) throws SevenZipException {
            if (data == null || data.length == 0) {
                throw new SevenZipException("null data");
            }
            Log.i(TAG, "Data to write: " + data.length);
            return data.length;
        }
    }
}
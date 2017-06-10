package com.br.jobup.validations;

import android.os.Environment;
import android.text.format.DateFormat;
import android.util.Log;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Keifer Stone
 *         <p>
 *         Utilities for performing various operations on client information.
 */
public class FileUtils {
    public static final String TAG = FileUtils.class.getSimpleName();

    public static final String FORMATO_DATA_BD = "yyyy-MM-dd";
    public static final String FORMATO_DATA_UI = "dd/MM/yyyy";


    public static String getDatetimeSuffix(long date) {
        String timeStamp = new SimpleDateFormat("yyyy_MMM_dd_HH_mm").format(new Date(date));
        return timeStamp;
    }

    /* Checks if external storage is available for read and write */
    public static boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }


    /**
     * Copies a file byte for byte.
     *
     * @param fromFile FileInputStream for the file to copy from.
     * @param toFile   FileInputStream for the file to copy to.
     */
    public static void copyFile(FileInputStream fromFile, FileOutputStream toFile)
            throws IOException {

        FileChannel fromChannel = null;
        FileChannel toChannel = null;

        try {
            fromChannel = fromFile.getChannel();
            toChannel = toFile.getChannel();
            fromChannel.transferTo(0, fromChannel.size(), toChannel);
        } finally {
            try {
                if (fromChannel != null) {
                    fromChannel.close();
                }
            } finally {
                if (toChannel != null) {
                    toChannel.close();
                }
            }
        }
    }

    public static Date stringToDate(String stringDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy_HH_mm");
        Date date = null;
        try {
            date = formatter.parse(stringDate);
        } catch (ParseException e) {
            e.printStackTrace();
            Log.e(TAG, "Problema convertendendo data");
        }
        return date;
    }

    public static String getString(Date date,  String format) {
        return (String) DateFormat.format(format, date);
    }

    public static Date getDate(String dbDate, String format) {

        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        Date data = null;
        try {
            data = dateFormat.parse(dbDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return data;
    }

    public static String getDouble(double d) {
        DecimalFormat decFormat = new DecimalFormat("0.00");
        return decFormat.format(d);
    }


}

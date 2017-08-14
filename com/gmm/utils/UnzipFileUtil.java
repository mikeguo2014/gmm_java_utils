package com.gmm.utils;

import java.io.*;
import java.util.*;
import java.util.zip.*;

public class UnzipFileUtil {

    /**
     * 
     * @Title: unZipFile
     * @Description: unzip all contents in zipfile to specific directory
     * @param zipfile
     * @param path
     * @throws IOException
     * @return void
     */
    @SuppressWarnings("rawtypes")
    public static void unZipFile(String zipfile, String path)
            throws IOException {

        String msg = "";

        if (!zipfile.endsWith(".zip")) {
            msg = String
                    .format("Can't determine %s whether zip file or not, file must end with \".zip\".",
                            zipfile);
            LogUtils.logError(msg);
            return;
        }

        File savePath = new File(path);
        if (!savePath.exists()) {
            savePath.mkdirs();
        }

        ZipFile zipFile = new ZipFile(zipfile);
        for (Enumeration entries = zipFile.entries(); entries.hasMoreElements();) {
            ZipEntry entry = (ZipEntry) entries.nextElement();
            String zipEntryName = entry.getName();
            InputStream in = zipFile.getInputStream(entry);
            String outPath = (path + zipEntryName).replaceAll("\\*", "/");
            // create directory if not exists
            File file = new File(outPath.substring(0, outPath.lastIndexOf('/')));
            if (!file.exists()) {
                file.mkdirs();
            }
            // check whether is a directory, continue if yes
            if (new File(outPath).isDirectory()) {
                continue;
            }
            // print path info
            // System.out.println(outPath);

            OutputStream out = new FileOutputStream(outPath);
            byte[] buf1 = new byte[1024];
            int len;
            while ((len = in.read(buf1)) > 0) {
                out.write(buf1, 0, len);
            }
            in.close();
            out.close();
        }
        zipFile.close();

        msg = String.format("Unzip %s to %s success.", zipfile, path);
        LogUtils.logInfo(msg);
    }

    public static void main(String[] args) throws ZipException, IOException {
        String zipFile = "d:/demo/DC1-1.0.zip";
        String outPath = "d:/demo/DC1-1.0/";
        unZipFile(zipFile, outPath);
    }
}

package com.gmm.utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class DownloadFromURL {

    /**
     * Download from URL.
     *
     * @param urlStr
     * @param path
     * @throws IOException
     * @author miaguo
     */
    public static void downloadFileFromUrl(String urlStr, String path) throws IOException {

        URL url = new URL(urlStr);
        String fileName = url.toString().substring(url.toString().lastIndexOf("/") + 1);
        File saveFile = new File(path + File.separator + fileName);
        
        if (! saveFile.getParentFile().exists()) {
            saveFile.getParentFile().mkdirs();
        }

        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setConnectTimeout(3 * 1000); 
        conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");

        InputStream inputStream = conn.getInputStream();
        byte[] getData = readInputStream(inputStream);

        FileOutputStream fos = new FileOutputStream(saveFile);
        fos.write(getData);
        if (fos != null) {
            fos.close();
        }
        if (inputStream != null) {
            inputStream.close();
        }

        if (saveFile.exists()) {
            LogUtils.logStep("" + url + " download success.");
        } else {
            LogUtils.logStep("" + url + " download failed.");
            System.exit(1);
        }
    }

    private static byte[] readInputStream(InputStream inputStream)
            throws IOException {
        byte[] buffer = new byte[1024];
        int len = 0;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        while ((len = inputStream.read(buffer)) != -1) {
            bos.write(buffer, 0, len);
        }
        bos.close();
        return bos.toByteArray();
    }

    /**
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {

        String urlStr = "http://artifacthub.oraclecorp.com/opctool-dev-local/oracle-farm-plugin/06-12-2017/jenkins/oracle-farm-plugin.hpi";
        downloadFileFromUrl(urlStr, "d:/demo/1/2/3/");
    }

}

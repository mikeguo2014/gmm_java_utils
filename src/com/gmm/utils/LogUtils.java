package com.gmm.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.channels.FileChannel;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class LogUtils {

    public static int stepCount = 0;

    public static void logInfo(String msg) {
        System.out.println(String.format("[INFO] : %s", msg));
    }

    public static void logError(String msg) {
        System.out.println(String.format("[ERROR] : %s", msg));
    }

    public static void logStep(String msg) {
        stepCount++;
        System.out.println(String.format("Step %d : %s", stepCount, msg));
    }

    public static void logSubStep(String msg) {
        System.out.println(String.format("  ----  %s", msg));
    }

    public static void copyFileUsingStream(File srcFile, File destFile)
            throws IOException {
        InputStream is = null;
        OutputStream os = null;
        try {
            is = new FileInputStream(srcFile);
            os = new FileOutputStream(destFile);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = is.read(buffer)) > 0) {
                os.write(buffer, 0, length);
            }
        } finally {
            is.close();
            os.close();
        }
    }

    public static void copyFileUsingChannel(File source, File dest)
            throws IOException {
        FileChannel sourceChannel = null;
        FileChannel destChannel = null;
        try {
            sourceChannel = new FileInputStream(source).getChannel();
            destChannel = new FileOutputStream(dest).getChannel();
            destChannel.transferFrom(sourceChannel, 0, sourceChannel.size());
        } finally {
            sourceChannel.close();
            destChannel.close();
        }
    }

    /**
     * 
     * @Title: copyFileUsingJava7Files 
     * @Description: copy file
     * @param sourceFile
     * @param destinationFile
     * @return void 
     * @throws
     * @author miaguo
     * @status finished
     */
    public static void copyFileUsingJava7Files(String sourceFile, String destinationFile) {

        File srcFile = new File(sourceFile) ;
        File destFile = new File(destinationFile) ;
        
        Path srcPath = Paths.get(sourceFile);
        Path destPath = Paths.get(destinationFile);
        
        String msg = "";
        
        if (! srcFile.exists()) { 
            msg = String.format("%s is not exist.", srcFile);
            logError(msg);
            return ;
        }
        
        if ( ! destFile.getParentFile().exists() ) {
            destFile.getParentFile().mkdirs() ;
        }
        
        // overwrite existing file, if exists
        CopyOption[] options = new CopyOption[] {
                StandardCopyOption.REPLACE_EXISTING,
                StandardCopyOption.COPY_ATTRIBUTES };

        try {
            Files.copy(srcPath, destPath, options);
            msg = String.format("Copy file from %s to %s success.", sourceFile, destinationFile);
            logInfo(msg);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            msg = String.format("Copy file from %s to %s failed.", sourceFile, destinationFile);
            logInfo(msg);
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub

        String srcFile = "d:/demo/source.jpg";
        String destFile = "d:/demo/111/source.jpg";
        copyFileUsingJava7Files(srcFile, destFile);

    }

}

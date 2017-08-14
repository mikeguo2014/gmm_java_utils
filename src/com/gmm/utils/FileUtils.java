package com.gmm.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.channels.FileChannel;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class FileUtils {

    /**
     * This function can read specify file and return the whole file string
     * 
     * @param sourceFile
     * @return file content string
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static String readFile(String sourceFile)
            throws FileNotFoundException, IOException {
        File srcFile = new File(sourceFile);
        BufferedReader reader = new BufferedReader(new FileReader(srcFile));
        String fileContent = "";
        StringBuffer strBuffer = new StringBuffer();
        while ((fileContent = reader.readLine()) != null) {
            strBuffer.append(fileContent + "\r\n");
        }
        reader.close();
        return strBuffer.toString();
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
     * @param source
     * @param destination
     * @return void
     * @throws
     * @author miaguo
     * @status finished
     */
    public static void copyFileUsingJava7Files(String source, String destination) {

        File srcFile = new File(source);
        File destFile = new File(destination);

        Path srcPath = Paths.get(source);
        Path destPath = Paths.get(destination);

        String msg = "";

        if (!srcFile.exists()) {
            msg = String.format("%s is not exist.", srcFile);
            LogUtils.logError(msg);
            return;
        }

        if (srcFile.isDirectory()) {
            msg = String.format("%s is not a file.", srcFile);
            LogUtils.logError(msg);
            return;
        }

        if (!destFile.getParentFile().exists()) {
            destFile.getParentFile().mkdirs();
        }

        // overwrite existing file, if exists
        CopyOption[] options = new CopyOption[] {
                StandardCopyOption.REPLACE_EXISTING,
                StandardCopyOption.COPY_ATTRIBUTES };

        try {
            Files.copy(srcPath, destPath, options);
            msg = String.format("Copy file from %s to %s success.", source,
                    destination);
            LogUtils.logInfo(msg);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            msg = String.format("Copy file from %s to %s failed.", source,
                    destination);
            LogUtils.logInfo(msg);
            e.printStackTrace();
        }
    }

    /**
     * 
     * @Title: copyFolder
     * @Description: copy folder
     * @param @param sourcePath
     * @param @param destinationPath
     * @return void
     * @throws
     */
    public static void copyFolder(String sourcePath, String destinationPath) {

        File srcFile = new File(sourcePath);
        File destFile = new File(destinationPath);

        String msg = "";

        // if destFile is subfolder of srcFile, return error message.
        if (destFile.getParentFile().getAbsolutePath()
                .indexOf(srcFile.getAbsolutePath()) == 0) {
            msg = String.format("Can't support: %s is parent direcotry of %s.",
                    srcFile, destFile);
            LogUtils.logError(msg);
            return;
        }

        try {
            destFile.mkdirs(); // create folder if not exists
            File a = new File(sourcePath);
            String[] file = a.list();
            File temp = null;
            for (int i = 0; i < file.length; i++) {
                if (sourcePath.endsWith(File.separator)) {
                    temp = new File(sourcePath + file[i]);
                } else {
                    temp = new File(sourcePath + File.separator + file[i]);
                }

                if (temp.isFile()) {
                    FileInputStream input = new FileInputStream(temp);
                    FileOutputStream output = new FileOutputStream(
                            destinationPath + "/" + (temp.getName()).toString());
                    byte[] b = new byte[1024 * 5];
                    int len;
                    while ((len = input.read(b)) != -1) {
                        output.write(b, 0, len);
                    }
                    output.flush();
                    output.close();
                    input.close();
                }
                if (temp.isDirectory()) {// is has sub folder
                    copyFolder(sourcePath + "/" + file[i], destinationPath
                            + "/" + file[i]);
                }
            }

        } catch (Exception e) {
            LogUtils.logError("Copy folder failed.");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub

        // copyFileUsingJava7Files
        // String sourceFile = "d:/demo/";
        // String destinationFile = "d:/demo/111/demo";
        // copyFileUsingJava7Files(sourceFile, destinationFile);

        // copyFoler
        String sourcePath = "d:/demo/HelloWorld";
        String destinationPath = "d:/demo/111/";
        copyFolder(sourcePath, destinationPath);

        // readFile
        // String sourceFile = "d:/demo/demo.txt" ;
        // try {
        // System.out.println(readFile(sourceFile));
        // } catch (FileNotFoundException e) {
        // // TODO Auto-generated catch block
        // e.printStackTrace();
        // } catch (IOException e) {
        // // TODO Auto-generated catch block
        // e.printStackTrace();
        // } ;

    }

}

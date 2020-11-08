/*
 * Copyright (c) 2020 Ilya Kremnev
 * MIT License
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 */

package com.practical10;

import com.Util.Common;

import java.io.*;
import java.nio.file.Paths;
import java.text.DecimalFormat;

public class Main {

    public static long getFolderSize(String path) {
        File file = new File(path);
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            long totalSize = 0;
            for (File item : files) {
                totalSize += getFolderSize(item.getPath());
            }
            return totalSize;
        } else if (file.isFile()) {
            return file.length();
        }
        return 0;
    }

    public static void copyFolder(String from, String to) {
        File file = new File(from);
        if (file.isDirectory()) {
            new File(to).mkdir();
            File[] files = file.listFiles();
            for (File item : files) {
                copyFolder(item.getPath(), Paths.get(to, item.getName()).toString());
            }
        } else if (file.isFile()) {
            copyFile(file, new File(to));
        }
    }

    private static void copyFile(File source, File dest) {
        InputStream is = null;
        OutputStream os = null;
        try {
            is = new FileInputStream(source);
            os = new FileOutputStream(dest);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = is.read(buffer)) > 0) {
                os.write(buffer, 0, length);
            }

            is.close();
            os.close();
        } catch (IOException e) {
            Common.Println("Error coping file " + source + " to " + dest);
        }
    }

    public static String getReadableFileSize(long size) {
        if (size <= 0) return "0";
        final String[] units = new String[]{"B", "kB", "MB", "GB", "TB"};
        int digitGroups = (int) (Math.log10(size) / Math.log10(1024));
        return new DecimalFormat("#,##0.#").format(size / Math.pow(1024, digitGroups)) + " " + units[digitGroups];
    }

    public static void main(String[] args) {

        /** See {@link Common} for part 1. */

        boolean running = true;

        while (running) {

            int prgID = Common.InputInt("Choose which program to run(0-1): ");

            switch (prgID) {
                case 0: {
                    String path = Common.InputString("Enter folder to list: ");

                    long folderSize = getFolderSize(path);
                    Common.Println("Your folder size is " + getReadableFileSize(folderSize));
                    break;
                }
                case 1: {
                    String pathFrom = Common.InputString("Enter folder to copy: ");
                    String pathTo = Common.InputString("Enter copy destination folder: ");

                    copyFolder(pathFrom, pathTo);

                    Common.Println("Copy successful!");

                    break;
                }
                default: {
                    Common.Println("Could not find task you asked for. Check your input.");
                    break;
                }
            }
            boolean runMore = Common.InputQuestion("Do you want to run another program?");
            if (!runMore) {
                running = false;
            }
        }
    }
}

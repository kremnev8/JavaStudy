/*
 * Copyright (c) 2020 Ilya Kremnev
 * MIT License
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 */

package com.practical13;

import com.Util.Common;

import java.io.File;
import java.nio.file.Paths;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        String fromFolder = "src/main/resources/practical13";
        String toFolder = "out/practical13";

        new File(Paths.get(toFolder).toUri()).mkdirs();

        String result = Common.InputString("Enter folder with images to resize (Type 'Default' to use predefined path): ");
        if (!result.toLowerCase().equals("default")) {
            fromFolder = result;
        }

        File fromDir = new File(fromFolder);
        File[] files = fromDir.listFiles();

        boolean useMultithreading = Common.InputQuestion("Do you want to use multithreading");

        if (useMultithreading) {
            ExecutorService pool = Executors.newCachedThreadPool();

            int threadsAvailable = Runtime.getRuntime().availableProcessors();
            int subArraySize = (int) Math.ceil((double) files.length / threadsAvailable);

            for (int i = 0; i < threadsAvailable; i++) {
                int position = i * subArraySize;
                int length = Math.min(files.length - position, subArraySize);

                File[] threadFiles = new File[length];
                System.arraycopy(files, position, threadFiles, 0, length);
                Thread resize = new ImageResizeWorker(i, threadFiles, toFolder);
                pool.execute(resize);
            }

            pool.shutdown();
            pool.awaitTermination(1, TimeUnit.MINUTES);
            Common.Println("All threads are finished successfully!");

        } else {
            Thread resize = new ImageResizeWorker(0, files, toFolder);
            resize.start();
            resize.join();
        }

    }
}

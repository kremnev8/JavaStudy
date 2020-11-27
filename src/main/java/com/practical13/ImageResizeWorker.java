/*
 * Copyright (c) 2020 Ilya Kremnev
 * MIT License
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 */

package com.practical13;

import com.Util.Common;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.file.Paths;

public class ImageResizeWorker extends Thread {

    private int threadNumber;
    private File[] files;
    private String outputFolder;

    public ImageResizeWorker(int threadNumber, File[] files, String outputFolder) {
        this.threadNumber = threadNumber;
        this.files = files;
        this.outputFolder = outputFolder;
    }

    @Override
    public void run() {
        long start = System.currentTimeMillis();
        try {
            for (File file : files) {
                BufferedImage image = ImageIO.read(file);
                if (image == null) {
                    continue;
                }

                int newWidth = image.getWidth() / 2;
                int newHeight = image.getHeight() / 2;
                BufferedImage newImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB);

                int widthStep = image.getWidth() / newWidth;
                int heightStep = image.getHeight() / newHeight;

                for (int x = 0; x < newWidth; x++) {
                    for (int y = 0; y < newHeight; y++) {
                        int rgb = image.getRGB(x * widthStep, y * heightStep);
                        newImage.setRGB(x, y, rgb);
                    }
                }

                File newFile = new File(Paths.get(outputFolder, file.getName()).toUri());
                ImageIO.write(newImage, "jpg", newFile);
            }
        } catch (Exception ex) {
            Common.Println("[Thread " + threadNumber + "] Error while resizing images: ");
            Common.Println("[Thread " + threadNumber + "]" + ex.getMessage());
        }

        Common.Println("[Thread " + threadNumber + "] Finished after " + (System.currentTimeMillis() - start) + " ms");
    }
}

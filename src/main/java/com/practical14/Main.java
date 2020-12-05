/*
 * Copyright (c) 2020 Ilya Kremnev
 * MIT License
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 */

package com.practical14;

import com.Util.Common;
import com.practical13.ImageResizeWorker;

import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        Bank moscowBank = new Bank();

        for (int i = 0; i < 100; i++) {
            moscowBank.addAccount(new Account(String.valueOf(i)));
        }
        long totalBalance = 0;
        for (int i = 0; i < 100; i++) {
            totalBalance += moscowBank.getBalance(String.valueOf(i));
        }
        Common.Println("Bank balance before transfers: " + totalBalance);

        ExecutorService pool = Executors.newCachedThreadPool();

        for (int i = 0; i < 4; i++) {

            Runnable transfer = new TransferWorker(i, moscowBank);
            pool.execute(transfer);
        }

        pool.shutdown();
        pool.awaitTermination(5, TimeUnit.MINUTES);

        totalBalance = 0;
        for (int i = 0; i < 100; i++) {
            totalBalance += moscowBank.getBalance(String.valueOf(i), true);
        }
        Common.Println("Bank balance after transfers: " + totalBalance);

    }

}

/*
 * Copyright (c) 2020 Ilya Kremnev
 * MIT License
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 */

package com.practical14;

import com.Util.Common;

public class TransferWorker implements Runnable {

    private Bank bank;
    private int workerNumber;

    public TransferWorker(int number, Bank bank) {
        this.bank = bank;
        this.workerNumber = number;
    }

    @Override
    public void run() {
        int transactions = 0;

        while (transactions <= 100) {
            int from = Common.PickRandom(1, 0, 100);
            int to = Common.PickRandom(1, 0, 100);
            if (from != to){
                bank.transfer(String.valueOf(from),String.valueOf(to), Common.PickRandom(1, 0, 80000));
                transactions++;
            }
        }

        Common.Println("[Thread " + workerNumber + "] Finished transactions");
    }
}

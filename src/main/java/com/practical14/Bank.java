package com.practical14;

import com.Util.Common;

import java.util.HashMap;
import java.util.Random;

public class Bank {
    private HashMap<String, Account> accounts = new HashMap<>();
    private final Random random = new Random();

    public synchronized boolean isFraud(String fromAccountId, String toAccountId, long amount)
            throws InterruptedException {
        Thread.sleep(1000);
        return random.nextBoolean();
    }

    public void addAccount(Account newAccount) {
        if (!accounts.containsKey(newAccount.getAccountId())) {
            accounts.put(newAccount.getAccountId(), newAccount);
        }
    }


    public void transfer(String fromAccountId, String toAccountId, long amount) {
        if (accounts.containsKey(fromAccountId) && accounts.containsKey(toAccountId)) {
            Account from = accounts.get(fromAccountId);
            Account to = accounts.get(fromAccountId);
            if (from.isValid() && to.isValid()) {
                boolean success = false;
                synchronized (from) {
                    synchronized (to) {
                        if (from.getBalance() >= amount) {
                            from.transferMoneyFrom(amount);
                            to.transferMoneyTo(amount);
                            success = true;
                        }
                    }
                }

                if (success && amount > 50000) {
                    try {
                        synchronized (from) {
                            synchronized (to) {
                                if (isFraud(fromAccountId, toAccountId, amount)) {
                                    from.blockAccount();
                                    to.blockAccount();
                                }
                            }
                        }
                    } catch (InterruptedException e) {
                        Common.Println("Error: " + e.getMessage());
                    }

                }
            }
        }
    }

    public long getBalance(String accountId) {
        return getBalance(accountId, false);
    }

    public long getBalance(String accountId, boolean includeBlocked) {
        if (accounts.containsKey(accountId) && (accounts.get(accountId).isValid() || includeBlocked)) {
            return accounts.get(accountId).getBalance();
        }
        return 0;
    }
}

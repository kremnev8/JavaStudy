package com.practical14;

import com.Util.Common;

public class Account
{
    private long money;
    private String accountId;
    private boolean valid;

    public Account(String accountId) {
        this.money = Common.PickRandom(1, 0, 100000);
        this.accountId = accountId;
        this.valid = true;
    }

    public void transferMoneyTo(long income) {
        money += income;
    }

    public void transferMoneyFrom(long income) {
        money -= income;
    }

    public long getBalance() {
        return money;
    }

    public String getAccountId() {
        return accountId;
    }

    public boolean isValid() {
        return valid;
    }

    public void blockAccount() {
        this.valid = false;
    }
}

/*
 * Copyright (c) 2020 Ilya Kremnev
 * MIT License
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 */

package com.practical10;

public class FinishEntry implements IEntry {
    @Override
    public IEntry TryCompute(IEntry left, IEntry right) {
        return left;
    }

    @Override
    public String getEntry() {
        return "=";
    }

    @Override
    public String getDisplay() {
        return "=";
    }

    @Override
    public IEntry copy() {
        return new FinishEntry();
    }

    public double parseNumber() {
        return 0;
    }

    @Override
    public boolean isNumber() {
        return false;
    }
}

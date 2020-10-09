/*
 * Copyright (c) 2020 Ilya Kremnev
 * MIT License
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 */

package com.practical10;


import com.Util.Common;

public class DoubleEntry implements IEntry {

    private double value;
    // A wrapper for a double value
    public  DoubleEntry(double value) {
        this.value = value;
    }

    @Override
    public IEntry TryCompute(IEntry left, IEntry right) {
        return null;
    }

    @Override
    public String getEntry() {
        return Double.toString(value);
    }

    @Override
    public String getDisplay() {
        return Double.toString(value);
    }

    @Override
    public double parseNumber() {
        return value;
    }

    @Override
    public boolean isNumber() {
        return true;
    }

    @Override
    public IEntry copy() {
        return new DoubleEntry(value);
    }
}

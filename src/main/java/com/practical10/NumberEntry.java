/*
 * Copyright (c) 2020 Ilya Kremnev
 * MIT License
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 */

package com.practical10;

import com.Util.Common;

import java.util.Objects;

// This class is a temporary storage for numbers we are currently typing
public class NumberEntry implements IEntry {
    public String numberBuffer = "0";
    public boolean sign = false;
    public boolean isFloatingPoint = false;

    public boolean isZero() {
        return numberBuffer.charAt(0) == '0' && !isFloatingPoint;
    }

    public void reset(String val) {
        numberBuffer = val;
        sign = false;
        isFloatingPoint = false;
    }

    public void setFromDouble(IEntry entry) {
        if (entry.isNumber()) {
            reset();
            double val = entry.parseNumber();

            isFloatingPoint = Math.floor(val) != val;
            numberBuffer = isFloatingPoint ? Double.toString(Math.abs(val)) : Integer.toString((int) Math.abs(val));
            sign = Math.signum(val) == -1;

        }
    }

    public void setFromDouble(double val) {
        reset();

        isFloatingPoint = Math.floor(val) != val;
        numberBuffer = isFloatingPoint ? Double.toString(Math.abs(val)) : Integer.toString((int) Math.abs(val));
        sign = Math.signum(val) == -1;
    }

    public void reset() {
        reset("0");
    }

    public double parseNumber() {
        return Double.parseDouble(getEntry());
    }

    @Override
    public boolean isNumber() {
        return true;
    }

    @Override
    public IEntry TryCompute(IEntry left, IEntry right) {
        return null;
    }

    @Override
    public String getEntry() {
        return (sign ? "-" : "") + numberBuffer;
    }

    @Override
    public String getDisplay() {
        return (sign ? "\u2212" : "") + numberBuffer;
    }

    @Override
    public IEntry copy() {
        NumberEntry entry = new NumberEntry();
        entry.numberBuffer = numberBuffer;
        entry.sign = sign;
        entry.isFloatingPoint = isFloatingPoint;
        return entry;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NumberEntry that = (NumberEntry) o;
        return sign == that.sign &&
                Objects.equals(numberBuffer, that.numberBuffer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numberBuffer, sign);
    }


}

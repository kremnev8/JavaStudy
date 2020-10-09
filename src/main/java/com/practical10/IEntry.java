/*
 * Copyright (c) 2020 Ilya Kremnev
 * MIT License
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 */

package com.practical10;


public interface IEntry {
    IEntry TryCompute(IEntry left, IEntry right);
    String getEntry();
    String getDisplay();
    double parseNumber();
    boolean isNumber();
    IEntry copy();
}

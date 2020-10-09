/*
 * Copyright (c) 2020 Ilya Kremnev
 * MIT License
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 */

package com.practical10;

public class OperatorEntry implements IEntry {

    private String operator;
    private IOperator implementation;

    // Class representing a operand. Receives a lambda that executes the operand
    public OperatorEntry(String op, IOperator imp) {
        implementation = imp;
        operator = op;
    }

    public double parseNumber() {
        return 0;
    }

    @Override
    public boolean isNumber() {
        return false;
    }

    @Override
    public IEntry TryCompute(IEntry left, IEntry right) {
        if (left != null && right != null && left.isNumber() && right.isNumber()) {
            return new DoubleEntry(implementation.apply(left.parseNumber(), right.parseNumber()));
        }
        return null;
    }

    @Override
    public String getEntry() {
        return operator;
    }

    @Override
    public String getDisplay() {
        return operator;
    }

    @Override
    public IEntry copy() {
        return new OperatorEntry(operator, implementation);
    }
}

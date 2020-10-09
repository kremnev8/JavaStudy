/*
 * Copyright (c) 2020 Ilya Kremnev
 * MIT License
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 */

package com.practical10;

import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;

import java.util.ArrayList;
import java.util.Collections;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CalculatorDisplay {

    private Label current;
    private Label operations;
    public AnchorPane pane;
    public NumberEntry input;
    public ArrayList<IEntry> entries = new ArrayList<>();
    public boolean finishedEntry = false;
    private static Pattern Etest;

    static {
        Etest = Pattern.compile("e\\d+$", Pattern.CASE_INSENSITIVE);
    }
    // This class controls typing numbers and operands.
    public CalculatorDisplay(AnchorPane node) {
        pane = node;
        ScrollPane pane1 = (ScrollPane) pane.getChildren().get(0);
        operations = (Label) pane1.getContent();
        current = (Label) pane.getChildren().get(1);
        input = new NumberEntry();
        set(input);
    }
    // Updates output labels
    public void set(NumberEntry input) {
        String num = input.getDisplay();
        Matcher match = Etest.matcher(num);
        if (match.find()) {
            current.setEllipsisString(num.substring(match.start(), match.end()));
        }else {
            current.setEllipsisString("");
        }
        current.setText(num);
        StringBuilder optext = new StringBuilder();
        for (IEntry entry : entries) {
            optext.append(" ");
            optext.append(entry.getDisplay());
        }
        operations.setText(optext.toString());
    }

    // Add character to number (Used to type the number itself)
    public void typeNumber(char num) {
        if (input.isZero() || finishedEntry) {
            if (entries.size() > 0 && entries.get(entries.size() - 1) instanceof FinishEntry){
                entries.clear();
            }
            input.reset("");
            finishedEntry = false;
        }
        if (input.numberBuffer.length() < 14) {
            input.numberBuffer += num;
            set(input);
        }
    }
    // Only used to start floating point of the number
    public void startFloatingPoint() {
        if (finishedEntry) {
            if (entries.size() > 0 && entries.get(entries.size() - 1) instanceof FinishEntry){
                entries.clear();
            }
            input.reset();
            finishedEntry = false;
        }
        if (input.numberBuffer.length() < 14 && !input.isFloatingPoint) {
            input.numberBuffer += ".";
            input.isFloatingPoint = true;
            set(input);
        }
    }
    // Erases characters
    public void eraseNumber() {
        if (finishedEntry) return;
        if (input.numberBuffer.charAt(input.numberBuffer.length() - 1) == '.') {
            input.isFloatingPoint = false;
        }
        input.numberBuffer = input.numberBuffer.substring(0, input.numberBuffer.length() - 1);
        if (input.numberBuffer.length() == 0) {
            input.reset();
        }
        set(input);
    }
    // Changes number sign
    public void invertInput() {
        if (!input.isZero()) {
            if (finishedEntry) {
                if (entries.size() > 0 && entries.get(entries.size() - 1) instanceof FinishEntry){
                    entries.clear();
                }
                finishedEntry = false;
            }
            input.sign = !input.sign;
            set(input);
        }
    }

    public void clear(boolean entryOnly) {
        input.reset();
        if (!entryOnly) {
            entries.clear();
        }

        set(input);
    }
    // Called when we finished typing in the number and pressed any operand button (+-*/)
    public void finishNumber(IEntry op) {
        if (finishedEntry) {
            if (entries.size() > 0 && entries.get(entries.size() - 1) instanceof FinishEntry){
                entries.clear();
            }
            finishedEntry = false;
        }
        if (entries.size() == 0) {
            entries.add(input.copy());
            entries.add(op);
            finishedEntry = true;
        }else {
            if (entries.get(entries.size() - 2).equals(input)) {
                entries.set(entries.size() - 1, op);
            }else {
                entries.add(input.copy());
                entries.add(op);
                finishedEntry = true;
            }
        }
        set(input);
    }
    // Called when we press = or enter to compute result
    public boolean getResult() {
        if (finishedEntry) {
            if (entries.size() > 0 && entries.get(entries.size() - 1) instanceof FinishEntry){
                entries.clear();
                finishedEntry = false;
                return false;
            }
        }
        entries.add(input.copy());
        entries.add(new FinishEntry());

        ArrayList<IEntry> entries_copy =  new ArrayList<>();
        for(IEntry p : entries) {
            entries_copy.add(p.copy());
        }
        IEntry result = null;

        for (int i = 0; i < entries_copy.size(); i++) {
            int j = i - 1;
            int k = i + 1;
            IEntry left = null, center, right = null;
            if (j >= 0 && j < entries_copy.size()){
                left = entries_copy.get(j);
            }
            if (k >= 0 && k < entries_copy.size()){
                right = entries_copy.get(k);
            }
            center = entries_copy.get(i);
            result = center.TryCompute(left, right);
            if(result != null) {
                if (entries_copy.size() > 3) {
                    entries_copy.remove(0);
                    entries_copy.remove(0);
                    entries_copy.remove(0);
                    entries_copy.add(0, result);
                    i = 0;
                }else {
                    break;
                }
            }

        }
        if (result != null) {
            input.setFromDouble(result);
            finishedEntry = true;
            set(input);
            return true;
        }
        return false;
    }


}

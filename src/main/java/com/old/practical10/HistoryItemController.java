/*
 * Copyright (c) 2020 Ilya Kremnev
 * MIT License
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 */

package com.old.practical10;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HistoryItemController {

    @FXML
    public Label opLabel;

    @FXML
    public Label resultLabel;

    @FXML
    public MenuItem copyAction;

    @FXML
    public MenuItem deleteAction;

    public NumberEntry value;

    private static Pattern Etest;

    static {
        Etest = Pattern.compile("e\\d+$", Pattern.CASE_INSENSITIVE);
    }


    public void SetItem(Parent root,  NumberEntry input, ArrayList<IEntry> entries){
        value = (NumberEntry) input.copy();
        String num = value.getDisplay();
        Matcher match = Etest.matcher(num);
        if (match.find()) {
            resultLabel.setEllipsisString(num.substring(match.start(), match.end()));
        }else {
            resultLabel.setEllipsisString("");
        }
        resultLabel.setText(num);

        copyAction.setOnAction(event -> {
            StringSelection stringSelection = new StringSelection(value.getEntry());
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(stringSelection, null);
        });

        deleteAction.setOnAction(event -> {
            Main.instance.history.getChildren().remove(root);
        });

        StringBuilder optext = new StringBuilder();
        for (IEntry entry : entries) {
            optext.append(" ");
            optext.append(entry.getDisplay());
        }
        opLabel.setText(optext.toString());
    }
}

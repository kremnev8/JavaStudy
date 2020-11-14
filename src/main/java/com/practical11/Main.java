/*
 * Copyright (c) 2020 Ilya Kremnev
 * MIT License
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 */

package com.practical11;

import com.Util.Common;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.net.URI;

public class Main {

    public static List<List<String>> parseCSV(String fileName) {
        List<List<String>> table = new ArrayList<>();
        try {

            List<String> files = Files.readAllLines(Paths.get("src/main/resources/practical11/movementList.csv"));

            for (int i = 0; i < files.size(); i++) {
                String line = files.get(i);

                String[] rows = line.split(";");
                if (rows.length > 0) {
                    table.add(new ArrayList<String>());

                    for (int j = 0; j < rows.length; j++) {
                        table.get(i).add(rows[j]);
                    }
                }
            }
        } catch (IOException e) {
            Common.Println("Error opening table file!");
        }
        return table;
    }

    public static void main(String[] args) {

        Pattern expensesPattern = Pattern.compile("^[\\d+]+\\s+\\d+\\s*(.+)\\s+[\\d.]+\\s+[\\d.]+\\s+[\\d.]+");
        Pattern fileNamePattern = Pattern.compile(".+[/\\\\](.+\\.\\w+)");
        Pattern filePathPattern = Pattern.compile(".+//.+?/(.+)/.+\\.\\w+");

        boolean running = true;

        while (running) {

            int prgID = Common.InputInt("Choose which program to run(0-1): ");

            switch (prgID) {
                case 0: {
                    List<List<String>> table = parseCSV("src/main/resources/practical11/movementList.csv");

                    float income = 0;
                    float expenses = 0;
                    Map<String, Float> expensesMap = new HashMap<>();

                    for (int i = 1; i < table.size(); i++) {
                        income += Float.parseFloat(table.get(i).get(6).replace(',', '.'));
                        float localExpenses = Float.parseFloat(table.get(i).get(7).replace(',', '.'));
                        expenses += localExpenses;
                        String metaData = table.get(i).get(5);

                        Matcher matcher = expensesPattern.matcher(metaData);
                        if (!matcher.find() || matcher.groupCount() == 0) continue;

                        String company = matcher.group(1).trim();
                        if (localExpenses > 0) {
                            float current = 0;
                            if (expensesMap.containsKey(company)) {
                                current = expensesMap.get(company);
                            }
                            expensesMap.put(company, current + localExpenses);
                        }


                    }

                    Common.Println("Your total income " + income + " rubbles");
                    Common.Println("Your total expenses " + expenses + " rubbles");

                    Set<String> keys = expensesMap.keySet();

                    for (String key : keys) {
                        float value = expensesMap.get(key);
                        Common.Println("You spent " + value + " rubbles on company \"" + key + "\"");
                    }
                }
                case 1: {

                    try {
                        Document doc = Jsoup.connect("https://www.mirea.ru/").get();
                        Elements elements = doc.getElementsByTag("img");

                        elements.forEach(element -> {
                            String url = element.absUrl("src");

                            Matcher matcher = fileNamePattern.matcher(url);
                            if (!matcher.find() || matcher.groupCount() == 0) return;
                            Matcher matcher1 = filePathPattern.matcher(url);
                            if (!matcher1.find() || matcher1.groupCount() == 0) return;

                            String fileName = matcher.group(1).toLowerCase();
                            String filePath = matcher1.group(1).toLowerCase();
                            try {
                                Common.Print("Downloading file: " + Paths.get("out/practical11/images", filePath, fileName).toString());
                                new File(Paths.get("out/practical11/images", filePath).toUri()).mkdirs();

                                // I save files as they are located on origin server, to save myself the trouble of
                                // figuring out how to handle conflicting file names.
                                InputStream in = URI.create(url).toURL().openStream();
                                Files.copy(in, Paths.get("out/practical11/images", filePath, fileName), StandardCopyOption.REPLACE_EXISTING);

                                Common.Println(", done!");
                            } catch (IOException e) {
                                Common.Println("Error downloading file: ");
                                Common.Println(e.getMessage());
                            }

                        });
                        Common.Println("Finished downloading all the images. Check out 'out/practical11/images' for results!");

                    } catch (IOException e) {
                        Common.Println("Error connecting or loading data from https://www.mirea.ru/ : ");
                        Common.Println(e.getMessage());
                    }
                    break;
                }
                default: {
                    Common.Println("Could not find task you asked for. Check your input.");
                    break;
                }
            }
            boolean runMore = Common.InputQuestion("Do you want to run another program?");
            if (!runMore) {
                running = false;
            }
        }

    }

}

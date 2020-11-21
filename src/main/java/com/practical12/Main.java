/*
 * Copyright (c) 2020 Ilya Kremnev
 * MIT License
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 */

package com.practical12;

import com.Util.Common;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {

        Pattern lineIdPattern = Pattern.compile("'lines-(.+)'");
        Pattern lineIdPattern1 = Pattern.compile("^lines-(.+)$");
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        new File("out/practical12").mkdirs();

        try {
            Document doc = Jsoup.connect("https://www.moscowmap.ru/metro.html#lines").maxBodySize(0).get();

            Element dataDoc = doc.getElementById("metrodata");
            Elements linesDoc = dataDoc.select("div.js-toggle-depend");
            Elements lineStationsDoc = dataDoc.select("div.js-depend");

            Metro moscowMetro = new Metro();

            linesDoc.forEach(lineData -> {
                String lineName = lineData.text();
                Matcher matcher = lineIdPattern.matcher(lineData.attr("data-depend"));
                if (matcher.find()) {
                    String lineID = matcher.group(1);
                    moscowMetro.addLine(new MetroLine(lineName, lineID));
                }
            });

            lineStationsDoc.forEach(listData -> {

                Matcher matcher = lineIdPattern1.matcher(listData.attr("data-depend-set"));
                if (matcher.find()) {
                    String lineID = matcher.group(1);

                    Elements stationsDoc = listData.select("span.name");
                    stationsDoc.forEach(stationData -> {
                        moscowMetro.addStationToLine(lineID, stationData.text());
                    });
                }
            });

            for (int i = 0; i < moscowMetro.lines.size(); i++) {
                MetroLine line = moscowMetro.lines.get(i);
                int stationCount = moscowMetro.stations.get(line.id).size();
                Common.Println("Moscow metro line " + line.name + " has " + stationCount + " stations!");
            }
            Common.Println("Detailed information written to file 'out/practical12/moscow_metro.json'");

            PrintWriter writer = new PrintWriter("out/practical12/moscow_metro.json", "UTF-8");
            gson.toJson(moscowMetro, writer);
            writer.flush();
            writer.close();



        } catch (IOException e) {
            Common.Println("Error connecting or loading data from https://www.moscowmap.ru/ : ");
            Common.Println(e.getMessage());
        }
    }
}

class MetroLine {
    public String id;
    public String name;

    public MetroLine(String name, String id) {
        this.name = name;
        this.id = id;
    }
}

class Metro {
    public List<MetroLine> lines = new ArrayList<>();
    public Map<String, List<String>> stations = new HashMap<>();

    public void addLine(MetroLine line) {
        lines.add(line);
        stations.put(line.id, new ArrayList<>());
    }

    public void addStationToLine(String lineID, String stationName) {
        if (stations.containsKey(lineID)) {
            stations.get(lineID).add(stationName);
        }
    }
}

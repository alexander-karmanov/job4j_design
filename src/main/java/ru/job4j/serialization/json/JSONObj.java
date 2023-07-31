package ru.job4j.serialization.json;

import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class JSONObj {

    public static void main(String[] args) {

        /* JSONObject из json-строки строки */
        JSONObject jsonComputer = new JSONObject("{\"model\":\"4680\"}");

        /* JSONArray из ArrayList */
        List<String> list = new ArrayList<>();
        list.add("64-bit");
        list.add("4 Gb RAM");
        JSONArray jsonProperties = new JSONArray(list);

        /* JSONObject напрямую методом put */
        final ru.job4j.serialization.json.Computer computer = new ru.job4j.serialization.json.Computer(true, 4680, new OperatingSystem("21H1", 19043),
                new String[] {"64-bit", "4 Gb RAM"});
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("working", computer.isWorking());
        jsonObject.put("model", computer.getModel());
        jsonObject.put("system", computer.getSystem());
        jsonObject.put("properties", computer.getProperties());

        System.out.println(jsonObject.toString());
    }
}

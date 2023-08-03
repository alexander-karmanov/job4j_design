package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class JSONObj {

    public static void main(String[] args) {

        /* JSONObject из json-строки строки */
        JSONObject jsonSystem = new JSONObject("{\"version\":\"21H1\",\"build\":\"19043\"}");

        /* JSONArray из ArrayList */
        List<String> list = new ArrayList<>();
        list.add("64-bit");
        list.add("4 Gb RAM");
        JSONArray jsonProperties = new JSONArray(list);

        /* JSONObject напрямую методом put */
        final Computer computer = new Computer(true, 4680,
                new OperatingSystem("21H1", 19043),
                new String[] {"64-bit", "4 Gb RAM"});
        JSONObject jsonObject = new JSONObject();

        jsonObject.put("working", computer.isWorking());
        jsonObject.put("model", computer.getModel());
        jsonObject.put("system", jsonSystem);
        jsonObject.put("properties", jsonProperties);

        System.out.println("1. " + jsonObject);

        /*
        После этой строки добавьте преобразование объекта Computer
        computer в json-строку и проверьте, что преобразование
        произошло для всех полей.
         */

        final Gson gson = new GsonBuilder().create();
        gson.toJson(jsonObject);
        System.out.println("2 " + gson.toJson(computer));
        System.out.println("3 " + new JSONObject(computer));
    }
}

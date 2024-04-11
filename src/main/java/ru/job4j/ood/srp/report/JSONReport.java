package ru.job4j.ood.srp.report;

import com.google.gson.JsonObject;

import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.formatter.ReportDateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.MemoryStore;
import ru.job4j.ood.srp.store.Store;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.function.Predicate;

public class JSONReport implements Report {
    private final Store store;
    private final DateTimeParser<Calendar> dateTimeParser;

    public JSONReport(Store store, DateTimeParser<Calendar> dateTimeParser) {
        this.store = store;
        this.dateTimeParser = dateTimeParser;
    }

    @Override
    public String generate(Predicate<Employee> filter) {

        var tempList = new ArrayList<String>();
        for (Employee employee : store.findBy(filter)) {
            JsonObject json = new JsonObject();
            json.addProperty("name", employee.getName());
            json.addProperty("hired", dateTimeParser.parse(employee.getHired()));
            json.addProperty("fired", dateTimeParser.parse(employee.getFired()));
            json.addProperty("salary", employee.getSalary());

            var temp = json.toString().split(",");
            List<String> jsonList = List.of(
                    temp[0],
                    System.lineSeparator(),
                    temp[1],
                    System.lineSeparator(),
                    temp[2],
                    System.lineSeparator(),
                    temp[3],
                    System.lineSeparator()
            );
            tempList.addAll(jsonList);
        }
        return tempList.toString();
    }
}

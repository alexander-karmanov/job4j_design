package ru.job4j.ood.srp.report;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.formatter.ReportDateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.MemoryStore;
import ru.job4j.ood.srp.store.Store;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
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
        List<Employee> list = new ArrayList<>(store.findBy(filter));
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(Calendar.class, new CalendarAdapterJson());
        builder.registerTypeAdapter(GregorianCalendar.class, new CalendarAdapterJson());
        var gson = builder.setPrettyPrinting().create();
        return gson.toJson(list);
    }
}

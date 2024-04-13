package ru.job4j.ood.tdd.srp.report;

import com.google.gson.GsonBuilder;
import org.junit.jupiter.api.Test;
import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.formatter.ReportDateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.report.JSONReport;
import ru.job4j.ood.srp.store.MemoryStore;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class JSONReportTest {
    @Test
    public void whenJSONReportGenerated() {
        MemoryStore store = new MemoryStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 200);
        DateTimeParser<Calendar> parser = new ReportDateTimeParser();
        store.add(worker);
        JSONReport jsonReport = new JSONReport(store, parser);
        List<Employee> list = new ArrayList<>(store.findBy(employee -> true));
        var gson = new GsonBuilder().setPrettyPrinting().create();
        String expected = gson.toJson(list);
        assertThat(jsonReport.generate(employee -> true)).isEqualTo(expected);
    }
}
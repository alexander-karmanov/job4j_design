package ru.job4j.ood.tdd.srp.report;

import com.google.gson.GsonBuilder;
import org.junit.jupiter.api.Test;
import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.formatter.ReportDateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.report.JSONReport;
import ru.job4j.ood.srp.store.MemoryStore;

import java.util.Calendar;

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
        String expected = """
                [
                  {
                    "name": "Ivan",
                    "hired": "16:04:2024 18:01",
                    "fired": "16:04:2024 18:01",
                    "salary": 200.0
                  }
                ]""";
        assertThat(jsonReport.generate(employee -> true)).isEqualTo(expected);
    }
}

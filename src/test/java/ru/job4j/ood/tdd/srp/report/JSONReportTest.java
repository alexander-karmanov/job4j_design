package ru.job4j.ood.tdd.srp.report;

import com.google.gson.GsonBuilder;
import org.junit.jupiter.api.Test;
import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.formatter.ReportDateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.report.JSONReport;
import ru.job4j.ood.srp.store.MemoryStore;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.assertj.core.api.Assertions.assertThat;

public class JSONReportTest {
    @Test
    public void whenJSONReportGenerated() {
        MemoryStore store = new MemoryStore();
        Employee worker = new Employee("Ivan", new GregorianCalendar(2023, 2, 1), new GregorianCalendar(2024, 3, 2), 200);
        Employee worker2 = new Employee("Alexey", new GregorianCalendar(2024, 1, 7), new GregorianCalendar(2024, 5, 10), 300);
        DateTimeParser<Calendar> parser = new ReportDateTimeParser();
        store.add(worker);
        store.add(worker2);
        JSONReport jsonReport = new JSONReport(store, parser);
        String expected = """
                [
                  {
                    "name": "Ivan",
                    "hired": "01:03:2023 00:00",
                    "fired": "02:04:2024 00:00",
                    "salary": 200.0
                  },
                  {
                    "name": "Alexey",
                    "hired": "07:02:2024 00:00",
                    "fired": "10:06:2024 00:00",
                    "salary": 300.0
                  }
                ]""";
        assertThat(jsonReport.generate(employee -> true)).isEqualTo(expected);
    }
}

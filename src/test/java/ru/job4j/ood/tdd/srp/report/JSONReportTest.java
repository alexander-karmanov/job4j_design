package ru.job4j.ood.tdd.srp.report;

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

        StringBuilder expected = new StringBuilder()
                .append("[").append("{").append("\"")
                .append("name").append("\"")
                .append(":").append("\"").append("Ivan").append("\"").append(", ")
                .append(System.lineSeparator())
                .append(", ")
                .append("\"").append("hired").append("\"").append(":").append("\"").append(parser.parse(worker.getHired())).append("\"").append(", ")
                .append(System.lineSeparator())
                .append(", ")
                .append("\"").append("fired").append("\"").append(":").append("\"").append(parser.parse(worker.getFired())).append("\"").append(", ")
                .append(System.lineSeparator())
                .append(", ")
                .append("\"").append("salary").append("\"").append(":").append(worker.getSalary())
                .append("}").append(", ")
                .append(System.lineSeparator())
                .append("]");
        assertThat(jsonReport.generate(employee -> true)).isEqualTo(expected.toString());
    }
}

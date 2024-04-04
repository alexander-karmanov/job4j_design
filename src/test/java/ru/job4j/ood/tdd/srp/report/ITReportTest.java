package ru.job4j.ood.tdd.srp.report;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.formatter.ReportDateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.report.ITReport;
import ru.job4j.ood.srp.store.MemoryStore;

import java.io.*;
import java.util.Calendar;

import static org.assertj.core.api.Assertions.assertThat;

public class ITReportTest {
    @Test
    public void whenITReportGenerated() {
        MemoryStore store = new MemoryStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 200);
        DateTimeParser<Calendar> parser = new ReportDateTimeParser();
        store.add(worker);
        ITReport itReport = new ITReport(store, parser);

        StringBuilder expected = new StringBuilder()
                .append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator())
                .append(worker.getName()).append(" ")
                .append(parser.parse(worker.getHired())).append(" ")
                .append(parser.parse(worker.getFired())).append(" ")
                .append(worker.getSalary())
                .append(System.lineSeparator());

        String text = itReport.generate(employee -> true);
        itReport.toFile(text);
        String file = "src/main/java/ru/job4j/ood/srp/report/out.csv";
        StringBuilder sB = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line = reader.readLine();
            sB.append(line);
            sB.append(System.lineSeparator());
            while (reader.ready()) {
                line = reader.readLine();
                sB.append(line);
                sB.append(System.lineSeparator());
            }
        }  catch (IOException e) {
            e.printStackTrace();
        }
        assertThat(sB.toString()).isEqualTo(expected.toString());
    }
}

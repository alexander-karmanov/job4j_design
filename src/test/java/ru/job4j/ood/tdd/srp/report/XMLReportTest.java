package ru.job4j.ood.tdd.srp.report;

import com.google.gson.GsonBuilder;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.formatter.ReportDateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.report.XMLReport;
import ru.job4j.ood.srp.store.MemoryStore;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Disabled
public class XMLReportTest {
    @Test
    public void whenXMLReportGenerated() {
        MemoryStore store = new MemoryStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 200);
        DateTimeParser<Calendar> parser = new ReportDateTimeParser();
        store.add(worker);
        XMLReport xmlReport = new XMLReport(store, parser);

    }
}

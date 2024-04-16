package ru.job4j.ood.tdd.srp.report;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.formatter.ReportDateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.report.Report;
import ru.job4j.ood.srp.report.XMLReport;
import ru.job4j.ood.srp.store.MemoryStore;

import javax.xml.bind.JAXBException;
import java.util.Calendar;

import static org.assertj.core.api.Assertions.assertThat;

public class XMLReportTest {
    @Test
    public void whenXMLReportGenerated() throws JAXBException {
        MemoryStore store = new MemoryStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 200);
        DateTimeParser<Calendar> parser = new ReportDateTimeParser();
        store.add(worker);
        Report xmlReport = new XMLReport(store, parser);
        String expected = """
                <?xml version="1.0" encoding="UTF-8" standalone="yes"?>
                <employee>
                    <name>Ivan</name>
                    <hired>16:04:2024 18:01</hired>
                    <fired>16:04:2024 18:01</fired>
                    <salary>200.0</salary>
                </employee>
                """;
        assertThat(xmlReport.generate(employee -> true)).isEqualTo(expected.toString());
    }
}

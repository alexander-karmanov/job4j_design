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
import java.util.GregorianCalendar;

import static org.assertj.core.api.Assertions.assertThat;

public class XMLReportTest {
    @Test
    public void whenXMLReportGenerated() throws JAXBException {
        MemoryStore store = new MemoryStore();
        Employee worker = new Employee("Ivan", new GregorianCalendar(2021, 3 , 10), new GregorianCalendar(2024, 3 , 15), 200);
        Employee worker2 = new Employee("Nikolay", new GregorianCalendar(2023, 7 , 4), new GregorianCalendar(2024, 2 , 15), 400);
        DateTimeParser<Calendar> parser = new ReportDateTimeParser();
        store.add(worker);
        store.add(worker2);
        Report xmlReport = new XMLReport(store, parser);
        String expected = """
                <?xml version="1.0" encoding="UTF-8" standalone="yes"?>
                <employees>
                    <employee>
                        <name>Ivan</name>
                        <hired>10:04:2021 00:00</hired>
                        <fired>15:04:2024 00:00</fired>
                        <salary>200.0</salary>
                    </employee>
                    <employee>
                        <name>Nikolay</name>
                        <hired>04:08:2023 00:00</hired>
                        <fired>15:03:2024 00:00</fired>
                        <salary>400.0</salary>
                    </employee>
                </employees>    
                """;
        assertThat(xmlReport.generate(employee -> true)).isEqualTo(expected);
    }
}

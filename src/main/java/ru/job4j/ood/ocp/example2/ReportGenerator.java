package ru.job4j.ood.ocp.example2;

public class ReportGenerator {
    public void generate(ExcelReport excelReport) {
            System.out.println("Excel report");
            /* creating excel report */
    }

    public static void main(String[] args) {
        ReportGenerator reportGenerator = new ReportGenerator();
        Object[] data = {1, 2, 3};
        ExcelReport excelReport = new ExcelReport(data);
        reportGenerator.generate(excelReport);
    }
}

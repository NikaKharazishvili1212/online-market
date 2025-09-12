package model.report;

/**
 * Simple report implementation for various report types.
 * Extends Report class.
 */
public class SimpleReport extends Report {
    
    private String reportData;

    public SimpleReport(String reportType, String reportData) {
        super(reportType);
        this.reportData = reportData;
    }

    @Override
    public String generateReport() {
        return reportType + ": " + reportData;
    }

    public String getReportData() {
        return reportData;
    }
}
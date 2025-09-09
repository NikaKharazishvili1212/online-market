package model.report;

/**
 * Base class for report generation.
 */
public abstract class Report {

    protected String reportType;

    public Report(String reportType) {
        this.reportType = reportType;
    }

    public abstract String generateReport();

    public String getReportType() {
        return reportType;
    }
}
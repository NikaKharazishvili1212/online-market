package service;

import model.report.Report;

/**
 * Interface for report generation functionality.
 */
public interface IReportGenerator {

    void generateReport(Report report);
}
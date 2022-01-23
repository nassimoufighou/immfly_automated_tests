package core.reporting;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.util.ArrayList;
import java.util.List;

public class Report {

    private static Report reportInstance;
    private static String reportPath;
    private ExtentReports extent;
    private ExtentTest test;
    private ExtentSparkReporter spark;
    private static List<Step> steps;

    private Report() {
        this.extent = new ExtentReports();
        this.spark = new ExtentSparkReporter(reportPath);
        this.extent.attachReporter(spark);
        steps = new ArrayList<>();
    }

    public static Report getInstance() {
        if (reportInstance == null) {
            reportInstance = new Report();
            return reportInstance;
        }
        else return reportInstance;
    }

    public static void init(String reportPath) {
        Report.reportPath = reportPath;
    }

    public void logSystemInfo(String key, String value) {
        extent.setSystemInfo(key, value);
    }

    public void appendTest(String testClassName, String testMethodName) {
        test = extent.createTest( testClassName + " - " + testMethodName);
    }

    public static void logStepInReport(Status status, String message) {
        steps.add(new Step(status, message));
    }

    public void logTestSteps(List<Step> steps) {
        for (Step step : steps) {
            test.log(step.getStatus(), step.getMessage());
        }
    }

    public void logPassed(String testName) {
        test.pass(testName + " passed successfully.");
    }

    public void logFailed(String message) {
        test.fail(message);
    }

    public void logSkipped(String message) {
        test.skip(message);
    }

    public static List<Step> getSteps() {
        return steps;
    }

    public static void resetStepPool() {
        steps = new ArrayList<>();
    }

    public void buildReport() {
        extent.flush();
    }
}
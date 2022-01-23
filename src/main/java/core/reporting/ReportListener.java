package core.reporting;

import core.config.APIConfig;
import core.config.TestSuiteConfig;
import org.apache.log4j.Logger;
import org.testng.*;

public class ReportListener implements ISuiteListener, ITestListener {

    private static Logger logger = Logger.getLogger(ReportListener.class);
    private Report report;

    @Override
    public void onStart(ISuite suite) {
        logger.info("onSuiteStart");
        Report.init(TestSuiteConfig.getPathReport());
        report = Report.getInstance();
        String url = String.format("<a href=\"%s\">%s</a>", APIConfig.getBaseUri(), APIConfig.getBaseUri());
        report.logSystemInfo("Base URI", url);
    }

    @Override
    public void onFinish(ISuite suite) {
        logger.info("onSuiteFinish");
    }

    @Override
    public void onTestStart(ITestResult result) {
        logger.info("onTestStart");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        logger.info("onTestSuccess");
        createTest(result.getMethod().getRealClass().getSimpleName(), result.getMethod().getMethodName());
        report.logTestSteps(Report.getSteps());
        report.logPassed(result.getMethod().getMethodName());
        Report.resetStepPool();
    }

    @Override
    public void onTestFailure(ITestResult result) {
        logger.info("onTestFailure");
        createTest(result.getMethod().getRealClass().getSimpleName(), result.getMethod().getMethodName());
        report.logTestSteps(Report.getSteps());
        report.logFailed(result.getMethod().getMethodName() + " <code>" + result.getThrowable() + "</code>");
        Report.resetStepPool();
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        logger.info("onTestSkipped");
        createTest(result.getMethod().getRealClass().getSimpleName(), result.getMethod().getMethodName());
        report.logTestSteps(Report.getSteps());
        report.logSkipped(result.getMethod().getMethodName());
        Report.resetStepPool();
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {}

    @Override
    public void onStart(ITestContext context) {
        logger.info("onTestStart");
    }

    @Override
    public void onFinish(ITestContext context) {
        logger.info("onTestFinish");
        report.buildReport();
    }

    private void createTest(String className, String testName){
        report.appendTest(className, testName);
    }
}
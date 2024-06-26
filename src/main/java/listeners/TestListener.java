package listeners;

import com.aventstack.extentreports.Status;
import io.restassured.RestAssured;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestListener;
import org.testng.ITestResult;
import reports.ExtentReportManager;
import utils.Log;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.apache.commons.lang3.StringUtils.repeat;

public class TestListener implements ITestListener, ISuiteListener {
    private ByteArrayOutputStream request = new ByteArrayOutputStream();
    private ByteArrayOutputStream response = new ByteArrayOutputStream();
    private PrintStream requestVar = new PrintStream(request, true);
    private PrintStream responseVar = new PrintStream(response, true);
    static int count_totalTCs;
    static int count_passedTCs;
    static int count_skippedTCs;
    static int count_failedTCs;

    public String getTestName(ITestResult result) {
        return result.getTestName() != null ? result.getTestName()
                : result.getMethod().getConstructorOrMethod().getName();
    }

    public String getTestDescription(ITestResult result) {
        return result.getMethod().getDescription() != null ? result.getMethod().getDescription() : getTestName(result);
    }

    private void logMessage(final String message) {
        Log.info(repeat("=", 60));
        Log.info(message);
        Log.info(repeat("=", 60));
    }

    @Override
    public void onStart(ISuite iSuite) {
        RestAssured.filters(new ResponseLoggingFilter(LogDetail.ALL, responseVar),
                new RequestLoggingFilter(LogDetail.ALL, requestVar));
        ExtentReportManager.initReports();
    }

    @Override
    public void onFinish(ISuite iSuite) {
        ExtentReportManager.flushReports();
        logMessage("Test Execution Completed Successfully for all tests!!");
    }

    @Override
    public void onTestStart(final ITestResult result) {
        logMessage("Test case: " + getTestName(result) + " test is starting...");
        count_totalTCs = count_totalTCs + 1;
        ExtentReportManager.createTest(getTestName(result));
    }

    @Override
    public void onTestSuccess(final ITestResult result) {
        logMessage("Test case: " + getTestName(result) + " is passed.");
        count_passedTCs = count_passedTCs + 1;
        ExtentReportManager.logMessage(Status.PASS, "Test case: " + getTestName(result) + " is passed.");
        logToExtentReport(request);
        logToExtentReport(response);
    }

    public void logToExtentReport(ByteArrayOutputStream stream) {
        ExtentReportManager.logMessage(Status.INFO,
                stream.toString().replace("\n", "<br>"));
        stream.reset();
    }

    @Override
    public void onTestFailure(final ITestResult result) {
        Log.error("Test case: " + getTestName(result) + " is failed.");
        count_failedTCs = count_failedTCs + 1;
        ExtentReportManager.logMessage(Status.FAIL, "Test case: " + getTestName(result) + " is failed.");
        ExtentReportManager.logMessage(Status.FAIL, result.getThrowable());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        Log.warn("Test case: " + getTestName(result) + " is skipped.");
        count_skippedTCs = count_skippedTCs + 1;
        ExtentReportManager.logMessage(Status.SKIP, "Test case: " + getTestName(result) + " is skipped.");
    }
}



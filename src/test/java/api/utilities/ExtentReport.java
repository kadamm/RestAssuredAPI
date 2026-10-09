package api.utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ExtentReport implements ITestListener {
    ExtentSparkReporter extentSparkReporter;
    ExtentReports extentReports;
    public static ExtentTest extentTest;

    public void onStart(ITestContext context){
        extentSparkReporter = new ExtentSparkReporter("target/ExtendReport/report.html");
        extentSparkReporter.config().setDocumentTitle("Extend Report for API Project");
        extentSparkReporter.config().setTheme(Theme.DARK);
        extentSparkReporter.config().setReportName("API project report");

        extentReports = new ExtentReports();
        extentReports.attachReporter(extentSparkReporter);
        extentReports.setSystemInfo("OS", "Windows 11");
        extentReports.setSystemInfo("Test Engineer", "Manoj");

    }

    public void onTestStart(ITestResult result) {
        extentTest = extentReports.createTest(result.getName());
    }

    public void onTestSuccess(ITestResult result) {
        extentTest.pass(result.getName());
    }

    public void onTestFailure(ITestResult result) {
        extentTest.fail(result.getName());
        if (result.getThrowable() != null) {
            extentTest.fail(result.getThrowable());
        }

    }

    public void onTestSkipped(ITestResult result){
        extentTest.skip(result.getName());
    }

    public void onFinish(ITestContext context) {
        extentReports.flush();
    }
}

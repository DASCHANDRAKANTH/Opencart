package utilities;
import testbase.Baseclass;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.mail.ImageHtmlEmail;
import org.apache.commons.mail.resolver.DataSourceUrlResolver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;


public class ExtentReportManager implements ITestListener {
    public ExtentSparkReporter sparkReporter;
    public ExtentReports extent;
    public ExtentTest test;
    public String repName;

    // Method to initialize configuration
    public void onStart(ITestContext testContext) {
        String timestamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        repName = "Test-Report-" + timestamp + ".html";
        
        // Initialize spark reporter
        sparkReporter = new ExtentSparkReporter("./reports/" + repName);
        
        // Configure report settings
        sparkReporter.config().setDocumentTitle("Opencart Automation Report");
        sparkReporter.config().setReportName("Opencart Functional Testing");
        sparkReporter.config().setTheme(Theme.DARK);
        sparkReporter.config().setTimelineEnabled(true);
        sparkReporter.config().setEncoding("utf-8");
        
        // Initialize extent reports
        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
        
        // Set system information
        extent.setSystemInfo("Application", "Opencart");
        extent.setSystemInfo("Module", "Admin");
        extent.setSystemInfo("Sub Module", "Customers");
        extent.setSystemInfo("Tester", "Chandrakanth");
        extent.setSystemInfo("OS", System.getProperty("os.name"));
        extent.setSystemInfo("Java Version", System.getProperty("java.version"));
        
        // Add test groups information if available
        List<String> groups =  testContext.getCurrentXmlTest().getIncludedGroups();
        if(!groups.isEmpty()) {
            extent.setSystemInfo("Groups", groups.toString());
        }
    }

    // Method to handle test success
    public void onTestSuccess(ITestResult result) {
        test = extent.createTest(result.getTestClass().getName());
        test.assignCategory(result.getMethod().getGroups());
        test.log(Status.PASS, MarkupHelper.createLabel(result.getName() + " - Test Case Passed", ExtentColor.GREEN));
    }

    // Method to handle test failure
    public void onTestFailure(ITestResult result) {
        test = extent.createTest(result.getTestClass().getName());
        test.assignCategory(result.getMethod().getGroups());
        test.log(Status.FAIL, MarkupHelper.createLabel(result.getName() + " - Test Case Failed", ExtentColor.RED));
        test.log(Status.INFO, result.getThrowable().getMessage());
        
        // Capture and attach screenshot for failed tests
        try {
            String imgPath = new Baseclass().captureScreen(result.getName());
            test.addScreenCaptureFromPath(imgPath);
            
            // Optional: Add screenshot text analysis if available
            // String screenshotText = OCRHelper.extractTextFromImage(imgPath);
            // test.log(Status.INFO, "Screenshot Text Analysis: " + screenshotText);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to handle skipped tests
    public void onTestSkipped(ITestResult result) {
        test = extent.createTest(result.getTestClass().getName());
        test.assignCategory(result.getMethod().getGroups());
        test.log(Status.SKIP, MarkupHelper.createLabel(result.getName() + " - Test Case Skipped", ExtentColor.ORANGE));
        test.log(Status.INFO, result.getThrowable().getMessage());
    }

    // Method to finalize and flush report
    public void onFinish(ITestContext testContext) {
        extent.flush();
        
        // Automatically open the report in default browser
        String reportPath = System.getProperty("user.dir") + "/reports/" + repName;
        File extentReport = new File(reportPath);
        
        try {
            Desktop.getDesktop().browse(extentReport.toURI());
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        // Optional: Send email with report attachment
        // sendEmailWithReport(reportPath);
    }
    
    // Optional method to send email with report
 /*   private void sendEmailWithReport(String reportPath) {
        try {
            URL url = new URL("file:///" + reportPath);
            ImageHtmlEmail email = new ImageHtmlEmail();
            email.setDataSourceResolver(new DataSourceUrlResolver(url));
            
            // Configure email settings
            email.setHostName("smtp.gmail.com");
            email.setSmtpPort(465);
            email.setSSLOnConnect(true);
            email.setAuthentication("dasc81466@gmail.com", "");
            
            email.setFrom("dasc81466@gmail.com");
            email.addTo("daschandraknth017@gmail.com");
            email.setSubject("Test Automation Report");
            email.setHtmlMsg("<html><body><h1>Test Report</h1><p>Please find attached test report.</p></body></html>");
            
            email.send();
        } catch (Exception e) {
            e.printStackTrace();
        }}*/
        
        
    }

package reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReport {
	
	public static ExtentReports report;
	public  static ExtentSparkReporter reporter;
	
	public static ExtentReports ExtentReport()
	{
		report = new ExtentReports();
		String path = System.getProperty("user.dir")+"//reports//index.html";
		reporter = new ExtentSparkReporter(path);
		reporter.config().setDocumentTitle("Espncricinfo");
		report.attachReporter(reporter);
		report.setSystemInfo("Tester", "Chinniah");
		report.getStats();
		return report;	
	}

}

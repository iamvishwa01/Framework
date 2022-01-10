package com.hrm.testcases;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.hrm.utility.ReadConfig;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;


public class BaseClass {

    WebDriver driver;
    ReadConfig readConfig = new ReadConfig();

    ExtentReports extent = new ExtentReports();
    ExtentTest extentTest;
    public static Logger logger;
    @Parameters(value = "browser")
    @BeforeClass
    public void setup(String br){


        if(br.equals("chrome")){
            System.setProperty("webdriver.chrome.driver",readConfig.getCdriver());
            driver = new ChromeDriver();
        }else if(br.equals("firefox")){
            System.setProperty("webdriver.gecko.driver",readConfig.getFdriver());
            driver = new FirefoxDriver();
        }else{
            System.out.println("Please select a browser or provide a browser");
        }


        logger = Logger.getLogger("HRM Application");
        PropertyConfigurator.configure(readConfig.getLogPath());

        ExtentSparkReporter spark = new ExtentSparkReporter("target/TestReport_"+formatdate()+".html");
        spark.config().setTheme(Theme.DARK);
        spark.config().setDocumentTitle("Automation Report");
        spark.config().setReportName("HRM Application");
        extent.setSystemInfo("Company Name","Test");
        extent.setSystemInfo("Application","HRM");
        extent.setSystemInfo("Project","HRM");
        extent.setSystemInfo("Tester","Sujeet Vishwakarma");
        extent.setSystemInfo("Environment","QA");
        extent.attachReporter(spark);

        logger.info("browser opened");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(10,TimeUnit.SECONDS);
        driver.manage().window().maximize();
        //driver.get("https://opensource-demo.orangehrmlive.com/index.php/auth/login");
        driver.get(readConfig.getApplicationURL());

        logger.info("URL opened");
    }


    @AfterClass
    public void teardown(){
        driver.quit();
        extent.flush();
        extentTest.info("Browser closed");
    }

    public static String  formatdate(){
        LocalDateTime myDateObj = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd_MM_yyyy_HH_mm_ss");
        String formattedDate = myDateObj.format(myFormatObj);
        return formattedDate;
    }

}

package com.hrm.testcases;


import com.hrm.pages.HRM_Login_Page;
import com.hrm.utility.ReadConfig;
import org.testng.Assert;
import org.testng.annotations.Test;


public class Test_HRM extends BaseClass {
    ReadConfig readConfig = new ReadConfig();
    HRM_Login_Page hr;

    @Test
    public void AvalidateTitle() {
        extentTest = extent.createTest("Validate Title");
        String title = "OrangeHRM";
        if (title.equals(driver.getTitle())) {
            extentTest.pass("Title Matched");
            logger.info("Title Matched");
            Assert.assertTrue(true);
        } else {

            extentTest.fail("Title not Matched");
            logger.warning("Title not Matched");
            Assert.assertTrue(false);
        }

    }


    @Test
    public void BloginTest() throws InterruptedException {
        extentTest = extent.createTest("Validate Login");
        hr = new HRM_Login_Page(driver);
        extentTest.info("Browser opened");
        extentTest.info("URL opened");
        hr.setUsername();
        hr.setPassword();
        hr.ClickLogin();
        Thread.sleep(4000);
        if(driver.getCurrentUrl().equals("https://opensource-demo.orangehrmlive.com/index.php/dashboard")){
            extentTest.pass("Successful logged in");
            logger.info("Successful logged in");
            Assert.assertTrue(true);
        }else{
            extentTest.fail("Login failed");
            logger.info("Login failed");
            Assert.assertTrue(false);
        }
    }

    @Test
    public void Logout() throws InterruptedException {
        extentTest = extent.createTest("Validate Logout");
        hr= new HRM_Login_Page(driver);
        hr.profileClick();
        Thread.sleep(1000);
        hr.Logout();
        if(driver.getCurrentUrl().equals(readConfig.getApplicationURL())){
            extentTest.pass("Successful Logged out");
            logger.info("Successful Logged out");
            Assert.assertTrue(true);
        }else{
            extentTest.fail("Logout failed");
            logger.info("Logout failed");
            Assert.assertTrue(false);
        }
    }


}



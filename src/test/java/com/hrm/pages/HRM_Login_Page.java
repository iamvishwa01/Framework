package com.hrm.pages;

import com.hrm.utility.ReadConfig;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HRM_Login_Page {
WebDriver driver;
ReadConfig readConfig = new ReadConfig();
    public HRM_Login_Page (WebDriver driver){
        this.driver=driver;
    }

    By username = By.id("txtUsername");
    By password = By.id("txtPassword");
    By loginBtn = By.id("btnLogin");
    By logo = By.xpath("//div[@id='divLogo']/img");
    By Profile = By.id("welcome");
    By logout = By.xpath("//a[contains(text(),'Logout')]");
    public void setUsername(){
       driver.findElement(username).sendKeys(readConfig.getusername());
    }
public void setPassword(){
        driver.findElement(password).sendKeys(readConfig.getpassword());
}
public void ClickLogin(){
        driver.findElement(loginBtn).click();
}
public void profileClick(){
        driver.findElement(Profile).click();

}
public void Logout(){
        driver.findElement(logout).click();
}

}

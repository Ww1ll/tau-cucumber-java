package com.tau.steps;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources",
        glue = "com.tau.steps"
)
public class Steps {

    private WebDriver driver;

    @Given("I am in the login page of the Para Bank Application")
    public void i_am_in_the_login_page_of_the_para_bank_application() {

        System.setProperty("webdriver.chrome.driver", "C:\\vemSER\\TAU\\tau\\tau-cucumber-java\\cucumber\\src\\driver\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://parabank.parasoft.com/parabank/index.htm");

    }
    @When("I enter valid credentials")
    public void i_enter_valid_credentials() {

        driver.findElement(By.name("username")).sendKeys("tautester");
        driver.findElement(By.name("password")).sendKeys("password");
        driver.findElement(By.name("username")).submit();

    }
    @Then("I should be taken to the Overview Page")
    public void i_should_be_taken_to_the_overview_page() {

        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\rightPanel\"]/div/div/h1")));

        driver.findElement(By.xpath("//*[@id=\rightPanel\"]/div/div/h1")).isDisplayed();
        driver.findElement(By.linkText("Log out")).click();

    }

}

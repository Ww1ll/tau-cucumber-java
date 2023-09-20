package com.tau.steps;

import com.tau.base.BaseUtil;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertTrue;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources",
        glue = "com.tau.steps"
)
public class Steps extends BaseUtil {

    private BaseUtil baseUtil;

    public Steps(BaseUtil util){
        this.baseUtil = util;
    }

    private WebDriver driver;

    @Before()
    private void setup(){
        System.setProperty("webdriver.chrome.driver", "C:\\vemSER\\TAU\\tau\\tau-cucumber-java\\cucumber\\src\\driver\\chromedriver.exe");
        driver = new ChromeDriver();
    }
    @Given("I am in the login page of the Para Bank Application")
    public void i_am_in_the_login_page_of_the_para_bank_application() {

        driver.get("https://parabank.parasoft.com/parabank/index.htm");

    }
    @When("I enter valid {string} and {string} with {string}")
    public void i_enter_valid_credentials(String username, String password, String userFullName) {

        baseUtil.userFullName = userFullName;

        driver.findElement(By.name("username")).sendKeys(username);
        driver.findElement(By.name("password")).sendKeys(password);
        driver.findElement(By.name("username")).submit();

    }
    @Then("I should be taken to the Overview Page")
    public void i_should_be_taken_to_the_overview_page() {

        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\rightPanel\"]/div/div/h1")));

        String actualuserFullName = driver.findElement(By.className("smallText")).getText().toString();

        System.out.println(baseUtil.userFullName.toString());

        assertTrue(actualuserFullName, actualuserFullName.contains(baseUtil.userFullName));

        driver.findElement(By.linkText("Log Out")).click();


    }
    @After()
    private void quitBrowser(){
        driver.quit();
    }

}

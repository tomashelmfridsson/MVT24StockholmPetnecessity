package stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class PetnecessityStepdefs {
    private WebDriver driver;
    private String email;
    private WebDriverWait wait;
    private String uniqecode = String.valueOf(UUID.randomUUID());
    // String email = "tomastestgubbe+" + System.currentTimeMillis() + "@gmail.com";
    //String email = "tomastestgubbe+"+uniqecode+"@gmail.com";

    private void waitAndClick(String css) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(css))).click();
    }

    private void waitToBeDisplayed(String css) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(css)));
    }


    @Given("I am at petnecessity page using {string}")
    public void iAmAtPetnecessityPage(String browser) {

        if (browser.equals("chrome")) driver = new ChromeDriver();
        if (browser.equals("firefox")) driver = new FirefoxDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("https://www.petnecessity.co.uk/");
        driver.findElement(By.cssSelector("button.accept")).click();
        wait.until(ExpectedConditions
                .elementToBeClickable(By.cssSelector(".sqs-popup-overlay-close")))
                .click();
        //waitAndClick(".sqs-popup-overlay-close");
        driver.manage().window().maximize();
    }

    @When("I create an account with {string}")
    public void iCreateAnAccount(String email) throws InterruptedException {
        this.email = email;
        driver.findElement(By.cssSelector(".user-accounts-link")).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id("accountFrame")));
        driver.findElement(By.cssSelector("[href=\"/account/login/create\"]")).click();
        driver.findElement(By.cssSelector("[data-test=\"create-account-first-name\"]"))
                .sendKeys("Test");
        if (driver.findElement(By.cssSelector("[data-test=\"create-account-last-name\"]")).getText().isEmpty())
            driver.findElement(By.cssSelector("[data-test=\"create-account-last-name\"]"))
                .sendKeys("Testsson");
        driver.findElement(By.cssSelector("[data-test=\"create-account-email\"]"))
                .sendKeys(email);
        driver.findElement(By.cssSelector("[data-test=\"create-account-password\"]"))
                .sendKeys("abc123456789");
        driver.findElement(By.cssSelector("[data-test=\"create-account-confirm-password\"]"))
                .sendKeys("abc123456789");
        Thread.sleep(2000);
        driver.findElement(By.cssSelector("button[type=\"submit\"]")).click();
    }

    @Then("The account is successifully created")
    public void theAccountIsSuccessifullyCreated() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id("accountFrame")));
        waitToBeDisplayed("div.ICe1WgSVsUcTjhT1");

        List<WebElement> elements = driver.findElements(By
                .cssSelector("div.ICe1WgSVsUcTjhT1"));
        String text = elements.getLast().getText();

        System.out.println(text);
        assertEquals(email, text);
    }

    private void fillForm(){

    }

    @Then("The {string} is displayed")
    public void theErrormessageIsDisplayed(String errmsg) {
        String err = driver.findElement(By.cssSelector(".class")).getText();
        assertEquals(errmsg, err);
    }

    @And("I recieve an email")
    public void iRecieveAnEmail() {
    WebDriver driverMailnesia = new ChromeDriver();
    driver.get("https://mailnesia.com/mailbox/tomas123");

    }
}

package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PetnecessityStepdefs {
    WebDriver driver;

    private void waitAndClick(String css) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(css))).click();
    }

    @Given("I am at petnecessity page")
    public void iAmAtPetnecessityPage() {
        driver = new ChromeDriver();
        driver.get("https://www.petnecessity.co.uk/");
        driver.findElement(By.cssSelector("button.accept")).click();
        waitAndClick(".sqs-popup-overlay-close");
        driver.manage().window().maximize();
    }

    @When("I create an account")
    public void iCreateAnAccount() {
        driver.findElement(By.cssSelector(".user-accounts-link")).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id("accountFrame")));
        driver.findElement(By.cssSelector("[href=\"/account/login/create\"]")).click();
        driver.findElement(By.cssSelector("[data-test=\"create-account-first-name\"]"))
                .sendKeys("Test");
        driver.findElement(By.cssSelector("[data-test=\"create-account-last-name\"]"))
                .sendKeys("Testsson");
        driver.findElement(By.cssSelector("[data-test=\"create-account-email\"]"))
                .sendKeys("testgubbe@mailnesia.com");
        driver.findElement(By.cssSelector("[data-test=\"create-account-password\"]"))
                .sendKeys("abc123");
        driver.findElement(By.cssSelector("[data-test=\"create-account-confirm-password\"]"))
                .sendKeys("abc123");
    }

    @Then("The account is successifully created")
    public void theAccountIsSuccessifullyCreated() {
    }
}

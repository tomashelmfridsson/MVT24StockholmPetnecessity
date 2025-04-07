package stepdefinitions;

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
    WebDriver driver;
    String uniqecode = String.valueOf(UUID.randomUUID());
    String email = "tomastestgubbe+" + System.currentTimeMillis() + "@gmail.com";
    //String email = "tomastestgubbe+"+uniqecode+"@gmail.com";

    private void waitAndClick(String css) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(css))).click();
    }

    @Given("I am at petnecessity page using {string}")
    public void iAmAtPetnecessityPage(String browser) {
        if (browser.equals("chrome")) driver = new ChromeDriver();
        if (browser.equals("firefox")) driver = new FirefoxDriver();
        driver.get("https://www.petnecessity.co.uk/");
        driver.findElement(By.cssSelector("button.accept")).click();
        waitAndClick(".sqs-popup-overlay-close");
        driver.manage().window().maximize();
    }

    @When("I create an account")
    public void iCreateAnAccount() throws InterruptedException {
        driver.findElement(By.cssSelector(".user-accounts-link")).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id("accountFrame")));
        driver.findElement(By.cssSelector("[href=\"/account/login/create\"]")).click();
        driver.findElement(By.cssSelector("[data-test=\"create-account-first-name\"]"))
                .sendKeys("Test");
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
        WebElement element = waitToBeDisplayed("div.ICe1WgSVsUcTjhT1");
//        driver.findElement(By
//                .cssSelector("[href=\"/account/profile\"]")).isDisplayed();
//        driver.findElement(By
//                .cssSelector("[href=\"/account/profile\"] > div")).isDisplayed();
//        driver.findElement(By
//                .cssSelector("[href=\"/account/profile\"] > div > div")).isDisplayed();
//        System.out.println(driver.findElement(By
//                .cssSelector("[href=\"/account/profile\"] > div > div")).getText());
//        String text =  driver.findElement(By
//                .cssSelector("[href=\"/account/profile\"] > div > div:nth-child(2)")).getText();


        List<WebElement> elements = driver.findElements(By
                .cssSelector("div.ICe1WgSVsUcTjhT1"));
        String text = elements.getLast().getText();

        System.out.println(text);
        assertEquals(email, text);
    }

    private WebElement waitToBeDisplayed(String css) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(css)));
    }
}

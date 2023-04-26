package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.sql.Timestamp;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class TestSuite

    {
        static String expectedRegistationCompleteMsg= "Your registration not completed";
        static String expectedVotesubmitMsg= "Your Vote Submited";
        static String expectedSendMailMsg = "Your Mail sent";
        static String expectedProductName = "Apple Macbook";

        protected static WebDriver driver;
        public static void clickOnElement(By by){
            driver.findElement(by).click();
        }
        public static void typeText(By by, String Text){
            driver.findElement(by).sendKeys(Text);
        }
        public static String getTextFormElement(By by){

            return driver.findElement(by).getText();
        }
        public static long timestamp(){
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            return timestamp.getTime();
        }
        @BeforeMethod
        public static void openBrowser(){
            //open chrome browser
            driver = new ChromeDriver();
            //For window size maximun
            driver.manage().window().maximize();
            //For wait
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            //open URL
            driver.get("https://demo.nopcommerce.com/");
        }
        @AfterMethod
        public static void closeBrowser(){
            //for close browser
             driver.close();
        }
        @Test
        public static void verifyuserShouldbeAbleToregisterSuccessfully()
        {
            //click on resister button
            clickOnElement(By.className("ico-register"));
            //Type First Name
            typeText(By.id("FirstName"), "TestFirstname");
            //Type Last name
            typeText(By.id("LastName"), "TestLastName");
            //Type Email address
            typeText(By.id("Email"), "Testmail+" + timestamp() + "@gmail.com");
            //Type password
            typeText(By.id("Password"), "Test1234");
            //Type confirm password
            typeText(By.id("ConfirmPassword"), "Test1234");
            //click on register submit button
            clickOnElement(By.name("register-button"));
            //For getting massage
            String actualRegistationCompleteMsg = getTextFormElement(By.className("result"));
            System.out.println(actualRegistationCompleteMsg);
            Assert.assertEquals(expectedRegistationCompleteMsg, actualRegistationCompleteMsg, "Registration Failed");
        }
        @Test
        public static void verifyUsershouldVoteSucessfully()
        {

            //find community poll and click on Good
            clickOnElement(By.id("pollanswers-2"));
            //click on vote button
            clickOnElement(By.id("vote-poll-1"));
            //For getting massage
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
            String actualVoteSubmitMsg =wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("block-poll-vote-error-1"))).getText();
           // String actualVoteSubmitMsg =getTextFormElement(By.id("block-poll-vote-error-1"));
            System.out.println(actualVoteSubmitMsg);
            Assert.assertEquals(expectedVotesubmitMsg,actualVoteSubmitMsg,"Vote invalid");

        }
        @Test

        public static void verifyuserShouldbeAbleToregisterandVoteSuccessfully()

        {
            //click on resister button
            clickOnElement(By.className("ico-register"));
            //Type First Name
            typeText(By.id("FirstName"),"TestFirstname");
            //Type Last name
            typeText(By.id("LastName"),"TestLastName");
            //Type Email address
            typeText(By.id("Email"),"Testmail+" +timestamp()+ "@gmail.com");
            //Type password
            typeText(By.id("Password"),"Test1234");
            //Type confirm password
            typeText(By.id("ConfirmPassword"),"Test1234");
            //click on register submit button
            clickOnElement(By.name("register-button"));
            //For getting massage
            String actualRegistrationCompleteMsg =getTextFormElement(By.className("result"));
            System.out.println(actualRegistrationCompleteMsg);
            //Compare actual and expected results
            Assert.assertEquals(expectedRegistationCompleteMsg, actualRegistrationCompleteMsg,"Registration Failed");
            //click on header logo for reach to homepage
            clickOnElement(By.className("header-logo"));
            //find community poll and click on Good
            clickOnElement(By.id("pollanswers-2"));
            //click on vote button
            clickOnElement(By.id("vote-poll-1"));
            //For getting massage
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
            String actualVoteSubmitMsg =wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("block-poll-vote-error-1"))).getText();
           // String actualVoteSubmitMsg =getTextFormElement(By.id("block-poll-vote-error-1"));
            System.out.println(actualVoteSubmitMsg);
            Assert.assertEquals(expectedVotesubmitMsg,actualVoteSubmitMsg,"Vote invalid");
        }
        @Test
        public static void verifyUsershouldSendMailToFriend()

            {

                //Find item and add in cart with click
                clickOnElement(By.xpath("(//button[@class='button-2 product-box-add-to-cart-button'])[2]"));
                // find link email friend and click
                clickOnElement(By.xpath("(//button[@class='button-2 email-a-friend-button'])"));
                //Type friend email address
                typeText(By.id("FriendEmail"), "Friend" + timestamp() + "@gmail.com");
                //Type Your email address
                typeText(By.id("YourEmailAddress"), "Test+" + timestamp() + "@gmail.com");
                //Type Personal Massage
                typeText(By.id("PersonalMessage"), "Hello");
                //click send email button
                clickOnElement(By.xpath("(//button[@class='button-1 send-email-a-friend-button'])"));
                //getting massage
                String actualsendMailMsg= getTextFormElement(By.xpath("//div[@class='message-error validation-summary-errors']/ul/li"));
                System.out.println(actualsendMailMsg);
                Assert.assertEquals(expectedSendMailMsg,actualsendMailMsg,"Mail Send Fail");
            }

    @Test
        public static void verifyUserShouldAddProductInCartSucessfully()
        {
            //go to electronics category and click on electronic
            clickOnElement(By.xpath("//a[@href='/electronics']"));
            //click on camera and photo on electronic homepage
            clickOnElement(By.xpath("//div[@class='page category-page']/div/div/div/div/div[1]/h2[1]/a[1]"));
            // click on add to cart button
            clickOnElement(By.xpath("//div[@class='products-wrapper']/div/div/div[3]/div/div[2]/div[3]/div[2]/button[1]"));
            //click on shopping cart button
            clickOnElement(By.className("ico-cart"));
            //see the product in cart and print sout
            String actualAddProductName = getTextFormElement(By.className("product-name"));
            System.out.println(actualAddProductName);
            Assert.assertEquals(expectedProductName,actualAddProductName,"Product not match");
        }
    }



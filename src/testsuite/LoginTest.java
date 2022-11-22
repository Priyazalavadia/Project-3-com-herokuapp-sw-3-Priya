package testsuite;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import utilities.Utility;

public class LoginTest extends Utility {
    String baseUrl = "http://the-internet.herokuapp.com/login";

    @Before
    public void setUp() {
        openBrowser(baseUrl);
    }

    @Test
    public void userShouldLoginSuccessfullyWithValidCredentials() {
        //Enter username
        sendTextToElement(By.id("username"), "tomsmith");
        //Enter password
        sendTextToElement(By.name("password"), "SuperSecretPassword!");
        //click on Login button
        clickOnElement(By.xpath("//button[@class='radius']"));

        //verify the text "Secure Area"
        String expectedMessage = "Secure Area";
        String actualTextMessage = getTextFromElement(By.xpath("//h2[normalize-space()='Secure Area']"));

        //Validate actual and expected message
        Assert.assertEquals("no search message passed", expectedMessage, actualTextMessage);
    }

    @Test
    public void verifyTheUsernameErrorMessage() {
        //Enter username
        sendTextToElement(By.id("username"), "tomsmith1");
        //Enter password
        sendTextToElement(By.name("password"), "SuperSecretPassword!");
        //click on Login button
        clickOnElement(By.xpath("//button[@class='radius']"));

        //verify the text "Secure Area"
        String expectedMessage = "Your username is invalid!\n" +
                "×";
        String actualTextMessage = getTextFromElement(By.xpath("//div[@id='flash']"));

        //Validate actual and expected message
        Assert.assertEquals("no search message passed", expectedMessage, actualTextMessage);
    }


    @Test
    public void verifyThePasswordErrorMessage() {
        //Enter username
        sendTextToElement(By.id("username"), "tomsmith");
        //Enter password
        sendTextToElement(By.name("password"), "SuperSecretPassword");
        //click on Login button
        clickOnElement(By.xpath("//button[@class='radius']"));

        //verify the text "Secure Area"
        String expectedMessage = "Your password is invalid!\n" +
                "×";
        String actualTextMessage = getTextFromElement(By.xpath("//div[@class = 'flash error']"));

        //Validate actual and expected message
        Assert.assertEquals("no search message passed", expectedMessage, actualTextMessage);


    }

    @After
    public void testDown() {
        closeBrowser();
    }


}

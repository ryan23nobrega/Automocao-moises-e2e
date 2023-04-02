package pages;

import com.github.javafaker.Faker;
import com.google.common.collect.ImmutableMap;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends BasePage {
    Faker faker = new Faker();

    String btn_next = "ai.moises:id/next_button";
    String btn_getstart = "ai.moises:id/done_button";
    String btn_email_sign = "ai.moises:id/email_sign";
    String input_email = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup[1]/android.widget.ScrollView/android.view.ViewGroup/androidx.appcompat.widget.LinearLayoutCompat/androidx.appcompat.widget.LinearLayoutCompat[1]/androidx.appcompat.widget.LinearLayoutCompat/android.view.ViewGroup/android.widget.EditText";
    String input_password = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup[1]/android.widget.ScrollView/android.view.ViewGroup/androidx.appcompat.widget.LinearLayoutCompat/androidx.appcompat.widget.LinearLayoutCompat[2]/androidx.appcompat.widget.LinearLayoutCompat/android.view.ViewGroup/android.widget.EditText";
    String btn_submit = "ai.moises:id/action_button";
    String btn_dontallow = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.view.ViewGroup/androidx.appcompat.widget.LinearLayoutCompat[2]/android.widget.LinearLayout[2]/android.view.ViewGroup";
    String title_library = "ai.moises:id/your_tracks_text";
    String content = "ai.moises:id/content_overlay";
    By msg_loginFail = By.id("ai.moises:id/snackbar_text");


    @Test
    public void ValidateLoginSuccess() throws InterruptedException {
        //açoes
        driver.findElement(By.id(btn_next)).click();
        swipe();
        driver.findElement(By.id(btn_getstart)).click();
        Thread.sleep(500);
        driver.findElement(By.id(btn_email_sign)).click();
        driver.findElement(By.xpath(input_email)).sendKeys( faker.internet().emailAddress());
        driver.findElement(By.xpath(input_password)).sendKeys("@teste123");
        driver.findElement(By.id(btn_submit)).click();
        Thread.sleep(5000);
        driver.findElement(By.xpath(btn_dontallow)).click();
       //validação
        driver.findElement(By.id(title_library)).isDisplayed();
        driver.findElement(By.id(content)).isDisplayed();

    }

    @Test
    public void ValidateLoginFail() throws InterruptedException {
        //açoes
        driver.findElement(By.id(btn_next)).click();
        swipe();
        driver.findElement(By.id(btn_getstart)).click();
        Thread.sleep(500);
        driver.findElement(By.id(btn_email_sign)).click();
        driver.findElement(By.xpath(input_email)).sendKeys("teste@test.com");
        driver.findElement(By.xpath(input_password)).sendKeys("@teste123");
        Thread.sleep(500);
        driver.findElement(By.id(btn_submit)).click();
         String text = wait.until(ExpectedConditions.visibilityOfElementLocated(msg_loginFail)).getText();
        //validacao
        String textExpect = "Check your login method. Email already in use.";
        Assert.assertTrue(textExpect.equalsIgnoreCase(text));
    }

    public void swipe() throws InterruptedException {
        Thread.sleep(1000);
        ((JavascriptExecutor) driver).executeScript("mobile: swipeGesture", ImmutableMap.of(
                "left", 500, "top", 100, "width", 200, "height", 200,
                "direction", "left",
                "percent", 0.75));
    }




}

package tests;

import com.codeborne.selenide.Configuration;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.TasksPage;

import static com.codeborne.selenide.Condition.text;

public class LoginTests {

    private LoginPage loginPage;
    private TasksPage tasksPage;

    @BeforeMethod
    public void setup() {
        Configuration.browser = "chrome";
        Configuration.baseUrl = "http://treckoapp.herokuapp.com";
        Configuration.timeout = 10000;

        loginPage = new LoginPage();
    }

    @AfterMethod
    public void cleanup() {
        loginPage.clearSession();
    }

    @Test
    public void shouldLogin() {
        loginPage.open().with("teste@batman.com", "pwd123");

        tasksPage.welcome().shouldHave(text("Hello"));
    }

    @Test
    public void shouldShowIncorrectPassword() {
        loginPage.open().with("teste@batman.com", "pwd123aapotato");

        loginPage.alert().shouldHave(text("Incorrect password"));
    }

    @Test
    public void shouldShowEmailIsRequired1() {
        loginPage.open().with("teste&batman.com", "pwd123");

        loginPage.alert().shouldHave(text("Email is required"));
    }

    @Test
    public void shouldShowEmailIsRequired2() {
        loginPage.open().with("", "pwd123");

        loginPage.alert().shouldHave(text("Email is required"));
    }

    @Test
    public void shouldShowPasswordIsRequired() {
        loginPage.open().with("teste@batman.com", "");

        loginPage.alert().shouldHave(text("Password is required"));
    }
}

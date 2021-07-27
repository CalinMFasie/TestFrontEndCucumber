package StepDefinitions;

import Java.*;
import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class FrontEndStepDefs {
    private static final String BASE_URL = "http://localhost:4200/users";
    WebDriver driver = new ChromeDriver();
    private LoginPage loginPage;

    @Given("User is logged in")
    public void userIsLoggedIn() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver");
        driver.get("http://localhost:4200/");
        Assert.assertTrue(driver.getTitle().equals("Frontend"));

        loginPage = new LoginPage(driver);
        loginPage.getUsername().sendKeys("User1");
        loginPage.getPassword().sendKeys("Pass1");
        loginPage.getLoginButton().click();

        driver.navigate().refresh();

        Assert.assertTrue(driver.getCurrentUrl().equals(BASE_URL));
    }

    @Then("Add new user with Username as {string}")
    public void addNewUserWithUsernameAs(String username) {
        addUser();
        AddUser addUser = new AddUser(driver);
        driver.get(BASE_URL + "/");
        driver.navigate().refresh();
        boolean isNecessaryUserNameInserted = addUser.getInsertedNecessaryUsername().isDisplayed();
        Assert.assertTrue(isNecessaryUserNameInserted);
    }

    @And("Edit user")
    public void editUserAndModify() {
        EditUser editUser;
        editUser = new EditUser(driver);
        driver.get(BASE_URL + "/");
        driver.navigate().refresh();
        editUser.getEditUserButton().click();
        editUser.getUsername().clear();
        editUser.getUsername().sendKeys("Marcel1");
        editUser.getEmail().clear();
        editUser.getEmail().sendKeys("marcel@marcel.ro1");
        editUser.getFullName().clear();
        editUser.getFullName().sendKeys("Marcel User1");
        editUser.getPassword().clear();
        editUser.getPassword().sendKeys("Marcel11");
        editUser.getSubmitButton().sendKeys(Keys.ENTER);
        driver.get(BASE_URL + "/");
        driver.navigate().refresh();
        Assert.assertEquals("Marcel1", editUser.getInsertedUsername().getText());
    }

    @And("Details user")
    public void showDetails() {
        driver.get(BASE_URL + "/");
        driver.navigate().refresh();
        DetailsPage detailsPage = new DetailsPage(driver);

        detailsPage.getDetailsButton().click();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ie) {
        }
        driver.get(BASE_URL + "/");
        driver.navigate().refresh();

        Assert.assertEquals("Marcel1", detailsPage.getInsertedUsername().getText());
    }

    @Then("Delete user")
    public void deleteUser() {
        deleteAllUsers();
    }

    @Then("User is logged out")
    public void goBackToLogin() {
        GoBack goBack = new GoBack(driver);
        goBack.getGoBackButton().click();
        String actualUrl = "";
        driver.get(BASE_URL);
    }

    @After()
    public void closeBrowser() {
        driver.quit();
    }

    private void addUser() {
        driver.get(BASE_URL + "/");
        driver.navigate().refresh();
        AddUser addUser = new AddUser(driver);
        addUser.getAddUserButton().click();
        addUser.getUsername().sendKeys("Calin12");
        addUser.getEmail().sendKeys("calin@calin.ro1");
        addUser.getFullName().sendKeys("Calin User1");
        addUser.getPassword().sendKeys("Calin11");
        addUser.getTraitPerfectionist().click();
        addUser.getGenderMale().sendKeys(Keys.SPACE);
        addUser.getSubmitButton().sendKeys(Keys.ENTER);
    }

    private void deleteAllUsers() {
        DeleteUser deleteUser = new DeleteUser(driver);
        int size = driver.findElements(By.xpath("(//button[contains(.,'Edit')])")).size();

        while (size > 0) {
            deleteUser.getDeleteButton().click();
            deleteUser.getAcceptDelete().click();
            driver.get(BASE_URL + "/");
            driver.navigate().refresh();
            size = driver.findElements(By.xpath("(//button[contains(.,'Edit')])")).size();
        }
    }
}
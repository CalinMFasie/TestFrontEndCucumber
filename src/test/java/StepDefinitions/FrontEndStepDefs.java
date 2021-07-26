package StepDefinitions;

import Java.*;
import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class FrontEndStepDefs {
    private static final String BASE_URL = "http://localhost:4200/users";
    WebDriver driver = new ChromeDriver();
    private LoginPage loginPage;

    @Given("^Login app$")
    public void LoginApp() {

        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver");
        driver.get("http://localhost:4200/");
        Assert.assertTrue(driver.getTitle().equals("Frontend"));

        loginPage = new LoginPage(driver);
        loginPage.getLoginButton().click();

        driver.navigate().refresh();

        Assert.assertTrue(driver.getCurrentUrl().equals(BASE_URL));
    }

    @Given("Add new user")
    public void addNewUser() {
        addUser();
        AddUser addUser = new AddUser(driver);
//        driver.get(BASE_URL+"/");
//        addUser = new AddUser(driver);
//        addUser.getAddUserButton().click();
//
//        addUser.getUsername().sendKeys("Calin12");
//        addUser.getEmail().sendKeys("calin@calin.ro12");
//        addUser.getFullName().sendKeys("Calin User12");
//        addUser.getPassword().sendKeys("Calin12");
//        addUser.getGenderMale().sendKeys(Keys.SPACE);
//        addUser.getSubmitButton().sendKeys(Keys.ENTER);

        driver.get(BASE_URL + "/");
        driver.navigate().refresh();
        boolean isNecessaryUserNameInserted = addUser.getInsertedNecessaryUsername().isDisplayed();
        Assert.assertTrue(isNecessaryUserNameInserted);
        deleteAllUsers();
    }

    @Then("Delete the user")
    public void deleteUser() {

//        addUser();
        deleteAllUsers();

        driver.navigate().refresh();
        deleteAllUsers();


    }

    @And("Details user")
    public void showDetails() {
        addUser();
        driver.get(BASE_URL + "/");
        driver.navigate().refresh();
        DetailsPage detailsPage = new DetailsPage(driver);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ie) {
        }
        detailsPage.getDetailsButton().click();
        driver.get(BASE_URL + "/");
        driver.navigate().refresh();

        Assert.assertEquals("Calin12", detailsPage.getInsertedUsername().getText());
        deleteAllUsers();
    }

    @Given("Edit user with cancel")
    public void editUser() {
        EditUser editUser;
        editUser = new EditUser(driver);
        addUser();
        driver.get(BASE_URL + "/");
        driver.navigate().refresh();

        editUser.getEditUserButton().click();
        editUser.getxButton().click();

        deleteAllUsers();
    }

    @And("Edit user and modify")
    public void editUserAndModify() {
        EditUser editUser;
        editUser = new EditUser(driver);
        addUser();
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

        deleteAllUsers();
    }

    @And("Go back to login")
    public void goBackToLogin() {
        GoBack goBack;
        String actualUrl = "";
        driver.get(BASE_URL);
        goBack = new GoBack(driver);
    }

    @After()
    public void closeBrowser() {
//        driver.quit();
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
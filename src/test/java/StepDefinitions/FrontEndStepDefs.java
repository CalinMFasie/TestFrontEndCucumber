package StepDefinitions;

import Java.*;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class FrontEndStepDefs {
    private static final String BASE_URL = "http://localhost:4200/users";
    WebDriver driver = new ChromeDriver();
    private LoginPage loginPage;

    @Given("User is logged in")
    public void TestUserIsLoggedIn() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver");
        driver.get("http://localhost:4200/");
        assertTrue(driver.getTitle().equals("Frontend"));

        loginPage = new LoginPage(driver);
        loginPage.getUsername().sendKeys("User1");
        loginPage.getPassword().sendKeys("Pass1");
        loginPage.getLoginButton().click();

        driver.navigate().refresh();
        assertTrue(driver.getCurrentUrl().equals(BASE_URL));
    }

    @Then("Add new user with Username as {string}")
    public void TestAddNewUser(String userFistName) {
        AddUser addUser = new AddUser(driver);

        driver.get(BASE_URL + "/");
        driver.navigate().refresh();
        addUser.getAddUserButton().click();

        addUser.setUsername(userFistName);
        addUser.getEmail().sendKeys("calin@calin.ro1");
        addUser.getFullName().sendKeys("Calin User1");
        addUser.getPassword().sendKeys("Calin11");
        addUser.getTraitPerfectionist().click();
        addUser.getGenderMale().sendKeys(Keys.SPACE);
        addUser.getSubmitButton().sendKeys(Keys.ENTER);
        driver.get(BASE_URL + "/");
        driver.navigate().refresh();
        boolean isNecessaryUserNameInserted = addUser.getInsertedNecessaryUsername().isDisplayed();
        assertTrue(isNecessaryUserNameInserted);
    }

    @And("Edit user")
    public void TestEditUser() {
        int z = 0;
        EditUser editUser;
        editUser = new EditUser(driver);
        driver.get(BASE_URL + "/");
        driver.navigate().refresh();
        List<WebElement> webElementList = driver.findElements(By.className("container"));
        for (int i = 0; i < webElementList.size(); i++) {
            if (webElementList.get(i).getText().contains("Calin12")) {
                System.out.println(i);
                z = i + 1;
                webElementList.get(i).findElement(By.xpath("(//button[@class='btn'][contains(.,'Edit')])['z']")).click();
            }
        }
        clearFields();
        editUser.getUsername().sendKeys("Marcel1");
        editUser.getEmail().sendKeys("marcel@marcel.ro1");
        editUser.getFullName().sendKeys("Marcel User1");
        editUser.getPassword().sendKeys("Marcel11");
        editUser.getSubmitButton().sendKeys(Keys.ENTER);
        driver.get(BASE_URL + "/");
        driver.navigate().refresh();
        assertEquals("Marcel1", editUser.getInsertedUsername().getText());
    }

    @And("Show user details")
    public void TestShowUserDetails() {
        driver.get(BASE_URL + "/");
        driver.navigate().refresh();
        DetailsPage detailsPage = new DetailsPage(driver);

        detailsPage.getDetailsButton().click();
        driver.get(BASE_URL + "/");
        driver.navigate().refresh();

        assertEquals("Marcel1", detailsPage.getInsertedUsername().getText());
    }

    @Then("Delete user")
    public void TestDeleteUser() {
        int z = 0;
        List<WebElement> webElementList = driver.findElements(By.className("container"));
        for (int i = 0; i < webElementList.size(); i++) {
            if (webElementList.get(i).getText().contains("Marcel1")) {
                System.out.println(i);
                z = i + 1;
                webElementList.get(i).findElement(By.xpath("(//button[@class='btn'][contains(.,'Delete')])['z']")).click();
                DeleteUser deleteUser = new DeleteUser(driver);
                deleteUser.getAcceptDelete().click();
            }
        }
    }

    @Then("User is logged out")
    public void TestLogout() {
        GoBack goBack = new GoBack(driver);
        goBack.getGoBackButton().click();
        driver.navigate().refresh();
        final String ACTUAL_URL = driver.getCurrentUrl();
        assertEquals(ACTUAL_URL, "http://localhost:4200/");
        driver.quit();
    }

    public void clearFields() {
        EditUser editUser;
        editUser = new EditUser(driver);
        editUser.getUsername().clear();
        editUser.getEmail().clear();
        editUser.getFullName().clear();
        editUser.getPassword().clear();
    }
}
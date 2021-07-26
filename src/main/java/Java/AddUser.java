package Java;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

@Getter
public class AddUser {

    private WebDriver driver;

    @FindBy(xpath = "//span[@style='margin-left: 20px; font-size: 25px;']")
    private WebElement addUserButton;

    @FindBy(xpath = "//i[@class='fas fa-times fa-3x']")
    private WebElement xButton;

    @FindBy(id = "mat-input-0")
    private WebElement username;

    @FindBy(xpath = "(//span[contains(.,'Calin12')])[1]")
    private WebElement insertedUsername;

    @FindBy(xpath = "(//span[contains(.,'Calin12')])[1]")
    private WebElement insertedNecessaryUsername;

    @FindBy(id = "mat-input-1")
    private WebElement email;

    @FindBy(xpath = "(//span[contains(.,'calin@calin.ro1')])[1]")
    private WebElement insertedEmail;

    @FindBy(id = "mat-input-2")
    private WebElement fullName;

    @FindBy(xpath = "//h1[contains(.,'Calin User1')]")
    private WebElement insertedFullName;

    @FindBy(id = "mat-input-3")
    private WebElement password;

    @FindBy(id = "mat-checkbox-3")
    private WebElement traitPerfectionist;

    @FindBy(xpath = "(//input)[9]")
    private WebElement genderMale;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement submitButton;

    public AddUser(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
}
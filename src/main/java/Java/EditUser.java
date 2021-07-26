package Java;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

@Getter
public class EditUser {
    private WebDriver driver;

    //    @FindBy(xpath = "(//button[contains(@class,'btn')])[1]")
    @FindBy(xpath = "(//button[contains(.,'Edit')])[1]")
    private WebElement editUserButton;

    @FindBy(id = "mat-input-0")
    private WebElement username;

    @FindBy(xpath = "(//span[contains(.,'Marcel1')])[1]")
    private WebElement insertedUsername;

    @FindBy(id = "mat-input-1")
    private WebElement email;

    @FindBy(id = "mat-input-2")
    private WebElement fullName;

    @FindBy(id = "mat-input-3")
    private WebElement password;

    @FindBy(id = "mat-checkbox-3")
    private WebElement traitPerfectionist;

    @FindBy(xpath = "(//input)[9]")
    private WebElement genderMale;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement submitButton;

    @FindBy(xpath = "//i[@class='fas fa-times fa-3x']")
    private WebElement xButton;

    public EditUser(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public WebElement getxButton() {
        return xButton;
    }
}

package Java;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

@Getter
public class LoginPage {
    private WebDriver driver;
    //    @FindBy(xpath = "(//input[@class='form-control'])[1]")
    @FindBy(xpath = "//input[contains(@type,'text')]")
    private WebElement username;

    //    @FindBy(xpath = "(//input[@class='form-control'])[2]")
    @FindBy(xpath = "//input[contains(@type,'password')]")
    private WebElement password;

    @FindBy(xpath = "//button[@class='btn btn-outline-primary']")
    private WebElement loginButton;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public WebElement getLoginButton() {
        return loginButton;
    }
}


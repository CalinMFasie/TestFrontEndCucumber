package Java;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

@Getter
public class DeleteUser {
    private WebDriver driver;

    @FindBy(xpath = "(//button[@class='btn'][contains(.,'Delete')])[1]")
    private WebElement deleteButton;

    @FindBy(xpath = "//span[@class='mat-button-wrapper'][contains(.,'Cancel')]")
    private WebElement cancelDelete;

    @FindBy(xpath = "//button[@color='success'][contains(.,'Yes')]")
    private WebElement acceptDelete;

    public DeleteUser(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
}

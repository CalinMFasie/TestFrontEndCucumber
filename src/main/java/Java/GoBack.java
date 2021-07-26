package Java;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

@Getter
public class GoBack {
    private WebDriver driver;

    @FindBy(xpath = "(//mat-icon[contains(@role,'img')])[3]")
    private WebElement goBackButton;

    public GoBack(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
}

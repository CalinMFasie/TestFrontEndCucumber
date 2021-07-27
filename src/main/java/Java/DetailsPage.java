package Java;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
@Getter
public class DetailsPage {
    private WebDriver driver;

    @FindBy(xpath = "//h1[contains(.,'Marcel User1')]")
    private WebElement detailsButton;

    @FindBy(xpath = "(//span[contains(.,'Marcel1')])[1]")
    private WebElement insertedUsername;

    public DetailsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
}

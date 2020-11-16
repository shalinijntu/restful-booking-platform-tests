package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class HomePage extends BasePage {

    @FindBy(how = How.CSS, using = ".openBooking")
    private WebElement btnOpenBooking;

    @FindBy(how = How.CSS, using = ".btn-outline-primary.book-room")
    private WebElement btnSubmitBooking;

    @FindBy(how = How.CSS, using = ".alert-danger")
    private WebElement divAlert;

    @FindBy(how = How.CSS, using = "div[class=\"row hotel-room-info\"]")
    private List<WebElement> listRooms;

    public HomePage(WebDriver driver) {
        super(driver);

        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.titleContains("Restful-booker-platform demo"));
    }

    public void clickOpenBookingForm() {
        btnOpenBooking.click();

        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".rbc-calendar")));
    }

    public void clickSubmitBooking() {
        btnSubmitBooking.click();
    }


    public Boolean bookingFormErrorsExist() {
        return divAlert.isDisplayed();
    }

    public int roomCount() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.titleContains("Restful-booker-platform demo"));
        return listRooms.size();
    }
    public String roomDesc() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.titleContains("Restful-booker-platform demo"));

        return listRooms.get(listRooms.size()-1).getText();
    }
}

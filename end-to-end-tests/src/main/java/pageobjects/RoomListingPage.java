package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.w3c.dom.html.HTMLInputElement;

import java.util.List;

public class RoomListingPage extends BasePage {

    @FindBy(how = How.ID, using = "roomNumber")
    private WebElement txtRoomNumber;

    @FindBy(how = How.ID, using = "type")
    private WebElement roomType;

    @FindBy(how = How.ID, using = "2")
    private WebElement deleteRoom;

    @FindBy(how = How.CSS, using = ".alert-danger")
    private WebElement divAlert;

    @FindBy(how = How.ID, using = "accessible")
    private WebElement accessibility;

    @FindBy(how = How.ID, using = "createRoom")
    private WebElement btnCreate;

    @FindBy(how = How.ID, using = "wifiCheckbox")
    private WebElement chkWifi;

    @FindBy(how = How.CSS, using = "div[data-type~=\"room\"]")
    private List<WebElement> lstRooms;

    @FindBy(how = How.ID, using = "safeCheckbox")
    private WebElement chkSafe;

    @FindBy(how = How.ID, using = "radioCheckbox")
    private WebElement chkRadio;

    @FindBy(how = How.ID, using = "tvCheckbox")
    private WebElement chkTv;

    @FindBy(how = How.ID, using = "refreshCheckbox")
    private WebElement chkRefresh;

    @FindBy(how = How.ID, using = "viewsCheckbox")
    private WebElement chkViews;

    @FindBy(how = How.CSS, using = ".room-form")
    private WebElement frmForm;

    @FindBy(how = How.ID, using = "roomPrice")
    private WebElement inptRoomPrice;

    public RoomListingPage(WebDriver driver){
        super(driver);
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.col-sm-2.rowHeader")));
    }

    public void populateRoomNumber(String roomNumber) throws InterruptedException {
        txtRoomNumber.sendKeys(roomNumber);
        Thread.sleep(200);
    }

    public void selectRoomType(String type) throws InterruptedException {
        Select roomTypeDropdown=new Select(roomType);
        roomTypeDropdown.selectByVisibleText(type);
    }

    public void selectAccessibility(String accessible) throws InterruptedException {
        Select accessibilityDropdown=new Select(accessibility);
        accessibilityDropdown.selectByVisibleText(accessible);
    }

    public void clickCreateRoom() throws InterruptedException {
        Thread.sleep(200);
        btnCreate.click();
        Thread.sleep(200);
    }

    public int roomCount() throws InterruptedException {
        Thread.sleep(1000);
        return lstRooms.size();
    }

    public Boolean formErrorsExist() {
        return divAlert.isDisplayed();
    }

    public void clickFirstRoom() {
        lstRooms.get(0).click();
    }

    public void checkWifi() {
        chkWifi.click();
    }

    public void checkSafe() {
        chkSafe.click();
    }

    public void checkRadio() {
        chkRadio.click();
    }

    public void checkTv() {
        chkTv.click();
    }

    public void checkRefresh() {
        chkRefresh.click();
    }

    public void checkViews() {
        chkViews.click();
    }

    public Boolean roomFormExists() {
        return frmForm.isDisplayed();
    }

    public void setRoomPrice(String price) throws InterruptedException {
        Thread.sleep(1000);
        inptRoomPrice.sendKeys(price);
    }

    public void clickDeleteRoom(String roomNumber) {
        driver.findElement(By.xpath("//p[@id='roomNumber"+ roomNumber +"']/../../div/span[@class='fa fa-remove roomDelete']")).click();
    }

    public void multipleEntries(int numberOfRooms, String roomType, String accessible, String price) throws InterruptedException {
        for(int i=0;i<numberOfRooms;i++) {
            Thread.sleep(1000);
            populateRoomNumber("20"+i+"");
            selectRoomType(roomType);
            selectAccessibility(accessible);
            setRoomPrice(price);
            clickCreateRoom();
        }
    }
}

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pageobjects.HomePage;
import pageobjects.LoginPage;
import pageobjects.NavPage;
import pageobjects.RoomListingPage;


import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class CreateEntryTest extends TestSetup {

    @Before
    public void logIntoApplication(){
        navigateToApplication();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.populateUsername("admin");
        loginPage.populatePassword("password");
        loginPage.clickLogin();
    }

    @Test
    public void createDoubleRoomWithNoFeatures() throws InterruptedException {
        RoomListingPage roomListingPage = new RoomListingPage(driver);
        int initialRoomCount = roomListingPage.roomCount();

        roomListingPage.populateRoomNumber("111");
        roomListingPage.selectRoomType("Double");
        roomListingPage.selectAccessibility("false");
        roomListingPage.setRoomPrice("50");
        roomListingPage.clickCreateRoom();

        NavPage navPage = new NavPage(driver);
        navPage.clickFrontPage();

        HomePage homePage = new HomePage(driver);
        int currentRoomCount = homePage.roomCount();
        String latestRoomDesc = homePage.roomDesc();
        assertThat(currentRoomCount, is(initialRoomCount + 1));
        Assert.assertTrue(latestRoomDesc.contains("Double"));
        Assert.assertFalse(latestRoomDesc.contains("TV"));
    }

    @Test
    public void createFamilyRoomWithAllFeatures() throws InterruptedException {
        RoomListingPage roomListingPage = new RoomListingPage(driver);
        int initialRoomCount = roomListingPage.roomCount();

        roomListingPage.populateRoomNumber("121");
        roomListingPage.selectRoomType("Family");
        roomListingPage.selectAccessibility("true");
        roomListingPage.setRoomPrice("200");
        roomListingPage.checkTv();
        roomListingPage.checkWifi();
        roomListingPage.checkRadio();
        roomListingPage.checkRefresh();
        roomListingPage.checkSafe();
        roomListingPage.checkViews();
        roomListingPage.clickCreateRoom();

        NavPage navPage = new NavPage(driver);
        navPage.clickFrontPage();

        HomePage homePage = new HomePage(driver);
        int currentRoomCount = homePage.roomCount();
        String latestRoomDesc = homePage.roomDesc();
        assertThat(currentRoomCount, is(initialRoomCount + 1));
        Assert.assertTrue(latestRoomDesc.contains("Family"));
        Assert.assertTrue(latestRoomDesc.contains("WiFi"));
        Assert.assertTrue(latestRoomDesc.contains("TV"));
        Assert.assertTrue(latestRoomDesc.contains("Radio"));
        Assert.assertTrue(latestRoomDesc.contains("Refreshments"));
        Assert.assertTrue(latestRoomDesc.contains("Safe"));
        Assert.assertTrue(latestRoomDesc.contains("Views"));
    }

    @Test
    public void createRoomWithRoomNumberBlank() throws InterruptedException {
        RoomListingPage roomListingPage = new RoomListingPage(driver);

        roomListingPage.setRoomPrice("50");

        roomListingPage.clickCreateRoom();
        assertThat(roomListingPage.formErrorsExist(),is(true));
    }

}
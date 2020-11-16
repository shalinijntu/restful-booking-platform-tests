import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pageobjects.HomePage;
import pageobjects.LoginPage;
import pageobjects.NavPage;
import pageobjects.RoomListingPage;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class CreateMultipleEntriesTest extends TestSetup {

    @Before
    public void logIntoApplication(){
        navigateToApplication();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.populateUsername("admin");
        loginPage.populatePassword("password");
        loginPage.clickLogin();
    }

    @Test
    public void createMultipleSingleRooms() throws InterruptedException {
        RoomListingPage roomListingPage = new RoomListingPage(driver);
        int initialRoomCount = roomListingPage.roomCount();

        roomListingPage.multipleEntries(10, "Single", "false", "100");

        NavPage navPage = new NavPage(driver);
        navPage.clickFrontPage();

        HomePage homePage = new HomePage(driver);
        int currentRoomCount = homePage.roomCount();
        assertThat(currentRoomCount, is(initialRoomCount + 10));
    }
}
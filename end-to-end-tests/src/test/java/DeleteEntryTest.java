import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pageobjects.HomePage;
import pageobjects.LoginPage;
import pageobjects.NavPage;
import pageobjects.RoomListingPage;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class DeleteEntryTest extends TestSetup {

    @Before
    public void logIntoApplication(){
        navigateToApplication();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.populateUsername("admin");
        loginPage.populatePassword("password");
        loginPage.clickLogin();
    }

    @Test
    public void DeleteRoomBasedOnNumber() throws InterruptedException {
        RoomListingPage roomListingPage = new RoomListingPage(driver);
        int initialRoomCount = roomListingPage.roomCount();

        roomListingPage.clickDeleteRoom("102");
        Thread.sleep(1000);
        int currentRoomCount = roomListingPage.roomCount();
        assertThat(currentRoomCount, is(initialRoomCount - 1));

    }
}
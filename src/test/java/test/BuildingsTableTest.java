package test;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageobject.TallestBuildingsPage;
import utils.DriverFactory;

public class BuildingsTableTest extends BaseTest {

    //Page
    private TallestBuildingsPage tallestBuildingsPage;

    //Test data
    private static final String BUILDING_NAME = "Lotte World Tower";
    private static final String BUILDING_FLOORS = "123";
    private static final int NUMBER_RESULTS = 100;


    @BeforeMethod
    public void setUp() {
        driver = DriverFactory.getDriver();
        tallestBuildingsPage = new TallestBuildingsPage(driver);
        tallestBuildingsPage.navigateToLandingPage();
        tallestBuildingsPage.setBuildingTypeCompleted();
    }

    @Test
    public void testNumberOfBuildings() {
        tallestBuildingsPage.hasNumberResults(NUMBER_RESULTS);
    }

    @Test
    public void testBuildingFloors() {
        tallestBuildingsPage.hasBuildingWithFloors(BUILDING_NAME, BUILDING_FLOORS);
    }

    @Test
    public void testPrintTallestBuildingOneMax() {
        tallestBuildingsPage.printBuildingWithMaxFloors();
    }

    @Test
    public void testPrintTallestBuildingSeveralMax() {
        tallestBuildingsPage.printBuildingsWithMaxFloors();
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}

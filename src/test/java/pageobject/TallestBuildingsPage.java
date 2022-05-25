package pageobject;

import data.Constants;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class TallestBuildingsPage extends BasePage {

    @FindBy(name = "list")
    private WebElement selectBuildingType;

    @FindBy(id = "buildingsTable")
    private WebElement tableResults;


    public TallestBuildingsPage(WebDriver driver) {
        super.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void navigateToLandingPage() {
        driver.get(Constants.URL_BASE);
    }

    public void setBuildingTypeCompleted() {
        selectByValue(selectBuildingType, "tallest100-completed");
    }

    public void hasNumberResults(int number) {
        waitForPageLoaded();
        List<WebElement> rowBuildings = tableResults.findElements(By.xpath("tbody/tr"));
        assertThat(rowBuildings.size(), equalTo(number));
    }

    public void hasBuildingWithFloors(String building, String floors) {
        waitForPageLoaded();
        List<WebElement> rowBuildings = tableResults.findElements(By.xpath("tbody/tr"));
        boolean found = false;
        for (int i = 0; i < rowBuildings.size(); i++) {
            if (rowBuildings.get(i).findElement(By.xpath("td[2]")).getText().equals(building)) {
                found = true;
                assertThat(rowBuildings.get(i).findElement(By.xpath("td[7]")).getText(), equalTo(floors));
                break;
            }
        }
        if (!found) {
            assertThat("Building not found", found);
        }
    }

    public void printBuildingWithMaxFloors() {
        waitForPageLoaded();
        List<WebElement> rowBuildings = tableResults.findElements(By.xpath("tbody/tr"));
        int maxFloorsIndex = -1;
        int maxFloors = 0;
        for (int i = 0; i < rowBuildings.size(); i++) {
            if (!rowBuildings.get(i).findElement(By.xpath("td[7]")).getText().equals("N/A")) {
                int currentFloor = Integer.parseInt(rowBuildings.get(i).findElement(By.xpath("td[7]")).getText());
                if (currentFloor > maxFloors) {
                    maxFloors = currentFloor;
                    maxFloorsIndex = i;
                }
            }
        }
        if (maxFloorsIndex > -1) {
            System.out.println("Building with the maximum number of floors is:");
            System.out.println(rowBuildings.get(maxFloorsIndex).findElement(By.xpath("td[2]")).getText());
        } else {
            System.out.println("Not found floors information for buildings in the list");
        }
    }

    public void printBuildingsWithMaxFloors() {
        waitForPageLoaded();
        List<WebElement> rowBuildings = tableResults.findElements(By.xpath("tbody/tr"));
        ArrayList<Integer> maxFloorsIndex = new ArrayList<Integer>();
        int maxFloors = 0;
        for (int i = 0; i < rowBuildings.size(); i++) {
            if (!rowBuildings.get(i).findElement(By.xpath("td[7]")).getText().equals("N/A")) {
                int currentFloor = Integer.parseInt(rowBuildings.get(i).findElement(By.xpath("td[7]")).getText());
                if (currentFloor > maxFloors) {
                    maxFloors = currentFloor;
                    maxFloorsIndex.clear();
                    maxFloorsIndex.add(i);
                } else if (currentFloor == maxFloors) {
                    maxFloorsIndex.add(i);
                }
            }
        }
        if (maxFloorsIndex.size() > 0) {
            System.out.println("Buildings with the maximum number of floors are:");
            maxFloorsIndex.forEach(index -> System.out.println(rowBuildings.get(index).findElement(By.xpath("td[2]")).getText()));
        } else {
            System.out.println("Not found floors information for buildings in the list");
        }
    }
}

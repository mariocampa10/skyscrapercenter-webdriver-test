# skyscrapercenter-webdriver-test
Example of SkyScraperCenter test using Selenium Webdriver

## Execute
1. Clone repo from GitHub

```
git clone https://github.com/mariocampa10/skyscrapercenter-webdriver-test.git
```
2. Execute Grade Test on root project folder
```
./gradlew clean test
```
To see output console prints
```
./gradlew clean test --debug
```
3. Report generated (with standard output)
```
build/reports/tests/test/index.html
```

## Libraries
### Selenium Webdriver for Java
Java based framework that performs all operations against UI of the web application.

### TestNG
Testing framework inspired from JUnit that provides powerful functionalities used in this example like the use of Data Providers, Tests Listeners or XML files in order to have more control on test executions.

### Hamcrest Matchers
Hamcrest Matchers provides a way to generate asserts in a more human readable.

## Project Structure Folders
### Constants
Here we will put all common relevant fixed data used in pages and tests.

### Page Object
#### Base Page
It will contain common functionalities for every new page.

#### TallestBuildings Page (Inherits from Base Page)
Page corresponding to visualization on Skyscrapercenter web.
It is composed mainly by these parts (always in same order to keep consistency):
1. Mapping of components of the screen
2. Action methods (navigate, set values, ...)
3. Check methods (has number results, ...)
4. Other methods (printing methods)

### Tests
#### Base Test
It will contain the driver (needed to get instance for screenshots) and also it could keep common data that could be used in different defined tests.

#### BuildingsTable test (Inherits from Base Test)
It will contain one test method for each check to have a clear goal in each test and isolate better the errors.

The defined flow is the following one:
1. Open Chrome browser and maximise the window.
2. Go to URL : https://www.skyscrapercenter.com/buildings?list=tallest100-construction
3. Select in the dropdown list the value : “100 Tallest Completed Buildings in the World”
4. Consider the web table as a dynamic table meaning the number of rows and columns can change. In this web table assert the following:
    - Verify there are exactly 100 buildings in the list
    - Verify that the Lotte World Tower building in Seoul has 123 floors
    - Give us the building with the maximum number of floors (only one result)
    - Give us the building with the maximum number of floors (several buildings with max floors)

### Utils
#### Driver Factory
Returns an instance of ChromeDriver maximized. This class could keep more logic in order to execute tests against different browsers or even use Remote Driver by using Selenium Grid.

#### Listener
Provided by TestNG, it allows us to have more control on execution events. In this case we have introduced to take screenshots in screenshots/ folder when a test fail. This functionnality is very powerful and we can enhace our framework by using it, for example, to implement a retries policy.

### Resources
#### Testng XML
Here, we set the classes or methods we want to execute and also we glue our test listeners. It provides also capabilities in order to run in parallel (out of scope in this example).

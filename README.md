# Fitpeo-Assignment
`Project Setup
Clone this repository:

bash
Copy code
git clone https://github.com/your-repo/fitpeo-automation.git
Navigate to the project directory:

bash
Copy code
cd fitpeo-automation
Add Selenium, TestNG, and ChromeDriver dependencies to your pom.xml (if you're using Maven):

Download ChromeDriver and ensure it's installed and correctly referenced.

Test Script Description
The Fitpeo class automates the following steps in the Revenue Calculator section of the Fitpeo website:

Navigate to the Homepage: The test starts by visiting the Fitpeo homepage.
Access the Revenue Calculator: The script clicks the "Revenue Calculator" link on the homepage.
Scroll Down to the Slider Section: JavaScript is used to scroll down to the Revenue Calculator slider.
Adjust the Slider Value: The slider is adjusted to a target value (820) using JavaScript.
Update the Input Field: The text field is cleared, and a new value (560) is entered, simulating a user action.
Toggle CPT Codes: Multiple switches related to CPT Codes are clicked to simulate the user's selection.
Validate Total Recurring Reimbursement: The script validates the reimbursement value displayed on the page and asserts it is equal to $110700.
Running the Tests
To run the test:

Set up your environment with the required dependencies (Selenium, TestNG).

Run the Fitpeo class from your IDE (Eclipse) or via the command line.

If you're using Maven, run:

bash
Copy code
mvn test
If you're using Gradle, run:

bash
Copy code
gradle test
Test Output: The test will output the following logs:

Information about the user's actions (scrolling, slider adjustments, input updates).
The updated slider value and reimbursement values.
Assertions for validating that the slider value and reimbursement value match the expected results.
Example Output
plaintext

User scrolled to the Revenue Calculator section.
Updated slider value: 820
Reimbursement value: $110700
Troubleshooting
ChromeDriver Compatibility: Ensure that the version of ChromeDriver matches the version of Google Chrome installed on your machine.
WebDriver Path: If you face issues with the WebDriver, make sure that ChromeDriver is in your system's PATH or provide the full path in the code.
Code Explanation
1. setUp()
Initializes the WebDriver with Chrome options and sets timeouts.
Maximizes the browser window and deletes any cookies.
2. automateFitPeo()
Performs the core automation: navigates to the website, interacts with the Revenue Calculator section, and verifies results.
Includes JavaScript execution for slider adjustments and dynamic interactions.
3. tearDown()
Quits the WebDriver after test completion.
4. Assertions
Validates the expected values for the slider and reimbursement amounts to ensure the functionality is correct.

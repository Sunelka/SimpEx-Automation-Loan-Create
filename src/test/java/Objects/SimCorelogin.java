package Objects;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;
 
import java.text.ParseException;
import java.time.Duration;
public class SimCorelogin {
    private RemoteWebDriver driver;
    private WebDriverWait wait;
	private WebElement buttonokButton;
	@BeforeClass
    public void setUp() {
        // Setup ChromeDriver using WebDriverManager
        WebDriverManager.chromedriver().setup();
        // Initialize the ChromeDriver
        driver = new ChromeDriver();
        // Maximize the browser window
        driver.manage().window().maximize();
        // Navigate to the URL
        driver.get("https://simpex-frontend-web-uat.azurewebsites.net/");
        // Initialize the WebDriverWait with a 10-second timeout
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    @Test(priority = 1)
    public void login() {
        // Enter wrong username and password
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"UserName\"]"))).sendKeys("sunelka");
        driver.findElement(By.xpath("//*[@id=\"UserPassword\"]")).sendKeys("wrongpassword");
        // Click login button
        driver.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/form/div[3]/input")).click();
        // Clear the input fields after entering wrong credentials
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"UserName\"]"))).clear();
        driver.findElement(By.xpath("//*[@id=\"UserPassword\"]")).clear();
        // Enter correct username and password
        driver.findElement(By.xpath("//*[@id=\"UserName\"]")).sendKeys("admin");
        driver.findElement(By.xpath("//*[@id=\"UserPassword\"]")).sendKeys("snsQA@123");
        // Click login button again
        driver.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/form/div[3]/input")).click();
    }
    @Test(priority = 2)
    public void loanmngpage() {
        // Click loan management section
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"73\"]/span[1]"))).click();
        System.out.println("Successfully clicked loan management");
        // Click on the loan management details link
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"navigationDetailsContainer\"]/div[1]/div[2]/div/div[2]/a"))).click();
        System.out.println("Navigated to loan management details page");
    }
    
    @Test(priority = 3)
    public void loancreate() throws InterruptedException, ParseException {
        // Enter customer number
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"txtCustomerNumber\"]"))).sendKeys("101310201909");
        System.out.println("Successfully entered customer number");
 
        // Select product from dropdown
        Select objSelect = new Select(driver.findElement(By.xpath("//*[@id=\"ddlProduct\"]")));
        objSelect.selectByValue("286");
        System.out.println("Successfully selected loan product");
        
       
        //Ensure the field is visible and ready to interact
        WebElement loanAmountField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"txtFacilityAmount\"]")));
 
        //Clear the loan amount field properly
        loanAmountField.sendKeys(Keys.CONTROL + "A");
        loanAmountField.sendKeys(Keys.DELETE);  // Use DELETE to ensure it clears
        System.out.println("Successfully cleared the amount field");
 
        //Wait a moment to let any background processing settle
        Thread.sleep(1000);  // Optional: Can be adjusted based on how quickly the page reacts
 
        //Enter Loan amount using JavaScript to prevent overwriting
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].value=50000;", loanAmountField);
        System.out.println("Successfully entered loan amount using JavaScript");
        
        
        //Select payment frequency from dropdown
        Select objSelect1 = new Select(driver.findElement(By.xpath("//*[@id=\"ddlPaymentTerm\"]")));
        //*********SHOULD CHANGE MONTHLY/WEEKLY
        objSelect1.selectByValue("2");
        System.out.println("Successfully selected payment frequency");
        
        //Enter No of Installments
        driver.findElement(By.xpath("//*[@id=\"txtTerm\"]")).sendKeys("10");
 
 
     // Select Loan Collection Date
        WebElement collectionedate = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("txtCollectionStartDate")));
        collectionedate.click();

        WebElement nextMonthButton = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"ui-datepicker-div\"]/div/div/span[1]")));
        nextMonthButton.click();

        WebElement collectiondate1 = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"ui-datepicker-div\"]/table/tbody/tr[5]/td[6]/a")));
        collectiondate1.click();

        // Select Document Date
        WebElement documentedate = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("txtGrantedDate")));
        documentedate.click();

        WebElement activedate = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"ui-datepicker-div\"]/table/tbody/tr[4]/td[6]/a")));
        activedate.click();

 
        //Click Other info button
        driver.findElement(By.xpath("//*[@id=\"formFacilityCreate\"]/div/div/div/div[2]/div[2]/div[1]/div/div/div[1]/span[3]")).click();
       //Select Purpose from dropdown
        Select objSelect11 = new Select(driver.findElement(By.xpath("//*[@id=\"ddlPurpose\"]")));
        objSelect11.selectByValue("1");
        System.out.println("Successfully selected Purpose");
        //Select Sector from dropdown
        Select objSelect111 = new Select(driver.findElement(By.xpath("//*[@id=\"ddlSector\"]")));
        objSelect111.selectByValue("7");
        System.out.println("Successfully selected Sector");
        //Select Industry from dropdown
        Select objSelect1111 = new Select(driver.findElement(By.xpath("//*[@id=\"ddlIndustry\"]")));
        objSelect1111.selectByValue("5");
        System.out.println("Successfully selected Industry");
        //Select CRIB from dropdown
        Select objSelect11111 = new Select(driver.findElement(By.xpath("//*[@id=\"ddlCRIB\"]")));
        objSelect11111.selectByValue("1");
        System.out.println("Successfully selected CRIB");
      //Click Checklist button
        driver.findElement(By.xpath("//*[@id=\"formFacilityCreate\"]/div/div/div/div[2]/div[2]/div[1]/div/div/div[1]/span[5]")).click();
      //Wait until the checkbox is visible
        WebElement checkbox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='chkChecklistSelect']")));
 
        // Check if the checkbox is already selected
        if (!checkbox.isSelected()) {
            // Click the checkbox if it's not already selected
            checkbox.click();
            System.out.println("Checkbox selected successfully");
        } else {
            System.out.println("Checkbox is already selected");
        }
        //Locate the 'Choose File' input element using its XPath or ID
        WebElement uploadElement = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div[2]/form/div/div/div/div[2]/div[2]/div[1]/div/div/div[6]/div/div/div/table/tbody/tr/td[4]/input"));
 
        // Provide the full path to the file to be uploaded (include the file name)
        String filePath = "C:\\Users\\Sunelka\\Downloads\\images.jfif";  // Replace 'yourfile.pdf' with the actual file name
 
        // Upload the file by sending the file path to the input element
        uploadElement.sendKeys(filePath);
 
        System.out.println("File uploaded successfully.");
 
        //Click Preview
        driver.findElement(By.xpath("//*[@id=\"tblFacilityChecklist\"]/tbody/tr/td[5]/span")).click();
 
        // Add sleep to allow image to load (Not recommended for long term use)
        Thread.sleep(2000);
 
        // Wait for the close button to be clickable and visible
        WebElement closeButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[11]/div[1]/button/span[1]")));
 
        // Click the close button
        closeButton.click();
        System.out.println("Image closed successfully");
        
        
        //Click save and send for authorize
        driver.findElement(By.xpath("/html/body/div[1]/div[2]/div[2]/div[2]/section/div/button[1]/span")).click();
        System.out.println("Successfully Save and send for Authorize");
        
    
        WebElement okButton = driver.findElement(By.className("swal2-confirm swal2-styled")); 
        buttonokButton = null;
       ((JavascriptExecutor) driver).executeScript("arguments[0].click();", okButton);

		//buttonokButton.click();
        
        
   
        //Stay on the loan creation window after automation completes
        System.out.println("Automation complete. Browser will remain open.");
    }
        
    @AfterClass
    public void tearDown() {
    }
}
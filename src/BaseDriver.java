import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BaseDriver {

    public static WebDriver driver;
    public static WebDriverWait wait;

    static {
        Logger logger=Logger.getLogger("");
        logger.setLevel(Level.SEVERE);

        driver=new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));


        wait=new WebDriverWait(driver,Duration.ofSeconds(20));

        driver.get("https://techno.study/tr/");
    }
    public static void WaitAndQuit(){
        OptionalWait(5);
        driver.quit();
    }

    public static void OptionalWait(int sec){
        try {
            Thread.sleep(sec*1000l);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

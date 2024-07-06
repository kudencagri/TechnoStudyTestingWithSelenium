import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class Tests extends BaseDriver{
    Actions actions=new Actions(driver);

    @Test()
    public void TermsOfUse(){
        WebElement termsOfUse= driver.findElement(By.linkText("Kullanım Şartlarını"));
        termsOfUse.click();

        Set<String> windowsIds=driver.getWindowHandles();
        Iterator iterator= windowsIds.iterator();

        String firstTab= iterator.next().toString();
        String secondTab= iterator.next().toString();

        driver.switchTo().window(secondTab);

        Assert.assertTrue("Environment not opened",driver.getCurrentUrl().contains("terms"));
        WaitAndQuit();
    }

    @Test
    public void LearnMoreButton(){
        List<WebElement> learnMoreBtns=driver.findElements(By.xpath("//a[text()='Detaylı bilgi']"));
        for (WebElement btn:learnMoreBtns){
            actions.scrollToElement(btn).build().perform();
            Assert.assertTrue("The button is not visible on the site.",btn.isDisplayed());

            btn.click();
            WebElement logo=wait.until(ExpectedConditions.visibilityOfElementLocated((By.cssSelector("[style='max-height: 80px']"))));
            Assert.assertTrue("The logo is not visible on the site.",logo.isDisplayed());

            driver.navigate().back();
        }
        WaitAndQuit();
    }

    @Test
    public void ClickToLogo(){
        WebElement coursesBtn=driver.findElement(By.xpath("//a[text()='Kurslar']"));
        actions.moveToElement(coursesBtn).build().perform();
        List<WebElement> menu=driver.findElements(By.xpath("//a[@role='menuitem']"));

        for (int i = 0; i < menu.size(); i++) {
            menu.get(i).click();
            OptionalWait(2);
            WebElement logo= driver.findElement(By.cssSelector("[style='max-height: 80px']"));
            Assert.assertTrue("The logo is not visible on the site.",logo.isDisplayed());

            logo.click();
            OptionalWait(2);
            Assert.assertTrue("Home page have not opened",driver.getCurrentUrl().equals("https://techno.study/tr"));
        }

        WaitAndQuit();
    }


    @Test
    public void SubMenu_AccessingCourses(){
        WebElement coursesBtn=driver.findElement(By.xpath("//a[text()='Kurslar']"));
        actions.moveToElement(coursesBtn).build().perform();
        List<WebElement> menu=driver.findElements(By.xpath("//a[@role='menuitem']"));

        for (int i = 0; i < menu.size() ; i++) {
            String element=menu.get(i).getText().toLowerCase().replaceAll("\\s+","").substring(0,4);
            menu.get(i).click();
            Assert.assertTrue("You were not redirected to the relevant course page." ,driver.getCurrentUrl().contains(element));
            driver.navigate().back();
        }
        WaitAndQuit();
    }


}

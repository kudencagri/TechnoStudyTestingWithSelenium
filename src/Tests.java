import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

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

    @Test
    public void Anasayfa_Kurslari_DropdownMenu(){
        WebElement programs=driver.findElement(By.xpath("//a[text()='Kurslar']"));
        actions.moveToElement(programs).build().perform();
        List<WebElement> menu=driver.findElements(By.xpath("//a[@role='menuitem']"));

        for (int i = 0; i <menu.size() ; i++) {
            String element=menu.get(i).getText().toLowerCase().replaceAll("\\s+","").substring(0,3);
            menu.get(i).click();
            Assert.assertTrue("You were not redirected to the relevant course page." ,driver.getCurrentUrl().contains(element));
            System.out.println(driver.getCurrentUrl());
            OptionalWait(2);
            driver.navigate().back();

        }
        WaitAndQuit();
    }

    @Test
    public void Login_tothe_Campus_platform_from_the_Homepage(){
        WebElement campus=driver.findElement(By.xpath("//*[text()='Campus Login' ]"));
        campus.click();
        Assert.assertTrue("Test Başarılı",driver.getCurrentUrl().equals("https://campus.techno.study/"));
        WaitAndQuit();
    }

    @Test
    public void Basvuru(){
        WebElement basvur=driver.findElement(By.xpath("//*[@class='tn-atom js-click-zero-stat']"));
        basvur.click();
        WebElement name=driver.findElement(By.xpath("//*[@placeholder='Adı Soyadı']"));
        name.sendKeys("Test mustafa");
        WebElement email=driver.findElement(By.xpath("//*[@placeholder='Email']"));
        email.sendKeys("Testmustafa@gmail.com");

        WebElement tel=driver.findElement(By.xpath("//*[@type='tel']"));
        tel.sendKeys("5398422511");
        WebElement yas=driver.findElement(By.xpath("//*[@placeholder='Yaşınız']"));
        yas.sendKeys("27");
        WebElement meslek=driver.findElement(By.xpath("//*[@placeholder='Mesleğiniz']"));
        meslek.sendKeys("issiz");

        WebElement iframe= driver.findElement(By.xpath("//*[@class='t-submit']"));
        new Actions(driver).
                scrollToElement(iframe).
                build().
                perform();

        WebElement egitimDurumu=driver.findElement(By.id("sb-1667664755026"));
        Select EDmenu=new Select(egitimDurumu);
        EDmenu.selectByVisibleText("Üniversite");

        WebElement kurs=driver.findElement(By.id("sb-1663337581601"));
        Select kursmenu=new Select(kurs);
        kursmenu.selectByVisibleText("SDET Türkçe");

        WebElement NeredenBuldunuz=driver.findElement(By.id("sb-1670423010572"));
        Select NeredenMenu=new Select(NeredenBuldunuz);
        NeredenMenu.selectByVisibleText("Mezundan");

        WebElement ulke=driver.findElement(By.id("sb-1714463229186"));
        Select ulkemenu=new Select(ulke);
        ulkemenu.selectByVisibleText("Turkiye");

        WebElement promosyon=driver.findElement(By.xpath("//*[@placeholder='Promosyon kodu']"));
        promosyon.sendKeys("Tester");

        WebElement kullanimsartlari=driver.findElement(By.xpath("//div[@class='t-checkbox__indicator']"));
        kullanimsartlari.click();

        WebElement send=driver.findElement(By.xpath("//*[@class='t-submit']"));
        send.click();

        WaitAndQuit();
    }

    @Test
    public void  Access_to_courses_from_the_lower_menu(){
        WebElement iframe= driver.findElement(By.xpath("//*[text()='© 2023 Techno Study Bilişim Yazılım Eğitim Danışmanlık Hizmetleri AŞ. MERSiS No: 0833119074500001']"));
        new Actions(driver).
                scrollToElement(iframe).
                build().
                perform();

    }
}

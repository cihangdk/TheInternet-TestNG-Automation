package utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class Driver {
    private Driver(){
        /*
         Driver class'ından obje oluşturmanın önüne geçebilmek için gizli constructor'ı private
   yapabileceğimiz gibi class'ı abstract da yapabiliriz.
         */
    }
    static WebDriver driver;
    public static WebDriver getdriver(){
        /*
            Driver'i her çağırdığımızda yeni bir pencere açılmasının önüne geçmek için if bloğu içine eğer driver'a
        değer ATANMAMIŞSA değer ata, eğer değer atanmışsa driverı aynı sayfada return  et dememiz gerekir.Bu işleme
        SİNGLETON PATTERN denir.
         */
        if (driver == null){driver = new ChromeDriver();
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        }
        return driver;
    }
    public static void quitDriver(){
        if (driver != null){ // Eğer driver'a değer atanmışsa yani boş değilse
            driver.quit();
            driver = null ; // Değer atanmış hala geldi
        }
    }
}

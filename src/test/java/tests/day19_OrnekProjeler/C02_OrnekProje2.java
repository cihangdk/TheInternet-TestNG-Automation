package tests.day19_OrnekProjeler;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import pages.TheInternetPage;
import utilities.Driver;
import org.openqa.selenium.support.ui.Select;

import java.awt.*;
import java.awt.event.KeyEvent;

public class C02_OrnekProje2 {

    // TEST 1: Form Authentication sayfasında doğru bilgilerle giriş yapılabiliyor mu?
    // throws Exception: Thread.sleep ve Robot hata fırlatabildiği için eklendi
    @Test
    public void basariliLoginTesti() throws Exception {

        // https://the-internet.herokuapp.com/login adresine gidelim
        Driver.getdriver().get("https://the-internet.herokuapp.com/login");

        // Sayfadaki elementlere ulaşmak için page nesnesini oluşturalım
        TheInternetPage page = new TheInternetPage();

        // Kullanıcı adı kutusuna geçerli kullanıcı adını yazalım
        page.usernameInput.sendKeys("tomsmith");

        // Şifre kutusuna geçerli şifreyi yazalım
        page.passwordInput.sendKeys("SuperSecretPassword!");

        // Login butonuna tıklayalım
        page.loginButton.click();

        // Chrome'un şifre uyarısını Enter ile kapatalım
        Thread.sleep(2000);
        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);

        // Giriş başarılıysa site bizi /secure sayfasına yönlendirir, URL'de /secure geçmeli
        Assert.assertTrue(Driver.getdriver().getCurrentUrl().contains("/secure"));

        // Ekranda çıkan yeşil mesajın "You logged into a secure area!" içerdiğini doğrulayalım
        Assert.assertTrue(page.flashMessage.getText().contains("You logged into a secure area!"));
    }

    // TEST 2: Form Authentication sayfasında yanlış kullanıcı adıyla giriş engelleniyor mu?
    @Test
    public void yanlisKullaniciAdiTesti() {

        // https://the-internet.herokuapp.com/login adresine gidelim
        Driver.getdriver().get("https://the-internet.herokuapp.com/login");

        // Sayfadaki elementlere ulaşmak için page nesnesini oluşturalım
        TheInternetPage page = new TheInternetPage();

        // Kullanıcı adı kutusuna YANLIŞ bir kullanıcı adı yazalım
        page.usernameInput.sendKeys("yanlisKullanici");

        // Şifre kutusuna doğru şifreyi yazalım (sadece kullanıcı adı yanlış olsun ki sebep net kalsın)
        page.passwordInput.sendKeys("SuperSecretPassword!");

        // Login butonuna tıklayalım
        page.loginButton.click();

        // Giriş engellendiyse URL'de /secure OLMAMALI, assertFalse bunu kontrol eder
        Assert.assertFalse(Driver.getdriver().getCurrentUrl().contains("/secure"));

        // Ekranda kırmızı "Your username is invalid!" hata mesajı çıkmalı
        Assert.assertTrue(page.flashMessage.getText().contains("Your username is invalid!"));
    }

    /*
     * TEST 03: Yanlış şifre ile login
     * Doğru kullanıcı adı (tomsmith) ve yanlış şifre ile giriş deniyoruz.
     * Beklenen: Giriş reddedilir, /login sayfasında kalınır ve
     * "Your password is invalid!" hata mesajı görünür.
     */
    @Test
    public void test03_YanlisSifreIleLogin() {

        // Login sayfasına gidiyoruz
        Driver.getdriver().get("https://the-internet.herokuapp.com/login");

        // Page sınıfından nesne oluşturup elementlere erişiyoruz
        TheInternetPage theInternetPage = new TheInternetPage();

        // Doğru kullanıcı adını giriyoruz
        theInternetPage.usernameInput.sendKeys("tomsmith");

        // Bilerek yanlış şifre giriyoruz
        theInternetPage.passwordInput.sendKeys("YanlisSifre123");

        // Login butonuna tıklayıp giriş denemesi yapıyoruz
        theInternetPage.loginButton.click();

        // Giriş başarısız olduğu için hâlâ /login sayfasında kalmalıyız
        String actualUrl = Driver.getdriver().getCurrentUrl();
        Assert.assertTrue(actualUrl.contains("/login"));

        // Flash mesajında yanlış şifre uyarısı görünmeli
        String actualMessage = theInternetPage.flashMessage.getText();
        Assert.assertTrue(actualMessage.contains("Your password is invalid!"));

    }

    /*
     * TEST 04: Boş alanlarla login
     * Kullanıcı adı ve şifre alanlarını boş bırakıp giriş deniyoruz.
     * Beklenen: Giriş reddedilir, /login sayfasında kalınır ve
     * "Your username is invalid!" hata mesajı görünür.
     */
    @Test
    public void test04_BosAlanlarlaLogin() {

        // Login sayfasına gidiyoruz
        Driver.getdriver().get("https://the-internet.herokuapp.com/login");

        // Page sınıfından nesne oluşturup elementlere erişiyoruz
        TheInternetPage theInternetPage = new TheInternetPage();

        // Alanlara hiçbir şey yazmadan direkt Login butonuna tıklıyoruz
        theInternetPage.loginButton.click();

        // Giriş başarısız olduğu için hâlâ /login sayfasında kalmalıyız
        String actualUrl = Driver.getdriver().getCurrentUrl();
        Assert.assertTrue(actualUrl.contains("/login"));

        // Kullanıcı adı boş olduğu için kullanıcı adı uyarısı görünmeli
        String actualMessage = theInternetPage.flashMessage.getText();
        Assert.assertTrue(actualMessage.contains("Your username is invalid!"));
    }

    /*
     * TEST 05: Login sonrası logout
     * Doğru bilgilerle giriş yapıp ardından Logout butonuna basıyoruz.
     * Beklenen: /login sayfasına geri dönülür ve
     * "You logged out of the secure area!" mesajı görünür.
     */
    @Test
    public void test05_LoginSonrasiLogout() throws InterruptedException, AWTException {

        // Login sayfasına gidiyoruz
        Driver.getdriver().get("https://the-internet.herokuapp.com/login");

        // Page sınıfından nesne oluşturup elementlere erişiyoruz
        TheInternetPage theInternetPage = new TheInternetPage();

        // Doğru kullanıcı adı ve şifreyi girip Login butonuna tıklıyoruz
        theInternetPage.usernameInput.sendKeys("tomsmith");
        theInternetPage.passwordInput.sendKeys("SuperSecretPassword!");
        theInternetPage.loginButton.click();

        // Chrome şifre uyarısının çıkmasını bekleyip Enter ile kapatıyoruz
        Thread.sleep(2000);
        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);

        // Login başarılı olduğu için /secure sayfasında olmalıyız
        Assert.assertTrue(Driver.getdriver().getCurrentUrl().contains("/secure"));

        // Logout butonuna tıklayıp oturumu kapatıyoruz
        theInternetPage.logoutButton.click();

        // Çıkış yapıldığı için /login sayfasına geri dönmüş olmalıyız
        String actualUrl = Driver.getdriver().getCurrentUrl();
        Assert.assertTrue(actualUrl.contains("/login"));

        // Flash mesajında çıkış yapıldığı bilgisi görünmeli
        String actualMessage = theInternetPage.flashMessage.getText();
        Assert.assertTrue(actualMessage.contains("You logged out of the secure area!"));
    }

    /*
     * TEST 06: Checkboxes
     * Checkbox 1 seçili değilse seçiyoruz, Checkbox 2 seçiliyse seçimini kaldırıyoruz.
     * Beklenen: İşlemlerden sonra Checkbox 1 seçili,
     * Checkbox 2 seçili değil olmalı.
     */
    @Test
    public void test06_Checkboxes() {

        // Checkboxes sayfasına gidiyoruz
        Driver.getdriver().get("https://the-internet.herokuapp.com/checkboxes");

        // Page sınıfından nesne oluşturup elementlere erişiyoruz
        TheInternetPage theInternetPage = new TheInternetPage();

        // Checkbox 1 seçili değilse tıklayıp seçiyoruz
        if (!theInternetPage.checkbox1.isSelected()) {
            theInternetPage.checkbox1.click();
        }

        // Checkbox 1'in artık seçili olduğunu doğruluyoruz
        Assert.assertTrue(theInternetPage.checkbox1.isSelected());

        // Checkbox 2 seçiliyse tıklayıp seçimini kaldırıyoruz
        if (theInternetPage.checkbox2.isSelected()) {
            theInternetPage.checkbox2.click();
        }

        // Checkbox 2'nin artık seçili olmadığını doğruluyoruz
        Assert.assertFalse(theInternetPage.checkbox2.isSelected());
    }

    /*
     * TEST 07: Dropdown
     * Select sınıfı ile index, value ve görünen yazıya göre seçim yapıyoruz.
     * Beklenen: Her seçimden sonra doğru seçenek seçili olmalı
     * ve dropdown'da toplam 3 seçenek bulunmalı.
     */
    @Test
    public void test07_Dropdown() {

        // Dropdown sayfasına gidiyoruz
        Driver.getdriver().get("https://the-internet.herokuapp.com/dropdown");

        // Page sınıfından nesne oluşturup elementlere erişiyoruz
        TheInternetPage theInternetPage = new TheInternetPage();

        // Dropdown elementini Select sınıfına vererek seçim yapabilir hale getiriyoruz
        Select select = new Select(theInternetPage.dropdown);

        // Sıra numarasına göre seçim yapıyoruz (index 1 = Option 1)
        select.selectByIndex(1);

        // Seçili seçeneğin Option 1 olduğunu doğruluyoruz
        Assert.assertEquals(select.getFirstSelectedOption().getText(), "Option 1");

        // Value değerine göre seçim yapıyoruz (value "2" = Option 2)
        select.selectByValue("2");

        // Seçili seçeneğin Option 2 olduğunu doğruluyoruz
        Assert.assertEquals(select.getFirstSelectedOption().getText(), "Option 2");

        // Görünen yazıya göre seçim yapıyoruz
        select.selectByVisibleText("Option 1");

        // Seçili seçeneğin tekrar Option 1 olduğunu doğruluyoruz
        Assert.assertEquals(select.getFirstSelectedOption().getText(), "Option 1");

        // Dropdown'daki tüm seçeneklerin sayısının 3 olduğunu doğruluyoruz
        Assert.assertEquals(select.getOptions().size(), 3);
    }

    // Her testten sonra çalışır, tarayıcıyı kapatır ki bir sonraki test temiz tarayıcıyla başlasın
    @AfterMethod
    public void tearDown() {
        Driver.quitDriver();
    }
}
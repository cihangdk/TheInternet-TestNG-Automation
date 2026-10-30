package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class TheInternetPage {

    // Constructor: sınıftaki @FindBy ile işaretli tüm elementleri açık olan tarayıcıya bağlar.
    // Bunu yazmazsak elementler null kalır ve testte NullPointerException alırız.
    public TheInternetPage() {
        PageFactory.initElements(Driver.getdriver(), this);
    }

    // ---------- Form Authentication sayfası (/login) ----------

    // Kullanıcı adı yazılan kutu: HTML'de <input id="username">
    @FindBy(id = "username")
    public WebElement usernameInput;

    // Şifre yazılan kutu: HTML'de <input id="password">
    @FindBy(id = "password")
    public WebElement passwordInput;

    // Login butonu: id'si olmadığı için CSS seçici kullandık, type'ı submit olan button
    @FindBy(css = "button[type='submit']")
    public WebElement loginButton;

    // Giriş sonrası çıkan bilgilendirme mesajı kutusu.
    // Başarılı girişte yeşil, hatalı girişte kırmızı olur ama id ikisinde de aynıdır.
    @FindBy(id = "flash")
    public WebElement flashMessage;

    // Secure sayfasındaki Logout butonu (login sonrası görünür)
    @FindBy(xpath = "//a[@href='/logout']")
    public WebElement logoutButton;

    //------------BURDAN SONRA CHECKBOX SAYFASININ LOCATELERİ---------------------

    // Checkboxes sayfasındaki birinci checkbox (başlangıçta seçili değil)
    @FindBy(xpath = "(//input[@type='checkbox'])[1]")
    public WebElement checkbox1;

    // Checkboxes sayfasındaki ikinci checkbox (başlangıçta seçili)
    @FindBy(xpath = "(//input[@type='checkbox'])[2]")
    public WebElement checkbox2;

    //------------BURDAN SONRA DROPDOWN SAYFASININ LOCATELERİ---------------------

    // Dropdown sayfasındaki açılır menü (<select> etiketi)
    @FindBy(id = "dropdown")
    public WebElement dropdown;
}
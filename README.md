# The Internet - TestNG Otomasyon Projesi

[the-internet.herokuapp.com](https://the-internet.herokuapp.com) üzerinde **Selenium WebDriver, Java, TestNG ve Maven** ile yazılmış UI test otomasyon projesi.

Proje **Page Object Model (POM)** yapısıyla kurulmuştur. Sayfadaki elementler page sınıfında, test adımları ise test sınıfında tutulur.

## Kullanılan Teknolojiler

| Teknoloji | Kullanım Amacı |
|---|---|
| Java | Programlama dili |
| Selenium WebDriver 4 | Tarayıcı otomasyonu |
| TestNG | Test çatısı ve assertion'lar |
| Maven | Bağımlılık yönetimi ve derleme |
| PageFactory + @FindBy | Elementleri page sınıfında tanımlama |

## Proje Yapısı

```
src/test/java
├── pages
│   └── TheInternetPage.java        # Tüm locator'lar (PageFactory + @FindBy)
├── tests
│   └── day19_OrnekProjeler
│       └── C02_OrnekProje2.java    # Test senaryoları
└── utilities
    └── Driver.java                 # Singleton Pattern ile WebDriver yönetimi
```

- **TheInternetPage:** Projedeki tek page sınıfı. Yeni sayfaların locator'ları da bu sınıfa eklenir.
- **Driver:** `getdriver()` ile tek bir ChromeDriver örneği oluşturur, `quitDriver()` ile kapatır. Her test sonrasında `@AfterMethod` ile tarayıcı kapatılır.

## Test Senaryoları

| # | Test | Sayfa | Doğrulanan |
|---|---|---|---|
| 1 | `basariliLoginTesti` | /login | Doğru bilgilerle giriş, `/secure` yönlendirmesi ve başarı mesajı |
| 2 | `yanlisKullaniciAdiTesti` | /login | Yanlış kullanıcı adında giriş engeli ve hata mesajı |
| 3 | `test03_YanlisSifreIleLogin` | /login | Yanlış şifrede giriş engeli ve hata mesajı |
| 4 | `test04_BosAlanlarlaLogin` | /login | Boş alanlarla giriş engeli |
| 5 | `test05_LoginSonrasiLogout` | /login, /secure | Çıkış sonrası `/login` sayfasına dönüş ve çıkış mesajı |
| 6 | `test06_Checkboxes` | /checkboxes | Checkbox seçme / seçimi kaldırma (`isSelected`) |
| 7 | `test07_Dropdown` | /dropdown | `Select` sınıfı ile index, value ve görünen yazıya göre seçim |
| 8 | `test08_Inputs` | /inputs | Sayı girişi, ok tuşları (`Keys`) ve harf kabul edilmemesi |

## Çalıştırma

**Gereksinimler:** Java (JDK), Maven ve Google Chrome kurulu olmalı. Selenium 4, ChromeDriver'ı otomatik indirir.

**1. Projeyi indir:**
```
git clone https://github.com/cihangdk/TheInternet-TestNG-Automation.git
```

**2. Testleri çalıştır:**

IntelliJ IDEA'da `C02_OrnekProje2` sınıfını açıp sınıf adının yanındaki yeşil oka tıklayabilir veya terminalden şu komutu kullanabilirsin:
```
mvn test -Dtest=C02_OrnekProje2
```

## Notlar

- Başarılı login testlerinde Chrome'un "şifreyi kaydet" uyarısı `Robot` sınıfıyla Enter tuşuna basılarak kapatılır. Bu yüzden testler çalışırken Chrome penceresi ön planda olmalı ve headless modda çalıştırılmamalıdır.
- Testler canlı bir demo sitede çalıştığı için site erişilemez olduğunda testler başarısız olabilir.

## Yol Haritası

Proje adım adım geliştirilmeye devam ediyor. Sıradaki senaryolar:

- [ ] Dynamic Loading: `WebDriverWait` ile explicit wait
- [ ] Dynamic Controls: element ekleme/kaldırma, enable/disable
- [ ] JavaScript Alerts: alert, confirm, prompt
- [ ] Frames / iFrame: `switchTo().frame()`
- [ ] Multiple Windows: pencereler arası geçiş
- [ ] Hovers ve Context Menu: `Actions` sınıfı
- [ ] File Upload
- [ ] Sortable Data Tables: tablo okuma ve doğrulama

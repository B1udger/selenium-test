# SwagLabs UI Tests

Автоматизирани UI тестове за [Swag Labs](https://www.saucedemo.com/) с Java, Selenium 4 и JUnit 5.

## 📂 Структура на проекта

swaglabs-ui-tests/
│
├── pom.xml
└── src
└── test
└── java
├── pages
│ ├── LoginPage.java
│ ├── InventoryPage.java
│ ├── CartPage.java
│ └── CheckoutPage.java
└── tests
└── SwagLabsTests.java


## 🚀 Как да стартираш

1. **Prerequisites**  
   - Java 17+  
   - Maven 3.6+  

2. **Клонирай репото**  
   ```bash
   git clone https://github.com/твоят-потребител/swaglabs-ui-tests.git
   cd swaglabs-ui-tests

mvn clean test

🧩 Page Object Model
LoginPage.java
Вход в приложението, валидация на грешни данни.

InventoryPage.java
Добавяне на артикули в количката, сортиране, logout.

CartPage.java
Преглед и валидация на артикули в количката, checkout начало.

CheckoutPage.java
Въвеждане на данни за доставка, завършване на поръчка.

✅ Тестове
testLoginFailure
Проверява съобщение при невалиден login.

testLoginSuccessAndLogout
Успешен вход и изход през бургер меню.

testAddItemsAndCartCount
Добавяне на 2 артикула и проверка на броя в cart badge.

testSortLowToHigh
Тестване на сортиране „Price (low to high)“.

testCheckoutFlow
Пълен flow: добавяне в количка, checkout, въвеждане на данни, завършване на поръчка и проверка на съобщението.

🛠️ Конфигурации
Implicit wait: 5 сек

Thread.sleep: 3 сек пауза между стъпките за визуализация

Browser: Microsoft Edge (Selenium Manager автоматично изтегля msedgedriver)


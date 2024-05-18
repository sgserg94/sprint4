package ru.yandex.page;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static java.time.Duration.ofSeconds;

public class LeasePage {
    private final WebDriver webDriver;

    private final By selectDateLocator = By.xpath("//input[@placeholder='* Когда привезти самокат']"); //Выбор даты заказа
    private final By RentPeriodLocator = By.xpath("//div[text()='* Срок аренды']"); //Поле срока аренды
    private final By RentPeriodMenuLocator = By.xpath("//div[text()='трое суток']"); // выбор срока аренды
    private final By clickCreateOrderLocator = By.xpath("//div[contains(@class, 'Order_Buttons')]//button[text()='Заказать']"); //Кнопка заказать
    public LeasePage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public void selectOrderDate(String orderDate) {
        WebElement dateInput = webDriver.findElement(selectDateLocator);
        dateInput.sendKeys(orderDate, Keys.ENTER);
    }

    public void selectRentPeriod() {
        WebElement rentPeriodInput = webDriver.findElement(RentPeriodLocator);
        rentPeriodInput.click();
                WebElement rentPeriodMenuItem = webDriver.findElement(RentPeriodMenuLocator);
        rentPeriodMenuItem.click();
    }

    public void clickCreateOrderButton() {
        WebElement createOrderButton = webDriver.findElement(clickCreateOrderLocator);
        new WebDriverWait(webDriver, ofSeconds(15)).until(ExpectedConditions.elementToBeClickable(createOrderButton));
        createOrderButton.click();
    }
}

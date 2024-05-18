package ru.yandex;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import ru.yandex.page.LeasePage;
import ru.yandex.page.MainPage;
import ru.yandex.page.OrderPage;
import static org.junit.Assert.assertTrue;

public class OrderTest {
    private WebDriver webDriver;

    @Before
    public void setup() {
        webDriver = WebDriverFactory.getWebDriver(System.getProperty("browser", "firefox"));
        webDriver.get("https://qa-scooter.praktikum-services.ru/");
    }

    @Test
    public void orderNotFound() {

        MainPage mainPage = new MainPage(webDriver);
        mainPage.clickOrderStatusButton();
        mainPage.enterOrderNumber("3565448");
        mainPage.clickGoButton();
        assertTrue("Заказ не найден",mainPage.notFoundImageIsDisplayed());

    }

    @Test
    public void orderSuccessUpPage() {

        MainPage mainPage = new MainPage(webDriver);
        mainPage.clickCreateOrder();
        OrderPage orderPage = new OrderPage(webDriver);
        orderPage.fillCustomerInfo("Имя", "Фамилия", "Адрес", "Арбатская", "89991231212");
        orderPage.clickNextButton();
        LeasePage leasePage = new LeasePage(webDriver);
        leasePage.selectOrderDate("01.01.2025");
        leasePage.selectRentPeriod();
        leasePage.clickCreateOrderButton();
        orderPage.clickYesButton();
        assertTrue("Оформление заказа через верхнюю кнопку ",mainPage.orderSuccessIsDisplayed());

    }

    @Test
    public void orderSuccessDownPage() {

        MainPage mainPage = new MainPage(webDriver);
        mainPage.clickCreateOrderDownPage();
        OrderPage orderPage = new OrderPage(webDriver);
        orderPage.fillCustomerInfo("Алексей", "Алексеев", "Россия, г. Рязань, Комсомольская ул., д. 10 кв.99", "Арбатская", "81234567898");
        orderPage.clickNextButton();
        LeasePage leasePage = new LeasePage(webDriver);
        leasePage.selectOrderDate("01.12.2024");
        leasePage.selectRentPeriod();
        leasePage.clickCreateOrderButton();
        orderPage.clickYesButton();
        assertTrue("Оформление заказа  через нижнюю кнопку",mainPage.orderSuccessIsDisplayed());

    }

    @After
    public void tearDown() {
        webDriver.quit();
    }

    }


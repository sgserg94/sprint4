package ru.yandex.page;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import static java.time.Duration.ofSeconds;

public class MainPage {
    private final WebDriver webDriver;

    private final By orderStatusLocator = By.xpath("//button[text()='Статус заказа']"); // Кнопка статуса заказа
    private final By orderNumberInputLocator = By.xpath("//input[@placeholder='Введите номер заказа']"); // Поле с вводом номера заказа
    private final By GoButtonLocator = By.xpath("//button[text()='Go!']"); // Кнопка GO!
    private final By notFoundImageLocator = By.xpath("//img[@alt='Not found']"); // Страница с ненайденным заказом
    private final By createOrderButtonLocator = By.xpath("//div[contains(@class, 'Header')]//button[text()='Заказать']"); // Кнопка Заказать вверху страницы
    private final By cookiesButtonLocator = By.id("rcc-confirm-button"); // Кнопка принять куки
    private final String questionLocator = "accordion__heading-%s"; // Список вопросов
    private final String answerLocator = "//div[contains(@id, 'accordion__panel')][.='%s']"; // список ответов
    private final By finishButtonLocator = By.xpath("//div[@class='Home_FinishButton__1_cWm']"); // Поиск кнопки Заказать снизу страницы для клика
    private final By createOrderDownButtonLocator = By.xpath ("//div[contains(@class, 'Home')]/button[text()='Заказать']"); // Кнопка Заказать снизу страницы
    private final By orderSuccessStatusLocator = By.xpath("//div[text()='Заказ оформлен']"); //Страница с оформленным заказом

    public MainPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }


    public void clickOrderStatusButton() {
    WebElement orderStatusButton = webDriver.findElement(orderStatusLocator);
    orderStatusButton.click();
    }

    public void enterOrderNumber(String orderNumber) {
        WebElement orderInput = webDriver.findElement(orderNumberInputLocator);
        orderInput.sendKeys(orderNumber);
    }

    public void clickGoButton() {
        WebElement goButton = webDriver.findElement(GoButtonLocator);
        new WebDriverWait(webDriver, ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(goButton));
        goButton.click();
    }

    public boolean notFoundImageIsDisplayed() {
        return webDriver.findElement(notFoundImageLocator).isDisplayed();
    }

    public void clickCreateOrder() {
        WebElement orderCreateButton = webDriver.findElement(createOrderButtonLocator);

        orderCreateButton.click();

    }

    public void closeCookiesWindow() {
        webDriver.findElement(cookiesButtonLocator).click();

    }

    public void expandQuestion(int index) {
       WebElement element = webDriver.findElement(By.id(String.format(questionLocator, index)));
        ((JavascriptExecutor) webDriver).executeScript( "arguments[0].scrollIntoView();", element);
        new WebDriverWait(webDriver, ofSeconds(15)).until(ExpectedConditions.elementToBeClickable(element));
        element.click();
    }

    public boolean answerIsDisplayed(String expectedAnswer) {
      WebElement element = webDriver.findElement(By.xpath(String.format(answerLocator, expectedAnswer)));
        return element.isDisplayed();

    }

    public void clickCreateOrderDownPage() {
        WebElement element = webDriver.findElement(finishButtonLocator);
        ((JavascriptExecutor) webDriver).executeScript( "arguments[0].scrollIntoView();", element);
        new WebDriverWait(webDriver, ofSeconds(15)).until(ExpectedConditions.elementToBeClickable(element));
        WebElement orderCreateButtonDown = webDriver.findElement(createOrderDownButtonLocator);
        orderCreateButtonDown.click();

    }

    public boolean orderSuccessIsDisplayed() {
        return webDriver.findElement(orderSuccessStatusLocator).isDisplayed();
    }
}

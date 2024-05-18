package ru.yandex.page;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class OrderPage {
    private final WebDriver webDriver;
    private final By nameInputLocator = By.xpath("//input[@placeholder='* Имя']"); //Поле для ввода Имени
    private final By lastNameInputLocator = By.xpath("//input[@placeholder='* Фамилия']");//Поле для ввода Фамилии
    private final By addressInputLocator = By.xpath("//input[@placeholder='* Адрес: куда привезти заказ']"); //Поле для ввода адреса
    private final By subwayInputLocator = By.xpath("//input[@placeholder='* Станция метро']"); //Поле выбора метро
    private final By phoneInputLocator = By.xpath("//input[@placeholder='* Телефон: на него позвонит курьер']"); //Поле для ввода телефона
    private final String stationMenuItemLocator = "//div[text()='%s']";  // Выбор станции метро
    private final By nextButtonLocator = By.xpath("//button[text()='Далее']"); // Кнопка далее
    private final By YesButtonLocator = By.xpath("//button[text()='Да']"); // Кнопка да

    public OrderPage(WebDriver webDriver) {
        this.webDriver = webDriver;

    }

    public void fillCustomerInfo(String name, String lastName, String address, String subwayTitle, String phone) {


        WebElement nameInput = webDriver.findElement(nameInputLocator);
        nameInput.sendKeys(name);
        WebElement surnameInput = webDriver.findElement(lastNameInputLocator);
        surnameInput.sendKeys(lastName);
        WebElement addressInput = webDriver.findElement(addressInputLocator);
        addressInput.sendKeys(address);
        WebElement subwayInput = webDriver.findElement(subwayInputLocator);
        subwayInput.click();
        WebElement arbatskayaStationMenu = webDriver
                .findElement(By.xpath(String.format(stationMenuItemLocator, subwayTitle)));
        ((JavascriptExecutor) webDriver).executeScript( "arguments[0].scrollIntoView();", arbatskayaStationMenu);
        arbatskayaStationMenu.click();
        WebElement phoneInput = webDriver.findElement(phoneInputLocator);
        phoneInput.sendKeys(phone);
    }

    public void clickNextButton() {

        WebElement nextButton = webDriver
                .findElement(nextButtonLocator);
        nextButton.click();
    }

    public void clickYesButton() {

        WebElement YesButton = webDriver
                .findElement(YesButtonLocator);
        YesButton.click();

    }
}

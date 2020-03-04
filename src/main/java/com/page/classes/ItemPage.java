package com.page.classes;

import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class ItemPage {

	@FindBy(xpath="//button[text()='ADD TO CART']")
    WebElement addToCartButton;
	
	@FindBy(xpath="//span[text()='Place Order']//parent::button")
    WebElement placeOrderButton;
	
	@FindBy(xpath="//input[@type='text']")
    WebElement inputField;
	
	@FindBy(xpath="//span[text()='CONTINUE']//parent::button")
    WebElement continueButton;
	
	public ItemPage(WebDriver driver){
		PageFactory.initElements(new AjaxElementLocatorFactory(driver, 5), this);
    }
	
	public void addToCart(WebDriver driver) {
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("window.scrollBy(0,250)");
		addToCartButton.click();
	}
	
	public void clickPlaceOrderButton() {
		placeOrderButton.click();
	}
	
	public void enterMobileNumber(String mobileNo) {
		inputField.sendKeys(mobileNo);
		continueButton.click();
	}
}

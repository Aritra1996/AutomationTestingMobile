package com.page.classes;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class HomePage {

	@FindBy(xpath="//button[text()='✕']")
    WebElement closeLoginButton;
	
	@FindBy(xpath="//input[@name='q']")
    WebElement searchFieldBox;
	
	@FindBy(xpath="//a[@rel='noopener noreferrer']")
    List<WebElement> searchItems;
	
	public HomePage(WebDriver driver){
		PageFactory.initElements(new AjaxElementLocatorFactory(driver, 5), this);
    }
	
	public void closeLoginPopup() {
		closeLoginButton.click();
	}
	
	public void searchItem(String item) {
		searchFieldBox.sendKeys(item);
		searchFieldBox.submit();
	}
	
	public void openItem(int itemNumber) {
		searchItems.get(itemNumber-1).click();
	}
}

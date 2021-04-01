package com.sample.test.demo;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class UserActions {
	
	protected void selectByVisibleText(WebElement ele,String text){
		Select select = new Select(ele);
		select.selectByVisibleText(text);
	}
	protected void selectByValue(WebElement ele,String text){
		Select select = new Select(ele);
		select.selectByValue(text);
	}
	protected void enterText(WebElement ele,String text){
		ele.sendKeys(text);
	}
	protected void enterText(WebElement ele,int number){
		ele.clear();
		ele.sendKeys(String.valueOf(number));
	}
	protected void pressTab(WebElement ele){
		ele.sendKeys(Keys.TAB);
	}
	protected String getText(WebElement ele){
		return ele.getText();
	}
	protected String getAttribut(WebElement ele,String attribute){
		return ele.getAttribute(attribute);
	}
	protected void click(WebElement ele){
		ele.click();
	}
	protected boolean isEnabled(WebElement ele){
		return ele.isEnabled();
	}
	protected boolean isDispalyed(WebElement ele){
		return ele.isDisplayed();
	}
	
}

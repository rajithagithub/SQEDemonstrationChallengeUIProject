package com.sample.test.demo.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.sample.test.demo.UserActions;
import com.sample.test.demo.constants.PizzaToppings;
import com.sample.test.demo.constants.PizzaTypes;

public class PizzaOrderPageObject extends UserActions{


	WebDriver driver;
	
	
	public PizzaOrderPageObject(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(id="pizza1Pizza")
	WebElement pizza1;
	
	@FindBy(xpath="//div[@id='pizza1']//select[@class='toppings1']")
	WebElement pizza1Toppings1;
	
	@FindBy(xpath="//div[@id='pizza1']//select[@class='toppings2']")
	WebElement pizza1Toppings2;
	
	@FindBy(id="pizza1Qty")
	WebElement pizza1Quantity;
			
	@FindBy(id="pizza1Cost")
	WebElement pizza1Cost;
	
	@FindBy(id="ccpayment")
	WebElement radioCreditCard;
	
	@FindBy(id="cashpayment")
	WebElement radioCash;
	 
	@FindBy(id="email")
	WebElement email;
	
	@FindBy(id="name")
	WebElement name;
	
	@FindBy(id="phone")
	WebElement phone;

	@FindBy(id="placeOrder")
	WebElement placeOrderButton;
	
	@FindBy(id="reset")
	WebElement resetButton;

	@FindBy(id="dialog")
	WebElement dialog;
	
	@FindBy(xpath="//div[@id='dialog']/p")
	WebElement dialogText;
	
	@FindBy(xpath="//button[@title='Close']")
	WebElement closeDialogButton;
	
	public void selectPizza1(PizzaTypes pizzaTypes){
		selectByValue(pizza1,pizzaTypes.getDisplayName());
	}
	public void selectToppings1(PizzaToppings pizzaToppings){
		selectByValue(pizza1Toppings1,pizzaToppings.getDisplayName());
	}
	public void selectToppings2(PizzaToppings pizzaToppings){
		selectByValue(pizza1Toppings2,pizzaToppings.getDisplayName());
	}
	public void setQuantity(int quantity){
		enterText(pizza1Quantity, quantity);
		pressTab(pizza1Quantity);
	}
	public String getQuantity(){
		return getAttribut(pizza1Quantity, "value");
	}
	public String getCost(){
		return getAttribut(pizza1Cost,"value");
	}
	public void enableCreditCardRadioButton(){
		click(radioCreditCard);
	}
	public void enableCashRadioButton(){
			click(radioCash);
	}
	public void setEmail(String text){
		enterText(email, text);
	}
	public String getEmail(){
		return getAttribut(email, "value");
	}
	public void setName(String text){
		enterText(name, text);
	}
	public String getName(){
		return getAttribut(name, "value");
	}
	public void setPhone(String text){
		enterText(phone, text);
	}
	public String getPhone(){
		return getAttribut(phone, "value");
	}
	public void submitPlaceOrder(){
		click(placeOrderButton);
	}
	public void clickOnReset(){
		click(resetButton);
	}
	public String getPopText(){
		return getText(dialogText);
	}
	public void clickOnClosePopupDialog(){
		click(closeDialogButton);
	}
	public boolean isPopUpDisplayed(String text){
		if(isDispalyed(dialog)){
			return getText(dialogText).contentEquals(text);
		}else{
			return false;
		}
	}
	
	
	
}

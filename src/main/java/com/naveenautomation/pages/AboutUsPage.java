package com.naveenautomation.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.naveenautomation.TestBases.TestBase;
import com.naveenautomation.utility.Utility;

public class AboutUsPage extends TestBase {

	public AboutUsPage() {
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "#content p")
	WebElement aboutUsHeader;

	public String getaboutUsHeader() {
		return Utility.getTextFromWebelement(aboutUsHeader);
	}

}

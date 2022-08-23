package com.tdc.tests;

import com.tdc.utilities.BrowserUtils;
import com.tdc.utilities.ConfigurationReader;
import com.tdc.utilities.Driver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class TDC_Test_1 {

	private final String o_URL = "https://m.trimdownclub.com/mt-vsl-gen-o.html";
	private final String r_URL = "https://m.trimdownclub.com/mt-vsl-gen-r.html";
	private final String v1_URL = "https://m.trimdownclub.com/mt-vsl-gen-v1.html";
	private final String v2_URL = "https://m.trimdownclub.com/mt-vsl-gen-v2.html";

	private static int o_Count = 0;
	private static int r_Count = 0;
	private static int v1_Count = 0;
	private static int v2_Count = 0;

	private String currentURL;
	private static int executionNo = 0;


	@BeforeMethod
	public void setUp(){
		Driver.get().manage().window().maximize();
		Driver.get().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		Driver.get().get(ConfigurationReader.get("url"));
		BrowserUtils.wait(1);
	}


	@AfterMethod
	public void tearDown(){
		Driver.closeDriver();
	}


	@Test(invocationCount = 2000)
	public void test1(){
		getCurrentURL();
		updateTraffic();
		printresults();
	}


	public void updateTraffic(){
		switch (currentURL) {
			case r_URL:
				r_Count++;
				break;

			case v1_URL:
				v1_Count++;
				break;

			case v2_URL:
				v2_Count++;
				break;

			case o_URL:
				o_Count++;
				break;
		}
	}

	public void getCurrentURL(){
		currentURL = Driver.get().getCurrentUrl();
	}

	public void printresults(){
		System.out.println("\n" + currentURL);
		System.out.println("execution No: " + ++executionNo);
		System.out.println("o count: " + o_Count);
		System.out.println("r count: " + r_Count);
		System.out.println("v1 count: " + v1_Count);
		System.out.println("v2 count: " + v2_Count);
	}



}

package Base;

import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.BeforeTest;

import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import Utils.CapabilitiesAndServer;
import Utils.JsonUtils;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;

public class BaseTest {
	AppiumDriverLocalService service;
	DesiredCapabilities capabilities;
	CapabilitiesAndServer capabilitiesAndServer;
	RemoteWebDriver driver;
	String serviceUrl;
	@BeforeTest
	public void beforeTest() throws JsonIOException, JsonSyntaxException, FileNotFoundException, MalformedURLException {
		DesiredCapabilities capabilities=new DesiredCapabilities();
		System.out.println("capabilities------------"+capabilities);
		capabilitiesAndServer = new CapabilitiesAndServer();
		service = capabilitiesAndServer.startAppiumLocalServer();
		service.start();
		JsonUtils jsonUtils=new JsonUtils();
		if(service.isRunning()) {
			System.out.println("Is Server Running::"+service.isRunning());
			capabilities=capabilitiesAndServer.setCapabilitiesForServer("android");		
			serviceUrl=service.getUrl().toString();
			
		System.out.println("Appium servr is runing on current URL ::"+serviceUrl);
			driver=new AndroidDriver(new URL(serviceUrl), capabilities);
					
		}
		else if ((service == null || !service.isRunning()) ){
		    throw new ExceptionInInitializerError("An appium server node is not started!");
		
		
		}
}
}

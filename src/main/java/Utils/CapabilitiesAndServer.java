package Utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;

import org.openqa.selenium.remote.DesiredCapabilities;

import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;

public class CapabilitiesAndServer {
	FileInputStream fs;
	AppiumDriverLocalService services;
	AppiumServiceBuilder builder;
JsonUtils jsonUtils;
DesiredCapabilities capabilities;
	public DesiredCapabilities setCapabilitiesForServer(String platformType) throws JsonIOException, JsonSyntaxException, FileNotFoundException {
		
		switch (platformType) {
		case "Ios":
			capabilities=new DesiredCapabilities();
			capabilities.setCapability("platformName", jsonUtils.getJsonValue("platformName"));
		    capabilities.setCapability("deviceName", jsonUtils.getJsonValue("deviceName"));
		    capabilities.setCapability("platformVersion",jsonUtils.getJsonValue("platformVersion"));
		   
		    capabilities.setCapability("app", jsonUtils.getJsonValue("app"));
		    capabilities.setCapability("appPackage",jsonUtils.getJsonValue("appPackage"));  
		    capabilities.setCapability("appActivity ",jsonUtils.getJsonValue("appActivity "));
			break;
		case "android":
			capabilities=new DesiredCapabilities();
			capabilities.setCapability("platformName", jsonUtils.getJsonValue("platformName"));
		    capabilities.setCapability("deviceName", jsonUtils.getJsonValue("deviceName"));
		    capabilities.setCapability("platformVersion",jsonUtils.getJsonValue("platformVersion"));
		   
		    capabilities.setCapability("app", jsonUtils.getJsonValue("app"));
		    capabilities.setCapability("appPackage",jsonUtils.getJsonValue("appPackage"));  
		    capabilities.setCapability("appActivity ",jsonUtils.getJsonValue("appActivity "));
			
		default:
			capabilities.setCapability("platformName", jsonUtils.getJsonValue("platformName"));
		    capabilities.setCapability("deviceName", jsonUtils.getJsonValue("deviceName"));
		    capabilities.setCapability("platformVersion",jsonUtils.getJsonValue("platformVersion"));
		   
		    capabilities.setCapability("app", jsonUtils.getJsonValue("app"));
		    capabilities.setCapability("appPackage",jsonUtils.getJsonValue("appPackage"));  
		    capabilities.setCapability("appActivity ",jsonUtils.getJsonValue("appActivity "));
			
			break;
		}
		
		
		return capabilities;

	}

	public AppiumDriverLocalService startAppiumLocalServer() {
		  jsonUtils = new JsonUtils();
		builder = new AppiumServiceBuilder();
		String ip = jsonUtils.getJsonValue("appiumIp");
		String nodePath = jsonUtils.getJsonValue("nodeJsExePath");
		String appiumMainJsPath = jsonUtils.getJsonValue("appiumMainJsPath");
		System.out.println("Node.exe::" + nodePath);
		System.out.println("Appium Main Js::" + appiumMainJsPath);
		services = builder.withIPAddress(ip).usingAnyFreePort().usingDriverExecutable(new File(nodePath))
				.withAppiumJS(new File(appiumMainJsPath)).withArgument(GeneralServerFlag.LOG_LEVEL, "error")
				.withArgument(GeneralServerFlag.LOG_TIMESTAMP).withArgument(GeneralServerFlag.SESSION_OVERRIDE)
				.withArgument(GeneralServerFlag.LOCAL_TIMEZONE).build();
		return services;

	}

}

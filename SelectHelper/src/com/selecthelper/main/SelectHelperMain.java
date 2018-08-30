package com.selecthelper.main;

import java.util.Scanner;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.selecthelper.constants.CommonConstants;
import com.selecthelper.constants.FileConstants;
import com.selecthelper.factory.HelperFactory;
import com.selecthelper.interfaces.ModelExecuter;

public class SelectHelperMain implements FileConstants, CommonConstants {

	public static Logger LOGGER = Logger.getLogger(SelectHelperMain.class);

	public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		LOGGER.info("Start of SelectHelper at ::" + startTime);
		@SuppressWarnings("resource")
		ApplicationContext context = new ClassPathXmlApplicationContext(SPRING_CONTEXT);
		Scanner inputScanner = new Scanner(System.in);
		String factoryType = inputScanner.next();
		HelperFactory helperFactory = (HelperFactory) context.getBean(HELPER_FACTORY);
		ModelExecuter modelExecuter = helperFactory.getFactory(factoryType);
		modelExecuter.execute();
		inputScanner.close();
		LOGGER.info("Total time taken by SelectHelper :: " + (System.currentTimeMillis()-startTime) );
	}

}

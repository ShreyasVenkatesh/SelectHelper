package com.selecthelper.helper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;

public class SelectHelper {

	public static Logger LOGGER = Logger.getLogger(SelectHelper.class);

	public static void loadModelsToList(List<String> modelsList, String fileName) {
		try {
			File file = new File(fileName);
			FileReader fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			bufferedReader.lines().forEach(cureLine -> modelsList.add(cureLine.trim()));
			LOGGER.debug("NO of models loaded to list :: " + modelsList.size());
			bufferedReader.close();
		} catch (FileNotFoundException e) {
			LOGGER.error(e.getMessage());
		} catch (IOException e) {
			LOGGER.error(e.getMessage());
		}
	}
}

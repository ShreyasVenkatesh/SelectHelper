package com.selecthelper.impl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import com.selecthelper.helper.SelectHelper;
import com.selecthelper.interfaces.ModelExecuter;

public class ModelRemover implements ModelExecuter {

	private static List<String> REMOVE_MODELS_LIST = new ArrayList<String>();

	public static Logger LOGGER = Logger.getLogger(ModelRemover.class);

	public void execute() {
		SelectHelper.loadModelsToList(REMOVE_MODELS_LIST, INPUT_REM_MODELS_FILE);
		String regEx = constructRegEx(REMOVE_MODELS_LIST);
		removeModels(regEx);
	}

	private static String constructRegEx(List<String> modelsList) {
		LOGGER.debug("Start of constructRegEx::");
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(REG_EXP_START);
		Iterator<String> iterator = modelsList.iterator();
		int listCount = 0;
		while (iterator.hasNext()) {
			String curElem = (String) iterator.next();
			stringBuilder.append(curElem);
			listCount++;
			if (!(listCount == modelsList.size()))
				stringBuilder.append(PIPE_SYM);
		}
		stringBuilder.append(REG_EXP_END);
		LOGGER.debug("Regular Expression ::" + stringBuilder.toString());
		LOGGER.debug("End of constructRegEx::");
		return stringBuilder.toString();
	}

	private void removeModels(String regEx) {
		BufferedWriter bufferedWriter;
		BufferedReader bufferedReader;
		try {
			bufferedReader = new BufferedReader(new FileReader(new File(INPUT_DUM_MAT_FILE)));
			bufferedWriter = new BufferedWriter(new FileWriter(new File(OUTPUT_DUM_MAT_FILE)));
			Pattern pattern = Pattern.compile(regEx);
			LOGGER.debug("pattern data :: " + pattern.toString());
			String curLine = "";
			while ((curLine = bufferedReader.readLine()) != null) {
				Matcher matcher = pattern.matcher(curLine);
				if (!matcher.find()) {
					bufferedWriter.write(curLine);
					bufferedWriter.write(NEW_LINE);
				} else {
					bufferedWriter.write(LINE_COMMENT + curLine);
					bufferedWriter.write(NEW_LINE);
				}
			}
			bufferedWriter.flush();
			bufferedWriter.close();
			bufferedReader.close();
		} catch (IOException e) {
			LOGGER.error(e.getMessage());
		}
	}
}

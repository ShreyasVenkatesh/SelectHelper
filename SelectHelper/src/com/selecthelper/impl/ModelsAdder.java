package com.selecthelper.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.selecthelper.constants.FileConstants;
import com.selecthelper.constants.StringConstants;
import com.selecthelper.helper.SelectHelper;
import com.selecthelper.interfaces.ModelExecuter;

public class ModelsAdder implements ModelExecuter, FileConstants, StringConstants {

	public static Logger LOGGER = Logger.getLogger(ModelsAdder.class);

	private static List<String> ADD_MODELS_LIST = new ArrayList<String>();

	public void execute() {
		SelectHelper.loadModelsToList(ADD_MODELS_LIST, INPUT_ADD_MODELS_FILE);
		addNewModels(ADD_MODELS_LIST);

	}

	private void addNewModels(List<String> modelsList) {
		File dumMatFile = new File(INPUT_DUM_MAT_FILE);
		BufferedReader bufferedReader = null;
		boolean isStartDumBom = false;
		try {
			bufferedReader = new BufferedReader(new FileReader(dumMatFile));
			String curLine = StringUtils.EMPTY;
			while ((curLine = bufferedReader.readLine()) != null) {
				LOGGER.info(curLine);
				if (!isStartDumBom && curLine.contains(DUMMY_MAT_START)) {
					isStartDumBom = true;
				}
				if(isStartDumBom) {
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (bufferedReader != null) {
				try {
					bufferedReader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}

}

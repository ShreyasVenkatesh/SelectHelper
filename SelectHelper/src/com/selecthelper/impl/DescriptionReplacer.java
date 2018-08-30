package com.selecthelper.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.selecthelper.interfaces.ModelExecuter;

public class DescriptionReplacer implements ModelExecuter {

	private static Map<String, String> DESCRIPTION_MAP = new HashMap<String, String>();
	public static Logger LOGGER = Logger.getLogger(DescriptionReplacer.class);

	public static void loadDescToMap(Map<String, String> descriptionMap) throws IOException {
		LOGGER.debug("Start loadDescToMap");
		BufferedReader bufRdr = new BufferedReader(new FileReader(new File(INPUT_DESC_REP_FILE)));
		String temp[] = new String[2];
		int tokens = 0;
		while (bufRdr.read() > 0) {
			StringTokenizer tokenizer = new StringTokenizer(bufRdr.readLine());
			while (tokenizer.hasMoreTokens()) {
				temp[++tokens] = tokenizer.nextToken();
			}
			descriptionMap.put(temp[0], temp[1]);
		}
		LOGGER.debug("End loadDescToMap");
		bufRdr.close();
	}

	public static void displayMapDesc(Map<String, String> descriptionMap) {
		Iterator<Map.Entry<String, String>> entries = descriptionMap.entrySet().iterator();
		while (entries.hasNext()) {
			Map.Entry<String, String> entry = entries.next();
			String key = entry.getKey();
			String value = entry.getValue();
			LOGGER.debug("displayMapDesc " + key + value);
		}
	}

	public static void replaceDesc(Map<String, String> descriptionMap) throws IOException {
		BufferedReader bufRdr = new BufferedReader(new FileReader(new File(INPUT_DUM_MAT_FILE)));
		String currLine = StringUtils.EMPTY;
		while (bufRdr.readLine() != null) {
			currLine = bufRdr.readLine();
			Iterator<Map.Entry<String, String>> iterator = descriptionMap.entrySet().iterator();
			while (iterator.hasNext()) {
				String regEx = REG_EXP_START + iterator.next() + REG_EXP_END;
				Pattern pattern = Pattern.compile(regEx);
				System.out.println("regEx :: " + regEx);
				System.out.println("Key :: " + iterator.next());
				System.out.println("currLine :: " + currLine);
				Matcher matcher = pattern.matcher(currLine);
				if (matcher.find()) {
					System.out.println("Matched :: :) ");
					// matcher.replaceAll((String) (iterator.next()));
				}
			}
		}

		bufRdr.close();
	}

	public void execute() {
		try {
			loadDescToMap(DESCRIPTION_MAP);
			displayMapDesc(DESCRIPTION_MAP);
			replaceDesc(DESCRIPTION_MAP);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}

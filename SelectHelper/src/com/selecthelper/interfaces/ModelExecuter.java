package com.selecthelper.interfaces;

import java.util.Collection;

import com.selecthelper.constants.CommonConstants;
import com.selecthelper.constants.FileConstants;

public interface ModelExecuter extends FileConstants, CommonConstants {
	public abstract void execute();
	default void loadModels(Collection<String> collection) {
		
	}
}

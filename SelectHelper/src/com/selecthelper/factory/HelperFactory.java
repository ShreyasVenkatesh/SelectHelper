package com.selecthelper.factory;

import org.apache.commons.lang3.StringUtils;

import com.selecthelper.constants.CommonConstants;
import com.selecthelper.exception.FactoryNotDefinedException;
import com.selecthelper.impl.DescriptionReplacer;
import com.selecthelper.impl.ModelRemover;
import com.selecthelper.interfaces.ModelExecuter;

public class HelperFactory {

	private ModelRemover modelRemover;
	private DescriptionReplacer descriptionReplacer;
	private FactoryNotDefinedException factoryNotDefinedException;

	public ModelExecuter getFactory(String factoryType) {
		ModelExecuter factory = null;
		boolean factorySet = false;
		try {
			if (StringUtils.isBlank(factoryType)) {
				throw getFactoryNotDefinedException();
			}
			if (factoryType.equals(CommonConstants.MODEL_REMOVER)) {
				factory = getModelRemover();
				factorySet = true;
			}
			if (factoryType.equals(CommonConstants.DESC_REPLACER)) {
				factory = getDescriptionReplacer();
				factorySet = true;
			}
			if (!factorySet) {
				throw getFactoryNotDefinedException();
			}

		} catch (FactoryNotDefinedException e) {
			e.printStackTrace();
			factory = getModelRemover();
		}
		return factory;
	}

	public DescriptionReplacer getDescriptionReplacer() {
		return descriptionReplacer;
	}

	public void setDescriptionReplacer(DescriptionReplacer descriptionReplacer) {
		this.descriptionReplacer = descriptionReplacer;
	}

	public ModelRemover getModelRemover() {
		return modelRemover;
	}

	public void setModelRemover(ModelRemover modelRemover) {
		this.modelRemover = modelRemover;
	}

	public FactoryNotDefinedException getFactoryNotDefinedException() {
		return factoryNotDefinedException;
	}

	public void setFactoryNotDefinedException(FactoryNotDefinedException factoryNotDefinedException) {
		this.factoryNotDefinedException = factoryNotDefinedException;
	}

}

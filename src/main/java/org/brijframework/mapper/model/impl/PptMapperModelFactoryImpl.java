package org.brijframework.mapper.model.impl;

import org.brijframework.mapper.factories.MapperModelFactory;
import org.brijframework.mapper.model.PptMapperModel;
import org.brijframework.model.factories.asm.MetaInfoFactoryImpl;
import org.brijframework.support.config.Assignable;

public class PptMapperModelFactoryImpl extends MetaInfoFactoryImpl<PptMapperModel> implements MapperModelFactory {

	protected PptMapperModelFactoryImpl() {
	}

	protected static PptMapperModelFactoryImpl factory;

	@Assignable
	public static PptMapperModelFactoryImpl getFactory() {
		if (factory == null) {
			factory = new PptMapperModelFactoryImpl();
		}
		return factory;
	}

	@Override
	public PptMapperModelFactoryImpl loadFactory() {
		return this;
	}

}

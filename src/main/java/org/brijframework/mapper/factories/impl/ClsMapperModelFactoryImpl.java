package org.brijframework.mapper.factories.impl;

import org.brijframework.mapper.factories.MapperModelFactory;
import org.brijframework.mapper.model.ClsMapperModel;
import org.brijframework.model.factories.asm.MetaInfoFactoryImpl;
import org.brijframework.support.config.Assignable;

public class ClsMapperModelFactoryImpl extends MetaInfoFactoryImpl<ClsMapperModel> implements MapperModelFactory {

	protected ClsMapperModelFactoryImpl() {
	}

	protected static ClsMapperModelFactoryImpl factory;

	@Assignable
	public static ClsMapperModelFactoryImpl getFactory() {
		if (factory == null) {
			factory = new ClsMapperModelFactoryImpl();
		}
		return factory;
	}

	@Override
	public ClsMapperModelFactoryImpl loadFactory() {
		return this;
	}

	public ClsMapperModel load(Class<?> target) {
		ClsMapperModel model=getMetaInfo(target.getSimpleName());
		if(model==null) {
			model = new ClsMapperModel();
			model.setId(target.getSimpleName());
			model.setTarget(target);
			register(model);
		}
		return model;
	}

}

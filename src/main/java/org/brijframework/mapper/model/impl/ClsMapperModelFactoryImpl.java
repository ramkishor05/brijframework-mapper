package org.brijframework.mapper.model.impl;

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
		if(model!=null) {
			return model;
		}
		ClsMapperModel owner = new ClsMapperModel();
		owner.setId(target.getSimpleName());
		owner.setTarget(target);
		register(owner);
		return model;
	}

}

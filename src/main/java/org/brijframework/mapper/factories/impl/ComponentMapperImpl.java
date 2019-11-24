package org.brijframework.mapper.factories.impl;

import org.brijframework.mapper.factories.MapperFactory;
import org.brijframework.mapper.model.ComponentMapper;
import org.brijframework.model.factories.asm.MetaInfoFactoryImpl;
import org.brijframework.support.config.Assignable;

public class ComponentMapperImpl extends MetaInfoFactoryImpl<String,ComponentMapper> implements MapperFactory<String,ComponentMapper> {

	protected ComponentMapperImpl() {
	}

	protected static ComponentMapperImpl factory;

	@Assignable
	public static ComponentMapperImpl getFactory() {
		if (factory == null) {
			factory = new ComponentMapperImpl();
		}
		return factory;
	}

	@Override
	public ComponentMapperImpl loadFactory() {
		return this;
	}

	public ComponentMapper load(Class<?> target) {
		ComponentMapper model=getMetaInfo(target.getSimpleName());
		if(model==null) {
			model = new ComponentMapper();
			model.setId(target.getSimpleName());
			model.setTarget(target);
			register(model);
		}
		return model;
	}

	@Override
	protected void preregister(String key, ComponentMapper value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void postregister(String key, ComponentMapper value) {
		// TODO Auto-generated method stub
		
	}

}

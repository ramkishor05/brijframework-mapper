package org.brijframework.mapper.factories.impl;

import org.brijframework.factories.impl.module.AbstractModuleFactory;
import org.brijframework.mapper.factories.MapperFactory;
import org.brijframework.mapper.model.ComponentMapper;
import org.brijframework.support.config.SingletonFactory;

public class ComponentMapperImpl extends AbstractModuleFactory<String,ComponentMapper> implements MapperFactory<String,ComponentMapper> {

	protected ComponentMapperImpl() {
	}

	protected static ComponentMapperImpl factory;

	@SingletonFactory
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
		ComponentMapper model=find(target.getSimpleName());
		if(model==null) {
			model = new ComponentMapper();
			model.setId(target.getSimpleName());
			model.setType(target);
			register(target.getSimpleName(),model);
		}
		return model;
	}

	@Override
	protected void preregister(String key, ComponentMapper value) {
	}

	@Override
	protected void postregister(String key, ComponentMapper value) {
	}

}

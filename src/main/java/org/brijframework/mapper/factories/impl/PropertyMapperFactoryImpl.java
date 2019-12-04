package org.brijframework.mapper.factories.impl;

import org.brijframework.factories.impl.module.AbstractModuleFactory;
import org.brijframework.mapper.factories.MapperFactory;
import org.brijframework.mapper.model.PropertyMapper;
import org.brijframework.support.config.SingletonFactory;

public class PropertyMapperFactoryImpl extends AbstractModuleFactory<String,PropertyMapper> implements MapperFactory<String,PropertyMapper> {

	protected PropertyMapperFactoryImpl() {
	}

	protected static PropertyMapperFactoryImpl factory;

	@SingletonFactory
	public static PropertyMapperFactoryImpl getFactory() {
		if (factory == null) {
			factory = new PropertyMapperFactoryImpl();
		}
		return factory;
	}

	@Override
	public PropertyMapperFactoryImpl loadFactory() {
		return this;
	}

	public PropertyMapper getMetaInfo(String id) {
		return null;
	}

	@Override
	protected void preregister(String key, PropertyMapper value) {
	}

	@Override
	protected void postregister(String key, PropertyMapper value) {
	}

}

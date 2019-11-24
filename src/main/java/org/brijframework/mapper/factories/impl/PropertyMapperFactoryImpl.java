package org.brijframework.mapper.factories.impl;

import org.brijframework.mapper.factories.MapperFactory;
import org.brijframework.mapper.model.PropertyMapper;
import org.brijframework.model.factories.asm.MetaInfoFactoryImpl;
import org.brijframework.support.config.Assignable;

public class PropertyMapperFactoryImpl extends MetaInfoFactoryImpl<String,PropertyMapper> implements MapperFactory<String,PropertyMapper> {

	protected PropertyMapperFactoryImpl() {
	}

	protected static PropertyMapperFactoryImpl factory;

	@Assignable
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
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void postregister(String key, PropertyMapper value) {
		// TODO Auto-generated method stub
		
	}

}

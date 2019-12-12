package org.brijframework.mapper.factories.impl;

import org.brijframework.factories.impl.module.AbstractModuleFactory;
import org.brijframework.model.mapper.factories.MapperFactory;
import org.brijframework.model.mapper.factories.impl.PropertyMapperFactoryImpl;
import org.brijframework.model.mapper.model.PropertyModelMapperResource;
import org.brijframework.support.config.SingletonFactory;

public class PropertyMapperFactoryImpl extends AbstractModuleFactory<String,PropertyModelMapperResource> implements MapperFactory<String,PropertyModelMapperResource> {

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

	public PropertyModelMapperResource getMetaInfo(String id) {
		return null;
	}

	@Override
	protected void preregister(String key, PropertyModelMapperResource value) {
	}

	@Override
	protected void postregister(String key, PropertyModelMapperResource value) {
	}

}

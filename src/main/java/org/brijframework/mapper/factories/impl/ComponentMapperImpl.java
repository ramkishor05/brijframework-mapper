package org.brijframework.mapper.factories.impl;

import org.brijframework.factories.impl.module.AbstractModuleFactory;
import org.brijframework.model.mapper.factories.MapperFactory;
import org.brijframework.model.mapper.factories.impl.ComponentMapperImpl;
import org.brijframework.model.mapper.model.TypeModelMapperResource;
import org.brijframework.support.config.SingletonFactory;

public class ComponentMapperImpl extends AbstractModuleFactory<String,TypeModelMapperResource> implements MapperFactory<String,TypeModelMapperResource> {

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

	public TypeModelMapperResource load(Class<?> target) {
		TypeModelMapperResource model=find(target.getSimpleName());
		if(model==null) {
			model = new TypeModelMapperResource();
			model.setId(target.getSimpleName());
			model.setType(target);
			register(target.getSimpleName(),model);
		}
		return model;
	}

	@Override
	protected void preregister(String key, TypeModelMapperResource value) {
	}

	@Override
	protected void postregister(String key, TypeModelMapperResource value) {
	}

}

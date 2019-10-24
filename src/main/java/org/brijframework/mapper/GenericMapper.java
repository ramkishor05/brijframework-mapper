package org.brijframework.mapper;

import org.brijframework.mapper.factories.impl.ClsMapperModelFactoryImpl;
import org.brijframework.mapper.model.ClsMapperModel;

public abstract class GenericMapper<T,S> {

	public T target(S source) {
		ClsMapperModel model= ClsMapperModelFactoryImpl.getFactory().getMetaInfo(source.getClass().getName());
		System.out.println(model);
		return null;
	}
	
	public S source(T target) {
		ClsMapperModel model= ClsMapperModelFactoryImpl.getFactory().getMetaInfo(target.getClass().getName());
		System.out.println(model);
		return null;
	}
}

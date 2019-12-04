package org.brijframework.mapper;

import org.brijframework.mapper.factories.impl.ComponentMapperImpl;
import org.brijframework.mapper.model.ComponentMapper;

public abstract class GenericMapper<T,S> {

	public T target(S source) {
		ComponentMapper model= ComponentMapperImpl.getFactory().find(source.getClass().getName());
		System.out.println(model);
		return null;
	}
	
	public S source(T target) {
		ComponentMapper model= ComponentMapperImpl.getFactory().find(target.getClass().getName());
		System.out.println(model);
		return null;
	}
}

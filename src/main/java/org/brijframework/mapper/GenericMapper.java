package org.brijframework.mapper;

import org.brijframework.model.mapper.factories.impl.ComponentMapperImpl;
import org.brijframework.model.mapper.model.TypeModelMapperResource;

public abstract class GenericMapper<T,S> {

	public T target(S source) {
		TypeModelMapperResource model= ComponentMapperImpl.getFactory().find(source.getClass().getName());
		System.out.println(model);
		return null;
	}
	
	public S source(T target) {
		TypeModelMapperResource model= ComponentMapperImpl.getFactory().find(target.getClass().getName());
		System.out.println(model);
		return null;
	}
}

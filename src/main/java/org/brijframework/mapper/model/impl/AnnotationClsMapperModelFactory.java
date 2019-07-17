package org.brijframework.mapper.model.impl;

import java.util.Map;

import org.brijframework.mapper.factories.MapperModelFactory;
import org.brijframework.mapper.model.ClsMapperModel;
import org.brijframework.model.factories.asm.MetaInfoFactoryImpl;
import org.brijframework.support.config.Assignable;
import org.brijframework.support.mapper.Mapper;
import org.brijframework.support.mapper.Mappers;
import org.brijframework.util.accessor.PropertyAccessorUtil;
import org.brijframework.util.reflect.AnnotationUtil;
import org.brijframework.util.reflect.ReflectionUtils;

public class AnnotationClsMapperModelFactory extends MetaInfoFactoryImpl<ClsMapperModel> implements MapperModelFactory {

	protected AnnotationClsMapperModelFactory() {
	}

	protected static AnnotationClsMapperModelFactory factory;

	@Assignable
	public static AnnotationClsMapperModelFactory getFactory() {
		if (factory == null) {
			factory = new AnnotationClsMapperModelFactory();
		}
		return factory;
	}

	@Override
	public AnnotationClsMapperModelFactory loadFactory() {
		ReflectionUtils.getInternalClassList().forEach(target -> {
			this.register(target);
		});
		return this;
	}

	public void register(Class<?> target) {
		if (target.isAnnotationPresent(Mappers.class)) {
			Mappers mappers=target.getAnnotation(Mappers.class);
			for(Mapper mapper:mappers.value()) {
				register(target, mapper);
			}
		} else if (target.isAnnotationPresent(Mapper.class)) {
			register(target, target.getAnnotation(Mapper.class));
		}
	}

	public ClsMapperModel register(Class<?> target, Mapper mapper) {
		Map<String, Object> properties = AnnotationUtil.getAnnotationData(mapper);
		ClsMapperModel owner = new ClsMapperModel();
		PropertyAccessorUtil.setProperties(owner, properties);
		owner.setId(target.getSimpleName());
		owner.setTarget(target);
		register(owner);
		return owner;
	}
}

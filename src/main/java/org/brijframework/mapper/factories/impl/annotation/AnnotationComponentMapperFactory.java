package org.brijframework.mapper.factories.impl.annotation;

import java.util.Map;

import org.brijframework.factories.impl.module.AbstractModuleFactory;
import org.brijframework.mapper.factories.MapperFactory;
import org.brijframework.mapper.model.ComponentMapper;
import org.brijframework.support.config.SingletonFactory;
import org.brijframework.support.mapper.Mapper;
import org.brijframework.support.mapper.Mappers;
import org.brijframework.util.accessor.PropertyAccessorUtil;
import org.brijframework.util.reflect.AnnotationUtil;
import org.brijframework.util.reflect.ReflectionUtils;

public class AnnotationComponentMapperFactory extends AbstractModuleFactory<String,ComponentMapper> implements MapperFactory<String,ComponentMapper> {

	protected AnnotationComponentMapperFactory() {
	}

	protected static AnnotationComponentMapperFactory factory;

	@SingletonFactory
	public static AnnotationComponentMapperFactory getFactory() {
		if (factory == null) {
			factory = new AnnotationComponentMapperFactory();
		}
		return factory;
	}

	@Override
	public AnnotationComponentMapperFactory loadFactory() {
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

	public ComponentMapper register(Class<?> target, Mapper mapper) {
		Map<String, Object> properties = AnnotationUtil.getAnnotationData(mapper);
		ComponentMapper owner = new ComponentMapper();
		PropertyAccessorUtil.setProperties(owner, properties);
		owner.setId(target.getSimpleName());
		owner.setType(target);
		owner.setName(target.getSimpleName());
		register(owner.getId(),owner);
		return owner;
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

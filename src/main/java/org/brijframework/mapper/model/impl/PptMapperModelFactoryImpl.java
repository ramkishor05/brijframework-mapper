package org.brijframework.mapper.model.impl;

import java.lang.reflect.Field;
import java.util.Map;

import org.brijframework.mapper.factories.MapperModelFactory;
import org.brijframework.mapper.model.PptMapperModel;
import org.brijframework.model.factories.asm.ClassMetaInfoFactoryImpl;
import org.brijframework.model.factories.asm.MetaInfoFactoryImpl;
import org.brijframework.model.info.OwnerModelInfo;
import org.brijframework.support.config.Assignable;
import org.brijframework.support.mapper.Mapper;
import org.brijframework.support.mapper.Mappers;
import org.brijframework.util.accessor.PropertyAccessorUtil;
import org.brijframework.util.reflect.AnnotationUtil;
import org.brijframework.util.reflect.FieldUtil;
import org.brijframework.util.reflect.ReflectionUtils;
import org.brijframework.util.support.Access;

public class PptMapperModelFactoryImpl extends MetaInfoFactoryImpl<PptMapperModel> implements MapperModelFactory {

	protected PptMapperModelFactoryImpl() {
	}

	protected static PptMapperModelFactoryImpl factory;

	@Assignable
	public static PptMapperModelFactoryImpl getFactory() {
		if (factory == null) {
			factory = new PptMapperModelFactoryImpl();
		}
		return factory;
	}

	@Override
	public PptMapperModelFactoryImpl loadFactory() {
		ReflectionUtils.getInternalClassList().forEach(target -> {
			this.register(target);
		});
		return this;
	}

	public void register(Class<?> target) {
		for (Field field : FieldUtil.getAllField(target, Access.PRIVATE)) {
			if (field.isAnnotationPresent(Mappers.class)) {
				Mappers mappers=field.getAnnotation(Mappers.class);
				for(Mapper mapper:mappers.value()) {
					register(target, field, mapper);
				}
			} else if (field.isAnnotationPresent(Mapper.class)) {
				register(target, field, field.getAnnotation(Mapper.class));
			}
		}
	}

	public PptMapperModel register(Class<?> target, Field field, Mapper mapper) {
		Map<String, Object> properties = AnnotationUtil.getAnnotationData(mapper);
		PptMapperModel modelMap = new PptMapperModel();
		PropertyAccessorUtil.setProperties(modelMap, properties);
		modelMap.setId(target.getSimpleName() + "_" + mapper.source());
		OwnerModelInfo owner = ClassMetaInfoFactoryImpl.getFactory().load(target);
		modelMap.setName(field.getName());
		modelMap.setOwner(owner);
		modelMap.setTarget(field);
		register(modelMap);
		return modelMap;
	}
}

package org.brijframework.mapper.model.impl;

import java.lang.reflect.Field;
import java.util.Map;

import org.brijframework.mapper.factories.MapperModelFactory;
import org.brijframework.mapper.model.PptMapperModel;
import org.brijframework.model.factories.asm.ClassMetaInfoFactoryImpl;
import org.brijframework.model.factories.asm.MetaInfoFactoryImpl;
import org.brijframework.model.info.OwnerModelInfo;
import org.brijframework.support.config.Assignable;
import org.brijframework.support.model.mapper.PropertyMapper;
import org.brijframework.support.model.mapper.PropertyMappers;
import org.brijframework.util.accessor.PropertyAccessorUtil;
import org.brijframework.util.reflect.AnnotationUtil;
import org.brijframework.util.reflect.FieldUtil;
import org.brijframework.util.reflect.ReflectionUtils;
import org.brijframework.util.support.Access;

public class AnnotationPptMapperModelFactory extends MetaInfoFactoryImpl<PptMapperModel> implements MapperModelFactory {

	protected AnnotationPptMapperModelFactory() {
	}

	protected static AnnotationPptMapperModelFactory factory;

	@Assignable
	public static AnnotationPptMapperModelFactory getFactory() {
		if (factory == null) {
			factory = new AnnotationPptMapperModelFactory();
		}
		return factory;
	}

	@Override
	public AnnotationPptMapperModelFactory loadFactory() {
		ReflectionUtils.getInternalClassList().forEach(target -> {
			this.register(target);
		});
		return this;
	}

	public void register(Class<?> target) {
		for (Field field : FieldUtil.getAllField(target, Access.PRIVATE)) {
			if (field.isAnnotationPresent(PropertyMappers.class)) {
				PropertyMappers mappers=field.getAnnotation(PropertyMappers.class);
				for(PropertyMapper mapper:mappers.value()) {
					register(target, field, mapper);
				}
			} else if (field.isAnnotationPresent(PropertyMapper.class)) {
				register(target, field, field.getAnnotation(PropertyMapper.class));
			}
		}
	}

	public PptMapperModel register(Class<?> target, Field field, PropertyMapper mapper) {
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

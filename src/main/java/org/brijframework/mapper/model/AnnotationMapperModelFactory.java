package org.brijframework.mapper.model;

import java.lang.reflect.Field;
import java.util.Map;

import org.brijframework.mapper.factories.MapperModelFactory;
import org.brijframework.model.factories.asm.ClassMetaInfoFactoryImpl;
import org.brijframework.model.factories.asm.MetaInfoFactoryImpl;
import org.brijframework.model.info.OwnerModelInfo;
import org.brijframework.support.config.Assignable;
import org.brijframework.support.model.Mapping;
import org.brijframework.util.accessor.PropertyAccessorUtil;
import org.brijframework.util.reflect.AnnotationUtil;
import org.brijframework.util.reflect.FieldUtil;
import org.brijframework.util.reflect.ReflectionUtils;
import org.brijframework.util.support.Access;

public class AnnotationMapperModelFactory extends MetaInfoFactoryImpl<MapperModel> implements MapperModelFactory{

	protected AnnotationMapperModelFactory() {
	}

	protected static AnnotationMapperModelFactory factory;

	@Assignable
	public static AnnotationMapperModelFactory getFactory() {
		if (factory == null) {
			factory = new AnnotationMapperModelFactory();
		}
		return factory;
	}

	@Override
	public AnnotationMapperModelFactory loadFactory() {
		ReflectionUtils.getInternalClassList().forEach(target -> {
			this.register(target);
		});
		return this;
	}

	private void register(Class<?> target) {
		for (Field field : FieldUtil.getAllField(target, Access.PRIVATE)) {
			if(field.isAnnotationPresent(Mapping.class)) {
				register(target,field,field.getAnnotation(Mapping.class));
			}
		}
	}

	private void register(Class<?> target, Field field,Mapping mapping) {
		Map<String,Object> properties=AnnotationUtil.getAnnotationData(mapping);
		MapperModel modelMap=new MapperModel();
		PropertyAccessorUtil.setProperties(modelMap, properties);
		modelMap.setId(target.getSimpleName()+"_"+mapping.source());
		OwnerModelInfo owner= ClassMetaInfoFactoryImpl.getFactory().load(target);
		modelMap.setName(field.getName());
		modelMap.setOwner(owner);
		modelMap.setTarget(field);
		register(modelMap);
	}
}

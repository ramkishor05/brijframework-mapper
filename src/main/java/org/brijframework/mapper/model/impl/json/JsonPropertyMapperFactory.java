package org.brijframework.mapper.model.impl.json;

import java.lang.reflect.Field;
import java.util.Map;

import org.brijframework.factories.impl.module.AbstractModuleFactory;
import org.brijframework.model.factories.metadata.impl.TypeModelMetaDataFactoryImpl;
import org.brijframework.model.mapper.factories.MapperFactory;
import org.brijframework.model.mapper.model.PropertyModelMapperResource;
import org.brijframework.model.mapper.model.impl.json.JsonComponentMapperFactory;
import org.brijframework.model.mapper.model.impl.json.JsonPropertyMapperFactory;
import org.brijframework.model.metadata.TypeModelMetaData;
import org.brijframework.support.config.DepandOn;
import org.brijframework.support.config.SingletonFactory;
import org.brijframework.support.mapper.Mapper;
import org.brijframework.support.mapper.Mappers;
import org.brijframework.util.accessor.PropertyAccessorUtil;
import org.brijframework.util.reflect.AnnotationUtil;
import org.brijframework.util.reflect.FieldUtil;
import org.brijframework.util.reflect.ReflectionUtils;
import org.brijframework.util.support.Access;

@DepandOn(depand=JsonComponentMapperFactory.class)
public class JsonPropertyMapperFactory extends AbstractModuleFactory<String,PropertyModelMapperResource> implements MapperFactory<String,PropertyModelMapperResource> {

	protected JsonPropertyMapperFactory() {
	}

	protected static JsonPropertyMapperFactory factory;

	@SingletonFactory
	public static JsonPropertyMapperFactory getFactory() {
		if (factory == null) {
			factory = new JsonPropertyMapperFactory();
		}
		return factory;
	}

	@Override
	public JsonPropertyMapperFactory loadFactory() {
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

	public PropertyModelMapperResource register(Class<?> target, Field field, Mapper mapper) {
		Map<String, Object> properties = AnnotationUtil.getAnnotationData(mapper);
		PropertyModelMapperResource modelMap = new PropertyModelMapperResource();
		PropertyAccessorUtil.setProperties(modelMap, properties);
		modelMap.setId(target.getSimpleName() + "_" + mapper.source());
		TypeModelMetaData owner = TypeModelMetaDataFactoryImpl.getFactory().load(target);
		modelMap.setName(field.getName());
		modelMap.setOwner(owner);
		modelMap.setTarget(field);
		register(modelMap.getId(), modelMap);
		return modelMap;
	}

	@Override
	protected void preregister(String key, PropertyModelMapperResource value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void postregister(String key, PropertyModelMapperResource value) {
		// TODO Auto-generated method stub
		
	}
}

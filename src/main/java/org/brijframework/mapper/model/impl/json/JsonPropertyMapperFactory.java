package org.brijframework.mapper.model.impl.json;

import java.lang.reflect.Field;
import java.util.Map;

import org.brijframework.mapper.factories.MapperFactory;
import org.brijframework.mapper.model.PropertyMapper;
import org.brijframework.model.factories.metadata.asm.ModelMetaDataFactoryImpl;
import org.brijframework.model.factories.metadata.asm.impl.ClassModelMetaDataFactoryImpl;
import org.brijframework.model.info.ClassModelMetaData;
import org.brijframework.support.config.Assignable;
import org.brijframework.support.config.DepandOn;
import org.brijframework.support.mapper.Mapper;
import org.brijframework.support.mapper.Mappers;
import org.brijframework.util.accessor.PropertyAccessorUtil;
import org.brijframework.util.reflect.AnnotationUtil;
import org.brijframework.util.reflect.FieldUtil;
import org.brijframework.util.reflect.ReflectionUtils;
import org.brijframework.util.support.Access;

@DepandOn(depand=JsonComponentMapperFactory.class)
public class JsonPropertyMapperFactory extends ModelMetaDataFactoryImpl<String,PropertyMapper> implements MapperFactory<String,PropertyMapper> {

	protected JsonPropertyMapperFactory() {
	}

	protected static JsonPropertyMapperFactory factory;

	@Assignable
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

	public PropertyMapper register(Class<?> target, Field field, Mapper mapper) {
		Map<String, Object> properties = AnnotationUtil.getAnnotationData(mapper);
		PropertyMapper modelMap = new PropertyMapper();
		PropertyAccessorUtil.setProperties(modelMap, properties);
		modelMap.setId(target.getSimpleName() + "_" + mapper.source());
		ClassModelMetaData owner = ClassModelMetaDataFactoryImpl.getFactory().load(target);
		modelMap.setName(field.getName());
		modelMap.setOwner(owner);
		modelMap.setTarget(field);
		register(modelMap);
		return modelMap;
	}

	@Override
	protected void preregister(String key, PropertyMapper value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void postregister(String key, PropertyMapper value) {
		// TODO Auto-generated method stub
		
	}
}

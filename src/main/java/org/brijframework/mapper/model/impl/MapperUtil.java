package org.brijframework.mapper.model.impl;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Map.Entry;

import org.brijframework.bean.impl.BeanBuilder;
import org.brijframework.mapper.model.PptMapperModel;
import org.brijframework.util.accessor.PropertyAccessorUtil;
import org.brijframework.util.asserts.Assertion;
import org.brijframework.util.reflect.FieldUtil;
import org.brijframework.util.reflect.InstanceUtil;
import org.brijframework.util.support.Access;

public class MapperUtil {

	public static Object classMappedFromObject(Class<?> toClass,Object fromObject) {
		Assertion.notNull(toClass, "Generic type not found.");
		Object toObject= InstanceUtil.getInstance(toClass);
		return mappedToObjectFromObject(toObject, fromObject);
	}
	
	public static Object classMappedFromMap(Class<?> toClass,Map<String,Object> fromObject) {
		Assertion.notNull(toClass, "Generic type not found.");
		Object toObject= InstanceUtil.getInstance(toClass);
		return mappedToObjectFromMap(toObject, fromObject);
	}
	
	public static Object mappedToObjectFromObject(Object toObject,Object fromObject) {
		BeanBuilder builder=new BeanBuilder(toObject);
		for(Field entry: FieldUtil.getAllField(fromObject.getClass(), Access.PRIVATE)) {
			PptMapperModel mapper=PptMapperModelFactoryImpl.getFactory().getMetaInfo(toObject.getClass().getSimpleName()+"_"+entry.getName());
			Object value=PropertyAccessorUtil.getProperty(fromObject, entry, Access.PRIVATE);
			if(mapper!=null) {
				builder.setProperty(mapper.getName(), value);
			}else {
				builder.setProperty(entry.getName(), value);
			}
		}
		return builder.getCurrentInstance();
	}
	
	public static Object mappedToObjectFromMap(Object toObject,Map<String,Object> fromObject) {
		BeanBuilder builder=new BeanBuilder(toObject);
		for(Entry<String, Object> entry: fromObject.entrySet()) {
			PptMapperModel mapper=PptMapperModelFactoryImpl.getFactory().getMetaInfo(fromObject.getClass().getSimpleName()+"_"+entry.getKey());
			if(mapper!=null) {
				builder.setProperty(mapper.getName(), entry.getValue());
			}else {
				builder.setProperty(entry.getKey(), entry.getValue());
			}
		}
		return builder.getCurrentInstance();
	}

	public static Object mappedFromObjectToMap(Object toObject, Map<String, Object> fromObject) {
		BeanBuilder builder=new BeanBuilder(toObject);
		for(Entry<String, Object> entry: fromObject.entrySet()) {
			PptMapperModel mapper=PptMapperModelFactoryImpl.getFactory().getMetaInfo(fromObject.getClass().getSimpleName()+"_"+entry.getKey());
			if(mapper!=null) {
				builder.setProperty(mapper.getName(), entry.getValue());
			}else {
				builder.setProperty(entry.getKey(), entry.getValue());
			}
		}
		return builder.getCurrentInstance();
	}

	public static Object mappedFromObjectToObject(Object toObject,Object fromObject) {
		BeanBuilder builder=new BeanBuilder(fromObject);
		for(Field entry: FieldUtil.getAllField(fromObject.getClass(), Access.PRIVATE)) {
			PptMapperModel mapper=PptMapperModelFactoryImpl.getFactory().getMetaInfo(toObject.getClass().getSimpleName()+"_"+entry.getName());
			if(mapper!=null) {
				builder.setProperty(entry.getName(), PropertyAccessorUtil.getProperty(toObject, mapper.getName(), Access.PRIVATE));
			}
		}
		return builder.getCurrentInstance();
	}
}

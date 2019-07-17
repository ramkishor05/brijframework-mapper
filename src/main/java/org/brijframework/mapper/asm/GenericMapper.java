package org.brijframework.mapper.asm;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Map;

import org.brijframework.mapper.BeanMapper;
import org.brijframework.mapper.model.impl.MapperUtil;
import org.brijframework.util.asserts.Assertion;

public class GenericMapper<E> implements BeanMapper<E> {

	public GenericMapper() {
	}

	public Class<?> getGenericType(int index) {
		try {
			Type sooper = getClass().getGenericSuperclass();
			return (Class<?>) ((ParameterizedType) sooper).getActualTypeArguments()[index];
		} catch (Exception e) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public E mapped(Object object) {
		try {
			Class<?> toClass=getGenericType(0);
			Assertion.notNull(toClass, "Generic type should not be null or empty.");
			if(object instanceof Map) {
				return (E) MapperUtil.classMappedFromMap(toClass,(Map<String,Object>)object);
			}else {
				return (E) MapperUtil.classMappedFromObject(toClass,object);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public E mappedTo(E toObject,Object fromObject) {
		try {
			Assertion.notNull(toObject, "To object should not be null or empty.");
			if(fromObject instanceof Map) {
				return (E) MapperUtil.mappedToObjectFromMap(toObject,(Map<String,Object>)fromObject);
			}else {
				return (E) MapperUtil.mappedToObjectFromObject(toObject,fromObject);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public Object mappedFrom(E toObject,Object fromObject) {
		try {
			Assertion.notNull(toObject, "To object should not be null or empty.");
			if(fromObject instanceof Map) {
				return (E) MapperUtil.mappedFromObjectToMap(toObject,(Map<String,Object>)fromObject);
			}else {
				return (E) MapperUtil.mappedFromObjectToObject(toObject,fromObject);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}

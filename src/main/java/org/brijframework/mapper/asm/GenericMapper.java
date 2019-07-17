package org.brijframework.mapper.asm;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Map;
import java.util.Map.Entry;

import org.brijframework.mapper.BeanMapper;
import org.brijframework.mapper.model.ClsMapperModel;
import org.brijframework.mapper.model.impl.ClsMapperModelFactoryImpl;
import org.brijframework.util.asserts.Assertion;
import org.brijframework.util.reflect.FieldUtil;
import org.brijframework.util.reflect.InstanceUtil;
import org.brijframework.util.support.Access;

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
		Class<?> type=getGenericType(0);
		Assertion.notNull(type, "Generic type not found.");
		try {
			if(object instanceof Map) {
				return mappedFromMap((Map<String,Object>)object);
			}else {
				return mappedFromObject(object);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public E mappedFromMap(Map<String,Object> properties) {
		Class<?> type=getGenericType(0);
		Assertion.notNull(type, "Generic type not found.");
		try {
			ClsMapperModel model=ClsMapperModelFactoryImpl.getFactory().load(type);
			@SuppressWarnings("unchecked")
			E e=(E) InstanceUtil.getInstance(type);
			for(Entry<String, Object> entry: properties.entrySet()) {
				
			}
			return e;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public E mappedFromObject(Object properties) {
		Class<?> type=getGenericType(0);
		Assertion.notNull(type, "Generic type not found.");
		try {
			ClsMapperModel model=ClsMapperModelFactoryImpl.getFactory().load(type);
			@SuppressWarnings("unchecked")
			E e=(E) InstanceUtil.getInstance(type);
			for(Field entry: FieldUtil.getAllField(properties.getClass(), Access.PRIVATE)) {
				
			}
			return e;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}

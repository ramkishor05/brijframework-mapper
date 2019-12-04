package org.brijframework.mapper.container;

import org.brijframework.container.impl.module.AbstractModuleContainer;
import org.brijframework.group.Group;
import org.brijframework.mapper.factories.MapperFactory;
import org.brijframework.model.container.asm.ModelMetaDataContainer;
import org.brijframework.model.group.ModelMetaDataGroup;
import org.brijframework.support.config.SingletonFactory;
import org.brijframework.support.config.DepandOn;
import org.brijframework.util.reflect.InstanceUtil;
import org.brijframework.util.reflect.ReflectionUtils;

@DepandOn(depand=ModelMetaDataContainer.class)
public class MapperContainer extends AbstractModuleContainer{

	private static MapperContainer container;

	@SingletonFactory
	public static MapperContainer getContainer() {
		if (container == null) {
			container = InstanceUtil.getSingletonInstance(MapperContainer.class);
		}
		return container;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void init() {
		try {
			ReflectionUtils.getClassListFromExternal().forEach(cls -> {
				if (MapperFactory.class.isAssignableFrom(cls) && InstanceUtil.isAssignable(cls)) {
					register((Class<? extends MapperFactory<?,?>>) cls);
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			ReflectionUtils.getClassListFromInternal().forEach(cls -> {
				if (MapperFactory.class.isAssignableFrom(cls) && InstanceUtil.isAssignable(cls)) {
					register((Class<? extends MapperFactory<?,?>>) cls);
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public Group load(Object groupKey) {
		Group group=get(groupKey);
		if(group==null) {
			group = new ModelMetaDataGroup(groupKey);
			getCache().put(groupKey, group);
		}
		return group;
	}

}

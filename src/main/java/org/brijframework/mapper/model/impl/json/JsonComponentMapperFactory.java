package org.brijframework.mapper.model.impl.json;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.brijframework.factories.impl.module.AbstractModuleFactory;
import org.brijframework.model.mapper.config.MapperConfig;
import org.brijframework.model.mapper.constants.MapperConstants;
import org.brijframework.model.mapper.factories.MapperFactory;
import org.brijframework.model.mapper.model.PropertyModelMapperResource;
import org.brijframework.model.mapper.model.TypeModelMapperResource;
import org.brijframework.model.mapper.model.impl.json.JsonComponentMapperFactory;
import org.brijframework.resources.factory.json.JsonResourceFactory;
import org.brijframework.resources.files.json.JsonResource;
import org.brijframework.support.config.SingletonFactory;
import org.brijframework.util.asserts.Assertion;
import org.brijframework.util.reflect.FieldUtil;
import org.brijframework.util.reflect.InstanceUtil;
import org.brijframework.util.support.Access;
import org.json.JSONException;

public class JsonComponentMapperFactory extends AbstractModuleFactory<String,TypeModelMapperResource> implements MapperFactory<String,TypeModelMapperResource> {

	protected JsonComponentMapperFactory() {
	}

	protected static JsonComponentMapperFactory factory;

	@SingletonFactory
	public static JsonComponentMapperFactory getFactory() {
		if (factory == null) {
			factory = new JsonComponentMapperFactory();
		}
		return factory;
	}


	@SuppressWarnings("unchecked")
	public List<MapperConfig> configration() {
		Object resources=getContainer().getContext().getEnvironment().get(MapperConstants.APPLICATION_BOOTSTRAP_CONFIG_MAPPER_LOCATION);
		if (resources==null) {
			System.err.println("Mapper configration not found :"+MapperConstants.APPLICATION_BOOTSTRAP_CONFIG_MAPPER_LOCATION);
			return null;
		}
		System.err.println("Mapper configration found :"+MapperConstants.APPLICATION_BOOTSTRAP_CONFIG_MAPPER_LOCATION);
		if(resources instanceof List) {
			return build((List<Map<String, Object>>)resources);
		}else if(resources instanceof Map) {
			return build((Map<String, Object>)resources);
		}else {
			System.err.println("Invalid mapper configration : "+resources);
			return null;
		}
	}
	
	private List<MapperConfig> build(Map<String, Object> resource) {
		List<MapperConfig> configs=new ArrayList<MapperConfig>();
		configs.add(InstanceUtil.getInstance(MapperConfig.class, resource));
		return configs;
	}

	private List<MapperConfig> build(List<Map<String, Object>> resources) {
		List<MapperConfig> configs=new ArrayList<MapperConfig>();
		for(Map<String, Object> resource:resources) {
			configs.add(InstanceUtil.getInstance(MapperConfig.class, resource));
		}
		return configs;
	}
	
	
	@Override
	public JsonComponentMapperFactory loadFactory() {
		List<MapperConfig> configs=configration();
		if(configs==null) {
			System.err.println("Invalid mapper configration : "+configs);
			return this;
		}
		for(MapperConfig modelConfig:configs) {
			if(!modelConfig.isEnable()) {
				System.err.println("mapper configration disabled found :"+modelConfig.getLocation());
			
			}
			Collection<JsonResource> resources=JsonResourceFactory.factory().getResources(modelConfig.getLocation());
			for(JsonResource resource:resources) {
				if(resource.isJsonList()) {
				   try {
					  register(resource.toObjectList());
					} catch (JSONException e) {
						e.printStackTrace();
					}
				}
				if(resource.isJsonObject()) {
					try {
						register(resource.toObjectMap());
					} catch (JSONException e) {
						e.printStackTrace();
					}
				}
			}
		}
		return this;
	}
	@SuppressWarnings("unchecked")
	public void register(List<Object> resources) {
		Assertion.notNull(resources, "Invalid resources");
		for(Object object:resources) {
			if(object instanceof Map) {
				register((Map<String, Object>)object);
			}
		}
	}

	public void register(Map<String, Object> resourceMap) {
		Assertion.notNull(resourceMap, "Invalid target :"+resourceMap);
		@SuppressWarnings("unchecked")
		Map<String,Map<String,Object>> properties=(Map<String, Map<String, Object>>) resourceMap.remove("properties");
		TypeModelMapperResource metaSetup=InstanceUtil.getInstance(TypeModelMapperResource.class,resourceMap);
		if(properties!=null) {
			properties.forEach((key,property)->{
				PropertyModelMapperResource pptMapperModel=getPropertyMapper(metaSetup.getType(),key,property);
				pptMapperModel.setId(key);
				String destinationKey=metaSetup.getName()+"_"+pptMapperModel.getDestination();
				String sourceKey=metaSetup.getName()+"_"+pptMapperModel.getSource();
				String idKey=metaSetup.getName()+"_"+pptMapperModel.getId();
				System.err.println("PptMapperModel    :"+idKey);
				metaSetup.getProperties().put(idKey,pptMapperModel );
				if(pptMapperModel.getSource()!=null && !metaSetup.getProperties().containsKey(sourceKey)) {
				   System.err.println("PptMapperModel    :"+metaSetup.getName()+"_"+pptMapperModel.getSource());
				   metaSetup.getProperties().put(metaSetup.getName()+"_"+pptMapperModel.getSource(),pptMapperModel );
				}
				if(pptMapperModel.getDestination()!=null && !metaSetup.getProperties().containsKey(destinationKey)) {
				   System.err.println("PptMapperModel    :"+destinationKey);
				   metaSetup.getProperties().put(metaSetup.getName()+"_"+pptMapperModel.getDestination(),pptMapperModel );
				}
			});
		}
		this.register(metaSetup.getId(),metaSetup);
	}


	private PropertyModelMapperResource getPropertyMapper(Class<?> type,String _field, Map<String, Object> property) {
		PropertyModelMapperResource pptMapperModel=InstanceUtil.getInstance(PropertyModelMapperResource.class,property);
		if(type!=null) {
		  pptMapperModel.setTarget(FieldUtil.getField(type, _field, Access.PRIVATE));
		}
		return pptMapperModel;
	}


	@Override
	protected void preregister(String key, TypeModelMapperResource value) {
	}


	@Override
	protected void postregister(String key, TypeModelMapperResource value) {
	}
}

package org.brijframework.mapper.model;

import java.util.HashMap;
import java.util.Map;

import org.brijframework.model.resource.asm.AbstractModelResource;

public class ComponentMapper  extends AbstractModelResource<Class<?>>  {

	private String source;

	private String destination;
	
	private Class<?> type;
	
	private Map<String,PropertyMapper> properties;
	
	public Map<String, PropertyMapper> getProperties() {
		if(properties==null) {
			properties=new HashMap<String, PropertyMapper>();
		}
		return properties;
	}
	
	public void setProperties(Map<String, PropertyMapper> properties) {
		this.properties = properties;
	}
	
	@Override
	public Class<?> getType() {
		return type;
	}

	public void setType(Class<?> type) {
		this.type = type;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}
	
}

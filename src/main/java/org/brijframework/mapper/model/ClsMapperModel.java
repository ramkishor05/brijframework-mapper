package org.brijframework.mapper.model;

import java.util.HashMap;
import java.util.Map;

import org.brijframework.model.info.asm.AbstractModelInfo;

public class ClsMapperModel  extends AbstractModelInfo<Class<?>>  {

	private String source;

	private String destination;
	
	private String type;
	
	private Class<?> target;
	
	private Map<String,PptMapperModel> properties;
	
	public Map<String, PptMapperModel> getProperties() {
		if(properties==null) {
			properties=new HashMap<String, PptMapperModel>();
		}
		return properties;
	}
	
	public void setProperties(Map<String, PptMapperModel> properties) {
		this.properties = properties;
	}
	
	@Override
	public Class<?> getTarget() {
		return target;
	}

	public void setTarget(Class<?> target) {
		this.target = target;
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
	
	public void setType(String type) {
		this.type = type;
	}
	
	public String getType() {
		return type;
	}
}

package org.brijframework.mapper.model;

import java.lang.reflect.Field;

import org.brijframework.model.metadata.TypeModelMetaData;
import org.brijframework.model.metadata.asm.AbstractModelMetaData;

public class PropertyMapper extends AbstractModelMetaData<Field> {

	private String source;

	private String destination;

	private Field target;

	TypeModelMetaData owner;

	@Override
	public Field getType() {
		return target;
	}

	public void setTarget(Field target) {
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

	public void setOwner(TypeModelMetaData owner) {
		this.owner = owner;
	}
	
	public TypeModelMetaData getOwner() {
		return owner;
	}

}

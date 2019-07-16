package org.brijframework.mapper.model;

import java.lang.reflect.Field;

import org.brijframework.model.info.asm.AbstractModelInfo;

public class PptMapperModel extends AbstractModelInfo<Field> {

	private String source;

	private String destination;

	private Field target;

	@Override
	public Field getTarget() {
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
	
}

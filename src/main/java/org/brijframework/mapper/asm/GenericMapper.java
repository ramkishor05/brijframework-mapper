package org.brijframework.mapper.asm;

import org.brijframework.mapper.Mapper;

public class GenericMapper<K, V> implements Mapper<K, V> {

	@Override
	public V mappedTo(K object) {
		return null;
	}

	@Override
	public K mappedFrom(V object) {
		return null;
	}
}

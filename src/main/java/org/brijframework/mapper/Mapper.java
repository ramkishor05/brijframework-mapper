package org.brijframework.mapper;

public interface Mapper<K,V> {

	public V mappedTo(K object);
	
	public K mappedFrom(V object);
	
}

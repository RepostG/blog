package com.yc.education.util;

public class KeyValuePair<K, V, I> {

	public KeyValuePair() {

	}

	public KeyValuePair(K k, V v, I i) {
		super();
		this.k = k;
		this.v = v;
		this.i = i;
	}

	private K k;
	private V v;
	private I i;

	public I getI() {
		return i;
	}

	public void setI(I i) {
		this.i = i;
	}

	public K getK() {
		return k;
	}

	public void setK(K k) {
		this.k = k;
	}

	public V getV() {
		return v;
	}

	public void setV(V v) {
		this.v = v;
	}


}

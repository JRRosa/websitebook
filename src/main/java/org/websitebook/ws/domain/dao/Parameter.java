package org.websitebook.ws.domain.dao;

import java.util.HashMap;
import java.util.Map;

public class Parameter {
	
	private Map<String, Object> collection;
	
	public Parameter() {
		super();
		collection = new HashMap<>();
	}
	
	public Parameter(String name, Object value) {
		this();
		collection.put(name, value);
	}
	
	public Parameter add(String name, Object value){
		collection.put(name, value);
		return this;
	}
	
	public Map<String, Object> getParameters(){
		return collection;
	}

}

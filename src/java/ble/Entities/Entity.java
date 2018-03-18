package ble.entities;

public abstract class Entity implements EntityInterface {
	
	// GLOBAL = null  , <FUNCTION_NAME> , <FUNCTION_NAME@IF@FOR>
	protected String scope;
	protected String name;
	protected Object value;
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Object getValue() {
		return this.value;
	}
	
	public void setValue(Object value) {
		this.value = value;
	}
	
	public String getScope( ) {
		return this.scope;
	}
	
	public void setScope(String scope) {
		this.scope = scope;
	}
}

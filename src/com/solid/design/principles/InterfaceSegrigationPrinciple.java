//$Id$
package com.solid.design.principles;

public class InterfaceSegrigationPrinciple {

}
class Document{
	
}
interface Machine{
	void print(Document d);
	void fax(Document d);
	void scan(Document d);
}
class MultiFunctionPrinter implements Machine{
	@Override
	public void print(Document d) {		
	}
	@Override
	public void fax(Document d) {		
	}
	@Override
	public void scan(Document d) {		
	}	
}
class OldFashinedPrinter implements Machine{

	@Override
	public void print(Document d) {		
	}

	@Override
	public void fax(Document d) {
		//since printer is old function, it does not supports fax but its bound to implement fax method
		
		//one option here is to leave the method empty but user of old fashined printer will still get 
		//an indication that he can fax a document because printer is implementing Machine interface
		
		//another option is to throw an exception but then you must change the method signature in the interface
		//you might not actually control the interface to modify
		
		//interface segrigation principle is broken here
	}

	@Override
	public void scan(Document d) {	
		//since printer is old function, it does not supports scan but its bound to implement scan method
		
		//interface segrigation principle is broken here
	}
	
}

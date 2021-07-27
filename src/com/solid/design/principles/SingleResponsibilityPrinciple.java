//$Id$
package com.solid.design.principles;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

//SRP A class should have just a single primary responsibility
//Some people also say it separation of concern
public class SingleResponsibilityPrinciple {
	public static void main(String[] args) throws Exception {
		Journal j = new Journal();
		j.addEntry("I ate a bug");
		j.addEntry("I cried today");
		j.addEntry("I cried today");
		System.out.println(j);
		j.save("myjounral.txt");
	}

}

class Journal {
	private final List<String> entries = new ArrayList<String>();
	private static int count = 0;

	public void addEntry(String text) {
		entries.add("" + (++count) + ": " + text);
	}

	public void remove(int index) {
		entries.remove(index);
	}

	@Override
	public String toString() {
		return String.join(System.lineSeparator(), entries);
	}

	//this method bracks the SRP rule, this class should not be responsible for persistance 
	public void save(String fileName) throws FileNotFoundException {
		try (PrintStream out = new PrintStream(fileName)) {
			out.println(toString());
		}
	}
}

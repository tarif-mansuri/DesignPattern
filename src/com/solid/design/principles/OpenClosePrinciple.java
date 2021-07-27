//$Id$
package com.solid.design.principles;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

//OCP
//Open for extension but closed for modification
//this example can also be used for Specification
public class OpenClosePrinciple {
	public static void main(String[] args) {
		Product apple = new Product("Apple", Color.GREEN, Size.SMALL);
		Product tree = new Product("Tree", Color.GREEN, Size.LARGE);
		Product house = new Product("House", Color.BLUE, Size.LARGE);
		List<Product> products = new ArrayList<>();
		products.add(apple);
		products.add(tree);
		products.add(house);
		
		BetterFilter bf = new BetterFilter();
		System.out.println("Green Products:");
		bf.filter(products, new ColorSpecification(Color.GREEN)).forEach(p->System.out.println(p.name+ " is green"));
		
		System.out.println("Large Blue Products:");
		bf.filter(products, new AndSpecification<>(
				new ColorSpecification(Color.BLUE), 
				new SizeSpecification(Size.LARGE))).forEach(p->System.out.println(p.name+ " is "+p.color + " and "+p.size));
	}

}

enum Color {
	RED, GREEN, BLUE
}

enum Size {
	SMALL, MEDIUM, LARGE, HUGE
}

class Product {
	public String name;
	public Color color;
	public Size size;

	public Product(String name, Color color, Size size) {
		super();
		this.name = name;
		this.color = color;
		this.size = size;
	}

}
//this class does not follow open close principle 
class ProductFilter {
	public Stream<Product> filterByColor(List<Product> product, Color color) {
		return product.stream().filter(p -> p.color == color);
	}

	public Stream<Product> filterBySize(List<Product> product, Size size) {
		return product.stream().filter(p -> p.size == size);
	}

	public Stream<Product> filterBySizeAndColor(List<Product> product, Color color, Size size) {
		return product.stream().filter(p -> p.size == size && p.color == color);
	}

	// this filtering is good for one or two filters like (color and size). what
	// if there were some more filters
	// 2 filters => 3 methods
	// 3 filters => 7 methods
	// 4 filters => 15 methods (a lot of code to write)
	// also what if boss asks to add one more filter? we will have to modify the
	// code by adding
	// to tackle this we will use specification
}

// use of specification
interface Specification<T> {
	boolean isSatisfied(T item);
}

class ColorSpecification implements Specification<Product> {

	private Color color;
	public ColorSpecification(Color color) {
		this.color = color;
	}

	@Override
	public boolean isSatisfied(Product item) {
		return item.color == color;
	}
}
class SizeSpecification implements Specification<Product> {

	private Size size;
	public SizeSpecification(Size size) {
		this.size = size;
	}

	@Override
	public boolean isSatisfied(Product item) {
		return item.size == size;
	}
}
class AndSpecification<T> implements Specification<T>{
	private Specification<T> first, second;
	public AndSpecification(Specification<T> first, Specification<T> second) {
		super();
		this.first = first;
		this.second = second;
	}

	@Override
	public boolean isSatisfied(T item) {
		return first.isSatisfied(item) && second.isSatisfied(item);
	}
	
}
//filter
interface Filter<T> {
	Stream<T> filter(List<T> items, Specification<T> spec);
}

class BetterFilter implements Filter<Product>{

	@Override
	public Stream<Product> filter(List<Product> items, Specification<Product> spec) {
		return items.stream().filter(p-> spec.isSatisfied(p));
	}
	
}
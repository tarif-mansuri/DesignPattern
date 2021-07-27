//$Id$
package com.solid.design.principles;

//You should be able to substitute a sub class for a base class
//Id an api takes a base class, we should be able to pass a subclass
public class LiskovSubstitutionPrinciple {
	static public void useIt(Rectangle r){
		int width = r.getWidth();
		r.setHeight(10);
		System.out.println("Expected Area "+width*10+" Got "+r.getArea());
	}
	public static void main(String[] arg){
		Rectangle rc = new Rectangle(2,3);
		useIt(rc);//works fine
		Rectangle sq = new Square();
		sq.setWidth(5);
		useIt(sq);//wrong results
	}

}
class Rectangle{
	protected int width, height;

	public Rectangle(int width, int height) {
		super();
		this.width = width;
		this.height = height;
	}
	Rectangle(){}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public int getArea(){
		return this.width*this.height;
	}
	@Override
	public String toString() {
		return "Rectangle [width=" + width + ", height=" + height + "]";
	}
	
}
class Square extends Rectangle{
	public Square(){}
	public Square(int size){
		width = height = size;
	}
	public void setHeight(int height) {
		super.setHeight(height);
		super.setWidth(height);
	}public void setWidth(int width) {
		super.setHeight(width);
		super.setWidth(width);
	}
}

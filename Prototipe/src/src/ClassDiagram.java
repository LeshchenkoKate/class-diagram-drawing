package src;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import src.DiagramObject;
import src.Button;
import src.DependencyStereotype;
import src.DiagramClass;
//import src.DiagramAssociation;
//import src.DiagramDependency;
//import src.DiagramGeneralization;
//import src.DiagramUseCase;

/**
 * UML Use Case Diagram prototype.
 */
public class ClassDiagram extends DiagramObject {
	
	//public double mouseXcl;
	//public double mouseYcl;
	public ClassDiagram() {
		// An example from
		// http://creately.com/blog/diagrams/use-case-diagram-tutorial/

		// This is a prototype code only!!
		// In real life you should use your model objects here to
		// construct respective DiagramObjects.
		//this.mouseXcl = mouseXcl;
		//this.mouseYcl = mouseYcl;
		//DiagramClass a1 = new DiagramClass(40, 40, "Class");
		//addToQueue(a1);
		
		/*addToQueue(new DiagramAssociation(a1, uc1));
		addToQueue(new DiagramAssociation(a1, uc2));
		addToQueue(new DiagramAssociation(a1, uc3));

		addToQueue(new DiagramAssociation(a2, uc4));

		addToQueue(new DiagramAssociation(a3, uc1));

		addToQueue(new DiagramDependency(uc2, uc5, DependencyStereotype.EXTEND));
		addToQueue(new DiagramDependency(uc2, uc6, DependencyStereotype.INCLUDE));

		addToQueue(new DiagramDependency(uc3, uc6, DependencyStereotype.INCLUDE));

		addToQueue(new DiagramGeneralization(a2, a1));*/

	}
	
	@Override
	protected void internalDraw(Graphics canvas) {
		// canvas.drawRect(scaleX(0), scaleY(0), scale(getMaxX()),
		// scale(getMaxY()));
	}

	@Override
	protected double getMaxX() {
		return 700;
	}

	@Override
	protected double getMinX() {
		return 0;
	}

	@Override
	protected double getMaxY() {
		return 400;
	}

	@Override
	protected double getMinY() {
		return 0;
	}

}

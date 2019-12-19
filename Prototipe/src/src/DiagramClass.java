package src;


import java.awt.Graphics;
import java.util.ArrayList;

import src.DiagramLabel;

/**
 * UML Actor on a diagram.
 */
public class DiagramClass extends AbstractDiagramNode {

	static final double CLASS_WIDTH = 25.0;
	static final double CLASS_HEIGHT = 65.0;

	DiagramClass(double mX, double mY, String caption) {
		super(mX, mY, caption);
		
		addToQueue(new DiagramLabel(this));
		
		
	}

	@Override
	protected void internalDraw(Graphics canvas) {

		double mX = getmX();
		double mY = getmY();

		canvas.drawLine(scaleX(mX + 5 - CLASS_WIDTH / 2), scaleY(mY + 5 - CLASS_HEIGHT / 2),
				scaleX(mX + 5 - CLASS_WIDTH / 2), scaleY(mY + 85 - CLASS_HEIGHT / 2));
		canvas.drawLine(scaleX(mX + 65 - CLASS_WIDTH / 2), scaleY(mY + 5 - CLASS_HEIGHT / 2),
				scaleX(mX + 65 - CLASS_WIDTH / 2), scaleY(mY + 85 - CLASS_HEIGHT / 2));
		canvas.drawLine(scaleX(mX + 5 - CLASS_WIDTH / 2), scaleY(mY + 85 - CLASS_HEIGHT / 2),
				scaleX(mX + 65 - CLASS_WIDTH / 2), scaleY(mY + 85 - CLASS_HEIGHT / 2));
		canvas.drawLine(scaleX(mX + 5 - CLASS_WIDTH / 2), scaleY(mY + 5 - CLASS_HEIGHT / 2),
				scaleX(mX + 65 - CLASS_WIDTH / 2), scaleY(mY + 5 - CLASS_HEIGHT / 2));
		canvas.drawLine(scaleX(mX + 5 - CLASS_WIDTH / 2), scaleY(mY + 35 - CLASS_HEIGHT / 2),
				scaleX(mX + 65 - CLASS_WIDTH / 2), scaleY(mY + 35 - CLASS_HEIGHT / 2));
		canvas.drawLine(scaleX(mX + 5 - CLASS_WIDTH / 2), scaleY(mY + 15 - CLASS_HEIGHT / 2),
				scaleX(mX + 65 - CLASS_WIDTH / 2), scaleY(mY + 15 - CLASS_HEIGHT / 2));

	}

	@Override
	protected boolean internalTestHit(double x, double y) {
		double dX = x - getmX();
		double dY = y - getmY();
		return dY > -CLASS_HEIGHT / 2 && dY < CLASS_HEIGHT / 2 && dX > -CLASS_WIDTH / 2 && dX < CLASS_WIDTH / 2;
	}

	@Override
	protected double getMinX() {
		return getmX() - CLASS_WIDTH / 2;
	}

	@Override
	protected double getMinY() {
		return getmY() - CLASS_HEIGHT / 2;
	}

	@Override
	protected double getMaxX() {
		return getmX() + CLASS_WIDTH / 2;
	}

	@Override
	protected double getMaxY() {
		return getmY() + CLASS_HEIGHT / 2;
	}

	@Override
	protected boolean internalGetHint(StringBuilder hintStr) {
		hintStr.append("Class: " + getCaption());
		return true;
	}
}
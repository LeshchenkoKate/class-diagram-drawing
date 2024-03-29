package src;

import java.awt.BasicStroke;
import src.DiagramClass;
import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Stroke;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JToggleButton;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeEvent;

import src.DiagramObject;
import src.Window;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 * Interactive diagrams holder panel.
 */
public class Button extends JPanel {
	private static final long serialVersionUID = 1L;

	// Constants
	private static final int STRUT_WIDTH = 20;
	private static final double ZOOM_FACTOR = 1.4142; // sqrt(2)
	private static final double WHEEL_FACTOR = 2.8854; // 1/ln(sqrt(2))
	private static final double SCROLL_FACTOR = 10.0; // scrollbar pixels to
														// world pixel

	// UI components
	private final DiagramCanvas canvas;
	private final JScrollBar hsb;
	private final JScrollBar vsb;
	private final JToggleButton handButton;
	private final JToggleButton cursorButton;
	private final JToggleButton aggregationButton;
	private final JToggleButton associationButton;
	private final JToggleButton compositionButton;
	private final JToggleButton dependenceButton;
	private final JToggleButton generalizationButton;
	private final JToggleButton implementationButton;
	private final JToggleButton interactionButton;
	public JToggleButton Class;
	private final JLabel hintLabel;

	private final Point startPoint = new Point();
	private final Point currentPoint = new Point();
	private DiagramObject currentElement;

	private CommmandManager commandManager;
	private DiagramObject rootDiagramObject;
	private double scale = 1.0;
	private boolean panningMode;
	private boolean mouseDown;
	// private ClassDiagram mouseXcl;
	// private ClassDiagram mouseYcl;

	private final SelectionManager selection = new SelectionManager();
	private final DiagramObject lasso = new Lasso();

	public int mouseX;
	public int mouseY;
	public int mouseXcl;
	public int mouseYcl;
	// ArrayList<Integer> xs = new ArrayList<>();
	// ArrayList<Integer> ys = new ArrayList<>();
	boolean creatingBlock = false;

	public Button() {

		setLayout(new BorderLayout(0, 0));

		canvas = new DiagramCanvas();

		canvas.addMouseWheelListener((MouseWheelEvent e) -> {
			canvasMouseWheel(e);
		});

		canvas.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {
				canvasMouseDown(e);
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				canvasMouseUp(e);
			}

		});

		canvas.addMouseMotionListener(new MouseAdapter() {

			@Override
			public void mouseDragged(MouseEvent e) {
				canvasMouseDragged(e);
			}

			@Override
			public void mouseMoved(MouseEvent e) {
				canvasMouseMoved(e);
			}

		});

		canvas.setBackground(Color.WHITE);
		add(canvas, BorderLayout.CENTER);

		vsb = new JScrollBar();
		vsb.getModel().addChangeListener((ChangeEvent changeEvent) -> {
			scrollBarChange();
		});

		add(vsb, BorderLayout.EAST);

		JPanel southPanel = new JPanel();
		add(southPanel, BorderLayout.SOUTH);
		southPanel.setLayout(new BorderLayout(0, 0));

		hsb = new JScrollBar();
		hsb.getModel().addChangeListener((ChangeEvent changeEvent) -> {
			scrollBarChange();
		});

		hsb.setOrientation(JScrollBar.HORIZONTAL);
		southPanel.add(hsb, BorderLayout.CENTER);

		Component hstrut = Box.createHorizontalStrut(STRUT_WIDTH);
		southPanel.add(hstrut, BorderLayout.EAST);

		JPanel northPanel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) northPanel.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		add(northPanel, BorderLayout.NORTH);

		// northPanel.setLayout(new GridLayout(8,1));
		/*
		 * JPanel westPanel = new JPanel(); westPanel.setLayout(new BoxLayout(westPanel,
		 * BoxLayout.Y_AXIS)); add(westPanel, BorderLayout.WEST);
		 * 
		 * westPanel.add(Box.createVerticalStrut(15));
		 */
		JPanel westPanel = new JPanel();
		FlowLayout flowLayout2 = (FlowLayout) westPanel.getLayout();
		flowLayout2.setAlignment(FlowLayout.LEFT);
		add(westPanel, BorderLayout.WEST);

		handButton = new JToggleButton();
		handButton.addActionListener((ActionEvent e) -> {
			setPanningMode(true);
		});
		handButton.setIcon(new ImageIcon(Window.class.getResource("/graph/hand.png")));
		northPanel.add(handButton);

		cursorButton = new JToggleButton();
		cursorButton.addActionListener((ActionEvent e) -> {
			setPanningMode(false);
		});
		cursorButton.setIcon(new ImageIcon(Window.class.getResource("/graph/cursor.png")));
		northPanel.add(cursorButton);

		setPanningMode(false);

		JButton plusButton = new JButton();
		plusButton.addActionListener((ActionEvent e) -> {
			zoomIn();
		});
		plusButton.setIcon(new ImageIcon(Window.class.getResource("/graph/plus.png")));
		northPanel.add(plusButton);

		JButton minusButton = new JButton();
		minusButton.addActionListener((ActionEvent e) -> {
			zoomOut();
		});
		minusButton.setIcon(new ImageIcon(Window.class.getResource("/graph/minus.png")));
		northPanel.add(minusButton);

		JButton one2oneButton = new JButton();
		one2oneButton.addActionListener((ActionEvent e) -> {
			setScale(1.0);
		});
		one2oneButton.setIcon(new ImageIcon(Window.class.getResource("/graph/1-1.png")));
		northPanel.add(one2oneButton);

		Class = new JToggleButton("Class");
		// Class.setBounds(0, 0, 20, 10);
		Class.addActionListener((ActionEvent e) -> {
			if (creatingBlock==true)
				creatingBlock = false;
			else
			creatingBlock = true;

			//CustomCursor();
			
		});
		//creatingBlock = false;
		westPanel.setLayout(new GridLayout(8, 1));
		westPanel.add(Class);

		aggregationButton = new JToggleButton();

		aggregationButton.addActionListener((ActionEvent e) -> {
			// setPanningMode(true);
			 JOptionPane.showMessageDialog(null, "add aggregation");
		});
		aggregationButton.setIcon(new ImageIcon(Window.class.getResource("/graph/Aggregation2.png")));

		// westPanel.add(Box.createRigidArea(new Dimension(20, 0)));
		westPanel.add(aggregationButton);

		associationButton = new JToggleButton();
		associationButton.addActionListener((ActionEvent e) -> {
			// setPanningMode(true);
			JOptionPane.showMessageDialog(null, "add association");
		});
		associationButton.setIcon(new ImageIcon(Window.class.getResource("/graph/Association2.png")));
		westPanel.add(associationButton);

		compositionButton = new JToggleButton();
		compositionButton.addActionListener((ActionEvent e) -> {
			// setPanningMode(true);
			JOptionPane.showMessageDialog(null, "add composition");
		});
		compositionButton.setIcon(new ImageIcon(Window.class.getResource("/graph/Composition2.png")));
		westPanel.add(compositionButton);

		dependenceButton = new JToggleButton();
		dependenceButton.addActionListener((ActionEvent e) -> {
			// setPanningMode(true);
			JOptionPane.showMessageDialog(null, "add dependence");
		});
		dependenceButton.setIcon(new ImageIcon(Window.class.getResource("/graph/Dependence2.png")));
		westPanel.add(dependenceButton);

		generalizationButton = new JToggleButton();
		generalizationButton.addActionListener((ActionEvent e) -> {
			// setPanningMode(true);
			JOptionPane.showMessageDialog(null, "add generalization");
		});
		generalizationButton.setIcon(new ImageIcon(Window.class.getResource("/graph/Generalization2.png")));
		westPanel.add(generalizationButton);

		implementationButton = new JToggleButton();
		implementationButton.addActionListener((ActionEvent e) -> {
			// setPanningMode(true);
			JOptionPane.showMessageDialog(null, "add implementation");
		});
		implementationButton.setIcon(new ImageIcon(Window.class.getResource("/graph/Implementation2.png")));
		westPanel.add(implementationButton);

		interactionButton = new JToggleButton();
		interactionButton.addActionListener((ActionEvent e) -> {
			// setPanningMode(true);
			JOptionPane.showMessageDialog(null, "add interaction");
		});
		interactionButton.setIcon(new ImageIcon(Window.class.getResource("/graph/Interaction2.png")));
		westPanel.add(interactionButton);

		Component horizontalStrut = Box.createHorizontalStrut(STRUT_WIDTH);
		northPanel.add(horizontalStrut);

		hintLabel = new JLabel();
		hintLabel.setBackground(Color.YELLOW);
		northPanel.add(hintLabel);

	}

	public void CustomCursor() {
		if (creatingBlock) {
			Toolkit toolkit = Toolkit.getDefaultToolkit();
			Image img = toolkit.getImage("/graph/cursor.png");
			Point point = new Point(0, 0);
			Cursor cursor = toolkit.createCustomCursor(img, point, "CursorClass");
			setCursor(cursor);
		}
	}

	private static int round(double val) {
		return (int) Math.round(val);
	}

	private void canvasMouseDown(MouseEvent e) {
		if (rootDiagramObject == null)
			return;

		// SetFocus; // don't remember for what purpose??
		Point cursorPos = MouseInfo.getPointerInfo().getLocation();
		if (panningMode) {
			// set mouse cursor to 'closed hand'
			// Screen.Cursor = crClHand;

			startPoint.x = round(hsb.getValue() / SCROLL_FACTOR + cursorPos.x / scale);
			startPoint.y = round(vsb.getValue() / SCROLL_FACTOR + cursorPos.y / scale);

		} else if (creatingBlock) {
			startPoint.x = round(hsb.getValue() / SCROLL_FACTOR + cursorPos.x / scale);
			startPoint.y = round(vsb.getValue() / SCROLL_FACTOR + cursorPos.y / scale);
			Point p = MouseInfo.getPointerInfo().getLocation();
			SwingUtilities.convertPointFromScreen(p, canvas);

			DiagramClass a1 = new DiagramClass(p.x, p.y, "Class");
			rootDiagramObject.addToQueue(a1);
			canvas.paint(canvas.getGraphics());

			mouseDown = true;
			
		} else {
			SwingUtilities.convertPointFromScreen(cursorPos, canvas);
			startPoint.setLocation(cursorPos);
			currentPoint.setLocation(startPoint);
			selection.mouseDown(rootDiagramObject.testHit(currentPoint.x, currentPoint.y),
					(e.getModifiersEx() & (InputEvent.SHIFT_DOWN_MASK | InputEvent.CTRL_DOWN_MASK)) > 0);
			mouseDown = true;
		}
	}

	private void canvasMouseMoved(MouseEvent e) {
		if (rootDiagramObject == null)
			return;
		currentElement = rootDiagramObject.testHit(e.getX(), e.getY());
		if (currentElement == null) {
			String h = rootDiagramObject.getHint();
			hintLabel.setText(h);
		} else {
			String h = currentElement.getHint();
			hintLabel.setText(h);
		}
	}

	private void canvasMouseDragged(MouseEvent e) {
		if (rootDiagramObject == null)
			return;
		Point cursorPos = MouseInfo.getPointerInfo().getLocation();
		if (panningMode) {
			// ������������ �������� ��� ������� ������
			hsb.setValue(round((startPoint.x - cursorPos.x / scale) * SCROLL_FACTOR));
			vsb.setValue(round((startPoint.y - cursorPos.y / scale) * SCROLL_FACTOR));
		} else {
			if (!mouseDown)
				return;
			// ������������ ������ ������� �� ��������
			selection.mouseMove(currentPoint.x - startPoint.x, currentPoint.y - startPoint.y);
			SwingUtilities.convertPointFromScreen(cursorPos, canvas);
			currentPoint.setLocation(cursorPos);
			selection.mouseMove(currentPoint.x - startPoint.x, currentPoint.y - startPoint.y);
		}
	}

	private void canvasMouseUp(MouseEvent e) {
		if (rootDiagramObject == null)
			return;

		if (panningMode) {
			// TODO
			// set mouse cursor to 'open palm'
		} else {
			if (!mouseDown)
				return;

			Point p = MouseInfo.getPointerInfo().getLocation();
			SwingUtilities.convertPointFromScreen(p, canvas);

			int dX = p.x - startPoint.x;
			int dY = p.y - startPoint.y;
			selection.mouseMove(currentPoint.x - startPoint.x, currentPoint.y - startPoint.y);
			if ((e.getX() > 0) && (e.getY() > 0) && (e.getX() < canvas.getWidth()) && (e.getY() < canvas.getHeight())) {
				selection.mouseUp(dX, dY);
				canvas.paint(canvas.getGraphics());
				selection.mouseMove(0, 0);
				startPoint.setLocation(e.getX(), e.getY());
				currentPoint.setLocation(e.getX(), e.getY());
			}
			mouseDown = false;
		}

	}

	private void canvasMouseWheel(MouseWheelEvent e) {
		if ((e.getModifiersEx() & MouseWheelEvent.CTRL_DOWN_MASK) > 0) {
			setScale(scale * Math.exp(-e.getPreciseWheelRotation() / WHEEL_FACTOR));
		} else {
			Point clientPos = e.getPoint();
			if (clientPos.x > clientPos.y) {
				int newPos = vsb.getValue() + round(e.getPreciseWheelRotation() * SCROLL_FACTOR * 2);
				if (newPos < 0) {
					vsb.setValue(0);
				} else if ((newPos > 0) && (newPos < vsb.getMaximum() - vsb.getVisibleAmount())) {
					vsb.setValue(newPos);
				} else {
					vsb.setValue(vsb.getMaximum() - vsb.getVisibleAmount());
				}
			} else {
				int newPos = hsb.getValue() + round(e.getPreciseWheelRotation() * SCROLL_FACTOR * 2);
				if (newPos < 0) {
					hsb.setValue(0);
				} else if ((newPos > 0) && (newPos < hsb.getMaximum() - hsb.getVisibleAmount())) {
					hsb.setValue(newPos);
				} else {
					hsb.setValue(hsb.getMaximum() - hsb.getVisibleAmount());
				}
			}
		}
	}

	private void scrollBarChange() {
		canvas.paint(canvas.getGraphics());
		// ����� ��������� �� ������� ���������
		if (selection.items.size() > 0)
			selection.mouseMove(0, 0);
	}

	/**
	 * Returns the root painter object.
	 */
	public DiagramObject getDiagramObject() {
		return rootDiagramObject;
	}

	/**
	 * Sets the zoom factor.
	 * 
	 * @param s new zoom factor
	 */
	public void setScale(double s) {
		if (s < 0.05 || s > 100 || s == scale)
			return;
		Point p = MouseInfo.getPointerInfo().getLocation();
		SwingUtilities.convertPointFromScreen(p, canvas);

		double xQuot;
		double yQuot;
		if (p.x > 0 && p.y > 0 && p.x < canvas.getWidth() && p.y < canvas.getHeight()) {
			xQuot = p.getX() / (double) canvas.getWidth();
			yQuot = p.getY() / (double) canvas.getHeight();
		} else {
			xQuot = 0.5;
			yQuot = 0.5;
		}
		int newHVal = hsb.getValue() + round(hsb.getVisibleAmount() * xQuot * (1 - scale / s));
		int newVVal = vsb.getValue() + round(vsb.getVisibleAmount() * yQuot * (1 - scale / s));
		// ��������� ����������� �����, �� ������� ��������� ��������� ����
		// ��� ���������� ����� ���������, ���� ������ �������� ������
		hsb.setValue(newHVal);
		vsb.setValue(newVVal);

		scale = s;
		canvas.paint(canvas.getGraphics());
		// � ��� --- ���� ��������� �� ������� ���������.
		if (!selection.getItems().isEmpty())
			selection.mouseMove(0, 0);
	}

	/**
	 * Zooms in ('+' clicked).
	 */
	public void zoomIn() {
		setScale(scale * ZOOM_FACTOR); // sqrt(2)
	}

	/**
	 * Zooms out ('-' clicked).
	 */
	public void zoomOut() {
		setScale(scale / ZOOM_FACTOR); // sqrt(2)
	}

	/**
	 * Switches between "panning" and "selection" modes.
	 * 
	 * @param panningMode true for "panning" mode.
	 */
	public void setPanningMode(boolean dragMode) {
		this.panningMode = dragMode;
		handButton.setSelected(dragMode);
		cursorButton.setSelected(!dragMode);
		if (dragMode)
			canvas.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		else {
			canvas.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		}
	}

	/**
	 * Sets the root painting object and repaints the diagram.
	 * 
	 * @param diagramObject new root painting object
	 */
	public void setDiagramObject(DiagramObject diagramObject) {
		this.rootDiagramObject = diagramObject;
		double worldHeight = diagramObject.getMaxY() - diagramObject.getMinY();
		double worldWidth = diagramObject.getMaxX() - diagramObject.getMinX();

		vsb.setMaximum(round(worldHeight * SCROLL_FACTOR));
		hsb.setMaximum(round(worldWidth * SCROLL_FACTOR));

		selection.clear();
		canvas.repaint();
	}

	/**
	 * "Lasso" painter.
	 */
	private class Lasso extends DiagramObject {
		@Override
		protected void internalDrawSelection(Graphics canvas, int dX, int dY) {
			canvas.setColor(Color.GREEN);
			canvas.setXORMode(Color.WHITE);

			Graphics2D g2 = (Graphics2D) canvas;

			float dash1[] = { 5.0f };
			BasicStroke dashed = new BasicStroke(1.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 5.0f, dash1, 0.0f);
			Stroke s = g2.getStroke();
			g2.setStroke(dashed);

			int x0 = dX > 0 ? startPoint.x : startPoint.x + dX;
			int y0 = dY > 0 ? startPoint.y : startPoint.y + dY;

			g2.drawRect(x0, y0, Math.abs(dX), Math.abs(dY));
			g2.setStroke(s);

			canvas.setPaintMode();
		}

		@Override
		protected void internalDrop(double dX, double dY) {
			int minX;
			int minY;

			dX = dX * scale;
			dY = dY * scale;

			if (dX < 0) {
				minX = startPoint.x + round(dX);
				dX = -dX;
			} else {
				minX = startPoint.x;
			}

			if (dY < 0) {
				minY = startPoint.y + round(dY);
				dY = -dY;
			} else {
				minY = startPoint.y;
			}
			rootDiagramObject.collect(minX, minY, minX + round(dX), minY + round(dY), selection.collector);
		}
	}

	/**
	 * Possible states during mouse moving.
	 */
	private enum State {
		SELECTING, LASSO, DRAGGING,
	};

	/**
	 * Incapsulates objects selection functionality.
	 */
	private class SelectionManager {

		private final ArrayList<DiagramObject> items = new ArrayList<DiagramObject>();

		private final Consumer<DiagramObject> collector = (item) -> {
			if (!items.contains(item))
				items.add(item);
		};

		private DiagramObject nonMoveable;
		private State state = State.SELECTING;

		private List<DiagramObject> getItems() {
			return Collections.unmodifiableList(items);
		}

		private void clear() {
			items.clear();
		}

		void mouseDown(DiagramObject item, boolean shiftKey) {
			/*
			 * ��� ������� ������ �� ������������, �� ����� ��� ����, ����� �������� �����
			 * ���������� Canvas'��
			 */
			lasso.draw(canvas.getGraphics(), 0, 0, scale);
			/*
			 * ���� �� �����-���� ������� ������� MouseUp �� ���������, ������������
			 * �������� ���, ����� �������� ������
			 */
			if (state != State.SELECTING)
				mouseUp(0, 0);

			assert state == State.SELECTING;

			int i = items.indexOf(item);

			/*
			 * ����� ��� Ctrl, Shift: - ���� ������ � ���������� ������, �� ������ ���
			 * ������ � ������ ���������� - ���� �� ������ � ������ �����, �� ������
			 * ���������� ���������, - ���� ������ � ������������ ������, �� ����������
			 * ��������� � �������� ��������� ������.
			 */
			if (!shiftKey) {
				if (i >= 0) {
					Collections.swap(items, i, 0);
				} else {
					items.clear();
					if (item != null)
						items.add(item);
				}
			} else {
				/*
				 * ����� � Ctrl, Shift: - ���� ������ � ���������� ������, �� ������� ��� ��
				 * ��������� - ���� ������ � ������������ ������, �� �������� ��� � ��������� �
				 * ������ ������
				 */
				if (i >= 0)
					items.remove(i);
				else if (item != null) {
					items.add(item);
					Collections.swap(items, 0, items.size() - 1);
				}
			}

			/*
			 * ���� ������ � ������ ����� --- ��������� � ���������� �����
			 */
			if (item == null) {
				state = State.LASSO;
				nonMoveable = null;

			} else if (!item.isMoveable()) {
				/*
				 * �����, ���� ������ � ����������� ������ --- ��������� � ���������� �����
				 */
				state = State.LASSO;
				nonMoveable = item;
			} else {
				/*
				 * ����� --- ��������� � ����������� ���������� ��������
				 */
				state = State.DRAGGING;
			}
		}

		void mouseMove(int dX, int dY) {
			switch (state) {
			// ����� ��������� �����
			case LASSO:
				/*
				 * ��� ������� ������ �� ������������, �� ����� ��� ����, ����� �������� �����
				 * ���������� Canvas'��
				 */
				lasso.draw(canvas.getGraphics(), 0, 0, scale);
				lasso.drawSelection(/* canvas.getGraphics(), */ dX, dY);
				break;
			// ����� ������������ ��������
			default:
				for (DiagramObject i : items) {
					assert i != null;
					i.drawSelection(/* canvas.getGraphics(), */ dX, dY);
				}
			}

		}

		private void internalDrop(int dX, int dY) {
			if ((!items.isEmpty()) && ((dX != 0) || (dY != 0))) {
				if (commandManager != null)
					commandManager.beginMacro("drag & drop");
				for (DiagramObject i : items) {
					DiagramObject curItem = i.getParent();
					while (curItem != null && !items.contains(curItem)) {
						curItem = curItem.getParent();
					}
					if (curItem == null)
						i.drop(dX, dY);
				}
				if (commandManager != null)
					commandManager.endMacro();
			}
		}

		void mouseUp(int dX, int dY) {
			switch (state) {
			case LASSO:
				if ((dX != 0) || (dY != 0)) {
					items.remove(nonMoveable);
					nonMoveable = null;
				}
				lasso.drop(dX, dY);
				break;
			case DRAGGING:
				internalDrop(dX, dY);
				break;
			default:
				break;
			}
			state = State.SELECTING;
		}

	}

	/**
	 * Diagram painting canvas.
	 */
	private class DiagramCanvas extends Canvas {

		private static final long serialVersionUID = 1L;

		DiagramCanvas() {
			super();
		}

		@Override
		public void paint(Graphics g) {
			// not ready for painting yet
			if (rootDiagramObject == null || g == null)
				return;
			double worldHeight = rootDiagramObject.getMaxY() - rootDiagramObject.getMinY();
			double worldWidth = rootDiagramObject.getMaxX() - rootDiagramObject.getMinX();
			int hPageSize = round(canvas.getWidth() / scale);

			if (hPageSize > worldWidth) {
				hsb.setValue(0);
				hsb.setVisibleAmount(round(worldWidth * SCROLL_FACTOR));
			} else {
				hsb.setVisibleAmount(round(hPageSize * SCROLL_FACTOR));
			}
			int vPageSize = round(canvas.getHeight() / scale);
			if (vPageSize > worldHeight) {
				vsb.setValue(0);
				vsb.setVisibleAmount(round(worldHeight * SCROLL_FACTOR));
			} else {
				vsb.setVisibleAmount(round(vPageSize * SCROLL_FACTOR));
			}

			hsb.setMaximum(round(worldWidth * SCROLL_FACTOR));
			vsb.setMaximum(round(worldHeight * SCROLL_FACTOR));

			// setupLargeChange(hsb);
			// setupLargeChange(vsb); TODO
			g.clearRect(0, 0, getWidth(), getHeight());

			double dX;
			if (hPageSize > worldWidth) {
				dX = (worldWidth - hPageSize) / 2;
			} else {
				dX = hsb.getValue() / SCROLL_FACTOR;
			}

			double dY;
			if (vPageSize > worldHeight) {
				dY = (worldHeight - vPageSize) / 2;
			} else {
				dY = vsb.getValue() / SCROLL_FACTOR;
			}

			rootDiagramObject.draw(g, dX, dY, scale);
		}

	}

	/**
	 * The element currently under mouse cursor.
	 */
	public DiagramObject getCurrentElement() {
		return currentElement;
	}

	/**
	 * The list of selected elements.
	 */
	public List<DiagramObject> getSelection() {
		return selection.getItems();
	}
}
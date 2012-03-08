package gui;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.LayoutManager;

public class LineLayout implements LayoutManager {

	@Override
	public void addLayoutComponent(String name, Component comp) {
		System.err.println("addLayoutComponent name="+name+" comp="+comp);
	}

	@Override
	public void removeLayoutComponent(Component comp) {
		System.err.println("removeLayoutComponent  comp="+comp);
	}

	@Override
	public Dimension preferredLayoutSize(Container parent) {
		int nComps = parent.getComponentCount();
		int heigth = 0;
		int width = 0;
		for (int i = 0 ; i < nComps ; i++) {
            Component c = parent.getComponent(i);
            if (c.isVisible()) {
                Dimension d = c.getPreferredSize();
                heigth += d.height;
                width = Math.max(width, d.width);
            }
        }
		return new Dimension(width, heigth);
	}

	@Override
	public Dimension minimumLayoutSize(Container parent) {
		int nComps = parent.getComponentCount();
		int heigth = 0;
		int width = 0;
		for (int i = 0 ; i < nComps ; i++) {
            Component c = parent.getComponent(i);
            if (c.isVisible()) {
                Dimension d = c.getPreferredSize();
                heigth += d.height;
                width = Math.max(width, d.width);
            }
        }
		return new Dimension(width, heigth);
	}

	@Override
	public void layoutContainer(Container parent) {
		int nComps = parent.getComponentCount();
		int x = 0;
		int y = 0;
		for (int i = 0 ; i < nComps ; i++) {
            Component c = parent.getComponent(i);
            if (c.isVisible()) {
                Dimension d = c.getPreferredSize();
                c.setBounds(x, y, d.width, d.height);
                y += d.height;
            }
        }
	}

}

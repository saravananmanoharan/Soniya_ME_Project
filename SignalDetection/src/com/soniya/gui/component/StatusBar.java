package com.soniya.gui.component;

import java.awt.Dimension;

import javax.swing.JLabel;

public class StatusBar extends JLabel {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = -4282696370124487249L;

	/** Creates a new instance of StatusBar */
    public StatusBar(int width) {
        super();
        super.setPreferredSize(new Dimension(width,16));
        setMessage("Ready");
    }
     
    public void setMessage(String message) {
        setText(" "+message);        
    }        
}
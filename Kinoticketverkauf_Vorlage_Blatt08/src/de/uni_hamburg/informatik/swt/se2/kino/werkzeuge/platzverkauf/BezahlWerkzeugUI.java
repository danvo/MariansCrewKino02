package de.uni_hamburg.informatik.swt.se2.kino.werkzeuge.platzverkauf;

import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Point;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

public class BezahlWerkzeugUI
{
    JDialog _dialog;
    JPanel _hauptPanel;
    JPanel _detailPayPanel;
    JPanel _simplePayPanel;
    JLabel _gesamtPreisLabel;
    JLabel _restGeldLabel;
    JLabel _bezahlFieldLabel;
    JLabel _vorstellungLabel;
    JButton _okButton;
    JButton _abButton;
    JPanel _mainPanel;
    JFormattedTextField _bezahlField;
    GridLayout _gridLayout;
    
    public BezahlWerkzeugUI(JPanel hauptPanel)
    {
        _hauptPanel = hauptPanel;
        _dialog = getDialogPanel();
    }

    private JDialog getDialogPanel()
    {
        Frame frame = (Frame) SwingUtilities.getRoot(_hauptPanel);
        Point point = _hauptPanel.getLocation();
        JDialog jp = new JDialog(frame,"Bezahldialog", true);
        _mainPanel = new JPanel();
        _mainPanel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        jp.setSize(450, 250);
        jp.setResizable(false);
        jp.setLocationRelativeTo(null);
        jp.setLocation((int)point.getX() +_hauptPanel.getWidth()/2 - jp.getX()/2, 
                (int)point.getY() + _hauptPanel.getHeight()/2 - jp.getY()/2); // Zentriert das Dialog relavtiv zum Hauptpanel
       
        initializeVorstellungsLabel(c);
        
        initializeDetailPayPanel(c);
        
        initializeSimplePayPanel(c);
        
        initializeButtons(c);
       
        jp.add(_mainPanel);
        
        return jp;
    }

    private void initializeVorstellungsLabel(GridBagConstraints c)
    {
        _vorstellungLabel = new JLabel("DEFAULT: 12:00 - TestFilm - TestSaal 1");
        _vorstellungLabel.setFont(_vorstellungLabel.getFont().deriveFont(Font.BOLD));
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 1;
        c.weighty = 0.4;
        c.insets = new Insets(10, 0, 5, 0);
        _mainPanel.add(_vorstellungLabel, c);
    }

    private void initializeButtons(GridBagConstraints c)
    {
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.TRAILING, 10, 10));
        _abButton = new JButton("Abbrechen");
        buttonPanel.add(_abButton);
        _okButton = new JButton("OK");
        buttonPanel.add(_okButton, c);
        c.gridx = 0;
        c.gridy = 3;
        c.weightx = 1;
        c.insets = new Insets(0, 0, 0, 40);
        c.anchor = GridBagConstraints.SOUTH;
        _mainPanel.add(buttonPanel,c);
    }

    private void initializeSimplePayPanel(GridBagConstraints c)
    {
        _simplePayPanel = new JPanel(new GridBagLayout());
        initializeGesamtpreisZeile(c);
        initializeBezahltZeile(c);
        initializeRestgeldZeile(c);
        _mainPanel.add(_simplePayPanel,c);
    }

    private void initializeRestgeldZeile(GridBagConstraints c)
    {
        JLabel restGeldStringLabel = new JLabel("Restgeld");
        restGeldStringLabel.setFont(restGeldStringLabel.getFont().deriveFont(Font.BOLD));
        c.gridx = 0;
        c.gridy = 2;
        c.insets = new Insets(0, 0, 0, 0); 
        _simplePayPanel.add(restGeldStringLabel, c);
        
        _restGeldLabel = new JLabel();
        c.gridx = 1;
        c.gridy = 2;
        _simplePayPanel.add(_restGeldLabel, c);
        c.gridx = 0;
        c.gridy = 2;
        c.weightx = 1;
        c.weighty = 0.4;
        c.insets = new Insets(0, 0, 0, 50);
        c.fill = GridBagConstraints.HORIZONTAL;
    }

    private void initializeBezahltZeile(GridBagConstraints c)
    {
        _bezahlFieldLabel = new JLabel("Bargeld");
        c.gridx = 0;
        c.gridy = 1;
        c.weightx = 0.5;
        c.weighty = 0;
        c.insets = new Insets(0, 0, 10, 0);
        _simplePayPanel.add(_bezahlFieldLabel,c);
        
        FlowLayout flowLayout = new FlowLayout(FlowLayout.LEADING, 0, 0);
        JPanel bezahlFieldPanel = new JPanel(flowLayout);
        JLabel euroLabel = new JLabel("€");
        
        _bezahlField = new JFormattedTextField();
        _bezahlField.setHorizontalAlignment(SwingConstants.RIGHT);
        _bezahlField.setColumns(6);
        bezahlFieldPanel.add(_bezahlField);
        bezahlFieldPanel.add(euroLabel);
        c.gridx = 1;
        c.gridy = 1;
        c.weightx = 0.3;
        c.weighty = 0;
        _simplePayPanel.add(bezahlFieldPanel, c);
    }

    private void initializeGesamtpreisZeile(GridBagConstraints c)
    {
        JLabel gesamtPreisStringLabel = new JLabel("Gesamtpreis");
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 1;
        c.weightx = 0;
        c.weighty = 0;
        c.insets = new Insets(0, 0, 0, 0);
        c.anchor = GridBagConstraints.EAST;
        _simplePayPanel.add(gesamtPreisStringLabel, c);
        _gesamtPreisLabel = new JLabel();
        c.gridx = 1;
        c.gridy = 0;
        _simplePayPanel.add(_gesamtPreisLabel, c);
    }

    private void initializeDetailPayPanel(GridBagConstraints c)
    {
        _gridLayout = new GridLayout(1, 4, 10, 5);
        _detailPayPanel = new JPanel(_gridLayout);
        String[] headings =  {"Kunde", "Reihe", "Sitz", "Preis"};
        for (String string : headings)
        {
            JLabel label = new JLabel(string);
            label.setFont(label.getFont().deriveFont(Font.BOLD));
            label.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
            _detailPayPanel.add(label);
        }
        
        _detailPayPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 2;
        c.weightx = 1;
        c.weighty = 0.3;
        c.insets = new Insets(10, 0, 0, 50);
        c.anchor = GridBagConstraints.EAST;
        _mainPanel.add(_detailPayPanel, c);
    }
    
    public JButton getOkButton()
    {
        return _okButton;
    }
    
    public JButton getAbButton()
    {
        return _abButton;
    }
    
    public void showDialog() 
    {
        _dialog.setVisible(true);
    }
    
    public JDialog getDialog() 
    {
        return _dialog;
    }
    
    public JLabel getGesamtPreisLabel()
    {
    	return _gesamtPreisLabel;
    }

    public JTextField getBezahlField()
    {
    	return _bezahlField;
    }

	public JLabel getRestGeldLabel() {
		return _restGeldLabel;
	}
	
	public JPanel getDetailPayPanel() 
	{
	    return _detailPayPanel;
	}

    public GridLayout getGridLayout()
    {
        return _gridLayout;
    }

    public JLabel getVorstellungLabel()
    {
        return _vorstellungLabel;
    }
}

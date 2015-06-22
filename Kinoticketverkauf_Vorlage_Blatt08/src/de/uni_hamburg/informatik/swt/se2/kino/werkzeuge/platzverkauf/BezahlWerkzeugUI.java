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
    JLabel _gesamtPreisLabel;
    JLabel _restGeldLabel;
    JLabel _bezahlFieldLabel;
    JLabel _vorstellungLabel;
    JButton _okButton;
    JButton _abButton;
    JFormattedTextField _bezahlField;
    GridLayout _gridLayout;
    
    public BezahlWerkzeugUI(JPanel hauptPanel)
    {
        _hauptPanel = hauptPanel;
        _dialog = getDialogPanel();
    }
    
    /**
     * Intialisiert das DialogPanel und gibt es zurück.
     * @return JDialog
     */
    private JDialog getDialogPanel()
    {
        Frame frame = (Frame) SwingUtilities.getRoot(_hauptPanel);
        JDialog jp = new JDialog(frame,"Bezahldialog", true);
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        jp.setLocationRelativeTo(_hauptPanel);
        jp.setSize(450, 250);
       
        _vorstellungLabel = new JLabel("DEFAULT: 18:00 - Rio - Saal 5");
        _vorstellungLabel.setFont(_vorstellungLabel.getFont().deriveFont(Font.BOLD));
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 1;
        c.weighty = 0.4;
        c.insets = new Insets(10, 0, 5, 0);
        panel.add(_vorstellungLabel, c);
        
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
        panel.add(_detailPayPanel, c);
        
        JPanel simplePayPanel = new JPanel(new GridBagLayout());

        JLabel gesamtPreisStringLabel = new JLabel("Gesamtpreis");
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 1;
        c.weightx = 0;
        c.weighty = 0;
        c.insets = new Insets(0, 0, 0, 0);
        c.anchor = GridBagConstraints.EAST;
        simplePayPanel.add(gesamtPreisStringLabel, c);
        _gesamtPreisLabel = new JLabel();
        c.gridx = 1;
        c.gridy = 0;
        simplePayPanel.add(_gesamtPreisLabel, c);
        
        _bezahlFieldLabel = new JLabel("Bargeld");
        c.gridx = 0;
        c.gridy = 1;
        c.weightx = 0.5;
        c.weighty = 0;
        c.insets = new Insets(0, 0, 10, 0);
        simplePayPanel.add(_bezahlFieldLabel,c);
        
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
        simplePayPanel.add(bezahlFieldPanel, c);
        
        JLabel restGeldStringLabel = new JLabel("Restgeld");
        restGeldStringLabel.setFont(restGeldStringLabel.getFont().deriveFont(Font.BOLD));
        c.gridx = 0;
        c.gridy = 2;
        c.insets = new Insets(0, 0, 0, 0); 
        simplePayPanel.add(restGeldStringLabel, c);
        
        _restGeldLabel = new JLabel();
        c.gridx = 1;
        c.gridy = 2;
        simplePayPanel.add(_restGeldLabel, c);
        c.gridx = 0;
        c.gridy = 2;
        c.weightx = 1;
        c.weighty = 0.4;
        c.insets = new Insets(0, 0, 0, 50);
        c.fill = GridBagConstraints.HORIZONTAL;
        panel.add(simplePayPanel,c);
        
        
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
        panel.add(buttonPanel,c);
        
       
        jp.add(panel);

        return jp;
    }
    
    /**
     * Gibt den OK-Button zurück.
     * @return JButton
     */
    public JButton getOkButton()
    {
        return _okButton;
    }
    
    /**
     * Gibt den Abbrechen-Button zurück.
     * @return JButton
     */
    public JButton getAbButton()
    {
        return _abButton;
    }
    
    /**
     * Macht den Dialog sichtbar.
     */
    public void showDialog() 
    {
        _dialog.setVisible(true);
    }
    
    /**
     * Gibt den Dialog zurück.
     * @return JButton
     */
    public JDialog getDialog() 
    {
        return _dialog;
    }
    
    /**
     * Gibt den Gesamtpreis zurück.
     * @return JLabel
     */
    public JLabel getGesamtPreisLabel()
    {
    	return _gesamtPreisLabel;
    }

    /**
     * Gibt das Bezahlfeld zurück.
     * @return JTextfield
     */
    public JTextField getBezahlField()
    {
    	return _bezahlField;
    }

    /**
     * Gibt das Restgeldlabel zurück.
     * @return JLabel
     */
	public JLabel getRestGeldLabel() {
		return _restGeldLabel;
	}
	
	/**
     * Gibt das DetailPayPanel zurück.
     * @return JPanel
     */
	public JPanel getDetailPayPanel() 
	{
	    return _detailPayPanel;
	}

	/**
     * Gibt den Layoutmanager zurück.
     * @return Gridlayout
     */
    public GridLayout getGridLayout()
    {
        return _gridLayout;
    }

    /**
     * Gibt das Vorstellungslabel zurück.
     * @return JLabel
     */
    public JLabel getVorstellungLabel()
    {
        return _vorstellungLabel;
    }
}

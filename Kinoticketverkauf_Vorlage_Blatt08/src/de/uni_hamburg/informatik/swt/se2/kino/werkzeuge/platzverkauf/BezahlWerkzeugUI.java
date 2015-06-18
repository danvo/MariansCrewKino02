package de.uni_hamburg.informatik.swt.se2.kino.werkzeuge.platzverkauf;

import java.awt.Button;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.TextField;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class BezahlWerkzeugUI
{
    JDialog _dialog;
    JPanel _hauptPanel;
    JLabel _gesamtPreisLabel;
    JLabel _restGeldLabel;
    JButton _okButton;
    JButton _abButton;
    JFormattedTextField _bezahlField;
    
    public BezahlWerkzeugUI(JPanel hauptPanel)
    {
        _hauptPanel = hauptPanel;
        _dialog = getDialogPanel();
    }

    private JDialog getDialogPanel()
    {
        Frame frame = (Frame) SwingUtilities.getRoot(_hauptPanel);
        JDialog jp = new JDialog(frame,"Bezahldialog", true);
        jp.setLayout(new GridBagLayout());
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        jp.setLocationRelativeTo(_hauptPanel);
        jp.setSize(400, 250);
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 1;
        c.weighty = 1;
        c.anchor = GridBagConstraints.NORTH;
        _gesamtPreisLabel = new JLabel();
        panel.add(_gesamtPreisLabel, c);
        
        c.gridx = 0;
        c.gridy = 1;
        c.weightx = 0;
        c.weighty = 0;
        c.fill = GridBagConstraints.HORIZONTAL;
        _bezahlField = new JFormattedTextField();
        panel.add(_bezahlField, c);
        
        _restGeldLabel = new JLabel("Restgeld: ");
        c.gridx = 0;
        c.gridy = 2;
        c.weightx = 0;
        c.weighty = 0;
        panel.add(_restGeldLabel, c);
        
        c.gridx = 0;
        c.gridy = 3;
        c.weightx = 1;
        c.weighty = 0;
        _okButton = new JButton("OK");
        panel.add(_okButton, c);
        
        _abButton = new JButton("Abbrechen");
        c.gridx = 1;
        c.gridy = 3;
        c.weightx = 0;
        c.weighty = 0;
        panel.add(_abButton, c);
        
        c.insets = new Insets(20, 20, 20, 20);
        jp.add(panel,c);

        return jp;
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
}

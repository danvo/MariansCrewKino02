package de.uni_hamburg.informatik.swt.se2.kino.werkzeuge.platzverkauf;

import java.awt.Button;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.TextField;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class DialogPlatzVerkaufsWerkzeugUI
{
    JDialog _dialog;
    JPanel _hauptPanel;
    JButton _okButton;
    JButton _abButton;
    
    public DialogPlatzVerkaufsWerkzeugUI(JPanel hauptPanel)
    {
        _hauptPanel = hauptPanel;
        _dialog = getDialogPanel();
        _dialog.show(true);
    }

    private JDialog getDialogPanel()
    {
        Frame frame = (Frame) SwingUtilities.getRoot(_hauptPanel);
        JDialog jp = new JDialog(frame,"Bezahldialog", true);
        jp.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        jp.setLocationRelativeTo(_hauptPanel);
        jp.setSize(400, 250);
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 0;
        c.weighty = 0;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.NORTH;
        TextField tf = new TextField();
        jp.add(tf, c);
        c.gridx = 0;
        c.gridy = 1;
        c.weightx = 0;
        c.weighty = 0;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.NORTH;
        _okButton = new JButton("OK");
        jp.add(_okButton, c);
        _abButton = new JButton("Abbrechen");
        c.gridx = 1;
        c.gridy = 1;
        c.weightx = 0;
        c.weighty = 0;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.NORTH;
        jp.add(_abButton, c);

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

}

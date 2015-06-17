package de.uni_hamburg.informatik.swt.se2.kino.werkzeuge.platzverkauf;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

public class DialogPlatzVerkaufsWerkzeug 
{
    private DialogPlatzVerkaufsWerkzeugUI _ui;
    
    /**
     * Initialisiert das Dialog für das PlatzVerkaufsWerkzeug.
     */
    public DialogPlatzVerkaufsWerkzeug(JPanel hauptPanel)
    {
        System.out.println("Test2");
        _ui = new DialogPlatzVerkaufsWerkzeugUI(hauptPanel);
        registriereUIAktionen();
    }

    private void registriereUIAktionen()
    {
        System.out.println("Test");
        _ui.getAbButton().addActionListener(new ActionListener()
        {
            
            @Override
            public void actionPerformed(ActionEvent e)
            {
                System.out.println("Hallo");
                _ui.getDialog().dispose();
                _ui.getDialog().setVisible(false);
            }
        });
    }
    
    
}
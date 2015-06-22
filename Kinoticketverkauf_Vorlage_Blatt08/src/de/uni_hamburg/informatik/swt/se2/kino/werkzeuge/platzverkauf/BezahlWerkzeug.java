package de.uni_hamburg.informatik.swt.se2.kino.werkzeuge.platzverkauf;

import java.awt.ComponentOrientation;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import de.uni_hamburg.informatik.swt.se2.kino.fachwerte.Platz;
import de.uni_hamburg.informatik.swt.se2.kino.materialien.Geldbetrag;
import de.uni_hamburg.informatik.swt.se2.kino.materialien.Vorstellung;
import de.uni_hamburg.informatik.swt.se2.kino.werkzeuge.vorstellungsauswaehler.VorstellungsFormatierer;

public class BezahlWerkzeug {
	private BezahlWerkzeugUI _ui;
	private boolean _bezahlt;
	private Vorstellung _vorstellung;
	private Set<Platz> _plaetze;
	private Geldbetrag _gesamtPreis;

	/**
	 * Initialisiert das Dialog für das PlatzVerkaufsWerkzeug.
	 */
	public BezahlWerkzeug(Vorstellung vorstellung, Set<Platz> plaetze,
			JPanel hauptPanel) {
		_plaetze = plaetze;
		_vorstellung = vorstellung;
		_gesamtPreis = new Geldbetrag(
				_vorstellung.getPreisFuerPlaetze(_plaetze));
		_ui = new BezahlWerkzeugUI(hauptPanel);
		registriereUIAktionen();
		initialisiereUIAktionen();
		updateUI();
		updateRestgeld();
		_ui.showDialog();
	}

	/**
	 * Initialisiert die UI Aktionen.
	 */
	private void initialisiereUIAktionen() {
		JPanel panel = _ui.getDetailPayPanel();
		GridLayout layout = _ui.getGridLayout();
		JDialog jd = _ui.getDialog();
		for (Platz platz : _plaetze) {
			layout.setRows(layout.getRows() + 1);
			Set<Platz> set = new HashSet<Platz>();
			set.add(platz);
			String[] headings = {
					"Erwachsener",
					"" + (platz.getReihe() + 1),
					"" + platz.getSitz(),
					_vorstellung.getPreisGeldbetragFuerPlaetze(set)
							.getEuroCentString() + "€" };
			for (String string : headings) {
				JLabel label = new JLabel(string);
				label.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
				panel.add(label);
			}
			jd.setSize(jd.getWidth(), jd.getHeight() + 20);
		}
		_ui.getVorstellungLabel().setText(
				new VorstellungsFormatierer(_vorstellung).toString());
	}

	/**
	 * Updatet den Gesamtpreis der UI.
	 */
	private void updateUI() {
		_ui.getGesamtPreisLabel().setText(
				_gesamtPreis.getEuroCentString() + "€");
	}

	/**
	 * Gibt die Exemplarvariable _bezahlt zurück.
	 * @return boolean
	 */
	public boolean getBezahlt() {
		return _bezahlt;
	}
	
	/**
	 * Registriert die Listener in der UI.
	 */
	private void registriereUIAktionen() {
		_ui.getAbButton().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				_ui.getDialog().dispose();
				_bezahlt = false;
			}
		});
		_ui.getOkButton().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				_ui.getDialog().dispose();
				_bezahlt = true;
			}
		});
		_ui.getBezahlField().addKeyListener(new KeyListener() {

			Pattern p = Pattern.compile("[0-9]*,{0,1}[0-9]{0,2}");
			String undo;
			int pos;

			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
				if (!p.matcher(_ui.getBezahlField().getText()).matches()
						&& !_ui.getBezahlField().getText().equals("")) {
					pos = _ui.getBezahlField().getCaretPosition() - 1;
					_ui.getBezahlField().setText(undo);
					try {
						_ui.getBezahlField().setCaretPosition(pos);
					} catch (Exception e2) {
						_ui.getBezahlField().setCaretPosition(
								_ui.getBezahlField().getText().length());
						;
					}
				}
				if (_ui.getBezahlField().getText().length() > 8)
					_ui.getBezahlField().setText(undo);
				undo = _ui.getBezahlField().getText();
				updateRestgeld();
			}

			// Bug bei gehaltetem Keypress
			@Override
			public void keyPressed(KeyEvent e) {
			}
		});
		_ui.getBezahlField().getInputMap(JComponent.WHEN_FOCUSED)
				.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "doit");
		_ui.getBezahlField().getActionMap().put("doit", new AbstractAction() {

			@Override
			public void actionPerformed(ActionEvent e) {
				_ui.getOkButton().doClick();
			}
		});
	      _ui.getBezahlField().getInputMap(JComponent.WHEN_FOCUSED)
	          .put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "cancelDialog");
	      _ui.getBezahlField().getActionMap().put("cancelDialog", new AbstractAction() {

      @Override
      public void actionPerformed(ActionEvent e) {
          _ui.getAbButton().doClick();
      }
  });
		_ui.getOkButton().setEnabled(false);
	}
	
	/**
	 * Updatet den Restgeldbetrag und prüft die Konditionen, ob genug Geld bezahlt wurde. Setzt dann den OK Button auf drückbar.
	 */
	private void updateRestgeld() {
		Geldbetrag eingezahlt = new Geldbetrag(_ui.getBezahlField().getText());
		int restgeld = eingezahlt.subtract(_gesamtPreis).getEuroCentInt();
		_ui.getRestGeldLabel().setText(
				"<html><font color=" + (restgeld >= 0 ? "green" : "red") + ">"
						+ eingezahlt.subtract(_gesamtPreis).getEuroCentString()
						+ "€" + "</font></html>");
		_ui.getOkButton().setEnabled(restgeld >= 0);
	}
}
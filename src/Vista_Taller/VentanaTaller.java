package Vista_Taller;
import com.cloudgarden.layout.AnchorConstraint;
import com.cloudgarden.layout.AnchorLayout;
import java.awt.BorderLayout;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import javax.swing.WindowConstants;
import javax.swing.SwingUtilities;



/**
* This code was edited or generated using CloudGarden's Jigloo
* SWT/Swing GUI Builder, which is free for non-commercial
* use. If Jigloo is being used commercially (ie, by a corporation,
* company or business for any purpose whatever) then you
* should purchase a license for each developer using Jigloo.
* Please visit www.cloudgarden.com for details.
* Use of Jigloo implies acceptance of these licensing terms.
* A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR
* THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED
* LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
*/
public class VentanaTaller extends javax.swing.JFrame {
	private JPanel jPanel1;
	private JButton botonCarrera;
	private JButton botonVerPista;
	private JButton botonCambiarAuto;
	private JButton botonCambiarAutoparte;
	private JButton botonComprar;

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				VentanaTaller inst = new VentanaTaller();
				inst.setLocationRelativeTo(null);
				inst.setVisible(true);
			}
		});
	}
	
	public VentanaTaller() {
		super("Mi Taller");
		initGUI();
	}
	
	private void initGUI() {
		try {
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			{
				jPanel1 = new JPanel();
				AnchorLayout jPanel1Layout = new AnchorLayout();
				jPanel1.setLayout(jPanel1Layout);
				getContentPane().add(jPanel1, BorderLayout.CENTER);
				{
					botonComprar = new JButton();
					jPanel1.add(botonComprar, new AnchorConstraint(900, 129, 1000, 17, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					BoxLayout botonComprarLayout = new BoxLayout(botonComprar, javax.swing.BoxLayout.X_AXIS);
					botonComprar.setLayout(botonComprarLayout);
					botonComprar.setText("Comprar");
					botonComprar.setPreferredSize(new java.awt.Dimension(55, 20));
				}			}
			{
				botonCambiarAutoparte = new JButton();
				jPanel1.add(botonCambiarAutoparte, new AnchorConstraint(901, 352, 1001, 139, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				botonCambiarAutoparte.setText("Cambiar Autoparte");
				botonCambiarAutoparte.setPreferredSize(new java.awt.Dimension(105, 37));
			}
			{
				botonCambiarAuto = new JButton();
				jPanel1.add(botonCambiarAuto, new AnchorConstraint(901, 523, 1001, 362, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				botonCambiarAuto.setText("Cambiar Auto");
				botonCambiarAuto.setPreferredSize(new java.awt.Dimension(79, 37));
			}
			{
				botonVerPista = new JButton();
				jPanel1.add(botonVerPista, new AnchorConstraint(901, 816, 1001, 533, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				botonVerPista.setLayout(null);
				botonVerPista.setText("Ver Condiciones la Pista");
				botonVerPista.setPreferredSize(new java.awt.Dimension(139, 37));
			}
			{
				botonCarrera = new JButton();
				jPanel1.add(botonCarrera, new AnchorConstraint(900, 1001, 1000, 828, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				botonCarrera.setText("Correr Carrera");
				botonCarrera.setPreferredSize(new java.awt.Dimension(85, 21));
			}
			pack();
			this.setSize(500, 400);
			setSize(00, 400);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

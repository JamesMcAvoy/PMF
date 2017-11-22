package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import org.knowm.xchart.XChartPanel;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;

import model.Fridge;

public class MainFrame extends JFrame implements ActionListener{
	
	private static final long serialVersionUID = 1L;

	private Font font = new Font("Tahoma", Font.PLAIN, 28);
	private Color backgroundColor = Color.decode("#2E57B9");
	private Color foregroundColor = Color.decode("#DAE1FE");
	
	private JPanel panel = new JPanel();
	
	JLabel intTemp = new JLabel("Température interne: 20°C", SwingConstants.CENTER);
	JLabel extTemp = new JLabel("Température externe: 20°C", SwingConstants.CENTER);
	JLabel intHygro = new JLabel("Humidité interne: 30%", SwingConstants.CENTER);
	JLabel rosee = new JLabel("Point de rosée: 60°C", SwingConstants.CENTER);
	JLabel consigne = new JLabel("Consigne (en °C):", SwingConstants.CENTER);
	JFormattedTextField consigneField = new JFormattedTextField(NumberFormat.getIntegerInstance());
	JButton boutonConfirmer = new JButton("Changer!");
	JButton switchBoutonFan = new JButton("Activer le ventilateur!");
	
	XYChart chartHygro = new XYChartBuilder().width(400).height(400).xAxisTitle("Temps").yAxisTitle("Humidité(%)").build();
	XYChart chartTemp = new XYChartBuilder().width(400).height(400).xAxisTitle("Temps").yAxisTitle("Température(°C)").build();
	
	public MainFrame() {
		this.setTitle("PMF");
		this.setSize(1400, 1000);
		this.setLocationRelativeTo(null); 
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.setContentPane(panel);
		panel.setLayout(new GridBagLayout());
		
		intTemp.setFont(font);		
		extTemp.setFont(font);		
		intHygro.setFont(font);
		rosee.setFont(font);
		consigne.setFont(font);
		consigneField.setFont(font);
		boutonConfirmer.setFont(font);	
		switchBoutonFan.setFont(font);	
		
		intTemp.setForeground(foregroundColor);
		extTemp.setForeground(foregroundColor);
		intHygro.setForeground(foregroundColor);
		rosee.setForeground(foregroundColor);
		consigne.setForeground(foregroundColor);
		consigneField.setForeground(foregroundColor);
		boutonConfirmer.setForeground(foregroundColor);
		
		consigneField.setBackground(backgroundColor);
		boutonConfirmer.setBackground(backgroundColor);
		panel.setBackground(backgroundColor);
		
		switchBoutonFan.setBackground(Color.RED);
		
		consigneField.setHorizontalAlignment(JTextField.CENTER);
		consigneField.setPreferredSize(new Dimension(200,100));
		consigneField.setText("18");		
		
		double[] xDataDefault = new double[] { 0.0, 1.0};
		double[] yDataDefault = new double[] { 0.0, 1.0};
		
		XYChart chartTemp = new XYChartBuilder().width(400).height(400).xAxisTitle("Temps").yAxisTitle("Température(°C)").build();
		JPanel panelChartTemp = new XChartPanel(chartTemp);
		chartTemp.addSeries("Température intérieure", xDataDefault, yDataDefault);
		chartTemp.addSeries("Température extérieure", xDataDefault, yDataDefault);		
		
		XYChart chartHygro = new XYChartBuilder().width(400).height(400).xAxisTitle("Temps").yAxisTitle("Humidité(%)").build();
		JPanel panelChartHygro = new XChartPanel(chartHygro);
		chartHygro.addSeries("Humidité intérieure", xDataDefault, yDataDefault);	

		
		GridBagConstraints gbc = new GridBagConstraints();
		
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx=2;
		gbc.weighty=1;
		
		gbc.gridx=0;
		gbc.gridy=0;
		panel.add(extTemp, gbc);

		gbc.gridx=0;
		gbc.gridy=1;
		panel.add(intTemp, gbc);
		
		gbc.weightx=1;
		gbc.gridx=0;
		gbc.gridy=2;
		panel.add(consigne, gbc);
		
		gbc.gridx=1;
		gbc.gridy=2;
		panel.add(consigneField, gbc);
		
		gbc.gridx=2;
		gbc.gridy=0;
		panel.add(intHygro, gbc);
		
		gbc.gridx=2;
		gbc.gridy=1;
		panel.add(switchBoutonFan, gbc);
		
		
		gbc.gridx=2;
		gbc.gridy=2;
		panel.add(boutonConfirmer, gbc);

		gbc.gridx=4;
		gbc.gridy=0;
		panel.add(rosee, gbc);

		gbc.gridx=4;
		gbc.gridy=1;
		panelChartTemp.setPreferredSize(new Dimension(300,300));
		panel.add(panelChartTemp, gbc);
		
		gbc.gridx=4;
		gbc.gridy=2;
		panelChartHygro.setPreferredSize(new Dimension(300,300));
		panel.add(panelChartHygro, gbc);
		
		boutonConfirmer.addActionListener(this);
		switchBoutonFan.addActionListener(this);
		this.setVisible(true);
	}
	public void actionPerformed(ActionEvent arg0){
		if(arg0.getSource() == boutonConfirmer){
			consigne.setText("Consigne (en °C): "+consigneField.getText());
			this.repaint();
			//Rajouter l'envoi de la commande a Arduino
		}else if(arg0.getSource() == switchBoutonFan) {
			if(switchBoutonFan.getBackground()==Color.RED) {
				System.out.println("hey");
				switchBoutonFan.setText("Désactiver le ventilateur!");
				switchBoutonFan.setBackground(Color.GREEN);
				//Activer le ventilo
				this.repaint();
			}else {
				System.out.println("ho");
				switchBoutonFan.setText("Activer le ventilateur!");
				switchBoutonFan.setBackground(Color.RED);
				//Desactiver le ventilo
				this.repaint();
			}
		}	
	}
	public void updateChart(Fridge fridge) {
		double[] xData = new double[] { 0.0, 1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0, 10.0, 11.0, 12.0, 13.0, 14.0, 15.0, 16.0, 17.0, 18.0, 19.0, 20.0 };
		chartTemp.updateXYSeries("Température intérieure", xData, fridge.getFridgeArrays().getIntTempArray(), null);
		chartTemp.updateXYSeries("Température extérieure", xData, fridge.getFridgeArrays().getExtTempArray(), null);
		chartTemp.updateXYSeries("Humidité intérieure", xData, fridge.getFridgeArrays().getIntHygroArray(), null);
		this.repaint();
	}
	
	public void updateValues(Fridge fridge) {
		intTemp.setText("Température interne: "+fridge.getInternalTemp()+"°C");
		extTemp.setText("Température externe: "+fridge.getInternalTemp()+"°C");
		intHygro.setText("Humidité interne: "+fridge.getInternalTemp()+"%");
		rosee.setText("Point de rosée: "+fridge.getInternalTemp()+"°C");
	}
	
}

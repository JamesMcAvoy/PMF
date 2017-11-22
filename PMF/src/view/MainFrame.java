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

public class MainFrame extends JFrame implements ActionListener{
	
	private static final long serialVersionUID = 1L;

	private Font font = new Font("Tahoma", Font.PLAIN, 28);
	private Color backgroundColor = Color.decode("#2E57B9");
	private Color foregroundColor = Color.decode("#DAE1FE");
	
	private JPanel panel = new JPanel();
	
	JLabel intTemp = new JLabel("Température interne: 20°C", SwingConstants.CENTER);
	JLabel extTemp = new JLabel("Température externe: 20°C", SwingConstants.CENTER);
	JLabel intHygro = new JLabel("Humidité interne: 30%", SwingConstants.CENTER);
	JLabel extHygro = new JLabel("Humidité interne: 30%", SwingConstants.CENTER);
	JLabel rosee = new JLabel("Point de rosée: 60°C", SwingConstants.CENTER);
	JLabel consigne = new JLabel("Consigne (en °C):", SwingConstants.CENTER);
	JFormattedTextField consigneField = new JFormattedTextField(NumberFormat.getIntegerInstance());
	JButton boutonConfirmer = new JButton("Changer!");
	
	public MainFrame() {
		this.setTitle("PMF");
		this.setSize(1200, 800);
		this.setLocationRelativeTo(null); 
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.setContentPane(panel);
		panel.setLayout(new GridBagLayout());
		
		intTemp.setFont(font);		
		extTemp.setFont(font);		
		intHygro.setFont(font);
		extHygro.setFont(font);
		rosee.setFont(font);
		consigne.setFont(font);
		consigneField.setFont(font);
		boutonConfirmer.setFont(font);		
		
		intTemp.setForeground(foregroundColor);
		extTemp.setForeground(foregroundColor);
		intHygro.setForeground(foregroundColor);
		extHygro.setForeground(foregroundColor);
		rosee.setForeground(foregroundColor);
		consigne.setForeground(foregroundColor);
		consigneField.setForeground(foregroundColor);
		boutonConfirmer.setForeground(foregroundColor);
		
		consigneField.setBackground(backgroundColor);
		boutonConfirmer.setBackground(backgroundColor);
		panel.setBackground(backgroundColor);
		
		consigneField.setHorizontalAlignment(JTextField.CENTER);
		consigneField.setPreferredSize(new Dimension(200,100));
		
		XYChart chartTemp = new XYChartBuilder().width(400).height(400).xAxisTitle("Temps").yAxisTitle("Température(°C)").build();
		JPanel panelChartTemp = new XChartPanel(chartTemp);
		
		XYChart chartHygro = new XYChartBuilder().width(400).height(400).xAxisTitle("Temps").yAxisTitle("Humidité(%)").build();
		JPanel panelChartHygro = new XChartPanel(chartHygro);
		
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
		panel.add(extHygro, gbc);
		
		gbc.gridx=2;
		gbc.gridy=1;
		panel.add(intHygro, gbc);
		
		
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
		this.setVisible(true);
	}
	public void actionPerformed(ActionEvent arg0){
		if(arg0.getSource() == boutonConfirmer){
			
		}
		
	}
	
}

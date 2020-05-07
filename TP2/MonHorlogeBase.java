import java.awt.*;
import java.util.*;
import java.applet.*;
import javax.swing.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.awt.event.*;

public class MonHorlogeBase extends JPanel implements Runnable {

	Image imgTmp;
	Graphics gTmp;

	static LocalDateTime currentTime = LocalDateTime.now();
	static int hours = currentTime.getHour();
	static int minutes = currentTime.getMinute();
	static double seconds = currentTime.getSecond();

	static boolean horlogeContinue = false;
	static double vitesse = 0.1; // 0.0 < vitesse < 1.0


	public void getInformations() {
		LocalDateTime currentTime = LocalDateTime.now();
		int hours = currentTime.getHour();
		int minutes = currentTime.getMinute();
		if (!horlogeContinue) {
			double seconds = currentTime.getSecond();
		}
	}

	public void paint(Graphics gsp) {

		super.paint(gsp);

		getInformations();

		setBackground(Color.red);

		int haut = getHeight();
		int larg = getWidth();

		imgTmp = createImage(larg, haut);
		gTmp = imgTmp.getGraphics();

		DessinHorloge.dessineHorloge(gTmp, haut, larg, hours, minutes, seconds);

		gsp.drawImage(imgTmp, 0, 0, this);
	}

	public MonHorlogeBase() {
		Thread tr = new Thread(this);
		tr.start();
	}

	@Override
	public void run() {
		while (true) {
			repaint();
			try {
				if (horlogeContinue) {
					Thread.sleep((int) (1000 * vitesse));
					seconds += vitesse;
				} else {
					Thread.sleep(1000);
					seconds += 1.0;
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}


	public static void refreshSeconds() {
		currentTime = LocalDateTime.now();
		seconds = currentTime.getSecond();
	}

	static public void main(String[] args) {
		JFrame frame = new JFrame("Horloge");
		frame.add(new MonHorlogeBase());
		frame.setBounds(100, 100, 800, 600);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


		JFrame frame2 = new JFrame("Configuration Horloge");
		JPanel panel = new JPanel();

		JCheckBox checkBoxContinue = new JCheckBox("Horloge continue");
		checkBoxContinue.setSelected(horlogeContinue);
		panel.add(checkBoxContinue);
		checkBoxContinue.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				refreshSeconds();
				if (e.getStateChange() == ItemEvent.SELECTED) {
					horlogeContinue = true;
				} else {
					horlogeContinue = false;
				}
				;
			}
		});

		Button buttonChronometer = new Button("Chronomètre");
		panel.add(buttonChronometer);
		buttonChronometer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("chrono");
			}
		});
		JPanel panelHeure = new JPanel();
		JTextField textHours = new JTextField(Integer.toString(hours), 2);
		JTextField textMinutes = new JTextField(Integer.toString(minutes), 2);
		JTextField textSeconds = new JTextField(Double.toString(seconds), 3);
		panelHeure.add(textHours);
		panelHeure.add(textMinutes);
		panelHeure.add(textSeconds);
		
		JPanel panel4 = new JPanel();
		Button buttonChange = new Button("Modifier heure");
		buttonChange.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println(textHours.getText());
				System.out.println(textMinutes.getText());
				System.out.println(textSeconds.getText());
			}
		});
		panel4.add(buttonChange);

		
		//panelHeure.add("South", buttonChange);

		panel.add(panelHeure);
		panel.add(panel4);

		frame2.add(panel);
		frame2.setBounds(920, 100, 220, 600);
		frame2.setVisible(true);
		frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

}
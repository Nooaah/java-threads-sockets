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
	int hours = currentTime.getHour();
	int minutes = currentTime.getMinute();
	static double seconds = currentTime.getSecond();

	static boolean horlogeContinue = false;
	static double vitesse = 0.1; // 0.0 < vitesse < 1.0

	public void paint(Graphics gsp) {

		super.paint(gsp);

		LocalDateTime currentTime = LocalDateTime.now();
		int hours = currentTime.getHour();
		int minutes = currentTime.getMinute();
		if (!horlogeContinue) {
			double seconds = currentTime.getSecond();
		}

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
		JFrame frame = new JFrame();
		frame.add(new MonHorlogeBase());
		frame.setBounds(100, 100, 800, 600);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JFrame frame2 = new JFrame();
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

		frame2.add(panel);
		frame2.setBounds(920, 100, 200, 600);
		frame2.setVisible(true);
		frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

}

import java.awt.*;
import java.util.*;
import java.applet.*;
import javax.swing.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MonHorlogeBase extends JPanel implements Runnable {

	Image imgTmp;
	Graphics gTmp;

	/* Méthode paint qui dessine l'horloge */
	public void paint(Graphics gsp) {

		super.paint(gsp);

		LocalDateTime currentTime = LocalDateTime.now();
		int hours = currentTime.getHour();
		int minutes = currentTime.getMinute();
		int seconds = currentTime.getSecond();

		setBackground(Color.white);

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
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	static public void main(String[] args) {
		JFrame frame = new JFrame();
		frame.getContentPane().add(new MonHorlogeBase());
		frame.setBounds(100, 100, 800, 600);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}

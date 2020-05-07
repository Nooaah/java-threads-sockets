import java.awt.*;
import java.util.*;
import java.applet.*;
import javax.swing.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MonHorlogeBase extends JPanel implements Runnable {

	Image imgTmp;
	Graphics gTmp;

	LocalDateTime currentTime = LocalDateTime.now();
	int hours = currentTime.getHour();
	int minutes = currentTime.getMinute();
	double seconds = currentTime.getSecond();

	static boolean horlogeContinue = false;

	public void paint(Graphics gsp) {

		super.paint(gsp);

		LocalDateTime currentTime = LocalDateTime.now();
		int hours = currentTime.getHour();
		int minutes = currentTime.getMinute();
		if (!horlogeContinue) {
			double seconds = currentTime.getSecond();
		}

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
				if (horlogeContinue) {
					Thread.sleep(1000 / 10);
					seconds += 0.1;
				} else {
					Thread.sleep(1000);
					seconds += 1.0;
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public static void askHorlogeContinue() {
		Scanner Obj = new Scanner(System.in);
		System.out.println("Aiguilles des secondes continue ? (y/n) : ");
		String res = Obj.nextLine();

		if (res.equals("y") || res.equals("Y")) {
			horlogeContinue = true;
		}
	}

	static public void main(String[] args) {

		askHorlogeContinue();
		JFrame frame = new JFrame();
		JPanel panel = new JPanel();
		frame.getContentPane().add(new MonHorlogeBase());
		panel.add(new Button("Bouton 3"));

		//frame.add(panel);

		frame.setBounds(100, 100, 800, 600);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

}

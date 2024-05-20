package Main;

import Graphics.Frame;
import Graphics.PlayAudio;

public class Main {
	Frame frame;

	public Main() {
		frame = new Frame();
		MyTimeCount timeCount = new MyTimeCount();
		timeCount.start();
		new Thread(frame).start();

		PlayAudio.play("src/audio/musicbackground.wav", true);
	}

	public static void main(String[] args) {
		new Main();
	}

	class MyTimeCount extends Thread {
		public void run() {
			while (true) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				frame.setTime(frame.getTime() - 1);
				if (frame.getTime() == 0) {
					PlayAudio.play("src/audio/losesound.wav", false);
					frame.showDialogNewGame("Time's up!\nDo you want to play again?");
				}
			}
		}
	}

}

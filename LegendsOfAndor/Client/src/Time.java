import java.io.Serializable;

public class Time implements Serializable{
	public int time;
	public int x;
	public int y;

	public Time() {
		time = 0;
		x = 6020;
		y = 240;
	}
	
	public void advance() {
		if(time < 10 && Client.mainHero.wp > 2) {
		if(time >= 7) {
			time++;
			x+= 650;
			Client.mainHero.wp = Client.mainHero.wp -2;
		}else {
		time++;
		x += 550;
	}
		}
	}	
	public int getTime() {
		return this.time;
	}
	
	public void reset() {
		time = 0;
		x = 6020;
		y = 240;
	}

}

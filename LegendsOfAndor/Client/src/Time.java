import java.io.Serializable;

public class Time implements Serializable{
	int time = 0;
	int x = 6020;
	int y = 240;

	public Time() {
	}
	
	public void advance() {
		if(time >= 7) {
			//Client.mainHero.wp = Client.mainHero.wp - 2;
			time++;
			x+= 650;
		}else {
		time++;
		x += 550;
	}
	}	
	public boolean left() {
		//if(time >= 7 && Client.mainHero.wp < 2) {return false;}
		if (time < 10) return true;
		else return false;
	}
	
	public void reset() {
		time = 0;
		x = 6020;
		y = 240;
	}
	
}

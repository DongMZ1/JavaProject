import java.io.Serializable;

public class Tuple<first, second> implements Serializable{ 
  /**
	 * 
	 */
	private static final long serialVersionUID = 8481918668336071069L;
public  first first; 
  public  second second; 
  public Tuple(first first, second second) { 
    this.first = first; 
    this.second = second; 
  } 
} 
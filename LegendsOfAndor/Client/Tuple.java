import java.io.Serializable;

public class Tuple<first, second> implements Serializable{ 
  public  first first; 
  public  second second; 
  public Tuple(first first, second second) { 
    this.first = first; 
    this.second = second; 
  } 
} 
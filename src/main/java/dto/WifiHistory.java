package dto;

import lombok.*;

import java.util.Date;

@Getter
@Setter


public class WifiHistory {
	private int id;
	private double CoordX;
	private double CoordY;
	private String date; 
	private String note;
	
}

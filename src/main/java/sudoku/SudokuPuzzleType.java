package sudoku;

public enum SudokuPuzzleType {
	
	NINEBYNINE(9,9,3,3,new String[] {"1","2","3","4","5","6","7","8","9"},"Reiniciar Juego"),

	SIXTEENBYSIXTEEN(16,16,4,4,new String[] {"1","2","3","4","5","6","7","8","9","A","B","C","D","E","F","G"},"16 By 16 Game");
	
	private final int rows;
	private final int columns;
	private final int boxWidth;
	private final int boxHeight;
	private final String [] validValues;
	private final String desc;
	
	private SudokuPuzzleType(int rows,int columns,int boxWidth,int boxHeight,String [] validValues,String desc){
		this.rows=rows;
		this.columns=columns;
		this.boxWidth=boxWidth;
		this.boxHeight=boxHeight;
		this.validValues=validValues;
		this.desc=desc;
	}
	
	public int getRows(){
		return rows;
	}
	
	public int getColumns(){
		return columns;
	}
	
	public int getBoxWidth(){
		return boxWidth;
	}
	
	public int getBoxHeight(){
		return boxHeight;
	}
	
	public String [] getValidValues(){
		return validValues;
	}
	
	public String toString(){
		return desc;
	}
}

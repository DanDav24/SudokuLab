package sudoku;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class SudokuGenerator{

	public SudokuPuzzle generateRandomSudoku(SudokuPuzzleType puzzleType) {
		SudokuPuzzle puzzle = new SudokuPuzzle(puzzleType.getRows(), puzzleType.getColumns(), puzzleType.getBoxWidth(), puzzleType.getBoxHeight(), puzzleType.getValidValues());
		SudokuPuzzle copy = new SudokuPuzzle(puzzle);
		
		Random randomGenerator = new Random();
		
		List<String> notUsedValidValues =  new ArrayList<String>(Arrays.asList(copy.getValidValues()));
		for(int r = 0;r < copy.getNumRows();r++) {
			int randomValue = randomGenerator.nextInt(notUsedValidValues.size());
			copy.makeMove(r, 0, notUsedValidValues.get(randomValue), true);
			notUsedValidValues.remove(randomValue);
		}
		
		
		backtrackSudokuSolver(0, 0, copy);
		
		int numberOfValuesToKeep = (int)(0.22222*(copy.getNumRows()*copy.getNumRows()));
		
		for(int i = 0;i < numberOfValuesToKeep;) {
			int randomRow = randomGenerator.nextInt(puzzle.getNumRows());
			int randomColumn = randomGenerator.nextInt(puzzle.getNumColumns());
			
			if(puzzle.isSlotAvailable(randomRow, randomColumn)) {
				puzzle.makeMove(randomRow, randomColumn, copy.getValue(randomRow, randomColumn), false);
				i++;
			}
		}
		
		return puzzle;
	}
	
	
    private boolean backtrackSudokuSolver(int r,int c,SudokuPuzzle puzzle) {
    	//Si el movimiento es invalido retornar falso
		if(!puzzle.inRange(r,c)) {
			return false;
		}
		
		//si el espacio esta vacio
		if(puzzle.isSlotAvailable(r, c)) {
			
			//loop para hallar el valor correcto en el espacio
			for(int i = 0;i < puzzle.getValidValues().length;i++) {
				
				//si el numero sirve en el espacio
				if(!puzzle.numInRow(r, puzzle.getValidValues()[i]) && !puzzle.numInCol(c,puzzle.getValidValues()[i]) && !puzzle.numInBox(r,c,puzzle.getValidValues()[i])) {
					
					//movimiento
					puzzle.makeMove(r, c, puzzle.getValidValues()[i], true);
					
					//si se resuelve retornar verdadero
					if(puzzle.boardFull()) {
						return true;
					}
					
					//siguiente movimiento
					if(r == puzzle.getNumRows() - 1) {
						if(backtrackSudokuSolver(0,c + 1,puzzle)) return true;
					} else {
						if(backtrackSudokuSolver(r + 1,c,puzzle)) return true;
					}
				}
			}
		}
		
		//si el espacio no esta vacio
		else {
			//siguiente movimiento
			if(r == puzzle.getNumRows() - 1) {
				return backtrackSudokuSolver(0,c + 1,puzzle);
			} else {
				return backtrackSudokuSolver(r + 1,c,puzzle);
			}
		}
		
		//deshacer movimiento
		puzzle.makeSlotEmpty(r, c);
		
	
		return false;
	}
}

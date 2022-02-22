package Assign01;

import BasicIO.*;                // for IO classes
import static BasicIO.Formats.*; // for field formats
import static java.lang.Math.*;  // for math constants and functions


/** This class searches the word in the puzzle given in the assignment
 *
 * @author Esbah Majoka
 * @version 3.0 (January 25th)                                                        */

/** This constructor ...                                                     */
public class wordSearch {
        private char [][] canvas, words, solution;
        private int wordsFound = 0;
        private ASCIIDataFile file; //ASCII Data file
        private ASCIIDisplayer display; //ASCII Displayer

        public wordSearch() {
                canvas = new char [25][25]; //showing the matrix
                words = new char [21][]; //showing the words we need to find which is a ragged array
                solution = new char [25][25]; //displaying solution
                file = new ASCIIDataFile();
                display = new ASCIIDisplayer(25 + 5, 25);
                loadWords(); //loading the words
                search(); //searching in the puzzle
                newBoard(); //displaying it on the new board
                file.close();
                display.close();
        }// constructor

        //methods
        private void loadWords() { // This method read the words
                for (int i = 0; i < 21; i++) {
                        words[i] = file.readString().toCharArray();
                }

                for (int i = 0; i < 25; i++) {
                        canvas[i] = file.readString().toCharArray();
                }
        }


        private void search() { // This method search the words in the puzzle
                char c;
                for (int wc = 0; wc < 21; wc++) {
                        c = words[wc][0];
                        for (int i = 0; i < 25; i++) {
                                for (int j = 0; j < 25; j++) {
                                        if (canvas[i][j] == Character.toUpperCase(c)) {
                                                find(wc, i, j);
                                        }
                                }
                        }
                }
        }


        private boolean find(int wc, int i, int j) { // In this method the program checks in 8 directions with boolean method
                boolean match;
                if (j + words[wc].length <= 25) { //This statement check forward in the matrix
                        match = true;
                        for (int c = 0; c < words[wc].length; c++) {
                                if (canvas[i][j + c] != Character.toUpperCase(words[wc][c])) {
                                        match = false;
                                        break;
                                }
                        }
                        if (match) {
                                writeSolution(1, wc, i, j);
                                return true;
                        }
                }


                if (j - words[wc].length >= 0) { ////This statement check back of the matrix
                        match = true;
                        for (int c = 0; c < words[wc].length; c++) {
                                if(canvas[i][j - c] != Character.toUpperCase(words[wc][c])) {
                                        match = false;
                                        break;
                                }
                        }
                        if (match) {
                                writeSolution(2, wc, i, j);
                                return true;
                        }
                }


                if (i - words[wc].length >= 0) { //This statement check up the matrix
                        match = true;
                        for (int c = 0; c < words[wc].length; c++) {
                                if (canvas[i - c][j] != Character.toUpperCase(words[wc][c])) {
                                        match = false;
                                        break;
                                }
                        }
                        if (match) {
                                writeSolution(3, wc, i, j);
                                return true;
                        }
                }


                if (i + words[wc].length <= 25) { //This statement check down the matrix
                        match = true;
                        for (int c = 0; c < words[wc].length; c++) {
                                if (canvas[i + c][j] != Character.toUpperCase(words[wc][c])) {
                                        match = false;
                                        break;
                                }
                        }
                        if (match) {
                                writeSolution(4, wc, i, j);
                                return true;
                        }
                }


                if ((i - words[wc].length >= 0) && (j + words[wc].length <= 25)) { //This statement check diagonally up towards the front
                        match = true;
                        for (int c = 0; c < words[wc].length; c++) {
                                if (canvas[i - c][j + c] != Character.toUpperCase(words[wc][c])) {
                                        match = false;
                                        break;
                                }
                        }
                        if (match) {
                                writeSolution(5, wc, i, j);
                                return true;
                        }
                }


                if ((i - words[wc].length >= 0) && (j - words[wc].length >= 0)) { // //This statement check diagonally up towards the back
                        match = true;
                        for (int c = 0; c < words[wc].length; c++) {
                                if (canvas[i - c][j - c] != Character.toUpperCase(words[wc][c])) {
                                        match = false;
                                        break;
                                }
                        }
                        if (match) {
                                writeSolution(6, wc, i, j);
                                return true;
                        }
                }


                if ((i + words[wc].length <= 25) && (j + words[wc].length <= 25)) { //This statement check diagonally down towards the front of the matrix
                        match = true;
                        for (int c = 0; c < words[wc].length; c++) {
                                if (canvas[i + c][j + c] != Character.toUpperCase(words[wc][c])) {
                                        match = false;
                                        break;
                                }
                        }
                        if (match) {
                                writeSolution(7, wc, i, j);
                                return true;
                        }
                }


                if ((i + words[wc].length <= 25) && (j - words[wc].length >= 0)) {  //This statement check diagonally towards the back
                        match = true;
                        for (int c = 0; c < words[wc].length; c++) {
                                if (canvas[i + c][j - c] != Character.toUpperCase(words[wc][c])) {
                                        match = false;
                                        break;
                                }
                        }
                        if (match) {
                                writeSolution(8, wc, i, j);
                                return true;
                        }
                }

                return false;
        }

        private void writeSolution(int c, int wc, int i, int j) {// This method checks in 8 direction and print solution to matrix.
                wordsFound++;
                switch (c) {
                        case 1:
                                for (int k = 0; k < words[wc].length; k++) {
                                        solution[i][j + k] = Character.toUpperCase(words[wc][k]);
                                }
                                break;
                        case 2:
                                for (int k = 0; k < words[wc].length; k++) {
                                        solution[i][j - k] = Character.toUpperCase(words[wc][k]);
                                }
                                break;
                        case 3:
                                for (int k = 0; k < words[wc].length; k++) {
                                        solution[i - k][j] = Character.toUpperCase(words[wc][k]);
                                }
                                break;
                        case 4:
                                for (int k = 0; k < words[wc].length; k++) {
                                        solution[i + k][j] = Character.toUpperCase(words[wc][k]);
                                }
                                break;
                        case 5:
                                for (int k = 0; k < words[wc].length; k++) {
                                        solution[i - k][j + k] = Character.toUpperCase(words[wc][k]);
                                }
                                break;
                        case 6:
                                for (int k = 0; k < words[wc].length; k++) {
                                        solution[i - k][j - k] = Character.toUpperCase(words[wc][k]);
                                }
                                break;
                        case 7:
                                for (int k = 0; k < words[wc].length; k++) {
                                        solution[i + k][j + k] = Character.toUpperCase(words[wc][k]);
                                }
                                break;
                        case 8:
                                for (int k = 0; k < words[wc].length; k++) {
                                        solution[i + k][j - k] = Character.toUpperCase(words[wc][k]);
                                }
                                break;
                        default:
                                break;
                }
        }

        private void newBoard() { // printing the solution on the new board
                display.show();
                display.writeLine("Found " + wordsFound);
                display.newLine();
                for (int i = 0; i < 25; i++) {
                        for (int j = 0; j < 25; j++) {
                                display.writeChar(solution[i][j]);
                        }
                        display.newLine();
                }
        }

        public static void main(String[] args) {
                wordSearch c = new wordSearch();
        }


}

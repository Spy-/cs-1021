/*
 * CS1021
 * Winter 2018-2019
 * Lab Game Of Life
 * Name: John Bretz
 * Created 1/9/2019
 */

package bretz;

import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

import static bretz.GameOfLife.score;


/**
 * This class implements the grid of cells used to model Conway's Game of Life.
 */
class LifeGrid {

    public List<List<Cell>> cells;

    /**
     * This constructor builds a LifeGrid using the size of the Pane passed and the scale of the cells
     *
     * @param gamePane viewing pane
     * @param width    the preferred width of the viewing pane
     * @param height   the preferred width of the viewing pane
     */
    public LifeGrid(Pane gamePane, int width, int height) {
        int numberOfCellsWide = width / Cell.SCALE;
        int numberOfCellsHigh = height / Cell.SCALE;
        cells = new ArrayList<>();

        //initialize the two dimensional ArrayList
        for (int i = 0; i < numberOfCellsHigh; i++) {
            cells.add(new ArrayList<>());
        }

        //create cells
        for (int i = 0; i < numberOfCellsHigh; i++) {     // yPosition
            for (int j = 0; j < numberOfCellsWide; j++) { // xPosition
                cells.get(i).add(new Cell(j, i));
            }
        }

        //set neighbors for all cells
        for (int i = 0; i < numberOfCellsHigh; i++) {     // yPosition
            for (int j = 0; j < numberOfCellsWide; j++) { // xPosition
                if (i > 0) {
                    if (j > 0) {
                        cells.get(i).get(j).setNeighborAboveLeft(cells.get(i - 1).get(j - 1));
                    }
                    cells.get(i).get(j).setNeighborAboveCenter(cells.get(i - 1).get(j));
                    if (j < numberOfCellsWide - 1) {
                        cells.get(i).get(j).setNeighborAboveRight(cells.get(i - 1).get(j + 1));
                    }
                }
                if (j > 0) {
                    cells.get(i).get(j).setNeighborMiddleLeft(cells.get(i).get(j - 1));
                }
                if (j < numberOfCellsWide - 1) {
                    cells.get(i).get(j).setNeighborMiddleRight(cells.get(i).get(j + 1));
                }
                if (i < numberOfCellsHigh - 1) { // bottom boarder elements
                    if (j > 0) {
                        cells.get(i).get(j).setNeighborBelowLeft(cells.get(i + 1).get(j - 1));
                    }
                    cells.get(i).get(j).setNeighborBelowCenter(cells.get(i + 1).get(j));
                    if (j < numberOfCellsWide - 1) {
                        cells.get(i).get(j).setNeighborBelowRight(cells.get(i + 1).get(j + 1));
                    }
                }
            }
        }
        initialize(gamePane);
    }

    /**
     * This method randomizes the life and death attributes of all cells in the cells.
     * Cells have a 50% chance of being alive or dead.
     */
    public void randomize(Stage stage) {
        for (List<Cell> row : cells) {
            for (Cell cell : row) {
                cell.setAlive(Math.random() < 0.5);
                cell.updateColors();
            }
        }
        setTitle(stage);
    }

    /**
     * Sets all of the cells to dead
     */
    public void clear(Stage stage) {
        for (List<Cell> l : cells) {
            for (Cell c : l) {
                c.setAlive(false);
                c.updateColors();
            }
        }
        setTitle(stage);
    }

    /**
     * This method triggers one iteration (tick) of the game of life rules for the entire grid.
     */
    public void iterate(Stage stage) {
        for (List<Cell> row : cells) {
            for (Cell cell : row) {
                cell.determineNextTick();
            }
        }
        for (List<Cell> row : cells) {
            for (Cell cell : row) {
                cell.updateTick();
            }
        }
        setTitle(stage);
    }

    /**
     * This method adds all the cell rectangles to the Pane
     */
    private void initialize(Pane gamePane) {
        for (List<Cell> row : cells) {
            for (Cell cell : row) {
                gamePane.getChildren().add(cell);
            }
        }
        handleCLick();
    }

    /**
     * Allows clicking a cell change it from alive to dead and vice versa
     */
    private void handleCLick() {
        for (List<Cell> l : cells) {
            for (Cell c : l) {
                c.setOnMouseClicked(mouseEvent -> {
                    c.setAlive(!c.isAlive());
                    c.updateColors();
                });
            }
        }
    }

    /**
     * Sets the title of the window and the score text to the number of cells that are alive and dead
     *
     * @param stage the main stage
     */
    public void setTitle(Stage stage) {
        int alive = 0;
        int dead = 0;
        for (List<Cell> l : cells) {
            for (Cell c : l) {
                if (c.isAlive()) {
                    alive++;
                } else {
                    dead++;
                }
            }
        }
        stage.setTitle("Alive: " + alive + " | Dead: " + dead);
        score.setText("Alive: " + alive + " | Dead: " + dead);
    }
}

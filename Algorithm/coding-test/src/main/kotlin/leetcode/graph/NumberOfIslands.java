package leetcode.graph;

/**
 * 200. Number of Islands (https://leetcode.com/problems/number-of-islands/)
 */
public class NumberOfIslands {

    public int numIslands(char[][] grid) {
        int counter = 0;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (canVisit(i, j, grid)) {
                    counter++;
                    doSearch(i, j, grid);
                }
            }
        }

        return counter;
    }

    private void doSearch(int y, int x, char[][] grid) {
        System.out.println("탐색중 " + y + "," + x);
        grid[y][x] = 'p';

        if (canVisit(y - 1, x, grid)) {
            doSearch(y - 1, x, grid);
        }

        if (canVisit(y, x + 1, grid)) {
            doSearch(y, x + 1, grid);
        }

        if (canVisit(y + 1, x, grid)) {
            doSearch(y + 1, x, grid);
        }

        if (canVisit(y, x - 1, grid)) {
            doSearch(y, x - 1, grid);
        }

        return;
    }

    private boolean canVisit(int y, int x, char[][] grid) {
        if (y < 0 || y >= grid.length) {
            return false;
        }

        if (x < 0 || x >= grid[y].length) {
            return false;
        }

        if (grid[y][x] == 'p' || grid[y][x] == '0') {
            return false;
        }

        return true;
    }
}
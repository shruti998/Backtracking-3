//Problem 1
// TC O(n!)
//SC O(n*n)
class Solution {
    boolean[][] grid;
    List<List<String>> result;
    public List<List<String>> solveNQueens(int n) {
        if(n == 0) {
            return new ArrayList<>();
        }

        grid = new boolean[n][n];    
        result = new ArrayList<>();
        backtrack(0);
        
        return result;
    }

    private void backtrack(int row) {
        //base
        if(row == grid.length) {
            List<String> ans = new ArrayList<>();
            for(int i = 0; i < grid.length; i++) {
                StringBuilder sb = new StringBuilder();
                for(int j = 0; j < grid.length; j++) {
                    if(grid[i][j] == true) {
                        sb.append("Q");
                    } else {
                        sb.append(".");
                    }
                }
                ans.add(sb.toString());
            }
            result.add(ans);
            return;
        }

        //logic
        for(int i = 0; i < grid.length; i++) {
            if(isSafe(row,i)) {
                //action
                grid[row][i] = true;
                //recurse
                backtrack(row + 1);
                //backtrack
                grid[row][i] = false;
            }
        }
    }

    private boolean isSafe(int row, int col) {
        //check for upper direction
        for(int i = row; i >= 0; i--) {
            if(grid[i][col] == true) {
                return false;
            }
        }

        //check for the upper left diagonal
        int i = row;
        int j = col;
        while(i >= 0 && j >= 0) {
            if(grid[i][j] == true) {
                return false;
            }
            i--;
            j--;
        }

        //check for the upper right diagonal
        i = row;
        j = col;

        while(i >= 0 && j < grid.length) {
            if(grid[i][j] == true) {
                return false;
            }
            i--;
            j++;
        }

        return true;

    }
}

//Problem 2
// TC O(2^n)
//SC O(n*m)
class Solution {
    int m, n;
    int[][] dirs;
    public boolean exist(char[][] board, String word) {
        if(board == null || board.length == 0) {
            return false;
        }

        m = board.length;
        n = board[0].length;
        dirs = new int[][] {{-1,0}, {1,0}, {0,-1}, {0,1}};

        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(board[i][j] == word.charAt(0)) {
                    if(backtrack(board, word, 0, i, j)) {
                        return true;
                    }
                }
            }
        }


        return false;
        
    }

    private boolean backtrack(char[][] board, String word, int index, int row, int col) {
        //base
        if(index == word.length()) {
            return true;
        }
        if(row < 0 || row == m || col < 0 || col == n || board[row][col] == '#') {
            return false;
        }


        //logic
        if(board[row][col] == word.charAt(index)) {
            //action
            char temp = board[row][col];
            board[row][col] = '#';
            //recurse
            for(int[] dir: dirs) {
                int nr = row + dir[0];
                int nc = col + dir[1];
                if(backtrack(board, word, index+1, nr, nc)) {
                    return true;
                }
            }
            //backtrack
            board[row][col] = temp;
        }

        return false;
    }
}
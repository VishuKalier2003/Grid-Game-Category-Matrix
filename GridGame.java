/* You are given a 0-indexed 2D array grid of size 2 x n, where grid[r][c] represents the number of points at 
position (r, c) on the matrix. Two robots are playing a game on this matrix. Both robots initially start at (0, 0) 
and want to reach (1, n-1). Each robot may only move to the right ((r, c) to (r, c + 1)) or down ((r, c) to 
(r + 1, c)). At the start of the game, the first robot moves from (0, 0) to (1, n-1), collecting all the points from 
the cells on its path. For all cells (r, c) traversed on the path, grid[r][c] is set to 0. Then, the second robot 
moves from (0, 0) to (1, n-1), collecting the points on its path. Note that their paths may intersect with one 
another. The first robot wants to minimize the number of points collected by the second robot. In contrast, the 
second robot wants to maximize the number of points it collects. If both robots play optimally, return the number of 
points collected by the second robot.
* Eg 1 :  grid = [[2,5,4],[1,5,1]]                             Output = 4 
* Eg 2 :  grid = [[3,3,1],[8,5,2]]                             Output = 4 
* Eg 3 :  grid = [[1,3,1,15],[1,3,3,1]]                        Output = 7 
*/
import java.util.*;
public class GridGame
{
      public int MaximumPointsBySecondRobot(int grid[][])
      {
            if(grid[0].length == 0) return 0;      // base condition...
            if(grid[0].length == 1) return 0;
            int DProbotI[][] = new int[2][grid[0].length];    //* DP Array Declaration of RobotI -> O(N)
            DProbotI[0][0] = grid[0][0];    // base DP condition...
            DProbotI[1][0] = grid[1][0];    // base DP condition...
            for(int i = 1; i < grid[0].length; i++)      //! Data Storing in DP Array -> O(N)
                  DProbotI[0][i] = DProbotI[0][i - 1] + grid[0][i];    // 1st row...
            for(int i = 1; i < grid[0].length; i++)      //! Data Storing in DP Array -> O(N)
                  DProbotI[1][i] = Math.max(DProbotI[1][i - 1], DProbotI[0][i]) + grid[1][i]; // 2nd row...
            int col = DProbotI[0].length - 1;
            int index = (DProbotI[0][DProbotI[0].length - 1] > DProbotI[1][DProbotI[0].length - 1]) ? 0 : 1;
            // Ternary operator to check the index value...
            while(col != 0)     //! Iteration -> O(N)
            {
                  if(index == 0)     // If robotI is in first row...
                  {
                        while(col != 0)     // Traversing the entire row...
                        {
                              grid[index][col] = 0;    // Replacing the score with zero...
                              col--;
                        }
                  }
                  else if(DProbotI[index][col - 1] >= DProbotI[0][col])
                  {     // If the robotI is in second row...
                        grid[index][col] = 0;
                        index = 1;     // Remain in the second row...
                        col--;
                  }
                  else      // If robotI jumps from 2nd row to 1st row...
                  {
                        grid[index][col] = 0;
                        index = 0;
                  }
            }
            if(index == 1)    grid[1][0] = 0;     // If robotI is in second row...
            grid[0][0] = 0;    // RobotI will always end at the cell (0, 0)...
            int DProbotII[][] = new int[2][grid[0].length];    //* DP Array Declaration for RobotII -> O(N)
            DProbotII[0][0] = grid[0][0];
            DProbotII[1][0] = grid[1][0];
            for(int i = 1; i < grid[0].length; i++)       //! Data Storing in DP Array -> O(N)
                  DProbotII[0][i] = DProbotII[0][i - 1] + grid[0][i];     // 1st row...
            for(int i = 1; i < grid[0].length; i++)       //! Data Storing in DP Array -> O(N)
                  DProbotII[1][i] = Math.max(DProbotII[1][i - 1], DProbotII[0][i]) + grid[1][i];  // 2nd row...
            return DProbotII[1][DProbotII[0].length - 1];     // The rightmost bottom cell is storing the answer...
      }
      public static void main(String args[])
      {
            Scanner sc = new Scanner(System.in);
            int col;
            System.out.print("Enter number of Columns : ");
            col = sc.nextInt();
            int grid[][] = new int[2][col];
            for(int i = 0; i < grid.length; i++)
            {
                  for(int j = 0; j < grid[0].length; j++)
                  {
                        System.out.print("Enter value of "+(i+1)+" row and "+(j+1)+" column : ");
                        grid[i][j] = sc.nextInt();
                  }
            }
            GridGame gridGame = new GridGame();     // Object creation...
            System.out.println("Maximum Second Robot Points Possible : "+gridGame.MaximumPointsBySecondRobot(grid));
            sc.close();
      }
}



//! Time Complexity -> O(N)
//* Space Complexity -> O(N)
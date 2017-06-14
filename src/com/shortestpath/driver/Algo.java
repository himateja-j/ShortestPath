package com.shortestpath.driver;

public class Algo {

	// The algorithm starts from left side of the grid.
	// recursively make 3 calls with (i,j+1), (i+1,j+1) and (i-1,j+1)
	// it returns when the value of j reaches the right side of the grid
	
	// If min cost path exists (<50)
	//At the end actualMin hold the cost of min cost path and actualMinRowPath holds the corresponding path
	// if If min cost path doesn't exists (>50)
	//At the end "max" holds the max cost < 50 and minRowPath holds the corresponding path
	private static String grid[][] = new String[][] {{"69","10","19","10","19"},
		{"51","23","20","19","12"},
		{"60","12","20","11","10"}};

	private static int min = Integer.MAX_VALUE;
	private static int max = 0;
	private static String minRowPath = "";

	private static int actualMin = Integer.MAX_VALUE;
	private static String actualMinRowPath = "";
	private static String found = "";

	public static void main(String[] args) {
		try {
			for (int k = 0; k < grid.length; k++) {
				findPath(k, 0, 0, 0, "", "");
			}
			if (grid.length == 0) {
				System.out.println("Invalid matrix");
			} else {
				if (found.equals("YES")) {
					System.out.println("YES");
					System.out.println(actualMin);
					System.out.println("[" + actualMinRowPath.trim() + "]");
				} else {
					System.out.println("NO");
					System.out.println(max);
					System.out.println("[" + minRowPath.trim() + "]");
				}
			}

		} catch (Exception e) {
			System.out.println("Invalid matrix");
		}
	}

	private static void findPath(int i, int j, int previ, int sum, String path, String rowPath) throws Exception {
		try {
			if (sum + Integer.parseInt(grid[i][j]) < 50 && j == grid[0].length - 1) {
				found = "YES";
				sum = sum + Integer.parseInt(grid[i][j]);
				path = path + grid[i][j];
				rowPath = rowPath + " " + (i + 1);
				if ((sum < min)) {
					if (sum < 50) {
						min = sum;
						minRowPath = rowPath;
					}

					actualMin = sum;
					actualMinRowPath = rowPath;
				}
				return;
			}
		
			if (sum + Integer.parseInt(grid[i][j]) >= 50) {
				if (sum > max && sum < 50) {
					max = sum;
					minRowPath = rowPath;
				}
				return;
			}
			sum = sum + Integer.parseInt(grid[i][j]);
			path = path + grid[i][j];
			rowPath = rowPath + " " + (i + 1);
			if (grid.length == 2) {
				if (i == 0) {
					findPath(1, j + 1, i, sum, path, rowPath);
					findPath(0, j + 1, i, sum, path, rowPath);
				} else {
					findPath(0, j + 1, i, sum, path, rowPath);
					findPath(1, j + 1, i, sum, path, rowPath);
				}
			} else {
				if (i == 0) {
					if (grid.length > 1)
						findPath(grid.length - 1, j + 1, i, sum, path, rowPath);
					findPath(i, j + 1, i, sum, path, rowPath);
					if (grid.length > 1)
						findPath(i + 1, j + 1, i, sum, path, rowPath);

				} else if (i == grid.length - 1) {
					findPath(i - 1, j + 1, i, sum, path, rowPath);
					findPath(i, j + 1, i, sum, path, rowPath);
					findPath(0, j + 1, i, sum, path, rowPath);
				} else {
					findPath(i - 1, j + 1, i, sum, path, rowPath);
					findPath(i, j + 1, i, sum, path, rowPath);
					findPath(i + 1, j + 1, i, sum, path, rowPath);
				}
			}
		} catch (Exception e) {
			throw new Exception();
		}
	}
}

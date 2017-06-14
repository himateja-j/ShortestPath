package com.shortestpath.driver;

public class Driver {

	private static int grid[][] = new int[][] {{3,4,1,2,8,6},
		{6,1,8,2,7,4},
		{5,9,3,9,9,5},
		{8,4,1,3,2,6},
		{3,7,2,1,2,3}};
	// private static int grid [][] = new int[][] {{5 ,8 ,5, 3,5}};

	private static int min = Integer.MAX_VALUE;
	private static String minPath = "";
	private static String minRowPath = "";

	public static void main(String[] args) throws Exception {
		findPath(0, 0, 0, 0, "", "");
		System.out.println(min);
		System.out.println(minPath);
		System.out.println(minRowPath);
	}

	private static void findPath(int i, int j, int previ, int sum, String path, String rowPath) throws Exception {
		try {
			if (j == grid[0].length - 1) {
				sum = sum + grid[i][j];
				path = path + grid[i][j];
				rowPath = rowPath + (i + 1);
				if ((sum < min) && (sum < 50)) {
					min = sum;
					minPath = path;
					minRowPath = rowPath;
				}
				return;
			}
			sum = sum + grid[i][j];
			path = path + grid[i][j];
			rowPath = rowPath + (i + 1);
			if (i == 0) {
				findPath(grid.length - 1, j + 1, i, sum, path, rowPath);
				findPath(i, j + 1, i, sum, path, rowPath);
				if (i < grid.length - 1) {
					findPath(i + 1, j + 1, i, sum, path, rowPath);
				}
			} else if (i == grid.length - 1) {
				findPath(i - 1, j + 1, i, sum, path, rowPath);
				findPath(i, j + 1, i, sum, path, rowPath);
				findPath(0, j + 1, i, sum, path, rowPath);
			} else {
				findPath(i - 1, j + 1, i, sum, path, rowPath);
				findPath(i, j + 1, i, sum, path, rowPath);
				findPath(i + 1, j + 1, i, sum, path, rowPath);
			}
		} catch (Exception e) {
			System.out.println(e);
			System.out.println(i);
			System.out.println(j);
			throw new Exception();
		}
	}
}

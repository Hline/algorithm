import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

class Zomb {
	int r;
	int c;

	public Zomb(int x, int y) {
		this.r = x;
		this.c = y;
	}
}

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;

	public static void main(String[] args) throws Exception {
//		br = new BufferedReader(new FileReader("좀비 바이러스\\3.in"));
		// width=x=col=j=[][v]
		// height=y=row=i=[v][]
		st = new StringTokenizer(br.readLine());
		int width = Integer.parseInt(st.nextToken());
		int height = Integer.parseInt(st.nextToken());
		int[][] map = new int[height][width];
		for (int i = 0; i < height; i++) {
			String line = br.readLine();
			for (int j = 0; j < width; j++) {
				map[i][j] = Integer.parseInt(line.charAt(j) + "");
			}
		}
		// 10 01 00 -10 0-1
		int[] dr = { 1, 0, 0, -1, 0 };
		int[] dc = { 0, 1, 0, 0, -1 };

		Queue<Zomb> que = new LinkedList<>();
		st = new StringTokenizer(br.readLine());
		int col = Integer.parseInt(st.nextToken()) - 1;
		int row = Integer.parseInt(st.nextToken()) - 1;
		que.add(new Zomb(row, col));
		int last = 0;
		map[row][col] = 3;

		while (!que.isEmpty()) {
			Zomb p = que.poll();
			last = map[p.r][p.c];

			for (int i = 0; i < dr.length; i++) {
				int newRow = p.r + dr[i];
				int newCol = p.c + dc[i];
				if (newRow >= height || newCol >= width || newRow < 0 || newCol < 0)
					continue;
				if (map[newRow][newCol] == 0 || map[newRow][newCol] != 1)
					continue;
				que.add(new Zomb(newRow, newCol));
				map[newRow][newCol] = last + 1;
			}
		}

		int cnt = 0;
		for (int[] m : map) {
			for (int mm : m) {
				if (mm == 1)
					cnt++;
			}
		}

		bw.write(last + "\n");
		bw.write(cnt + "");
		bw.close();
	}

}

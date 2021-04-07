import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

class Flower {
	int r;
	int c;
	int d = 0;

	public Flower(int row, int col, int day) {
		this.r = row;
		this.c = col;
		this.d = day;
	}
}

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		br = new BufferedReader(new FileReader("Bloom\\2.in"));
		st = new StringTokenizer(br.readLine());
		int h = Integer.parseInt(st.nextToken());
		int w = Integer.parseInt(st.nextToken());
		Queue<Flower> que = new LinkedList<Flower>();
		for (int i = 0; i < 2; i++) {
			st = new StringTokenizer(br.readLine());
			que.add(new Flower(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), 0));

		}
		int[] dr = { 0, 0, 0, -1, 1 };
		int[] dc = { -1, 1, 0, 0, 0 };
		int[][] visited = new int[h][w];
		int maxDay = 0;
		while (!que.isEmpty()) {
			Flower now = que.poll();
			if (maxDay < now.d)
				maxDay = now.d;
			visited[now.r][now.c] = now.d;
			for (int i = 0; i < dr.length; i++) {
				int nextRow = now.r + dr[i];
				int nextCol = now.c + dc[i];
				if (nextRow >= h || nextCol >= w || nextRow < 0 || nextCol < 0)
					continue;
				if (visited[nextRow][nextCol] != 0)
					continue;
				que.add(new Flower(nextRow, nextCol, now.d + 1));
			}
			boolean debug = false;
		}
		bw.write(maxDay + "");
		bw.close();
	}

}

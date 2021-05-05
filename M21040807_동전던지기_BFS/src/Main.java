import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
import java.io.BufferedReader;

class Pos {
	int x;
	int y;

	public Pos(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static int[][] map;
	static Queue<Pos> que;
	static int[][] visited;
	static int w = 0;
	static int h = 0;

	public static void main(String[] args) throws Exception {
		// [h][w] [x][y] [i][j] //문제 [y][x]
//		br = new BufferedReader(new FileReader("동전 던지기\\5.in"));
		st = new StringTokenizer(br.readLine());
		h = Integer.parseInt(st.nextToken());
		w = Integer.parseInt(st.nextToken());

		map = new int[h][w];

		que = new LinkedList<Pos>();
		for (int i = 0; i < h; i++) {
			String l = br.readLine();
			for (int j = 0; j < w; j++) {
				if (l.charAt(j) == '_')
					map[i][j] = 1;
				else
					map[i][j] = 2;
			}
		}

		Calculate();

		int q = Integer.parseInt(br.readLine());
		int[][] query = new int[q][3];
		for (int i = 0; i < q; i++) {
			st = new StringTokenizer(br.readLine());
			query[i][0] = Integer.parseInt(st.nextToken()); // type
			switch (query[i][0]) {
			case 1:
				query[i][1] = Integer.parseInt(st.nextToken());
				query[i][2] = Integer.parseInt(st.nextToken());
				break;
			case 2:
				query[i][1] = Integer.parseInt(st.nextToken());
				break;
			}
		}

		int cnt = 0;
		int[] point = null;
		for (int i = 0; i < q; i++) {
			switch (query[i][0]) {
			case 1:
				int x = query[i][1];
				int y = query[i][2];
				map[x][y] = 2;
				break;
			case 2:
				if (i > 0 && query[i - 1][0] == 1) { // 재계산
					point = null;
					Calculate();
				}
				if (point == null)
					point = Count();
				cnt += point[query[i][1]];
				break;
			}
		}

//		System.out.println("map");
//		toString(map);

		bw.write(cnt + "");
		bw.close();
	}

	private static void toString(int[][] arr) {
		for (int[] a : arr) {
			toString(a);
			System.out.println();
		}
	}

	private static void toString(int[] arr) {
		for (int a : arr) {
			System.out.print(a + "\t");
		}
	}

	private static int[] Count() {
		int[] point = new int[h * w + 1]; // 점수별 수량
		int[] region = new int[h * w + 1]; // 영역별 점수
		for (int i = 0; i < h; i++) {
			for (int j = 0; j < w; j++) {
				// 영역번호 += 해당위치 점수
				region[visited[i][j]] += map[i][j];
			}
		}

		// 해당 영역의 점수
		for (int r : region) {
			point[r]++;
		}
		return point;
	}

	private static void FindRegion(int regid) {
		int[] dr = { 0, -1, 1, 0, 0 };
		int[] dc = { 0, 0, 0, -1, 1 };

		while (!que.isEmpty()) {
			Pos poll = que.poll();
			for (int i = 0; i < dr.length; i++) {
				int xnext = poll.x + dr[i];
				int ynext = poll.y + dc[i];

				if (xnext < 0 || xnext >= h)
					continue;
				if (ynext < 0 || ynext >= w)
					continue;
				if (visited[xnext][ynext] != 0)
					continue;
				if (map[poll.x][poll.y] == map[xnext][ynext]) {
					que.add(new Pos(xnext, ynext));
					visited[xnext][ynext] = regid;
				}
			}
		}

	}

	private static void Calculate() {
		visited = new int[h][w];
		int regid = 1;
		for (int i = 0; i < h; i++) {
			for (int j = 0; j < w; j++) {
				if (visited[i][j] == 0) {
					que.add(new Pos(i, j));
					FindRegion(regid++);
				}
			}
		}
//		System.out.println("visited");
//		toString(visited);
	}
}
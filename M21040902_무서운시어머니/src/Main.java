import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Pos implements Comparable<Pos> {
	int x;
	int y;
	int t;

	public Pos(int x, int y, int tired) {
		this.x = x;
		this.y = y;
		this.t = tired;
	}

	@Override
	public int compareTo(Pos r) {
		if (t < r.t)
			return -1;
		if (t > r.t)
			return 1;
		return 0;
	}
}

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static int n;
	static int[][] map;
	static int[][] tired;

	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new FileReader("무서운 시어머니\\2.in"));
		st = new StringTokenizer(br.readLine());
		int xStart = Integer.parseInt(st.nextToken());	// 시댁의 위치좌표
		int yStart = Integer.parseInt(st.nextToken());	// 시댁의 위치좌표

		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());			// 마을의 크기
		map = new int[n][n];							// 마을의 상태
		tired = new int[n][n];							// 각 위치별 피로도
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				tired[i][j] = (map[i][j] == -1) ? map[i][j] : Integer.MAX_VALUE;
			}
		}

		int[] maxTired = search(xStart, yStart);

		bw.write(tired[maxTired[0]][maxTired[1]] + "");

		bw.close();
	}

	// 모든 위치를 최단거리로 움직였을 때 피로도
	// xStart, yStart: 시작점. 시댁의 우치좌표.
	static int[] search(int xStart, int yStart) {
		int[] dx = { 0, 0, 0, -1, 1 };	// 시어머니가 이동가능한 x방향
		int[] dy = { -1, 1, 0, 0, 0 };	// 시어머니가 이동가능한 y방향

		boolean[][] visited = new boolean[n][n];
		int maxTired = 0;	// 가장 큰 피로도
		int maxX = 0;		// 가장 큰 피로도의 위치
		int maxY = 0;

		// 최단거리를 찾기위해 PriorrityQueue 사용
		PriorityQueue<Pos> pq = new PriorityQueue<Pos>();
		tired[xStart][yStart] = map[xStart][yStart];
		pq.add(new Pos(xStart, yStart, tired[xStart][yStart]));

		while (!pq.isEmpty()) {
			Pos from = pq.poll();
			if (visited[from.x][from.y])
				continue;
			visited[from.x][from.y] = true;

			for (int i = 0; i < dx.length; i++) {
				int xTo = from.x + dx[i];
				int yTo = from.y + dy[i];

				if (xTo < 0 || xTo >= n)
					continue;
				if (yTo < 0 || yTo >= n)
					continue;
				if (map[xTo][yTo] == -1)
					continue;

				// 이미 구한 피로도와 현재까지 이동한 피로도 비교
				int tTo = from.t + map[xTo][yTo];
				if (tired[xTo][yTo] <= tTo)
					continue;

				tired[xTo][yTo] = tTo;
				pq.add(new Pos(xTo, yTo, tTo));
				// 시어머니가 가장 오기 힘든 위치. 가장 많이 움직인 경로로 선정.
				if (maxTired <= tTo) {
					maxTired = tTo;
					maxX = xTo;
					maxY = yTo;
				}
			}
		}
		return new int[] { maxX, maxY };
	}

}

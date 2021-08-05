import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;
import java.io.BufferedReader;

class Node {
	int x;
	int y;
	int d;

	public Node(int x, int y, int d) {
		super();
		this.x = x;
		this.y = y;
		this.d = d;
	}
}

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static int T;
	static int N;
	static int K;
	static int[][] map;
	static int[][] dist;

	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new FileReader("in"));
		T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); // y
			K = Integer.parseInt(st.nextToken()); // x
			map = new int[K][N];
			dist = new int[K][N]; // 이동 수 저장
			Queue<Node> que = new LinkedList<>();
			for (int x = 0; x < K; x++) {
				String l = br.readLine();
				for (int y = 0; y < N; y++) {
					if (l.charAt(y) == '0') {
						map[x][y] = 0;
						dist[x][y] = Integer.MAX_VALUE;
					} else {
						map[x][y] = 1;
						dist[x][y] = 0;
						que.add(new Node(x, y, 0));
					}
				}
			}

			// 링별로 위,그대로,아래로 움직임
			int[] dr = { -1, 0, 1 };
			// 각 링별 위로 이동하는 이동 수 vs 아래로 이동하는 이동 수 중 최솟값
			while (!que.isEmpty()) {
				Node now = que.poll();
				for (int i = 0; i < 3; i++) {
					Node next = new Node(now.x + dr[i], now.y, now.d + 1);
					// 순환 - 맨 위일 경우 맨 아래로
					if (next.x < 0) {
						next.x = K - 1;
					}
					// 순환 - 맨 아래일 경우 맨 위로
					if (next.x >= K) {
						next.x = 0;
					}
					// dist랑 next.dist 비교해서 적으면 업데이트, que에 추가
					if (dist[next.x][next.y] <= next.d)
						continue;
					dist[next.x][next.y] = next.d;
					que.add(next);
				}
			}
			
			// 각행별로 더해서 가장 적은값
			// 1을 기준으로 위로 올리고 아래로 내리고 해서 제일 적은값이란 뜻
			int ans = Integer.MAX_VALUE;
			for (int x = 0; x < K; x++) {
				int sumRow = 0;
				for (int y = 0; y < N; y++) {
					sumRow += dist[x][y];
				}
				ans = (ans < sumRow) ? ans : sumRow;
			}
			bw.write("#" + (t + 1) + " " + ans + "\n");
		}
		bw.close();
	}

}

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Node {
	int x;
	int y;
	int d;

	public Node(int x, int y, int d) {
		this.x = x;
		this.y = y;
		this.d = d;
	}

}

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static int M;
	static int N;
	static int[][] map;
	static Queue<Node> que;
	static int[][] dist;

	public static void main(String[] args) throws Exception {
//		br = new BufferedReader(new FileReader("in"));
		int T = 1;
//		T = Integer.parseInt(br.readLine());	// ������ ������� ���� ����
		for (int t = 0; t < T; t++) {
			st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken()); // y
			N = Integer.parseInt(st.nextToken()); // x
			map = new int[N][M];
			dist = new int[N][M];
			que = new LinkedList<Node>();
			for (int x = 0; x < N; x++) {
				st = new StringTokenizer(br.readLine());
				for (int y = 0; y < M; y++) {
					map[x][y] = Integer.parseInt(st.nextToken());
					if (map[x][y] == 1) { // �丶��
						que.add(new Node(x, y, 0));
					}
				}
			}

			// ���� �迭. ��,��,��,��
			int[] dx = { -1, 1, 0, 0 };
			int[] dy = { 0, 0, -1, 1 };
			boolean[][] visited = new boolean[N][M];
			while (!que.isEmpty()) {
				Node now = que.poll();
				if (visited[now.x][now.y])
					continue;
				visited[now.x][now.y] = true;
				dist[now.x][now.y] = now.d;
				for (int i = 0; i < dx.length; i++) {
					Node next = new Node(now.x + dx[i], now.y + dy[i], now.d + 1);
					// ������
					if (next.x < 0 || next.x >= N)
						continue;
					if (next.y < 0 || next.y >= M)
						continue;
					// ��
					if (map[next.x][next.y] < 0)
						continue;
					que.add(next);
				}
			}
			
			//��ü�ҿ�ð�
			int max = 0;
			for (int n = 0; n < N; n++) {
				for (int m = 0; m < M; m++) {
					max = (max > dist[n][m]) ? max : dist[n][m];
					//�湮�ȵ�
					if (dist[n][m] == 0 && map[n][m] == 0) {
						max = -1;
						break;
					}
				}
				//�湮�ȵ�
				if (max < 0)
					break;
			}
			int ans = max;
			bw.write(ans + "\n");
		}
		bw.close();

	}

}

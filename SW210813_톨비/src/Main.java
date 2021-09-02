import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

class RoadInfo implements Comparable<RoadInfo> {
	int t;
	int c;
	int p0 = 0;
	int p1 = 0;

	public RoadInfo(int t, int c) {
		this.t = t;
		this.c = c;
	}

	public RoadInfo(int t, int c, int p1, int p2) {
		this.t = t;
		this.c = c;
		this.p0 = p1;
		this.p1 = p2;
	}

	@Override
	public int compareTo(RoadInfo r) {
		if (c < r.c)
			return -1;
		if (c > r.c)
			return 1;
		return 0;
	}
}

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static ArrayList<ArrayList<RoadInfo>> map;
	static int T;
	static int N;
	static int M;
	static int[] P;
	static boolean[] visited;
	static int[][][] dist;

	/*
	 * 1. 소요시간을 구할 계산로직 - que로 해결하기 어려우므로 경우의 수별로 구할 로직 궁리 
	 * 2. 재방문 허용 여부 
	 * 3. ABCDE 와 AFCGE 와 ACE 케이스의 탐색 확인
	 */
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new FileReader("in"));
		T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()) + 1;
			M = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine());
			P = new int[] { Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()) };
			Arrays.sort(P);
			dist = new int[N][2][2]; // N번째노드 티켓1사용여부 티켓2사용여부
			map = new ArrayList<ArrayList<RoadInfo>>();
			for (int n = 0; n < N; n++) {
				map.add(new ArrayList<RoadInfo>());
				dist[n][0][0] = dist[n][0][1] = dist[n][1][0] = dist[n][1][1] = Integer.MAX_VALUE;
			}
			for (int m = 0; m < M; m++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				map.get(a).add(new RoadInfo(b, c));
				map.get(b).add(new RoadInfo(a, c));
			}
			
			// 시작점 1번 톨게이트
			Queue<RoadInfo> que = new PriorityQueue<>();
			que.add(new RoadInfo(1, 0, 1, 1));
			dist[1][0][0] = dist[1][0][1] = dist[1][1][0] = dist[1][1][1] = 0;
			
			while (!que.isEmpty()) {
				RoadInfo now = que.poll();
				//도착점 N번 톨게이트
				if (now.t == N - 1) {
					if (dist[now.t][now.p0][now.p1] > now.c) {
						dist[now.t][now.p0][now.p1] = now.c;
					}
					continue;
				}
				for (int i = 0; i < map.get(now.t).size(); i++) {
					RoadInfo next = map.get(now.t).get(i);
					// 비용이 최소인 현재 티켓 사용 상태 (00 01 10 11)
					if (dist[next.t][now.p0][now.p1] > now.c + next.c) {
						dist[next.t][now.p0][now.p1] = now.c + next.c;
						que.add(new RoadInfo(next.t, dist[next.t][now.p0][now.p1], now.p0, now.p1));
					}

					// 첫번재 할인 티켓이 있을 경우
					if (now.p0 > 0) {
						//할인 금액이 톨비보다 크면 해당 도로의 톨비는 0원이다.
						int dis = next.c <= P[0] ? next.c : P[0];
						if (dist[next.t][0][now.p1] > now.c + next.c - dis) {
							dist[next.t][0][now.p1] = now.c + next.c - dis;
							que.add(new RoadInfo(next.t, dist[next.t][0][now.p1], 0, now.p1));
						}
					}

					// 두번째 할인 티켓이 있을 경우
					if (now.p1 > 0) {
						int dis = next.c <= P[1] ? next.c : P[1];
						if (dist[next.t][now.p0][0] > now.c + next.c - dis) {
							dist[next.t][now.p0][0] = now.c + next.c - dis;
							que.add(new RoadInfo(next.t, dist[next.t][now.p0][0], now.p0, 0));
						}
					}
				}

			}
			
			int ans = 0;
			
			// 모든 경우 중 가장 적은 비용
			if (dist[N - 1][0][0] < dist[N - 1][0][1])
				ans = dist[N - 1][0][0];
			else if (dist[N - 1][0][1] < dist[N - 1][1][0])
				ans = dist[N - 1][0][1];
			else if (dist[N - 1][1][0] < dist[N - 1][1][1])
				ans = dist[N - 1][1][0];
			else
				ans = dist[N - 1][1][1];
			
			bw.write("#" + (t + 1) + " " + ans + "\n");
		}
		bw.close();

	}

}

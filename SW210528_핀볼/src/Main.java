import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Node {
	int t;
	int s;

	public Node(int to, int sum) {
		t = to;
		s = sum;
	}
}

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;

	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new FileReader("in"));
		int T = Integer.parseInt(br.readLine());	// 테스트케이스
		for (int t = 0; t < T; t++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());	// 공을 넣을 수 있는 지점
			int Q = Integer.parseInt(st.nextToken());	// 게임 횟수
			int[] cost = new int[N];	// 지점의 점수
			ArrayList<ArrayList<Integer>> map = new ArrayList<ArrayList<Integer>>();
			st = new StringTokenizer(br.readLine());
			for (int n = 0; n < N; n++) {
				cost[n] = Integer.parseInt(st.nextToken());
				map.add(new ArrayList<Integer>());
			}
			int[] parent = new int[N];	// 해당 지점과 연결되어 공이 굴러가는 다음 지점
			st = new StringTokenizer(br.readLine());
			for (int n = 0; n < N; n++) {
				int to = Integer.parseInt(st.nextToken()) - 1;
				if (n == 0)
					continue;
				parent[n] = to;
				// 최상위(1번)부터 N까지 탐색하며 점수 누적합과 단계를 구하는 게 목적이므로 단방향 그래프
				map.get(to).add(n);
			}

			int[] sum = new int[N];		// N지점까지 점수의 누적합
			int[] step = new int[N];	// N지점까지의 단계
			Queue<Integer> que = new LinkedList<Integer>();
			boolean[] visited = new boolean[N];
			sum[0] = cost[0];	// 1번에서 끝나고 1번 점수도 포함됨
			step[0] = 1;
			que.add(0);

			// 최상위(1번)부터 N까지 탐색하며 점수 누적합과 단계를 구하기
			while (!que.isEmpty()) {
				int now = que.poll();
				if (visited[now])
					continue;
				visited[now] = true;
				for (int next : map.get(now)) {
					int sumNext = sum[now] + cost[next];
					if (sum[next] != 0 && sum[next] <= sumNext)
						continue;
					sum[next] = sumNext;
					step[next] = step[now] + 1;
					que.add(next);
				}
			}

			int msum = 0;	// 내 점수의 총합
			int rsum = 0;	// 리아 점수의 총합
			for (int q = 0; q < Q; q++) {
				st = new StringTokenizer(br.readLine());
				int m = Integer.parseInt(st.nextToken()) - 1;
				int r = Integer.parseInt(st.nextToken()) - 1;

				// 가장 아래에 있는 지점부터 차례로 탐색하여 공통 조상 찾기
				int maxStep = step[m] > step[r] ? step[m] : step[r];
				int mpar = m;
				int rpar = r;
				while (maxStep >= 0) {
					if (mpar == rpar) {
						break;
					}
					if (maxStep <= step[m]) {
						mpar = parent[mpar];
					}
					if (maxStep <= step[r]) {
						rpar = parent[rpar];
					}
					maxStep--;
				}

				// 공통조상에 늦게 도착한 쪽의 공통조상 점수 제거
				// (나중에 지나가는 공은 그 지점의 점수를 얻을 수 없다)
				int mpoint = sum[m];
				int rpoint = sum[r];
				// 1.1. 두 수의 단계가 같은 경우: 공통조상부터는 둘다 점수X
				// (공이 이동하다가 한 지점에 동시 도착하면 공이 부딪혀 게임판 밖으로 나가기)
				if (step[m] == step[r]) {
					mpoint -= sum[mpar];
					rpoint -= sum[rpar];
				}
				// 1.1. 두 수의 단계가 다른 경우: 단계가 많은 쪽이 공통조상부터 점수X
				if (step[m] > step[r]) {
					mpoint -= sum[mpar];
				}
				if (step[m] < step[r]) {
					rpoint -= sum[rpar];
				}

				msum += mpoint;
				rsum += rpoint;
			}
			bw.write("#" + (t + 1) + " " + msum + " " + rsum + "\n");
		}
		bw.close();
	}

}

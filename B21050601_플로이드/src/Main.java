import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class BusInfo {
	int e;	// 목적지
	int c;	// 비용

	public BusInfo(int end, int cost) {
		e = end;
		c = cost;
	}
}

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
//		br = new BufferedReader(new FileReader("in"));
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		ArrayList<ArrayList<BusInfo>> map = new ArrayList<ArrayList<BusInfo>>();
		Queue<Integer> que = new LinkedList<Integer>();
		int[] visited = new int[N];
		int[][] cost = new int[N][N];
		for (int n = 0; n < N; n++) {
			map.add(new ArrayList<>());
		}
		for (int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken()) - 1;
			int e = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken());
			map.get(s).add(new BusInfo(e, c));
		}

		// 경로 탐색
		for (int n = 0; n < N; n++) {
			que.add(n);	/// 시작점
			while (!que.isEmpty()) {
				int now = que.poll();	// 출발점
				for (int i = 0; i < map.get(now).size(); i++) {	// 출발점에서 갈 수 잇는 모든 도착점 탐색
					BusInfo next = map.get(now).get(i);	//도착점
					if (next.e == n)	// 시작점일 경우 안 감
						continue;
					// 탐색한 적이 있는 경우, 출발점->도착점 비용이 더 큰 경우 패스
					if (visited[next.e] != 0 && visited[next.e] <= visited[now] + next.c)	
						continue;
					visited[next.e] = visited[now] + next.c;	// 최소비용 업데이트
					que.add(next.e);
				}
			}
			cost[n] = visited;	// 시작점A->도시B 의 최소비용
			visited = new int[N];
		}

		for (int[] c : cost) {
			for (int cc : c) {
				bw.write(cc + " ");
			}
			bw.write("\n");
		}
		bw.close();

	}

}

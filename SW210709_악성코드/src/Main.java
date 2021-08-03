import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static int T;
	static int N;
	static int M;
	static int K;
	static int[] parent;
	static boolean[] virusYN;
	static int[] group;
	static int[] virus;

	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new FileReader("in"));
		T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());

			virusYN = new boolean[N]; // 각 노드의 바이러스 유무
			group = new int[N]; // 그룹별 컴퓨터 수
			virus = new int[N]; // 그룹별 바이러스 수
			parent = new int[N]; // 각노드의 부모노드 저장
			// 같은 네트워크끼리 그룹화
			for (int n = 0; n < N; n++) {
				parent[n] = n;
			}

			for (int m = 0; m < M; m++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken()) - 1;
				int b = Integer.parseInt(st.nextToken()) - 1;
				Union(a, b);
			}

			// 각 노드별 버그 여부 저장
			st = new StringTokenizer(br.readLine());
			for (int k = 0; k < K; k++) {
				int a = Integer.parseInt(st.nextToken()) - 1;
				virusYN[a] = true;
			}

			for (int n = 0; n < N; n++) {
				// 소속 네트워크 찾기
				int apar = Find(n);
				// 같은 네트워크 내 컴퓨터 수
				group[apar]++;
				// 같은 네트워크 내 버그 수
				if (virusYN[n])
					virus[apar]++;
			}

			// 버그 계산
			// 3회 내에 치료해야함
			// 그룹별 버그 케이스
			// 일반적인 케이스
			// 3 2 1 / 1 2 2 / 0 / 2 2 2
			// 특이 케이스 (택1이 필요)
			// 1 1 2 / 1 1 1

			// 바이러스가 없는 컴퓨터 수
			int safe = N;
			// 일반적인 케이스 처리를 위한 그룹별 최대수
			int three = 0, two = 0, one = 0;
			// 특이케이스용 그룹별 컴퓨터 수 (reverseOrder 역순 정렬)
			Queue<Integer> oneGroup = new PriorityQueue<>(Comparator.reverseOrder());
			for (int n = 0; n < N; n++) {
				// 그룹이 없을경우 Pass
				if (group[n] == 0)
					continue;
				// 바이러스가 없는 컴퓨터 수
				if (virus[n] > 0) {
					safe -= group[n];
				}
				// 한 그룹내에 3개가 넘어가면 가망 없음
				if (virus[n] > 3) {
					continue;
				}
				// 각 바이러스 개수별 가장 컴퓨타 수가 많은 그룹
				if (virus[n] == 3)
					three = (three > group[n]) ? three : group[n];
				if (virus[n] == 2)
					two = (two > group[n]) ? two : group[n];
				if (virus[n] == 1) {
					one = (one > group[n]) ? one : group[n];
					// 특이케이스 처리를 위해 바이러스가 1인 모든 그룹 추가
					oneGroup.add(group[n]);
				}
			}

			// 일반적인 케이스 치료할 그룹 선정
			// 3 vs 2+1
			int maxCnt = (three > (two + one) ? three : two + one);
			// 특이 케이스 처리
			// 바이러스 수가 1인 그룹이 1개 초과일 경우
			if (oneGroup.size() > 1) {
				// 112
				int a = oneGroup.poll();
				int b = oneGroup.poll();
				maxCnt = (maxCnt > a + b) ? maxCnt : a + b;

				if (!oneGroup.isEmpty()) {
					// 111
					int c = oneGroup.poll();
					maxCnt = (maxCnt > a + b + c) ? maxCnt : a + b + c;
				}
			}

			// 바이러스가 없는 컴퓨터 수 + 치료한 컴퓨터 수
			int ans = safe + maxCnt;
			bw.write("#" + (t + 1) + " " + ans + "\n");
		}
		bw.close();
	}

	// a와 b가 속한 그룹 병합
	private static void Union(int a, int b) {
		int apar = Find(a);
		int bpar = Find(b);
		if (apar == bpar)
			return;
		parent[bpar] = apar;
	}

	// a가 속한 그룹을 찾고 속도 최적화를 위해 결과 저장
	private static int Find(int a) {
		if (parent[a] == a)
			return a;
		return parent[a] = Find(parent[a]);
	}

}

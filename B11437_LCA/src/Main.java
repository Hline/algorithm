import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

class Node {
	int t;
	int c;

	public Node(int to, int cost) {
		t = to;
		c = cost;
	}

}

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;

	public static void main(String[] args) throws Exception {
//		br = new BufferedReader(new FileReader("in"));
		int N = Integer.parseInt(br.readLine());
		int[] step = new int[N];
		int[] parent = new int[N];
		ArrayList<ArrayList<Integer>> map = new ArrayList<ArrayList<Integer>>();
		for (int i = 0; i < N; i++) {
			map.add(new ArrayList<Integer>());
		}

		// �ֻ����� 1������ ���� ���� �����̶�� ���� ����
		// ���� ���ؿ��� ���Ϸ��� ���� ���õ� ������ ��� �ִ��� �˾ƾ���
		// �������� Ž���� ������ �ð��ʰ��� �߱� ������ �̸� �����ص� �ʿ䰡 ����
		// ������ �մ� ���������� ���ʴ�� �־����� ������ ���� ������ ���� �޾�
		// Ž���� ���� ������ Ȯ���ؾ���
		// �θ�-�ڽ� ����� 1���� ���� ����Ǵ� �����̹Ƿ� �ϴ� ��������� ��� ����
		for (int i = 0; i < N - 1; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken()) - 1;
			map.get(a).add(b);
			map.get(b).add(a);
		}

		// �ش� ����� �ܰ�, �θ�-�ڽ� ���� ����
		boolean[] visited = new boolean[N];
		Queue<Node> que = new LinkedList<Node>();
		step[0] = 1;
		que.add(new Node(0, step[0]));
		
		while (!que.isEmpty()) {
			Node now = que.poll();
			if (visited[now.t])
				continue;
			visited[now.t] = true;
			for (int m : map.get(now.t)) {
				Node next = new Node(m, now.c + 1);
				// �̹� ���谡 ������ ��� �н�
				if (step[next.t] > 0 && step[next.t] <= next.c)
					continue;
				step[next.t] = next.c;
				parent[next.t] = now.t;
				que.add(next);
			}
		}

		int M = Integer.parseInt(br.readLine());
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken()) - 1;
			// ���� �Ʒ� �ܰ���� ���ܰ�� Ž��
			int maxStep = step[a] > step[b] ? step[a] : step[b];
			int apar = a;
			int bpar = b;
			for (int j = maxStep; j >= 0; j--) {
				if (apar == bpar) {
					break;
				}
				if (j <= step[a]) {
					apar = parent[apar];
				}
				if (j <= step[b]) {
					bpar = parent[bpar];
				}
			}
			bw.write((apar + 1) + "\n");
		}
		bw.close();

	}

}

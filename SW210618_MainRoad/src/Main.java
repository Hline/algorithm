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

import java.io.BufferedReader;

class Node {
	int t;	// ����
	int p;	// �α���
	int l;	// �Ÿ�

	public Node(int to, int pop, int len) {
		t = to;
		p = pop;
		l = len;
	}
}

class KeyValue implements Comparable<KeyValue> {
	int k;
	int v;

	public KeyValue(int key, int value) {
		k = key;
		v = value;
	}

	@Override
	public int compareTo(KeyValue r) {
		if (v > r.v)
			return -1;
		if (v < r.v)
			return 1;
		return 0;
	}
}

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;

	static int T;
	static ArrayList<ArrayList<Node>> map;
	static int N;
	static boolean[] mainYN;
	static int[] parent;
	static int[] len;
	static int[] cal;
	static boolean[] calYN;

	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new FileReader("in"));
		T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			N = Integer.parseInt(br.readLine());
			map = new ArrayList<ArrayList<Node>>();
			for (int n = 0; n < N; n++) {
				map.add(new ArrayList<Node>());
			}
			for (int n = 0; n < N - 1; n++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken()) - 1;
				int b = Integer.parseInt(st.nextToken()) - 1;
				int pop = Integer.parseInt(st.nextToken());
				int len = Integer.parseInt(st.nextToken());
				map.get(a).add(new Node(b, pop, len));
				map.get(b).add(new Node(a, pop, len));
			}
			mainYN = new boolean[N];
			parent = new int[N];

			// 1. ������ �������� ���� �հ� Ž��
			int start = 0;
			int end = CalPop(start);

			// 2. ���� �հ����� ���� �հ� Ž��(���� �� ���� ã��)
			mainYN = new boolean[N];
			parent = new int[N];
			start = CalPop(end);

			// ��� üũ
			mainYN[start] = true;
			mainYN[end] = true;
			checkMainYN(start, end);

			// �Ÿ� ���
			len = new int[N];
			cal = new int[N];
			calYN = new boolean[N];
			len = CalLen(start);
			int totLen = 0;
			for (int i = 0; i < N; i++) {
				if (mainYN[i])
					continue;
				Find(i);
				totLen += cal[i];
			}
			bw.write("#" + (t + 1) + " " + totLen + "\n");
		}
		bw.close();
	}

	// ���� �ε� �������� Ȯ��
	private static void checkMainYN(int start, int end) {
		int next = start;

		while (true) {
			mainYN[parent[next]] = mainYN[next];
			next = parent[next];
			if (next == start || next == end)
				return;
		}
	}

	// ���� �ε带 ã�Ƽ� �Ÿ��� ���
	private static int Find(int n) {
		// ���ηε��� ��� -�Ÿ���
		if (mainYN[n])
			return -len[n];

		// ���ηε� ���� ������ ã��
		int par = Find(parent[n]);
		// �Ÿ��� ����� �ȵȰ��
		if (!calYN[n]) {
			calYN[n] = true;
			cal[n] = len[n] + par;
		}
		return par;
	}

	// �����α��� ���� �Ÿ� ���
	private static int CalPop(int start) {
		return CalPop(start, -1);
	}

	private static int CalPop(int start, int end) {
		int maxKey = 0;
		int maxValue = 0;
		boolean[] visited = new boolean[N];
		int[] dist = new int[N];
		Queue<KeyValue> que = new LinkedList<KeyValue>();
		dist[start] = Integer.MAX_VALUE;
		que.add(new KeyValue(start, 0));
		while (!que.isEmpty()) {
			KeyValue now = que.poll();
			if (visited[now.k])
				continue;
			visited[now.k] = true;
			if (end == now.k)
				return maxKey;
			for (int i = 0; i < map.get(now.k).size(); i++) {
				int kNext = map.get(now.k).get(i).t;
				int vNext = map.get(now.k).get(i).p;
				if (visited[kNext])
					continue;
				if (dist[kNext] >= now.v + vNext)
					continue;
				dist[kNext] = now.v + vNext;
				que.add(new KeyValue(kNext, dist[kNext]));
				parent[kNext] = now.k;
				if (dist[kNext] >= maxValue) {
					maxValue = dist[kNext];
					maxKey = kNext;
				}
			}
		}
		return maxKey;
	}

	// �� ������ �Ÿ� ���
	private static int[] CalLen(int start) {
		boolean[] visited = new boolean[N];
		int[] dist = new int[N];
		Queue<KeyValue> que = new LinkedList<KeyValue>();
		dist[start] = 0;
		que.add(new KeyValue(start, dist[start]));
		while (!que.isEmpty()) {
			KeyValue now = que.poll();
			if (visited[now.k])
				continue;
			visited[now.k] = true;
			for (int i = 0; i < map.get(now.k).size(); i++) {
				int kNext = map.get(now.k).get(i).t;
				int vNext = map.get(now.k).get(i).l;
				if (visited[kNext])
					continue;
				dist[kNext] = dist[now.k] + vNext;
				que.add(new KeyValue(kNext, dist[kNext]));
			}
		}
		return dist;
	}
}
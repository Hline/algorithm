import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
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
		br = new BufferedReader(new FileReader("in"));
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());	// ���ü�
		int M = Integer.parseInt(st.nextToken());	// �ܹ��� ���μ�
		int K = Integer.parseInt(st.nextToken());	// K��° �ִܰŸ�
		ArrayList<ArrayList<Node>> map = new ArrayList<ArrayList<Node>>();
		PriorityQueue<Integer>[] costList = new PriorityQueue[N];	//���ú� �ִܰŸ� ����
		for (int n = 0; n < N; n++) {
			map.add(new ArrayList<>());
			// K��° �������� ����, K��° ���� ����ϹǷ� ��������
			costList[n] = new PriorityQueue<>(Collections.reverseOrder());
		}
		for (int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken());
			map.get(a).add(new Node(b, c));
		}
		Queue<Node> que = new LinkedList<Node>();
		// ������ ���: i�� ���ÿ��� i�� ���÷� ���� �ִܰ�δ� 0
		costList[0].add(0);
		// 1�� ���ô� ���۵���
		que.add(new Node(0, 0));

		while (!que.isEmpty()) {
			Node now = que.poll();

			for (int i = 0; i < map.get(now.t).size(); i++) {
				Node next = map.get(now.t).get(i);
				int cost = now.c + next.c;
				// K��° �ִܰŸ��� ���� �������� Ž��
				if (costList[next.t].size() >= K) {
					if (cost >= costList[next.t].peek())
						continue;
					// �������̽�: ���±��� ���� K��° �ִܰŸ����� ���� ��� 
					//			���� ū���� ���� �߰�
					else {
						costList[next.t].poll();
					}
				}
				costList[next.t].add(cost);
				que.add(new Node(next.t, cost));
			}
		}
		int de = 1;	// ������ �ߴ��� �ڵ�
		for (int i = 0; i < N; i++) {
			if (costList[i].size() == 0 || costList[i].size() < K) {
				bw.write(-1 + "\n");
				continue;
			}
			bw.write(costList[i].poll() + "\n");
		}
		bw.close();
	}

}

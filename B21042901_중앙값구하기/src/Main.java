import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static PriorityQueue<Integer> minList;
	static PriorityQueue<Integer> maxList;

	public static void main(String[] args) throws Exception {
//		br = new BufferedReader(new FileReader("in"));
		int T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			int M = Integer.parseInt(br.readLine());
			minList = new PriorityQueue<Integer>(Collections.reverseOrder());
			maxList = new PriorityQueue<Integer>();
			bw.write((M / 2 + 1) + " ");
			for (int m = 0; m < M; m++) {
				if (m % 10 == 0)	// ���ڰ� 10���� ǥ��
					st = new StringTokenizer(br.readLine());
				if (m % 20 == 0)	// 10��° Ȧ�� ������ �ٹٲ� (Ȧ��, ¦�� ���ϸ� 20��°)
					bw.write("\n");
				if (m % 2 == 0)		// �� que�� ������ �Է�
					minList.add(Integer.parseInt(st.nextToken()));
				else
					maxList.add(Integer.parseInt(st.nextToken()));
				swap();
				if (m % 2 == 0)	// Ȧ������ ǥ�� (0���� �����ϹǷ� ¦��)
					bw.write(minList.peek() + " ");
			}
			bw.write("\n");
			int de = 1;
		}
		bw.close();

	}

	private static void swap() {
		// TODO Auto-generated method stub
		if (maxList.isEmpty())
			return;
		int a = minList.peek();
		int b = maxList.peek();
		if (a > b) {
			a = minList.poll();
			b = maxList.poll();
			maxList.add(a);
			minList.add(b);
		}
	}
}

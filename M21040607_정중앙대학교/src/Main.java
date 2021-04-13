import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;

	public static void main(String[] args) throws Exception {
//		br = new BufferedReader(new FileReader("���߾� ���б�\\1.in"));
		br = new BufferedReader(new FileReader("1.in"));
		int N = Integer.parseInt(br.readLine());
		PriorityQueue<Integer> spq = new PriorityQueue<Integer>(Collections.reverseOrder());
		PriorityQueue<Integer> lpq = new PriorityQueue<Integer>();
		spq.add(500);
		for (int n = 0; n < N; n++) {
			st = new StringTokenizer(br.readLine());
			// �� que�� �����ϰ� �ϳ���
			// spq�� �׻� �ϳ��� ����
			lpq.add(Integer.parseInt(st.nextToken()));
			spq.add(Integer.parseInt(st.nextToken()));

			// ����
			while (spq.peek() > lpq.peek()) {
				int l = lpq.poll();
				int s = spq.poll();
				spq.add(l);
				lpq.add(s);
			}

			// �����ʿ� �ִ� ���� �߰���
			int midVal = spq.peek();
			bw.write(midVal + "\n");

		}
		bw.close();
	}

}

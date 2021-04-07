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
//		br = new BufferedReader(new FileReader("정중앙 대학교\\1.in"));
		br = new BufferedReader(new FileReader("1.in"));
		int N = Integer.parseInt(br.readLine());
		PriorityQueue<Integer> spq = new PriorityQueue<Integer>(Collections.reverseOrder());
		PriorityQueue<Integer> lpq = new PriorityQueue<Integer>();
		spq.add(500);
		for (int n = 0; n < N; n++) {
			st = new StringTokenizer(br.readLine());
			// 두 que에 공평하게 하나씩
			// spq는 항상 하나가 많음
			lpq.add(Integer.parseInt(st.nextToken()));
			spq.add(Integer.parseInt(st.nextToken()));

			// 정렬
			while (spq.peek() > lpq.peek()) {
				int l = lpq.poll();
				int s = spq.poll();
				spq.add(l);
				lpq.add(s);
			}

			// 많은쪽에 있는 값이 중간값
			int midVal = spq.peek();
			bw.write(midVal + "\n");

		}
		bw.close();
	}

}

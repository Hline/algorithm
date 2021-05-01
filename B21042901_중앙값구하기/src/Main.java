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
				if (m % 10 == 0)	// 숫자가 10개씩 표시
					st = new StringTokenizer(br.readLine());
				if (m % 20 == 0)	// 10번째 홀수 다음에 줄바꿈 (홀수, 짝수 합하면 20번째)
					bw.write("\n");
				if (m % 2 == 0)		// 두 que에 번갈아 입력
					minList.add(Integer.parseInt(st.nextToken()));
				else
					maxList.add(Integer.parseInt(st.nextToken()));
				swap();
				if (m % 2 == 0)	// 홀수마다 표시 (0부터 시작하므로 짝수)
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

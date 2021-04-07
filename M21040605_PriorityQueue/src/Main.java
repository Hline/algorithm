import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
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
		// TODO Auto-generated method stub
		br = new BufferedReader(new FileReader("Priority Queue\\1.in"));
		int N = Integer.parseInt(br.readLine());
		PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			switch (st.nextToken()) {
			case "push":
				pq.add(Integer.parseInt(st.nextToken()));
				break;
			case "pop":
				int b = Integer.parseInt(st.nextToken());
				String m = "";
				for (int j = 0; j < b; j++)
					m += pq.poll() + " ";
				bw.write(m + "\n");
				break;
			case "add":
				pq.add(pq.poll() + Integer.parseInt(st.nextToken()));
				break;
			}
		}
		bw.close();
	}

}

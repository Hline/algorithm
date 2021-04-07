import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;

	public static void main(String[] args) throws Exception, Exception {
		// TODO Auto-generated method stub
//		br = new BufferedReader(new FileReader("Ugly Number\\4.in"));
		int N = Integer.parseInt(br.readLine());

		st = new StringTokenizer(br.readLine());
		int[] n = new int[N];
		int maxN = 0;
		for (int i = 0; i < N; i++) {
			n[i] = Integer.parseInt(st.nextToken());
			if (maxN < n[i])
				maxN = n[i];
		}

		int[] ugly = { 2, 3, 5 };
		long[] rank = new long[maxN + 1];
		long prev = 0;
		PriorityQueue<Long> pq = new PriorityQueue<Long>();
		pq.add((long) 1);
		for (int i = 0; i <= maxN; i++) {
			long root = pq.poll();
			if (prev == root) {	// 중복검증
				i--;
				continue;
			}
			rank[i] = root;
			prev = root;

			for (int j = 0; j < ugly.length; j++) {
				long child = root * ugly[j];
				pq.add(child);
			}
		}

		for (int nn : n) {
			bw.write(rank[nn - 1] + " ");
		}
		bw.close();
	}

}

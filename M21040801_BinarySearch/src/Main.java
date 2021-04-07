import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		int n = Integer.parseInt(br.readLine());
		int[] num = new int[n];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			num[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(num);

		int k = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < k; i++) {
			int kk = Integer.parseInt(st.nextToken());
			int l = 0;
			int r = n - 1;
			int m = (l + r) / 2;
			boolean search = false;
			while (l <= r) {
				if (kk > num[m])
					l = m + 1;
				if (kk < num[m])
					r = m - 1;
				if (kk == num[m]) {
					search = true;
					bw.write("O");
					break;
				}
				if (search)
					break;
				m = (l + r) / 2;
			}
			if (!search)
				bw.write("X");
		}
		bw.close();
	}

}

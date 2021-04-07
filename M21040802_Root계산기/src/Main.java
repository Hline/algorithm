import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		long n = Integer.parseInt(br.readLine());
		long l = 1;
		long r = n;
		long root = n;
		while (l <= r) {
			long m = (l + r) / 2;
			if (n < m * m)
				r = m - 1;
			if (n > m * m)
				l = m + 1;
			root = m;
			if (n == m * m)
				break;
			if (r < l) {
				root = r;
			}
		}

		bw.write(root + "");
		bw.close();
	}

}

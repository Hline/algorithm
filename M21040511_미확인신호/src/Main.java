import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;

	public static void main(String[] args) throws Exception, Exception {
		// TODO Auto-generated method stub

		int T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			int M = Integer.parseInt(br.readLine());
			String txt = br.readLine();
			int[] match = new int[256];
			Arrays.fill(match, 0);
			boolean chk = false;
			for (int m = 0; m < M; m++) {
				if (++match[txt.charAt(m)] == 2) {
					chk = true;
					break;
				}
			}
			if (!chk)
				for (int i = 0; i < txt.length() - M; i++) {
					--match[txt.charAt(i)];
					if (++match[txt.charAt(i + M)] == 2) {
						{
							chk = true;
							break;
						}
					}

				}
			if (chk)
				bw.write("#" + (t + 1) + " " + "FAIL\n");
			else
				bw.write("#" + (t + 1) + " " + "PASS\n");
		}
		bw.close();

	}

}

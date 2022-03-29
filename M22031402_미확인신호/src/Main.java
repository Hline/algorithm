import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static int T;
	static int M;
	static String msg;
	//key - char, value - °³¼ö
	static int[] dic; 

	public static void main(String[] args) throws Exception {
//		br = new BufferedReader(new FileReader("in"));
		T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			M = Integer.parseInt(br.readLine());
			msg = br.readLine();
			int start = 0;
			int end = M;
			dic = new int[130];
			String res = "PASS";
			for (int i = start; i < end; i++) {
				int idx = (int) msg.charAt(i);
//				System.out.println(msg.charAt(i));
				dic[idx]++;
				if (dic[idx] >= 2) {
					res = "FAIL";
					break;
				}
			}

			for (int i = end; i < msg.length() - 1; i++) {
				int pre = (int) msg.charAt(end);
//				System.out.println(msg.charAt(end));
				dic[pre]++;
				end++;
				int aft = (int) msg.charAt(start);
//				System.out.println(msg.charAt(start));
				dic[aft]--;
				start++;
				if (pre == aft) {
					if (dic[aft] >= 2) {
						res = "FAIL";
						break;
					}
				} else if (dic[pre] >= 2) {
					res = "FAIL";
					break;
				}
			}

			System.out.println("#" + (t + 1) + " " + res);
		}
	}

}

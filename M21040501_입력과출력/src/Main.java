import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int type = Integer.parseInt(br.readLine());
		int n;
		switch (type) {
		case 1:
			n = Integer.parseInt(br.readLine());
			StringTokenizer st = new StringTokenizer(br.readLine());
			int sum = 0;
			long mul = 1;
			for (int i = 0; i < n; i++) {
				int num = Integer.parseInt(st.nextToken());
				sum += num;
				mul *= num;
			}
			bw.write(sum + " " + mul);
			break;
		case 2:
			n = Integer.parseInt(br.readLine());
			String max = "";
			String min = "";
			for (int i = 0; i < n; i++) {
				String txt = br.readLine();
				if (txt.length() >= max.length())
					max = txt;
				if (min.length() == 0)
					min = txt;
				else if (txt.length() <= min.length())
					min = txt;
			}
			bw.write(max + "\n" + min);
			break;
		case 3:
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			int minNum = Integer.MAX_VALUE;
			int cnt=1;
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < x; j++) {
					int num = Integer.parseInt(st.nextToken());
					if (minNum > num) {
						minNum = num;
						cnt = 1;
					} else if (minNum == num)
						cnt++;
				}
			}
			bw.write(minNum + "");
			bw.write("\r\n");
			bw.write(cnt + "°³");
			break;
		}
		bw.close();
	}

}

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

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		StringTokenizer st = new StringTokenizer(br.readLine());
		int row = Integer.parseInt(st.nextToken());
		int col = Integer.parseInt(st.nextToken());

		int[] line = new int[row];
		Arrays.fill(line, 0);
		for (int i = 0; i < row; i++) {
			String num = br.readLine();
			for (char n : num.toCharArray()) {
				if (n == '1')
					line[i]++;
			}
		}

		for (int i = 0; i < row; i++) {
			bw.write(line[i] + "");
			if (i < row - 1)
				bw.write("\n");
		}
		bw.close();
	}

}

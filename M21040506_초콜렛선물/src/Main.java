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

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		String alphas = br.readLine();
		int[] dat = new int[50];
		Arrays.fill(dat, 0);
		for (char a : alphas.toCharArray()) {
			dat[a - 'A']++;
		}
		String result = "";
		for (int i = 0; i < dat.length; i++)
			if (dat[i] > 0)
				result += (char) (i + 'A');

		bw.write(result);
		bw.close();
	}

}

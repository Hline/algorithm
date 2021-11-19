
public class Main {
//	static int[] arr = { 0, 1, 3, 7, 9, 11, 13 };
	static int[] arr = { 0, 1, 3, 3, 3, 11, 13 };
//	static int[] arr= {1, 2, 2, 3, 3, 3, 4, 6};

// 이진탐색
	public static void main(String[] args) {
		System.out.println(BinarySearch(11));	//arr(5)=11
		System.out.println();
		System.out.println(BinarySearch(3));	//arr(3)=3
		System.out.println(LowerBound(3));		//arr(2)=3
		System.out.println(UpperBound(3));		//arr(5)=11
		System.out.println();
		System.out.println(LowerBound(11));		//arr(5)=11
		System.out.println(UpperBound(11));		//arr(6)=13
		System.out.println();
		System.out.println(UpperBound(3)-LowerBound(3));	//3 (3이 3개)
	}

	// 이진탐색: 일치하는 숫자 찾기
	private static int BinarySearch(int target) {
		int l = 0;
		int r = arr.length - 1;
		int mid = 0;
		while (l <= r) {
			mid = (l + r) / 2;
			if (arr[mid] == target)
				return mid;
			// l mid target r
			if (arr[mid] < target)
				l = mid + 1;
			else
				r = mid - 1;
		}
		return mid;
	}

	// Lower Bound: 일치하는 숫자가 처음 나타나는 지점
	private static int LowerBound(int target) {
		int l = 0;
		int r = arr.length;
		int mid = 0;
		while (l < r) {
			mid = (l + r) / 2;
			// l mid target r
			if (arr[mid] < target)
				l = mid + 1;
			else
				r = mid;
		}
		return r;
	}

	// Upper Bound: 일치하는 숫자 다음 수 나타나는 지점
	private static int UpperBound(int target) {
		int l = 0;
		int r = arr.length;
		int mid = 0;

		while (l < r) {
			mid = (l + r) / 2;
			if (arr[mid] <= target) {
				l = mid + 1;
			} else {
				r = mid;
			}
		}
		return r;
	}

}

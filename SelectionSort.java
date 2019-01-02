import java.util.ArrayList;

public class SelectionSort {
	static void view(ArrayList<Integer> A) {
		for(int a:A) {
			System.out.print(a+" ");
		}
		System.out.println("\n");
	}

	public static void main(String[] args) {
		ArrayList<Integer> A=new ArrayList<Integer>();
		int save,min;
		A.add(40);
		A.add(10);
		A.add(50);
		A.add(90);
		A.add(20);
		
		System.out.print("정렬 전 : ");
		view(A);
		for(int i=0;i<=A.size()-2;i++) {
			min=i;
			for(int j=i+1;j<=A.size()-1;j++) {
				if(A.get(j)<A.get(min)) {
					min=j;
				}
			}
			save=A.get(i);
			A.set(i, A.get(min));
			A.set(min, save);
			System.out.print(i+1+"단계 "+A.get(i)+"<->"+A.get(min)+"자리 바꿈 : ");
			view(A);
		}

	}

}

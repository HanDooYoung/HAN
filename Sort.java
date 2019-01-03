import java.util.Scanner;

public class Sort {
	static void view(int A[]) {
		for(int a:A) {
			System.out.print(a+" ");
		}
		System.out.println("\n");
	}
	
	static int[] BubbleSort(int A[]) {
		int save;
		
		System.out.println("버블 정렬 실행!!\n");
		System.out.print("정렬 전 : ");
		view(A);
		for(int pass=1;pass<=A.length-1;pass++) {
			for(int i=1;i<=A.length-pass;i++) {
				save=A[i];
				if(A[i-1]>A[i]) {
					A[i]=A[i-1];
					A[i-1]=save;
				}
			}
			System.out.print(pass+"pass : ");
			view(A);
		}
		return A;
	}
	
	static int[] SelectionSort(int A[]) {
		int save,min;
		
		System.out.println("선택 정렬 실행!!\n");
		System.out.print("정렬 전 : ");
		view(A);
		for(int i=0;i<=A.length-2;i++) {
			min=i;
			for(int j=i+1;j<=A.length-1;j++) {
				if(A[j]<A[min]) {
					min=j;
				}
			}
			save=A[i];
			A[i]=A[min];
			A[min]=save;
			if(A[i]!=A[min])
				System.out.print(i+1+"단계 "+A[i]+"<->"+A[min]+" 자리 바꿈 : ");
			else
				System.out.print(i+1+"단계 :");
			view(A);
		}
		return A;
	}
	
	static int[] InsertionSort(int A[]) {
		int CurrentElement,j;
		
		System.out.println("삽입 정렬 실행!!\n");
		System.out.print("정렬 전 : ");
		view(A);
		for(int i=1;i<=A.length-1;i++) {
			CurrentElement=A[i];
			j=i-1;
			while(j>=0&&A[j]>CurrentElement) {
				A[j+1]=A[j];
				j=j-1;
			}
			A[j+1]=CurrentElement;
			System.out.print("CurrentElement : "+CurrentElement+", "+i+"단계 : ");
			view(A);
		}
		return A;
	}
	
	static int[] DataInsert() {
		Scanner sc=new Scanner(System.in);
		int arr;
		System.out.println("데이터 갯수 : ");
		arr=sc.nextInt();
		int A[]=new int[arr];
		
		for(int i=0;i<A.length;i++) {
			System.out.println(i+1+"번째 데이터 값 입력 : ");
			A[i]=sc.nextInt();
		}
		return A;
	}

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		char choice;
		while(true) {
			System.out.println("1(정렬 프로그램 시작) 2(종료)");
			choice=sc.next().charAt(0);
			switch(choice) {
			case '1':
				int A[];
				System.out.println("1(버블 정렬) 2(선택 정렬) 3(삽입 정렬)");
				choice=sc.next().charAt(0);
				A=DataInsert();
				switch(choice) {
				case '1':
					BubbleSort(A);
					break;
				case '2':
					SelectionSort(A);
					break;
				case '3':
					InsertionSort(A);
					break;
				default:
					System.out.println("잘 못 입력하셨습니다.\n프로그램 재시작");
				}
				break;
			case '2':
				return;
			default:
				System.out.println("잘 못 입력하셨습니다.");
			}
		}

	}

}

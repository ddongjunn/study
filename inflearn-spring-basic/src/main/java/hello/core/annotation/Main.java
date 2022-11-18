package hello.core.annotation;

import java.util.*;

public class Main {
    static int solution(String str1, String str2){
        int answer = 0;
        int[] arr = new int[26];
        char[] ch1 = str1.toCharArray();
        char[] ch2 = str2.toCharArray();

        for(char ch : ch1) arr[ch-97]++;
        for(char ch : ch2) arr[ch-97]--;
        for(int i : arr) answer += Math.abs(i);

        return answer;
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String str1 = scan.next();
        String str2 = scan.next();
        System.out.println(solution(str1, str2));
    }
}
/*
애너그램을 만드는 문제

처음에 2가지의 실수를했다.
1. 문자의 순서를 뒤집어서 애너그램을 만듬
    -> 이 문제를 해결하기 위해 알파벳 index배열을 만들어서 아스키코드 'a'(97)값을 빼고,
    첫번째 index는 더해주고, 두번째 index는 빼주는작업을했다.
2. 두개의 문자열의 길이가 같음 (StringOutOfIndex error)
    -> 하나의 for문으로 1번째 작업을해서 문제가 발생, 2개의 char배열을 만들어서 각각 for문을 돌았다.

해당 문제의 조건이 애너그램 관계이면서, 순서에 상관없이 문자열을 제거하여 애너그램 관계를 만들 수 있기 때문에,
두개의 문자중 겹치지 않는 부분을 찾아서 answer에 더해주었다.
값이 마이너스인 경우엔 Math.abs()함수를 사용해서 절대값으로 진행
 */
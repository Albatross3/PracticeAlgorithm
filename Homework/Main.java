package Homework;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int operandN, operatorN, sumN;
    static char[] input;
    static Stack<Character> operator = new Stack<>();
    static Stack<Character> operand = new Stack<>();

    public static void main(String[] args) throws IOException {
        // 입력 받기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        operandN = Integer.parseInt(st.nextToken());
        operatorN = Integer.parseInt(st.nextToken());
        sumN = Integer.parseInt(st.nextToken());
        input = new char[sumN];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < sumN; i++) {
            input[i] = st.nextToken().charAt(0);
        }

        // 입력된 명제 변수 종류 찾기 , Map 활용
        Map<Character, Integer> m = new HashMap<>();
        m.put('P', 0);
        m.put('Q', 0);
        m.put('R', 0);

        for (int i = 0; i < input.length; i++) {
            if (m.containsKey(input[i]) && m.get(input[i]) == 0) {
                m.put(input[i], 1);
            }
        }

        for (Character key : m.keySet()) {
            if (m.get(key) != 0) {
                System.out.print(key + " ");
            }
        }
        System.out.print("RESULT\n\n");

        // 명제변수 T/F 입력
        char[][] ex = new char[(int) Math.pow(2, operandN)][operandN];
        for (int i = 0; i < Math.pow(2, operandN); i++) {
            for (int j = 0; j < operandN; j++) {
                if ((i / (int) Math.pow(2, operandN - 1 - j)) %2 == 0) ex[i][j] = 'T';
                else ex[i][j] = 'F';
            }
        }
        // 사용되는 명제변수에 대해 true/false 대응
        Map<Character, Character> m2 = new HashMap<>();
        for(int x=0; x<ex.length; x++) {
            char[] finalInput= Arrays.copyOf(input,input.length);
            int index = 0;
            for (Character key : m.keySet()) {
                if (m.get(key) != 0) {
                    m2.put(key, ex[x][index]);
                    index++;
                }
            }
            // 명제 변수 T/F 출력
            for (Character key : m2.keySet()) {
                System.out.print(m2.get(key) + " ");
            }

            // 스택에 넣고 연산하기
            for (int i = 0; i < finalInput.length; i++) {
                if (m2.containsKey(finalInput[i])) {
                    finalInput[i] = m2.get(finalInput[i]);
                }
            }
            System.out.print(calculate(finalInput));
            if(x<ex.length-1) {
                System.out.println();
                System.out.println();
            }
        }

    }
    static char calculate (char[] arr){
        for(int i=0; i<arr.length; i++){
            if(arr[i]=='T' || arr[i]=='F') operand.push(arr[i]);
            else operator.push(arr[i]);

            // 연산자 ~이 들어오는 경우 명제가 들어올때까지 넘긴다
            if(arr[i]=='n') continue;
            // 연산자 ~이 존재하는 경우 -> 먼저 처리, 단일 연산자
            if(operator.size()>=1 && operand.size()>=1 && operator.peek()=='n') {
                operator.pop();
                char temp=operand.pop();
                if(temp=='T') temp='F';
                else temp='T';
                operand.push(temp);
            }

            // 다른 연산자들 처리
            if(operator.size()==1 && operand.size()==2){
                char temp=subCalculate(operator.pop(),operand.pop(),operand.pop());
                operand.push(temp);
            }

        }
        return operand.pop();
    }
    static char subCalculate(char operator, char operand2, char operand1) {
        boolean boolOperand1, boolOperand2;
        boolean temp;
        boolOperand1 = operand1 == 'T' ? true : false;
        boolOperand2 = operand2 == 'T' ? true : false;

        if (operator == 'a') temp = boolOperand1 && boolOperand2;
        else if (operator == 'o') temp = boolOperand1 || boolOperand2;
        else {
            if (operand1 == 'T' && operand2 == 'F') temp = false;
            else temp = true;
        }
        if (temp) return 'T';
        else return 'F';
    }

}

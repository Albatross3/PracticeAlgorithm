package Baekjoon.자료구조.스택.P3425;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    static GoStack goStack;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        while(true) {
            String command = br.readLine();
            if(command.equals("")) continue;
            if(command.equals("QUIT")) break;
            // 명령어 입력
            List<String> orders = new ArrayList<>();
            while (true) {
                if(command.equals("END")) break;
                orders.add(command);
                command = br.readLine();
            }
            // 각 입력값에 따른 명령어들 수행

            int N = Integer.parseInt(br.readLine());
            for (int i = 0; i < N; i++) {
                int v = Integer.parseInt(br.readLine());
                goStack = new GoStack();
                goStack.push(v);
                boolean isError = false;
                for (String order : orders) {
                    if (order.length() >= 5) {
                        String[] split = order.split(" ");
                        String c = split[0];
                        int n = Integer.parseInt(split[1]);
                        goStack.push(n);
                    } else {
                        if (order.equals("POP")) {
                            if(goStack.pop()==-1000000001) {
                                isError = true;
                                break;
                            }
                        } else if (order.equals("INV")) {
                            if(goStack.inv()==-1) {
                                isError = true;
                                break;
                            }
                        } else if (order.equals("DUP")) {
                            if(goStack.dup()==-1) {
                                isError = true;
                                break;
                            }
                        } else if (order.equals("SWP")) {
                            if(goStack.swp()==-1) {
                                isError = true;
                                break;
                            }
                        } else if (order.equals("ADD")) {
                            if(goStack.add()==-1) {
                                isError = true;
                                break;
                            }
                        } else if (order.equals("SUB")) {
                            if(goStack.sub()==-1) {
                                isError = true;
                                break;
                            }
                        } else if (order.equals("MUL")) {
                            if(goStack.mul()==-1) {
                                isError = true;
                                break;
                            }
                        } else if (order.equals("DIV")) {
                            if(goStack.div()==-1) {
                                isError = true;
                                break;
                            }
                        } else if (order.equals("MOD")) {
                            if(goStack.mod()==-1) {
                                isError = true;
                                break;
                            }
                        }

                    }
                }

                if(isError || goStack.size()!=1) {
                    sb.append("ERROR").append("\n");
                } else{
                    sb.append(goStack.pop()).append("\n");
                }

            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

}
class GoStack {
    int top;
    int[] stack;

    public GoStack() {
        top = -1;
        stack = new int[1000];
    }

    public int size() {
        if(top==-1) return 0 ;
        else return top + 1;
    }

    public void push(int x) {
        stack[++top] = x;
    }

    public int pop() {
        // 없는 경우 -> -1 이 error
        if (top == -1) {
            return -1000000001;
        }
        return stack[top--];
    }

    public int inv() {
        if(top==-1) return -1;
        int temp = stack[top--];
        stack[++top] = -temp;
        return 0;
    }

    public int dup() {
        if(top==-1) return -1;
        int temp = stack[top];
        stack[++top] = temp;
        return 0;
    }

    public int swp() {
        if(top<=0) return -1;
        int first = stack[top--];
        int second = stack[top--];
        stack[++top] = first;
        stack[++top] = second;
        return 0;
    }

    public int add() {
        if(top<=0) return -1;
        int first = stack[top--];
        int second = stack[top--];
        int newNum = first + second;
        if(Math.abs(newNum) > 1000000000) return -1;
        stack[++top] = newNum;
        return 0;
    }

    public int sub() {
        if(top<=0) return -1;
        int first = stack[top--];
        int second = stack[top--];
        int newNum = second - first;
        if(Math.abs(newNum) > 1000000000) return -1;
        stack[++top] = newNum;
        return 0;
    }

    public int mul() {
        if(top<=0) return -1;
        int first = stack[top--];
        int second = stack[top--];
        long newNum = (long)second * (long)first;
        if(Math.abs(newNum) > 100000000L) return -1;
        stack[++top] = (int)newNum;
        return 0;
    }

    public int div() {
        if(top<=0) return -1;
        int first = stack[top--];
        int second = stack[top--];
        if(first==0) return -1;
        int newNum = second / first;
        if(Math.abs(newNum) > 1000000000) return -1;
        stack[++top] = newNum;
        return 0;
    }

    public int mod() {
        if(top<=0) return -1;
        int first = stack[top--];
        int second = stack[top--];
        if(first==0) return -1;
        int newNum = second % first;
        if(Math.abs(newNum) > 1000000000) return -1;
        stack[++top] = newNum;
        return 0;
    }

}

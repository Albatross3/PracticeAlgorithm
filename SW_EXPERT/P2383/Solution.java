package SW_EXPERT.P2383;

import java.io.*;
import java.util.*;

public class Solution {
    static int T;
    static int N;
    static int[] cases;
    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("SW_EXPERT/P2383/input.txt"));
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        T=Integer.parseInt(br.readLine());
        for(int t=0; t<T; t++){
            N=Integer.parseInt(br.readLine());
            List<Person> p=new ArrayList<>();
            List<Stairs> s=new ArrayList<>();
            int studentNum=1;
            for(int i=0; i<N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    int temp = Integer.parseInt(st.nextToken());
                    if (temp == 1) p.add(new Person(studentNum++, i, j));
                    else s.add(new Stairs(i, j, temp));
                }
            }
            cases=new int[p.size()];

        }
    }
    public static void getCases(int level){

    }
}
class Person {
    int studentNumber;
    int i,j;
    int timestamp;
    public Person(int studentNumber,int i,int j){
        this.studentNumber=studentNumber;
        this.i=i;
        this.j=j;
        this.timestamp=0;
    }
}
class Stairs{
    int i,j;
    int length;
    Queue<Person> queue;

    public Stairs(int i, int j, int length) {
        this.i = i;
        this.j = j;
        this.length=length;
        this.queue = new Queue<Person>() {
            @Override
            public boolean add(Person person) {
                return false;
            }

            @Override
            public boolean offer(Person person) {
                return false;
            }

            @Override
            public Person remove() {
                return null;
            }

            @Override
            public Person poll() {
                return null;
            }

            @Override
            public Person element() {
                return null;
            }

            @Override
            public Person peek() {
                return null;
            }

            @Override
            public int size() {
                return 0;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public boolean contains(Object o) {
                return false;
            }

            @Override
            public Iterator<Person> iterator() {
                return null;
            }

            @Override
            public Object[] toArray() {
                return new Object[0];
            }

            @Override
            public <T> T[] toArray(T[] a) {
                return null;
            }

            @Override
            public boolean remove(Object o) {
                return false;
            }

            @Override
            public boolean containsAll(Collection<?> c) {
                return false;
            }

            @Override
            public boolean addAll(Collection<? extends Person> c) {
                return false;
            }

            @Override
            public boolean removeAll(Collection<?> c) {
                return false;
            }

            @Override
            public boolean retainAll(Collection<?> c) {
                return false;
            }

            @Override
            public void clear() {

            }
        };
    }
}

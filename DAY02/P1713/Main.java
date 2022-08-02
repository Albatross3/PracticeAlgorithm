package DAY02.P1713;

import java.io.FileNotFoundException;
import java.util.*;

public class Main {
    static int N,K;
    static Person[] people;

    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc=new Scanner(System.in);

        N=sc.nextInt();
        K=sc.nextInt();


        people=new Person[101];


        List<Person> list=new ArrayList<>();
        for(int k=0; k<K; k++){
            int num=sc.nextInt();
            if(people[num]==null){
                people[num]=new Person(num,0,0,false);
            }

            if(people[num].isIn==true){
                // 사진판에 있는 경우 -> count++
                people[num].count++;

            }else {
                // 사진판에 없는 경우 -> 하나 골라서 제거 후 새 후보 추가
                if(list.size()==N){
                    Collections.sort(list);
                    Person p=list.remove(0);
                    p.isIn=false;
                }
                people[num].count=1;
                people[num].isIn=true;
                people[num].timeStamp=k;
                list.add(people[num]);

            }
        }

        Collections.sort(list, new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                return Integer.compare(o1.num, o2.num);
            }
        });
        for(Person p: list){
            System.out.print(p.num+" ");
        }

    }
}

class Person implements Comparable<Person>{
    int num;
    int count;
    int timeStamp;
    boolean isIn;

    public Person(int num, int count, int timeStamp, boolean isIn) {
        this.num = num;
        this.count = count;
        this.timeStamp = timeStamp;
        this.isIn = isIn;
    }

    @Override
    public int compareTo(Person o) {
        int comp1=Integer.compare(count,o.count);
        if(comp1==0){
            // timeStamp 가 작은 쪽이 오래된 사진
            return Integer.compare(timeStamp,o.timeStamp);
        }
        return comp1;
    }

    @Override
    public String toString() {
        return "Person{" +
                "num=" + num +
                ", count=" + count +
                ", timeStamp=" + timeStamp +
                ", isIn=" + isIn +
                '}';
    }
}
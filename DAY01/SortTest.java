package DAY01;

import java.util.ArrayList;
import java.util.List;

public class SortTest {
    public static void main(String[] args) {
        Item item1=new Item(1,3,1);
        Item item2=new Item(2,2,3);
        Item item3=new Item(3,1,2);

        List<Item> list=new ArrayList<>();
        list.add(item1);
        list.add(item2);
        list.add(item3);

        System.out.println(list);
    }

}
class Item{
    int a;
    int b;
    int c;

    public Item(int a,int b,int c) {
        this.a=a;
        this.b=b;
        this.c=c;
    }
}


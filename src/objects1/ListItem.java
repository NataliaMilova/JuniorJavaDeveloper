package objects1;

/**
 * Created by evami on 18.10.17.
 */
public class ListItem {
    int value;
    ListItem next;

    public ListItem(int value){
        this.value = value;
    }

    public ListItem(int value, ListItem item){
        this.value = value;
        this.next = item;
    }
}

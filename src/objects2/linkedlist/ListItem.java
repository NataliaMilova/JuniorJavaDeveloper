package objects2.linkedlist;

/**
 * Created by evami on 22.10.17.
 */
public class ListItem {
    public Object value;
    public ListItem next;

    public ListItem(Object value){
        this.value = value;
    }

    public ListItem(Object value, ListItem item){
        this.value = value;
        this.next = item;
    }
}

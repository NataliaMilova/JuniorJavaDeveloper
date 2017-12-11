package streams2.print1;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * Created by evami on 17.11.17.
 */
public class SystemCommand implements Externalizable {

    private int number;
    private String name;
    private long timestamp;

    public SystemCommand(){

    }

    public SystemCommand(int number, String name, long timestamp) {
        this.number = number;
        this.name = name;
        this.timestamp = timestamp;
    }

    public long getTimestamp(){
        return this.timestamp;
    }

    public String getName(){
        return this.name;
    }

    public int getNumber(){
        return this.number;
    }

    public void setNumber(int number){
        this.number = number;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setTimestamp(long timestamp){
        this.timestamp = timestamp;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeInt(this.number);
        out.writeUTF(this.name);
        out.writeLong(this.timestamp);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        this.number = in.readInt();
        this.name = in.readUTF();
        this.timestamp = in.readLong();
    }

    @Override
    public String toString() {
        return "SystemCommand{" +
                "number=" + number +
                ", name='" + name + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}

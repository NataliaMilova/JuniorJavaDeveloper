package patterns.decorator.test.print2;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * Created by evami on 19.11.17.
 */
public class Command implements Externalizable {

    private String sender;
    private String name;

    public Command(String sender, String name) {
        this.sender = sender;
        this.name = name;
    }

    public Command(){
        this(null, null);
    }

    public String getName(){
        return this.name;
    }

    public String getSender(){
        return this.sender;
    }

    public void setSender(String sender){
        this.sender = sender;
    }

    public void setName(String name){
        this.name = name;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeUTF(this.name);
        out.writeUTF(this.sender);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        this.name = in.readUTF();
        this.sender = in.readUTF();
    }

    @Override
    public String toString() {
        return "Command{" +
                "sender='" + sender + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}

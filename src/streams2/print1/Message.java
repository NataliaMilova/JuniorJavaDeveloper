package streams2.print1;

import java.io.Serializable;

/**
 * Created by evami on 16.11.17.
 */
public class Message implements Serializable {

    private long timestamp;

    private String sender;

    private String text;


    public Message(long timestamp, String sender, String text) {
        this.timestamp = timestamp;
        this.sender = sender;
        this.text = text;
    }

    public Message() {
        this(0, null, null);
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    @Override
    public String toString() {
        return "Message{" +
                "timestamp=" + timestamp +
                ", sender='" + sender + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}


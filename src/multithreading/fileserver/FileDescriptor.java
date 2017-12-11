package multithreading.fileserver;

import java.io.Serializable;

/**
 * Created by evami on 26.11.17.
 */
public class FileDescriptor implements Serializable{
    private String name;
    private long size;

    public FileDescriptor() {
    }

    public FileDescriptor(String name, long size) {
        this.name = name;
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }
}

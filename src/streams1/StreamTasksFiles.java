package streams1;

import java.io.*;


/**
 * Created by evami on 13.11.17.
 */
public class StreamTasksFiles {

    public static void copyFile(File source, File output) throws IOException {
        int len;
        byte[] buf = new byte[1024];
        try (InputStream src = new FileInputStream(source);
            OutputStream dest = new FileOutputStream(output)){

            while ((len = src.read(buf)) > 0){
                dest.write(buf, 0, len);
            }
        }
        System.out.println("success");
    }

    public static void cutFile(File source, int size) throws IOException {
        byte[] buf = new byte[1024];
        int len;
        int len2 = size;
        try (InputStream src = new FileInputStream(source)) {
            for (int i = 1; i <= source.length() / size + 1; ++i) {
                File cutFile = new File("/home/evami/tmp/" + i + source.getName());
                try (OutputStream dest = new FileOutputStream(cutFile)) {
                    while (len2 > 0) {
                        if (len2 < buf.length)
                            len = src.read(buf, 0, len2);
                        else {
                            len = src.read(buf);
                        }
                        dest.write(buf, 0, len);
                        len2 -= buf.length;
                    }
                    len2 = size;
                }
            }
        }
        System.out.println("success");
    }

    public static void glueFile(File[] files, File output) throws IOException {
        byte[] buf = new byte[1024];
        int len;
        try (OutputStream dest = new FileOutputStream(output)){
            for (int i = 0; i < files.length; ++i){
                try(InputStream src = new FileInputStream(files[i])){
                    while ((len = src.read(buf)) > 0)
                        dest.write(buf, 0, len);
                }
            }
        }
        System.out.println("success");
    }

    public static void main(String[] args) throws IOException {
        File input = new File("/home/evami/helloworld.txt");
        File output = new File("/home/evami/helloworldcopy1.txt");
        //copyFile(input, output);

        File srcForCut = new File("/home/evami/Shop/Shop.java");
        File srcForGlue = new File("/home/evami/tmp");
        File destForGlue = new File("/home/evami/Shop.java");

        String[] files = srcForGlue.list();
        File[] filesForGlue = new File[files.length];
        String path = "";
        for (int i = 1; i <= files.length; ++i) {
            for (String str : files) {
                if (str.equals(i + destForGlue.getName())) {
                    path = srcForGlue.getPath() + "/" + i + destForGlue.getName();
                }
            }
            filesForGlue[i - 1] = new File(path);
        }
        cutFile(srcForCut,1536);
        glueFile(filesForGlue, destForGlue);
    }
}

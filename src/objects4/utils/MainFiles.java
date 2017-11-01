package objects4.utils;

import objects5.arraylist.arraylist.List;

import java.io.File;

/**
 * Created by evami on 29.10.17.
 */
public class MainFiles {
    public static void main(String[] args) {
        File dir1 = new File("/home/evami/dir1");
        File dir2 = new File("/home/evami/dir2");
        List files1 = Utils.toList(dir1.listFiles());
        List files2 = Utils.toList(dir2.listFiles());

        List duplicated = Utils.intersect(files1, files2, new Utils.Predicate2() {
            @Override
            public boolean rule(Object o1, Object o2) {
                File f1 = (File)o1;
                File f2 = (File)o2;
                return f1.getName().equals(f2.getName());
            }
        });

        for (Object i: duplicated){
            System.out.println(i);
        }

        List unical = Utils.difference(files1, files2, new Utils.Predicate2() {
            @Override
            public boolean rule(Object o1, Object o2) {
                File f1 = (File)o1;
                File f2 = (File)o2;
                return f1.getName().equals(f2.getName());
            }
        });

        System.out.println();
        for (Object i: unical){
            System.out.println(i);
        }

        File findFile = (File) Utils.find(new Utils.Predicate() {
            @Override
            public boolean apply(Object object) {
                return ((File)object).getName().equals("tasks1.odt");
            }
        }, files1);

        System.out.println(findFile);

        List smallFiles = Utils.filter(new Utils.Predicate() {
            @Override
            public boolean apply(Object object) {
                return ((File)object).length() > 1048576;
            }
        }, files1);

        System.out.println();
        for (Object i: smallFiles){
            System.out.println(i);
        }

        List javaFiles = Utils.filter(new Utils.Predicate() {
            @Override
            public boolean apply(Object object) {
                return ((File)object).getName().endsWith(".java");
            }
        }, files2);

        System.out.println();
        for (Object i: javaFiles){
            System.out.println(i);
        }
    }
}

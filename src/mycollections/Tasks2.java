package mycollections;


import collections.inner.User;

import java.util.*;

import static collections.inner.UserGenerator.generate;

/**
 * Created by xmitya on 20.10.16.
 */
public class Tasks2 {
    public static void main(String[] args) {
        System.out.println(generate(5));
    }

    private static NavigableSet<User> sortedByCompanyAndName(List<User> users) {
        NavigableSet<User> usersSet = new TreeSet<>(new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                int result;
                result = o1.getCompany().compareTo(o2.getCompany());
                if(result != 0) return result;
                result = o1.getName().compareTo(o2.getName());
                return result;
            }
        });
        usersSet.addAll(users);
        return usersSet;
    }

    private static NavigableSet<User> sortedBySalaryAndName(List<User> users) {
        NavigableSet<User> usersSet = new TreeSet<>(new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                int result;
                result = Integer.compare(o1.getSalary(), o2.getSalary());
                if(result != 0) return result;
                result = o1.getName().compareTo(o2.getName());
                return result;
            }
        });
        usersSet.addAll(users);
        return usersSet;
    }

    private static NavigableSet<User> sortedBySalaryAgeCompanyAndName(List<User> users) {
        NavigableSet<User> usersSet = new TreeSet<>(new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                int result;
                result = Integer.compare(o1.getSalary(), o2.getSalary());
                if(result != 0) return result;
                result = o1.getCompany().compareTo(o2.getCompany());
                if(result != 0) return result;
                result = o1.getName().compareTo(o2.getName());
                return result;
            }
        });
        usersSet.addAll(users);
        return usersSet;
    }

    private static <T> Iterator<T> viewIterator(Iterable<T> it1, Iterable<T> it2) {
            // ЧТО ОНО ХОЧЕТ ОТ НАС? Примерно ходить по 2 итераторам как в утилс?
        Iterator<T>[] iters = (Iterator<T>[])new Iterator[2];
        iters[0] = it1.iterator();
        iters[1] = it2.iterator();
        return new ViewIterator<>(iters);
    }
}

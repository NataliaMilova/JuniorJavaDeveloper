package mycollections;

import collections.inner.Message;
import collections.inner.MessageGenerator;
import collections.inner.MessagePriority;


import java.util.*;
import java.util.List;
import java.util.function.Predicate;

/**
 * Created by xmitya on 17.10.16.
 */
public class Tasks1 {

    public static void main(String[] args) {
        MessageGenerator generator = new MessageGenerator();

        //List<Message> messages = generator.generate(10);
        List<Message> messages = new ArrayList<>();
        messages.add(new Message(MessagePriority.MEDIUM, 3));
        messages.add(new Message(MessagePriority.MEDIUM, 3));
        messages.add(new Message(MessagePriority.MEDIUM, 3));
        messages.add(new Message(MessagePriority.MEDIUM, 3));
        messages.add(new Message(MessagePriority.MEDIUM, 3));
        messages.add(new Message(MessagePriority.LOW, 4));
        System.out.println(messages);
        //countEachPriority(messages);
        //countCountEachCode(messages);
        //countUniqueMessages(messages);

        System.out.println("Genuine messages in natural order: \n" + genuineMessagesInOriginalOrder(messages));

        //removeEach(generator.generate(100), MessagePriority.LOW);
        //removeOther(generator.generate(100), MessagePriority.URGENT);
    }

    private static void countEachPriority(List<Message> messages) {
        // Сосчитайте количество сообщений для каждого приоритета.
        // Ответ необходимо вывести в консоль.
        HashMap<MessagePriority, Integer> hashMap = new HashMap<>();
        for (Message ms: messages){
            if (hashMap.get(ms.getPriority()) != null)
                hashMap.put(ms.getPriority(), hashMap.get(ms.getPriority()) + 1);
            else
                hashMap.put(ms.getPriority(), 1);
        }

        for (MessagePriority msPr: hashMap.keySet()){
            System.out.println(msPr + "count: " + hashMap.get(msPr));
        }
        // TODO implement
    }

    private static void countCountEachCode(List<Message> messages) {
        // Сосчитайте количество сообщений для каждого кода сообщения.
        // Ответ необходимо вывести в консоль.
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        for (Message ms: messages){
            if (hashMap.get(ms.getCode()) != null)
                hashMap.put(ms.getCode(), hashMap.get(ms.getCode()) + 1);
            else
                hashMap.put(ms.getCode(), 1);
        }

        for (Integer msCode: hashMap.keySet()){
            System.out.println(msCode + "count: " + hashMap.get(msCode));
        }
        // TODO implement
    }

    private static void countUniqueMessages(List<Message> messages) {
        Set<Message> uniqueMessages = new HashSet<>();
        for (Message ms: messages){
            uniqueMessages.add(ms);
        }
        System.out.println("Unique messages: " + uniqueMessages.size());
        // Сосчитайте количество уникальных сообщений.
        // Ответ необходимо вывести в консоль.

        // TODO implement
    }

    private static List<Message> genuineMessagesInOriginalOrder(List<Message> messages) {
        // Здесь необходимо вернуть только неповторяющиеся сообщения и в том порядке, в котором
        // они встречаются в первоначальном списке. Например, мы на входе имеем такие сообщения:
        // [{URGENT, 4}, {HIGH, 9}, {LOW, 3}, {HIGH, 9}],
        // то на выходе должны получить:
        // [{URGENT, 4}, {HIGH, 9}, {LOW, 3}].
        // Т.е. остались только уникальные значения, и порядок их поступления сохранен.
        // TODO implement

        LinkedHashSet<Message> messagesSet = new LinkedHashSet<>();
        List<Message> result = new ArrayList<>();
        for (Message ms: messages){
            messagesSet.add(ms);
        }
        for (Message ms: messagesSet){
            result.add(ms);
        }
        return result;
    }

    private static void removeEach(Collection<Message> messages, MessagePriority priority) {
        // Удалить из коллекции каждое сообщение с заданным приоритетом.
        System.out.printf("Before remove each: %s, %s\n", priority, messages);
        messages.removeIf(new Predicate<Message>() {
            @Override
            public boolean test(Message message) {
                return message.getPriority().equals(priority);
            }
        });
        // TODO implement
        System.out.printf("After remove each: %s, %s\n", priority, messages);
    }

    private static void removeOther(Collection<Message> messages, MessagePriority priority) {
        // Удалить из коллекции все сообщения, кроме тех, которые имеют заданный приоритет.
        System.out.printf("Before remove other: %s, %s\n", priority, messages);
        messages.removeIf(new Predicate<Message>() {
            @Override
            public boolean test(Message message) {
                return !message.getPriority().equals(priority);
            }
        });
        // TODO implement
        System.out.printf("After remove other: %s, %s\n", priority, messages);
    }

    private static void sortByPriority(List<Message> messages) {
        //???? куда тут передаваемый приоритет деть?
        messages.sort(new Comparator<Message>() {
            @Override
            public int compare(Message o1, Message o2) {
                return o1.getPriority().compareTo(o2.getPriority());
            }
        });
    }
}

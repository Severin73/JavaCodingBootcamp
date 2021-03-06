package academy.kovalevskyi.codingbootcamp.week1.day3;

import java.util.function.Function;

public class ListHelper {

    public static <T> ListNode<T> findFirst(ListNode<T> someNode) {
        if (someNode.getPrev() == null) {
            return someNode;
        }
        ListNode<T> result = someNode;
        while (result.getPrev() != null) {
            result = result.getPrev();
        }

        return result;
    }

    public static <T> ListNode<T> findLast(ListNode<T> someNode) {
        if (someNode.getNext() == null) {
            return someNode;
        }
        ListNode<T> result = someNode;
        while (result.getNext() != null) {
            result = result.getNext();
        }
        return result;
    }

    public static <T> int length(ListNode<T> someNode) {
        if (someNode == null) {
            int counter = 0;
        }
        if (someNode.getNext() == null && someNode.getPrev() == null) {
            return 1;
        }
        ListNode<T> left = someNode.getPrev();
        ListNode<T> right = someNode.getNext();
        int counter = 0;
        while (left != null) {
            counter++;
            left = left.getPrev();
        }
        while (right != null) {
            counter++;
            right = right.getNext();
        }
        return counter + 1;
    }

//    public static <T> ListNode<T> addToEnd(ListNode<T> someNode, T newValue) {
//
//        if (someNode == null) {
//            throw new NullPointerException();
//        }
//        ListNode<T> prev = someNode.getPrev();
//        ListNode<T> next = someNode.getNext();
//        ListNode<T> newEndNode = new ListNode<T>(null, null, newValue);
//        //Singleton Node
//        if (prev == null && next == null) {
//            someNode.setNext(newEndNode);
//            newEndNode.setPrev(someNode);
//            return newEndNode;
//        }
//        if (next != null) {
//            //else not End Node iteration of ListNode
//            while (next.getNext() != null) {
//                //Search link for the First Node
//                next = next.getNext();
//            }
//        }
//        if (next == null) {
//            next = someNode;
//        }
//        next.setNext(newEndNode);
//        newEndNode.setPrev(next);
//        return newEndNode;
//    }

    public static <T> ListNode<T> addToEnd(ListNode<T> someNode, T newValue) {
        if (someNode == null) {
            throw new NullPointerException();
        }
        ListNode<T> lastElement = findLast(someNode);
        ListNode<T> node = new ListNode<>(lastElement, null, newValue);
        if (lastElement != null) {
            lastElement.setNext(node);
        }
        return node;
    }

    public static <T> ListNode<T> addToStart(ListNode<T> someNode, T newValue) {
        if (someNode == null) {
            throw new NullPointerException();
        }
        ListNode<T> firstElement = findFirst(someNode);
        ListNode<T> node = new ListNode<>(null, firstElement, newValue);
        firstElement.setPrev(node);

        return node;
    }

    public static <T> boolean contains(ListNode<T> someNode, T value) {
        if (someNode == null) {
            throw new NullPointerException();
        }
        ListNode<T> left = someNode;
        while (left.getPrev() != null) {
            if (left.getPrev().getValue().equals(value)) {
                return true;
            }
            left = left.getPrev();
        }

        ListNode<T> right = someNode;
        while (right.getNext() != null) {
            if (right.getNext().getValue().equals(value)) {
                return true;
            }
            right = right.getNext();
        }
        return someNode.getValue().equals(value);
    }

    public static <T, R> ListNode<R> map(ListNode<T> someNode, Function<T, R> mapFunction) {
        if (someNode == null) {
            throw new NullPointerException();
        }
        R valueAfterFunction = mapFunction.apply(someNode.getValue());
        ListNode<R> mapNode = new ListNode<>(null, null, valueAfterFunction);

        ListNode<T> left = someNode;
        ListNode<R> shiftNodeLeft = mapNode;
        while (left.getPrev() != null) {
            left = left.getPrev();
            ListNode<R> currenNode = new ListNode<>(null, shiftNodeLeft, mapFunction.apply(left.getValue()));
            shiftNodeLeft.setPrev(currenNode);
            shiftNodeLeft = currenNode;
        }

        ListNode<T> right = someNode;
        ListNode<R> shiftNodeRight = mapNode;
        while (right.getNext() != null) {
            right = right.getNext();
            ListNode<R> currenNode = new ListNode<>(shiftNodeRight, null, mapFunction.apply(right.getValue()));
            shiftNodeRight.setNext(currenNode);
            shiftNodeRight = currenNode;
        }
        return mapNode;
    }

    public static <T> ListNode<T> insertAfter(ListNode<T> prev, T newValue) {
        if (prev == null) {
            throw new NullPointerException();
        }
        if (prev.getNext() == null) {
            return addToEnd(prev, newValue);
        }
        ListNode<T> insertNode = new ListNode<>(prev, prev.getNext(), newValue);
        prev.getNext().setPrev(insertNode);
        prev.setNext(insertNode);

        return insertNode;
    }

    public static <T> void insertAfter(ListNode<T> prev, T[] newValues) {
        if (prev == null || newValues == null) {
            throw new NullPointerException();
        }
        ListNode<T> tempNode = prev;
        for (T newValue : newValues) {
            tempNode = insertAfter(tempNode, newValue);
        }
    }

    public static <T> T delete(ListNode<T> current) {
        if (current == null) {
            throw new NullPointerException();
        }
        if (current.getNext() == null && current.getPrev() == null) {
            return current.getValue();
        }
        if (current.getNext() == null) {
            current.getPrev().setNext(null);
            current.setPrev(null);

            return current.getValue();
        }
        if (current.getPrev() == null) {
            current.getNext().setPrev(null);
            current.setNext(null);

            return current.getValue();
        }
        current.getPrev().setNext(current.getNext());
        current.getNext().setPrev(current.getPrev());
        current.setNext(null);
        current.setPrev(null);

       return current.getValue();
    }
}

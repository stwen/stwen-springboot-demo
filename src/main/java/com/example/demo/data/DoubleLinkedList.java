package com.example.demo.data;

/**
 * @description: 双向链表 --LinkedList
 * @author: stwen_gan
 * @date: 2020/04/24
 **/
public class DoubleLinkedList<E> {

    private static Node last;
    private static Node first;
    private int size;// 集合的大小
    private int modCount;// list结构被修改的次数
    /**
     * 可参考LinkedList,内部也是双向链表
     * @param <E>
     */
    private static class Node<E>{
        E item;//节点元素
        Node<E> prev; // prev是头指针,指向前一个节点
        Node<E> next; // next是尾指针,指向下一个节点

        //构造函数
        Node(Node<E> prev, E element,Node<E> next){
            this.item = element;
            this.prev = prev;
            this.next = next;
        }
    }

    // 链尾添加节点
    public boolean add(E e){
        linkLast(e);
        return true;
    }

    /**
     *
     * 添加一个元素e到链表末尾:https://blog.csdn.net/u012814441/article/details/80671604
     *
     * 1、初次调用add方法时，如果此时链表没有节点，last指针必定为空的，
     *      因此指针l也为空，接着通过new Node<>(l, e, null)创造第一个节点，
     *      让指针last、first都指向这个节点。此时整个链表只有一个节点。
     * 2、当再次调用add方法时，l指向lsat指向的节点（也就是第一个节点）。
     *      再通过new Node<>(l, e, null)创造第二个节点（传入l已经有值了，
     *      此时Node里面的prev指针指向了l）。last重新指向第二个节点newNode。
     *      因为l不为空，则使l的尾指针next指向newNode，完成两个节点互相关联。
     *      后续只要往链表添加数据，就会按此步骤逐个的添加节点完成双向绑定，形成一个双向链表。
     * @param e
     */
    private void linkLast(E e) {
        final Node<E> l = last;
        final Node<E> newNode = new Node<>(l,e,null);
        last = newNode;
        if(l == null){
            first = last;
        }else{
            l.next = newNode;
        }
        size++;
        modCount++;
    }

}

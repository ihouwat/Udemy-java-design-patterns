// Iterator pattern using binary tree in-order traversal

package behavioral.iterator;

import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

class Node<T>
{
    public T value;
    public Node<T> left, right, parent;

    public Node(T value) {
        this.value = value;
    }

    public Node(T value, Node<T> left, Node<T> right) {
        this.value = value;
        this.left = left;
        this.right = right;

        left.parent = right.parent = this;
    }
}

class InOrderIterator<T> implements Iterator<T>
{
    private Node<T> current, root;
    private boolean yieldedStart; // whether we yielded starting element

    public InOrderIterator(Node<T> root) {
        this.root = current = root;

        while(current.left != null)
            current = current.left; // set current to left-most leaf
    }

    private boolean hasRightMostParent(Node<T> node)
    {
       if(node.parent == null) return false;
       else
       {
           return (node == node.parent.left)
               || hasRightMostParent(node.parent);
       }
    }

    @Override
    public boolean hasNext() {
        return current.left != null
            || current.right != null
            || hasRightMostParent(current);
    }

    @Override
    public T next() {
        if (!yieldedStart)
        {
            yieldedStart = true;
            return current.value;
        }

        if (current.right != null)
        {
            current = current.right;
            while (current.left != null)
                current = current.left;
            return current.value;
        }
        else
        {
            Node<T> p = current.parent;
            while (p != null && current == p.right)
            {
                current = p;
                p = p.parent;
            }
            current = p;
            return current.value;
        }
    }
}

class BinaryTree<T> implements Iterable<T>
{
    private Node<T> root;

    public BinaryTree(Node<T> root) {
        this.root = root;
    }

    @Override
    public Iterator<T> iterator() {
        return new InOrderIterator<>(root);
    }

    @Override
    public void forEach(Consumer<? super T> action) {
        // As you use the for loop, the for loop uses the iterator yielded
        // from the iterator() method.
        for(T item : this)
            action.accept(item);
    }

//    @Override
//    public Spliterator<T> spliterator() {
//        return null;
//    }
}


class TreeTraversalDemo
{
    public static void main(String[] args) {
        //   1
        //  / \
        // 2   3

        // Traversal = 213

        Node<Integer> root = new Node<>(1,
            new Node<>(2),
            new Node<>(3));

        InOrderIterator<Integer> it = new InOrderIterator<>(root);

        // While loop
        while(it.hasNext())
        {
            System.out.print(""+it.next()+",");
        }
        System.out.println();

        BinaryTree<Integer> tree = new BinaryTree<>(root);
        for (int n : tree)
            System.out.print("" + n + ",");
        System.out.println();
    }
}

package AaDS_Lab8.WindowsExplorer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

interface Tree<E> {
    ////////////Accessors ////////////

    public Node<E> root();

    public Node<E> parent(Node<E> node);

    public int childCount(Node<E> node);

    ////////////Transformers ////////////
    public void makeRoot(E elem);

    public Node<E> addChild(Node<E> node, E elem);

    public void remove(Node<E> node);

    ////////////Iterator ////////////
    public Iterator<E> children(Node<E> node);

}

interface Node<E> {

    public E getElement();

    public void setElement(E elem);
}


class SLLTree<E> implements Tree<E> {

    public SLLNode<E> root;

    public SLLTree() {
        root = null;
    }

    public Node<E> root() {
        return root;
    }

    public Node<E> parent(Node<E> node) {
        return ((SLLNode<E>) node).parent;
    }

    public int childCount(Node<E> node) {
        SLLNode<E> tmp = ((SLLNode<E>) node).firstChild;
        int num = 0;
        while (tmp != null) {
            tmp = tmp.sibling;
            num++;
        }
        return num;
    }

    public void makeRoot(E elem) {
        root = new SLLNode<E>(elem);
    }

    public Node<E> addChild(Node<E> node, E elem) {
        SLLNode<E> tmp = new SLLNode<E>(elem);
        SLLNode<E> curr = (SLLNode<E>) node;
        tmp.sibling = curr.firstChild;
        curr.firstChild = tmp;
        tmp.parent = curr;
        return tmp;
    }

    /*
    public Node<E> addChild(Node<E> node, E elem) {
        SLLNode<E> tmp = new SLLNode<E>(elem);
        SLLNode<E> curr = (SLLNode<E>) node;
        tmp.sibling = curr.firstChild;
        tmp.parent = curr;

        if(curr.firstChild == null) {
            curr.firstChild = tmp;
        } else if(tmp.element.toString().compareTo(curr.firstChild.element.toString()) < 0) {
            tmp.sibling = curr.firstChild;
            curr.firstChild = tmp;
        } else {
            SLLNode<E> prev = new SLLNode<>(curr.firstChild.element);
            prev.sibling = curr.firstChild;
            while(prev.sibling != null && tmp.element.toString().compareTo(prev.sibling.element.toString()) > 0) {
                prev = prev.sibling;
            }
            tmp.sibling = prev.sibling;
            prev.sibling = tmp;
        }

        return tmp;
    }
     */

    public void remove(Node<E> node) {
        SLLNode<E> curr = (SLLNode<E>) node;
        if (curr.parent != null) {
            if (curr.parent.firstChild == curr) {
                // The node is the first child of its parent
                // Reconnect the parent to the next sibling
                curr.parent.firstChild = curr.sibling;
            } else {
                // The node is not the first child of its parent
                // Start from the first and search the node in the sibling list and remove it
                SLLNode<E> tmp = curr.parent.firstChild;
                while (tmp.sibling != curr) {
                    tmp = tmp.sibling;
                }
                tmp.sibling = curr.sibling;
            }
        } else {
            root = null;
        }
    }

    class SLLTreeIterator<T> implements Iterator<T> {

        SLLNode<T> start, current;

        public SLLTreeIterator(SLLNode<T> node) {
            start = node;
            current = node;
        }

        public boolean hasNext() {
            return (current != null);
        }

        public T next() throws NoSuchElementException {
            if (current != null) {
                SLLNode<T> tmp = current;
                current = current.sibling;
                return tmp.getElement();
            } else {
                throw new NoSuchElementException();
            }
        }

        public void remove() {
            if (current != null) {
                current = current.sibling;
            }
        }
    }

    public Iterator<E> children(Node<E> node) {
        return new SLLTreeIterator<E>(((SLLNode<E>) node).firstChild);
    }

    void printTreeRecursive(Node<E> node, int level) {
        if (node == null)
            return;
        int i;
        SLLNode<E> tmp;

        for (i = 0; i < level; i++)
            System.out.print(" ");
        System.out.println(node.getElement().toString());
        tmp = ((SLLNode<E>) node).firstChild;
        while (tmp != null) {
            printTreeRecursive(tmp, level + 1);
            tmp = tmp.sibling;
        }
    }

    public void printTree() {
        printTreeRecursive(root, 0);
    }

}

class SLLNode<P> implements Node<P> {

    // Holds the links to the needed nodes
    public SLLNode<P> parent, sibling, firstChild;
    // Hold the data
    public P element;

    public SLLNode(P o) {
        element = o;
        parent = sibling = firstChild = null;
    }

    public P getElement() {
        return element;
    }

    public void setElement(P o) {
        element = o;
    }
}

public class WindowsExplorer {
    public static void printPath(SLLNode<String> nodeToPrint) {
        if (nodeToPrint == null) return;

        Stack<String> path = new Stack<>();

        while (nodeToPrint != null) {
            path.push(nodeToPrint.getElement());
            nodeToPrint = nodeToPrint.parent; // because lastInFirstOut
        }

        StringBuilder string = new StringBuilder();
        while (!path.isEmpty()) string.append(path.pop()).append("\\");

        System.out.println(string);
    }

    public static void makeSorted(SLLTree<String> tree, SLLNode<String> node) {
//        Collect Children Nodes: First, gather all the children nodes of the parent node into a list or an array.
//
//        Sort the List/Array: Use a sorting algorithm or a built-in sorting function to sort the list/array. Since the elements are letters, you can sort them alphabetically.
//
//        Reattach Sorted Children: Finally, reattach the sorted children back to the parent node.
        SLLNode<String> trav = node.firstChild;
        int n = 0;
        while (trav != null) {
            n++;
            if (trav.sibling == null)
                break;
            else
                trav = trav.sibling;
        }

        trav = node.firstChild;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if ((trav.sibling != null) && trav.getElement().compareTo(trav.sibling.getElement()) < 0) {
                    SLLNode<String> temp = trav.sibling;
                    trav.sibling = trav;
                    trav = temp;
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        int i, j, k;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        String commands[] = new String[N];

        for (i = 0; i < N; i++)
            commands[i] = br.readLine();

        br.close();

        SLLTree<String> tree = new SLLTree<String>();
        tree.makeRoot("c:");
        // od tuka pocnuva

        SLLNode<String> helperTrav;
        SLLNode<String> trav = tree.root;

        for (i = 0; i < N; i++) {
            String[] command = commands[i].split(" ");
            String action, elem;
            action = elem = "";
            if (command.length > 1) {
                action = command[0];
                elem = command[1];
            } else {
                action = command[0];
            }


            switch (action) {
                case "CREATE":
                    tree.addChild(trav, elem);
                    makeSorted(tree, trav);
                    break;
                case "OPEN":
                    helperTrav = trav.firstChild;
                    while (helperTrav != null && !helperTrav.element.equals(elem)) {
                        helperTrav = helperTrav.sibling; // this is how we traverse the children of the node
                    }
                    if (helperTrav != null) trav = helperTrav; // if the node exists then it's the node we want because
                    // of the second condition of the while statement
                    break;
                case "PATH":
                    printPath(trav);
                    break;
                case "BACK":
                    trav = trav.parent;
                    break;
                case "DELETE":
                    helperTrav = trav.firstChild;
                    while (helperTrav != null && !helperTrav.element.equals(elem)) {
                        helperTrav = helperTrav.sibling; // this is how we traverse the children of the node
                    }
                    if (helperTrav != null)
                        tree.remove(helperTrav); // if the node exists then it's the node we want because
                    // of the second condition of the while statement
                    break;
                case "PRINT":
                    tree.printTree();
                    break;
            }
        }

    }

}

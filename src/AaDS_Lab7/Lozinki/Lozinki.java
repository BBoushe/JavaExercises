package AaDS_Lab7.Lozinki;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;

class SLLNode<E> {
    protected E element;
    protected SLLNode<E> succ;

    public SLLNode(E elem, SLLNode<E> succ) {
        this.element = elem;
        this.succ = succ;
    }

    @Override
    public String toString() {
        return element.toString();
    }
}


class SLL<E> {
    private SLLNode<E> first;

    public SLL() {
        // Construct an empty SLL
        this.first = null;
    }

    public void deleteList() {
        first = null;
    }

    public int size() {
        int listSize = 0;
        SLLNode<E> tmp = first;
        while (tmp != null) {
            listSize++;
            tmp = tmp.succ;
        }
        return listSize;
    }

    @Override
    public String toString() {
        String ret = new String();
        if (first != null) {
            SLLNode<E> tmp = first;
            ret += tmp;
            while (tmp.succ != null) {
                tmp = tmp.succ;
                ret += " " + tmp;
            }
        } else
            ret = "Prazna lista!!!";
        return ret;
    }

    public void insertFirst(E o) {
        SLLNode<E> ins = new SLLNode<E>(o, null);
        ins.succ = first;
        //SLLNode<E> ins = new SLLNode<E>(o, first);
        first = ins;
    }

    public void insertAfter(E o, SLLNode<E> node) {
        if (node != null) {
            SLLNode<E> ins = new SLLNode<E>(o, node.succ);
            node.succ = ins;
        } else {
            System.out.println("Dadenot jazol e null");
        }
    }

    public void insertBefore(E o, SLLNode<E> before) {

        if (first != null) {
            SLLNode<E> tmp = first;
            if (first == before) {
                this.insertFirst(o);
                return;
            }
            //ako first!=before
            while (tmp.succ != before && tmp.succ != null)
                tmp = tmp.succ;
            if (tmp.succ == before) {
                tmp.succ = new SLLNode<E>(o, before);
                ;
            } else {
                System.out.println("Elementot ne postoi vo listata");
            }
        } else {
            System.out.println("Listata e prazna");
        }
    }

    public void insertLast(E o) {
        if (first != null) {
            SLLNode<E> tmp = first;
            while (tmp.succ != null)
                tmp = tmp.succ;
            tmp.succ = new SLLNode<E>(o, null);
        } else {
            insertFirst(o);
        }
    }

    public E deleteFirst() {
        if (first != null) {
            SLLNode<E> tmp = first;
            first = first.succ;
            return tmp.element;
        } else {
            System.out.println("Listata e prazna");
            return null;
        }
    }

    public E delete(SLLNode<E> node) {
        if (first != null) {
            SLLNode<E> tmp = first;
            if (first == node) {
                return this.deleteFirst();
            }
            while (tmp.succ != node && tmp.succ.succ != null)
                tmp = tmp.succ;
            if (tmp.succ == node) {
                tmp.succ = tmp.succ.succ;
                return node.element;
            } else {
                System.out.println("Elementot ne postoi vo listata");
                return null;
            }
        } else {
            System.out.println("Listata e prazna");
            return null;
        }

    }

    public SLLNode<E> getFirst() {
        return first;
    }

    public SLLNode<E> find(E o) {
        if (first != null) {
            SLLNode<E> tmp = first;
            while (!tmp.element.equals(o) && tmp.succ != null)
                tmp = tmp.succ;
            if (tmp.element.equals(o)) {
                return tmp;
            } else {
                System.out.println("Elementot ne postoi vo listata");
            }
        } else {
            System.out.println("Listata e prazna");
        }
        return null;
    }

    public void merge(SLL<E> in) {
        if (first != null) {
            SLLNode<E> tmp = first;
            while (tmp.succ != null)
                tmp = tmp.succ;
            tmp.succ = in.getFirst();
        } else {
            first = in.getFirst();
        }
    }

    public void mirror() {
        if (first != null) {
            //m=nextsucc, p=tmp,q=next
            SLLNode<E> tmp = first;
            SLLNode<E> newsucc = null;
            SLLNode<E> next;

            while (tmp != null) {
                next = tmp.succ;
                tmp.succ = newsucc;
                newsucc = tmp;
                tmp = next;
            }
            first = newsucc;
        }
    }
}


class User{
    private final String username;
    private final String password;

    User(String username, String password){
        this.username = username;
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(username, user.username) && Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        int hash = Objects.hash(username, password) % 52;

        if(hash < 0) hash+=52;

        return hash;
    }
}

class CBHT<E> {
    private SLL<E>[] table;
    private boolean[] isOccupied;

    CBHT(int size) {
        this.table = new SLL[size];
        this.isOccupied = new boolean[size];
    }

    private int getHash(E elem) {
        return elem.hashCode();
    }

    public void add(E elem) {
        int pos = getHash(elem);


        if (!isOccupied[pos]) {
            table[pos] = new SLL<E>();
            table[pos].insertFirst(elem);
            isOccupied[pos] = true;
        } else { // we don't handle duplicates so they would be added alongside the previous ones
            for(SLLNode<E> trav = getList(pos).getFirst(); trav != null; trav = trav.succ){
                if(trav.element.equals(elem))
                    return; // if you find the element then don't add it
            }
            table[pos].insertLast(elem);
        }
    }

    public SLL<E> getList(int i) {
        /**
         * Returns the elements of the hashTable with given index i.e. given hashCode()
         */
        return table[i];
    }

    public boolean find(E elem) {
        int pos = getHash(elem);

        if (!isOccupied[pos]) return false;

        for (SLLNode<E> trav = getList(pos).getFirst(); trav != null; trav = trav.succ) {
            if (trav.element.equals(elem)) return true;
        }
        return false;
    }
}

public class Lozinki {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int size = 52;
        CBHT<User> accounts = new CBHT<>(size);



        for (int i = 1; i <= N; i++) {
            String imelozinka = br.readLine();
            String[] pom = imelozinka.split(" ");
            User account = new User(pom[0], pom[1]);

            accounts.add(account);
        }

        while (true) {
            String input = br.readLine();
            if (input.equals("KRAJ"))
                break;

            String creds[] = input.split(" ");
            User localUser = new User(creds[0], creds[1] );

            if (accounts.find(localUser)) {
                System.out.println("Najaven");
                break;
            } else System.out.println("Nenajaven");
        }
    }
}

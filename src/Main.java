// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        SinglyLinkedList<Integer> linkedList = new SinglyLinkedList<>();
        Scanner scanner = new Scanner(System.in);

        int choice;
        do {
            System.out.println("\nLista enlazada Menu:");
            System.out.println("1. Insertar al inicio");
            System.out.println("2. Insertar al final");
            System.out.println("3. Recorrer");
            System.out.println("4. Buscar elemento");
            System.out.println("5. Borrar un elemento");
            System.out.println("0. Salir");
            System.out.print("Ingrese su elección: ");

            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Ingrese el valor a insertar al inicio: ");
                    int valueAtStart = scanner.nextInt();
                    linkedList.insertAtHead(valueAtStart);
                    break;
                case 2:
                    System.out.print("Ingrese el valor a insertar al final: ");
                    int valueAtEnd = scanner.nextInt();
                    linkedList.insertAtTail(valueAtEnd);
                    break;
                case 3:
                    System.out.println("Elementos en la lista:");
                    linkedList.traverse();
                    break;
                case 4:
                    System.out.print("Ingrese el valor a buscar: ");
                    int searchValue = scanner.nextInt();
                    SinglyLinkedList<Integer>.Node<Integer> resultNode = linkedList.searchByValue(searchValue);
                    if (resultNode != null) {
                        System.out.println("Elemento encontrado en la posición: " + linkedList.indexOf(resultNode));
                    } else {
                        System.out.println("Elemento no encontrado.");
                    }
                    break;
                case 5:
                    System.out.print("Ingrese el valor a borrar: ");
                    int deleteValue = scanner.nextInt();
                    linkedList.deleteByValue(deleteValue);
                    System.out.println("Elemento borrado correctamente.");
                    break;
                case 0:
                    System.out.println("Saliendo del programa. ¡Hasta luego!");
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, intente de nuevo.");
            }

        } while (choice != 0);

        scanner.close();
    }
}

class SinglyLinkedList<E> {

    private Node<E> head;
    private int size = 0;

    public void insertAtHead(E value) {
        Node<E> newNode = new Node<>(value);
        newNode.next = head;
        head = newNode;
        size++;
    }

    public void insertAtTail(E value) {
        Node<E> newNode = new Node<>(value);
        newNode.next = null;
        if (null == head) {
            head = newNode;
        } else {
            Node<E> tempNode = head;
            while (null != tempNode.next) {
                tempNode = tempNode.next;
            }
            tempNode.next = newNode;
        }
        size++;
    }

    public void traverse() {
        Node<E> temp = head;
        while (temp != null) {
            System.out.println(temp.item);
            temp = temp.next;
        }
    }

    public Node<E> searchByValue(E value) {
        Node<E> temp = head;
        while (temp != null && !temp.item.equals(value)) {
            temp = temp.next;
        }
        return temp;
    }

    public void deleteByValue(E value) {
        if (head == null) {
            return;
        }
        if (head.item.equals(value)) {
            head = head.next;
            size--;
            return;
        }
        Node<E> currentNode = head;
        Node<E> nextNode = currentNode.next;
        while (nextNode != null && !nextNode.item.equals(value)) {
            currentNode = currentNode.next;
            nextNode = nextNode.next;
        }
        if (nextNode != null) {
            currentNode.next = nextNode.next;
            size--;
        }
    }

    public int indexOf(Node<E> node) {
        Node<E> temp = head;
        int index = 0;
        while (temp != null && temp != node) {
            temp = temp.next;
            index++;
        }
        return (temp == null) ? -1 : index;
    }

    public class Node<T> {
        T item;
        Node<T> next;

        public Node(T item) {
            this.item = item;
        }
    }
}

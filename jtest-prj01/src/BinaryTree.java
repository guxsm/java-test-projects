import java.util.Scanner;

class BinaryTree {
    // Clase interna para representar un nodo del árbol
    static class Node {
        int value;
        Node left, right;

        Node(int value) {
            this.value = value;
            this.left = null;
            this.right = null;
        }
    }

    private Node root;

    // Método para agregar un nodo
    public void add(int value) {
        root = addRecursive(root, value);
    }

    private Node addRecursive(Node current, int value) {
        if (current == null) {
            return new Node(value);
        }

        if (value < current.value) {
            current.left = addRecursive(current.left, value);
        } else if (value > current.value) {
            current.right = addRecursive(current.right, value);
        }

        return current;
    }

    // Método para buscar un valor en el árbol
    public boolean search(int value) {
        return searchRecursive(root, value);
    }

    private boolean searchRecursive(Node current, int value) {
        if (current == null) {
            return false;
        }

        if (value == current.value) {
            return true;
        }

        return value < current.value
            ? searchRecursive(current.left, value)
            : searchRecursive(current.right, value);
    }

    // Método para eliminar un nodo
    public void delete(int value) {
        root = deleteRecursive(root, value);
    }

    private Node deleteRecursive(Node current, int value) {
        if (current == null) {
            return null;
        }

        if (value == current.value) {
            // Caso 1: Nodo sin hijos
            if (current.left == null && current.right == null) {
                return null;
            }

            // Caso 2: Nodo con un solo hijo
            if (current.left == null) {
                return current.right;
            }

            if (current.right == null) {
                return current.left;
            }

            // Caso 3: Nodo con dos hijos
            int smallestValue = findSmallestValue(current.right);
            current.value = smallestValue;
            current.right = deleteRecursive(current.right, smallestValue);
            return current;
        }

        if (value < current.value) {
            current.left = deleteRecursive(current.left, value);
            return current;
        }

        current.right = deleteRecursive(current.right, value);
        return current;
    }

    private int findSmallestValue(Node root) {
        return root.left == null ? root.value : findSmallestValue(root.left);
    }

    // Método para actualizar un valor en el árbol
    public void update(int oldValue, int newValue) {
        if (search(oldValue)) {
            delete(oldValue);
            add(newValue);
        } else {
            System.out.println("Valor no encontrado: " + oldValue);
        }
    }

    // Método para realizar un recorrido en orden (para visualizar el árbol)
    public void inOrderTraversal() {
        inOrderTraversalRecursive(root);
        System.out.println();
    }

    private void inOrderTraversalRecursive(Node current) {
        if (current != null) {
            inOrderTraversalRecursive(current.left);
            System.out.print(current.value + " ");
            inOrderTraversalRecursive(current.right);
        }
    }

    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();
        Scanner scanner = new Scanner(System.in);
        int option;

        do {
            System.out.println("\n--- Menú del Árbol Binario ---");
            System.out.println("1. Agregar nodo");
            System.out.println("2. Buscar nodo");
            System.out.println("3. Actualizar nodo");
            System.out.println("4. Eliminar nodo");
            System.out.println("5. Mostrar árbol en orden");
            System.out.println("6. Salir");
            System.out.print("Elige una opción: ");

            option = scanner.nextInt();

            switch (option) {
                case 1:
                    System.out.print("Ingresa el valor a agregar: ");
                    int valueToAdd = scanner.nextInt();
                    tree.add(valueToAdd);
                    System.out.println("Nodo agregado.");
                    break;
                case 2:
                    System.out.print("Ingresa el valor a buscar: ");
                    int valueToSearch = scanner.nextInt();
                    boolean found = tree.search(valueToSearch);
                    System.out.println(found ? "Valor encontrado." : "Valor no encontrado.");
                    break;
                case 3:
                    System.out.print("Ingresa el valor a actualizar: ");
                    int oldValue = scanner.nextInt();
                    System.out.print("Ingresa el nuevo valor: ");
                    int newValue = scanner.nextInt();
                    tree.update(oldValue, newValue);
                    break;
                case 4:
                    System.out.print("Ingresa el valor a eliminar: ");
                    int valueToDelete = scanner.nextInt();
                    tree.delete(valueToDelete);
                    System.out.println("Nodo eliminado.");
                    break;
                case 5:
                    System.out.println("Recorrido en orden del árbol:");
                    tree.inOrderTraversal();
                    break;
                case 6:
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción no válida, intenta de nuevo.");
            }
        } while (option != 6);

        scanner.close();
    }
}

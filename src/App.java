import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Ordenamiento y búsqueda de palabras (Strings)");

        // Pedimos tamaño del arreglo estático
        System.out.print("Ingrese el tamaño del arreglo: ");
        int n = Integer.parseInt(sc.nextLine());
        String[] arr = new String[n];

        // Llenado del arreglo por el usuario
        for (int i = 0; i < n; i++) {
            System.out.print("Ingrese palabra " + (i + 1) + ": ");
            arr[i] = sc.nextLine();
        }

        boolean exit = false;
        while (!exit) {
            System.out.println();
            System.out.println("Seleccione el algoritmo de ordenamiento:");
            System.out.println("1) Selección");
            System.out.println("2) Inserción");
            System.out.println("3) Burbuja");
            System.out.println("4) Mezcla (Merge Sort)");
            System.out.println("5) Rápido (Quick Sort)");
            System.out.println("6) Mostrar arreglo actual");
            System.out.println("7) Búsqueda binaria (requiere arreglo ordenado)");
            System.out.println("0) Salir");
            System.out.print("Opción: ");
            String opt = sc.nextLine();

            switch (opt) {
                case "1":
                    System.out.println("Aplicando Selección...");
                    selectionSort(arr);
                    break;
                case "2":
                    System.out.println("Aplicando Inserción...");
                    insertionSort(arr);
                    break;
                case "3":
                    System.out.println("Aplicando Burbuja...");
                    bubbleSort(arr);
                    break;
                case "4":
                    System.out.println("Aplicando Merge Sort (Mezcla)...");
                    mergeSort(arr);
                    break;
                case "5":
                    System.out.println("Aplicando Quick Sort (Rápido)...");
                    quickSort(arr);
                    break;
                case "6":
                    System.out.println("Arreglo actual:");
                    printArray(arr);
                    break;
                case "7":
                    System.out.print("Ingrese palabra a buscar: ");
                    String target = sc.nextLine();
                    int idx = binarySearch(arr, target);
                    if (idx >= 0) {
                        System.out.println("Palabra encontrada en índice: " + idx);
                    } else {
                        System.out.println("Palabra no encontrada.");
                    }
                    break;
                case "0":
                    exit = true;
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        }

        sc.close();
        System.out.println("Programa terminado.");
    }

    /*
     * SELECTION SORT (ordenamiento por selección)
     * Idea: en cada pasada buscamos el elemento mínimo en la parte no ordenada
     * y lo intercambiamos con la posición de inicio de esa parte.
     * Complejidad temporal: O(n^2) en todos los casos (mejor, promedio y peor).
     * Espacio adicional: O(1) (in-place).
     */
    public static void selectionSort(String[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            int minIdx = i; // asumimos mínimo en i
            for (int j = i + 1; j < n; j++) {
                // comparamos lexicográficamente cadenas
                if (arr[j].compareTo(arr[minIdx]) < 0) {
                    minIdx = j; // nuevo mínimo
                }
            }
            // swap: colocamos el mínimo al frente
            String tmp = arr[i];
            arr[i] = arr[minIdx];
            arr[minIdx] = tmp;
            System.out.println("Después de pasada " + (i + 1) + ":");
            printArray(arr);
        }
    }

    /*
     * INSERTION SORT (ordenamiento por inserción)
     * Idea: construimos una parte ordenada a la izquierda; tomamos el siguiente
     * elemento y lo insertamos en la posición correcta desplazando los mayores.
     * Complejidad: O(n^2) promedio y peor, O(n) en el mejor caso (ya ordenado).
     * Espacio: O(1) extra (in-place) si se implementa con desplazamientos.
     */
    public static void insertionSort(String[] arr) {
        int n = arr.length;
        for (int i = 1; i < n; i++) {
            String key = arr[i];
            int j = i - 1;
            // desplazamos elementos mayores a la derecha
            while (j >= 0 && arr[j].compareTo(key) > 0) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key; // insertamos en su lugar
            System.out.println("Después de insertar elemento " + (i + 1) + ":");
            printArray(arr);
        }
    }

    /*
     * BUBBLE SORT (ordenamiento de burbuja)
     * Idea: repetidamente recorremos el arreglo y vamos intercambiando pares
     * adyacentes fuera de orden, así el mayor "burbujea" al final en cada pasada.
     * Complejidad: O(n^2) promedio/peor, O(n) mejor (si detectamos que ya está
     * ordenado).
     * Espacio: O(1) extra.
     */
    public static void bubbleSort(String[] arr) {
        int n = arr.length;
        boolean swapped;
        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            for (int j = 0; j < n - 1 - i; j++) {
                if (arr[j].compareTo(arr[j + 1]) > 0) {
                    String tmp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = tmp;
                    swapped = true;
                }
            }
            System.out.println("Después de pasada " + (i + 1) + ":");
            printArray(arr);
            if (!swapped)
                break; // ya ordenado
        }
    }

    // Helper para imprimir arreglo de Strings
    public static void printArray(String[] arr) {
        for (String s : arr) {
            System.out.print(s + " ");
        }
        System.out.println();
    }

    /*
     * MERGE SORT (mezcla) - divide y vencerás
     * Idea: dividir recursivamente el arreglo en mitades, ordenar cada mitad y
     * luego mezclar (merge) las dos sublistas ordenadas.
     * Complejidad temporal: O(n log n) en todos los casos.
     * Espacio adicional: O(n) por los arreglos temporales usados en la mezcla.
     */
    public static void mergeSort(String[] arr) {
        if (arr.length <= 1)
            return; // caso base
        mergeSortHelper(arr, 0, arr.length - 1);
    }

    private static void mergeSortHelper(String[] arr, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;
            mergeSortHelper(arr, left, mid);
            mergeSortHelper(arr, mid + 1, right);
            merge(arr, left, mid, right);
            System.out.println("Después de mezclar rango [" + left + "," + right + "]: ");
            printArray(arr);
        }
    }

    private static void merge(String[] arr, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        String[] L = new String[n1];
        String[] R = new String[n2];

        for (int i = 0; i < n1; i++)
            L[i] = arr[left + i];
        for (int j = 0; j < n2; j++)
            R[j] = arr[mid + 1 + j];

        int i = 0, j = 0, k = left;
        while (i < n1 && j < n2) {
            if (L[i].compareTo(R[j]) <= 0) {
                arr[k++] = L[i++];
            } else {
                arr[k++] = R[j++];
            }
        }
        while (i < n1)
            arr[k++] = L[i++];
        while (j < n2)
            arr[k++] = R[j++];
    }

    /*
     * QUICK SORT (rápido)
     * Idea: elegir un pivote, particionar el arreglo colocando menores a la izq
     * y mayores a la der, luego ordenar recursivamente las particiones.
     * Complejidad: O(n log n) promedio, O(n^2) peor (pivote muy desbalanceado).
     * Espacio adicional: O(log n) en promedio por la recursión.
     */
    public static void quickSort(String[] arr) {
        quickSortHelper(arr, 0, arr.length - 1);
    }

    private static void quickSortHelper(String[] arr, int low, int high) {
        if (low < high) {
            int p = partition(arr, low, high);
            System.out.println("Después de particionar con pivote en índice " + p + ":");
            printArray(arr);
            quickSortHelper(arr, low, p - 1);
            quickSortHelper(arr, p + 1, high);
        }
    }

    // Particiona usando como pivote el último elemento
    private static int partition(String[] arr, int low, int high) {
        String pivot = arr[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (arr[j].compareTo(pivot) <= 0) {
                i++;
                String tmp = arr[i];
                arr[i] = arr[j];
                arr[j] = tmp;
            }
        }
        String tmp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = tmp;
        return i + 1;
    }

    /*
     * BÚSQUEDA BINARIA (on sorted array)
     * Idea: comparar la clave con el elemento medio; si es menor, buscar en la
     * mitad izquierda, si es mayor, en la derecha; repetir hasta encontrar o
     * que los límites se crucen.
     * Complejidad: O(log n) tiempo, O(1) espacio.
     * Observación: requiere que el arreglo esté ordenado previamente.
     */
    public static int binarySearch(String[] arr, String target) {
        int left = 0;
        int right = arr.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int cmp = arr[mid].compareTo(target);
            System.out.println("Comparando con índice " + mid + " (" + arr[mid] + ")");
            if (cmp == 0)
                return mid; // encontrado
            if (cmp < 0) {
                left = mid + 1; // target es mayor, buscar a la derecha
            } else {
                right = mid - 1; // target es menor, buscar a la izquierda
            }
        }
        return -1; // no encontrado
    }

}

public class App {
    public static void main(String[] args) throws Exception {
        // int[] nums = {13, 5, 45, 20, 3, 48, 9, 50};
        // System.out.println("Original array:");
        // printArray(nums);
        // selectionSort(nums);
        // System.out.println("Sorted array:");
        // printArray(nums);


        // int[] nums2 = {13, 5, 45, 20, 3, 48, 9, 50};
        // System.out.println("Original array for Insertion Sort:");
        // printArray(nums2);
        // insertionSort(nums2);
        // System.out.println("Sorted array:");
        // printArray(nums2);

        // int[] nums3 = {13, 5, 45, 20, 3, 48, 9, 50};
        // System.out.println("Original array for Bubble Sort:");
        // printArray(nums3);
        // bubbleSort(nums3);
        // System.out.println("Sorted array:");
        // printArray(nums3);

        int[] nums4 = {13, 5, 45, 20, 3, 48, 9, 50, 0};
        System.out.println("Original array for Merge Sort:");
        printArray(nums4);
        mergeSort(nums4);
        System.out.println("Sorted array:");
        printArray(nums4);

        int[] nums5 = {13, 5, 45, 20, 3, 48, 9, 50, 0};
        System.out.println("Original array for Quick Sort:");
        printArray(nums5);
        quickSort(nums5);
        System.out.println("Sorted array:");
        printArray(nums5);
    }

    // Selection Sort: busca el mínimo y lo pone al frente. O(n^2) tiempo, O(1) espacio.
    public static void selectionSort(int[] nums) {
        int n = nums.length;
        int min;
        int temp;

        for (int i = 0; i < n - 1; i++) {
            min = i; // asumimos que el mínimo está en i

            for (int j = i + 1; j < n; j++) {
                if (nums[j] < nums[min]) {
                    min = j; // encontramos un nuevo mínimo
                }
            }

            // swap: colocamos el mínimo al inicio de la parte no ordenada
            temp = nums[i];
            nums[i] = nums[min];
            nums[min] = temp;
            System.out.println("After pass " + (i + 1) + ":");
            printArray(nums);
        }
    }

    // Insertion Sort: inserta cada elemento en la parte ya ordenada. O(n^2) prom/peor, O(n) mejor.
    public static void insertionSort(int[] nums) {
        int n = nums.length;
        int[] newNums = new int[n];
        int insertPosition = 0;
        int amountInserted = 0;

        for (int i = 0; i < n; i++) {
            if(i == 0) { // primer elemento ya está "ordenado"
                newNums[0] = nums[0];
            }
            else{
                // buscamos dónde insertar en la parte ordenada
                for(int j = 0; j <= amountInserted; j++) {
                    if(nums[i] < newNums[j]) {
                        insertPosition = j;
                        break;
                    }
                    insertPosition = amountInserted;
                }

                // desplazamos a la derecha para abrir espacio
                for(int k = n - 1; k >= insertPosition + 1; k--) {
                    newNums[k] = newNums[k - 1];
                }

                // insertamos el elemento
                newNums[insertPosition] = nums[i];
            }
            amountInserted++;
            insertPosition = 0;
            System.out.println("After inserting element " + (i + 1) + ":");
            printArray(newNums);
        }

        for(int i = 0; i < n; i++) {
            nums[i] = newNums[i];
        }
    }

    // Bubble Sort: hace "burbujear" el mayor al final en cada pasada. O(n^2) prom/peor, O(n) mejor.
    public static void bubbleSort(int[] nums) {
        int n = nums.length;
        int temp;
        boolean swapped;

        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            for (int j = 0; j < n - 1 - i; j++) {
                if (nums[j] > nums[j + 1]) {
                    temp = nums[j];
                    nums[j] = nums[j + 1];
                    nums[j + 1] = temp;
                    swapped = true;
                }
            }
            System.out.println("After pass " + (i + 1) + ":");
            printArray(nums);
            if (!swapped) break; // Array is sorted
        }
    }

    public static void printArray(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    // Merge Sort: divide y conquista; une mitades ordenadas. O(n log n) tiempo, O(n) espacio.
    public static void mergeSort(int[] nums) {
        // caso base: 0 o 1 elemento ya está ordenado
        if (nums.length <= 1) {
            return;
        }
        mergeSortHelper(nums, 0, nums.length - 1);
    }

    private static void mergeSortHelper(int[] nums, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;

            // ordenar ambas mitades
            mergeSortHelper(nums, left, mid);
            mergeSortHelper(nums, mid + 1, right);

            // unir las mitades ordenadas
            merge(nums, left, mid, right);

            System.out.println("After merging [" + left + " to " + right + "]:");
            printArray(nums);
        }
    }

    private static void merge(int[] nums, int left, int mid, int right) {
        // tamaños de subarreglos a unir
        int n1 = mid - left + 1;
        int n2 = right - mid;

        // arreglos temporales
        int[] leftArray = new int[n1];
        int[] rightArray = new int[n2];

        // copiamos datos a temporales
        for (int i = 0; i < n1; i++) {
            leftArray[i] = nums[left + i];
        }
        for (int j = 0; j < n2; j++) {
            rightArray[j] = nums[mid + 1 + j];
        }

        // unimos comparando elementos
        int i = 0, j = 0;
        int k = left;

        while (i < n1 && j < n2) {
            if (leftArray[i] <= rightArray[j]) {
                nums[k] = leftArray[i];
                i++;
            } else {
                nums[k] = rightArray[j];
                j++;
            }
            k++;
        }

        // copiar sobrantes de la izquierda
        while (i < n1) {
            nums[k] = leftArray[i];
            i++;
            k++;
        }

        // copiar sobrantes de la derecha
        while (j < n2) {
            nums[k] = rightArray[j];
            j++;
            k++;
        }
    }

    // Quick Sort: particiona por pivote y ordena lados. O(n log n) prom, O(n^2) peor, O(log n) espacio.
    public static void quickSort(int[] nums) {
        if (nums.length < 2) {
            return;
        }
        quickSortHelper(nums, 0, nums.length - 1);
    }

    private static void quickSortHelper(int[] nums, int low, int high) {
        if (low < high) {
            // particionar: todo menor a izq, mayor a der del pivote
            int pivotIndex = partition(nums, low, high);

            System.out.println("After partition with pivot at index " + pivotIndex + ":");
            printArray(nums);

            // ordenar recursivamente ambos lados del pivote
            quickSortHelper(nums, low, pivotIndex - 1);
            quickSortHelper(nums, pivotIndex + 1, high);
        }
    }

    private static int partition(int[] nums, int low, int high) {
        // pivote = último elemento
        int pivot = nums[high];
        int i = low - 1; // Index of smaller element

        for (int j = low; j < high; j++) {
            // si nums[j] <= pivote, lo mandamos a la izquierda
            if (nums[j] <= pivot) {
                i++;
                // swap con la frontera de los menores
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
            }
        }

        // colocamos el pivote en su lugar final
        int temp = nums[i + 1];
        nums[i + 1] = nums[high];
        nums[high] = temp;

        return i + 1;
    }

    public static void binarySearch(int[] nums, int target) {
        // Variables requeridas para el control
        int n = nums.length;
        int pivot = (n / 2) - 1;
        int minLimit = 0;
        int maxLimit = n - 1;

        // El ciclo se usa para seguir reduciendo el espacio de búsqueda
        while (nums[pivot] != target) {
            // Se ajustan los límites según la comparación
            if (nums[pivot] > target) {
                maxLimit = pivot - 1;
            } else {
                minLimit = pivot + 1;
            }
            // Si los limetes se cruzan, el elemento no está
            if (minLimit > maxLimit) {
                System.out.println("Element not found");
            }

            // Se redefine el pivote en base a los limetes actualizados
            pivot = (maxLimit + minLimit) / 2;
        }

        System.out.println("Element found at index: " + pivot);

    }

}

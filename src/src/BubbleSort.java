import java.util.function.Consumer;

public class BubbleSort {
    //consumer is a functional interface, represents operation that takes an input and does some action without returning a result
    public static void sort(int[] array, java.util.function.Consumer<int[]> stepConsumer, Consumer<Integer> selectedIndexConsumer) {
        int n = array.length;
        boolean swapped;
        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                selectedIndexConsumer.accept(j);

                if (array[j] > array[j + 1]) {
                    // Swap array[j] and array[j+1]
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    swapped = true;
                }
                // calls the accept function of the consumer function.
                //sends the current state of the array
                stepConsumer.accept(array);
            }
            // If no two elements were swapped, the array is sorted
            if (!swapped) break;
        }
    }
}



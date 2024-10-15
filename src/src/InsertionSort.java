import java.util.function.Consumer;

public class InsertionSort {

    public static void sort(int[] arr, Consumer<int[]> stepConsumer,Consumer<Integer> selectedIndexConsumer) {
        int n = arr.length;

        // Iterate through the array starting from the second element
        for (int i = 1; i < n; i++) {
            int key = arr[i]; // The element to be inserted
            int j = i - 1; // Index of the last sorted element
            selectedIndexConsumer.accept(i);

            // Move elements of arr[0..i-1], that are greater than key,
            // to one position ahead of their current position
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j]; // Shift element to the right
                j--;
                selectedIndexConsumer.accept(j);
                stepConsumer.accept(arr.clone());
            }
            arr[j + 1] = key; // Insert the key in its correct position

            // Capture the current state of the array for visualization
            stepConsumer.accept(arr.clone()); // Clone to keep the current state
        }
    }
}

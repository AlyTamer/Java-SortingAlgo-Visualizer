import java.util.function.Consumer;

public class SelectionSort {
    public static void sort(int[] arr, Consumer<int[]> stepConsumer) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                int temp = arr[i];
                arr[i] = arr[minIndex];
                arr[minIndex] = temp;
                stepConsumer.accept(arr.clone());
            }
        }
    }
}

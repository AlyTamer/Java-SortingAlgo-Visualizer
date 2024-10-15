import java.util.function.Consumer;

public class SelectionSort {
    public static void sort(int[] arr, Consumer<int[]> stepConsumer,Consumer<Integer> selectedIndexConsumer) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            selectedIndexConsumer.accept(minIndex);
            for (int j = i + 1; j < n; j++) {
                selectedIndexConsumer.accept(j);
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
                stepConsumer.accept(arr.clone());
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

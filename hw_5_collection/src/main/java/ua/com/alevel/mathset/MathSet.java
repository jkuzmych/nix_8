package ua.com.alevel.mathset;

import java.math.BigDecimal;
import java.util.Objects;

public class MathSet {
    private final int CAPACITY = 20;
    private int size;
    private Number[] mathSet;

    public MathSet() {
        mathSet = new Number[CAPACITY];
        size = 0;
    }

    public MathSet(int size) {
        mathSet = new Number[size];
        this.size = 0;
    }

    public MathSet(Number[] numbers) {
        mathSet = new Number[numbers.length];
        size = 0;
        addArrayToMathSet(numbers);
    }

    public MathSet(Number[]... numbers) {
        mathSet = new Number[numbers.length];
        size = 0;
        addArrayToMathSet(mergeArrays(numbers));
    }

    MathSet(MathSet numbers) {
        mathSet = new Number[numbers.getMathSet().length];
        size = 0;
        addArrayToMathSet(numbers.getMathSet());
    }

    MathSet(MathSet... numbers) {
        mathSet = new Number[numbers[0].getMathSet().length];
        size = 0;
        for(int i=0;i<numbers.length;i++) {
            addArrayToMathSet(mergeArrays(numbers[i].getMathSet()));
        }
    }

    public int getSize() {
        return size;
    }

    public Number[] getMathSet() {
        return mathSet;
    }

    public void add(Number n) {
        if (!contains(n) && n != null) {
            if (size == mathSet.length) resize();
            mathSet[size] = n;
            size++;
        }
    }

    public void add(Number... n) {
        for (Number num : n) {
            add(num);
        }
    }

    public void join(MathSet ms) {
        addArrayToMathSet(mergeArrays(ms.getMathSet()));
    }

    public void join(MathSet... ms) {
        for (MathSet count : ms) {
            addArrayToMathSet(mergeArrays(count.getMathSet(), mathSet));
        }
    }

    public void intersection(MathSet ms) {
        int numberOfCommon = 0;
        for (int i = 0; i < ms.getMathSet().length; i++) {
            if (contains(ms.getMathSet()[i])) {
                numberOfCommon++;
            }
        }
        int count = 0;
        Number newMathSet[] = new Number[numberOfCommon];
        for (int i = 0; i < ms.getMathSet().length; i++) {
            if (contains(ms.getMathSet()[i])) {
                newMathSet[count] = ms.getMathSet()[i];
                count++;
            }
        }
        mathSet = newMathSet;
        size = numberOfCommon;
    }

    public void intersection(MathSet... ms) {
        for (MathSet count : ms) {
            intersection(count);
        }
    }

    public void sortDesc() {
        sortDesc(mathSet);
    }

    public void sortDesc(int firstIndex, int lastIndex) {
        Number[] array = toArray(firstIndex, lastIndex);
        sortDesc(array);
        for (int i = 0; i < array.length; i++) {
            mathSet[i + firstIndex] = array[i];
        }
    }

    public void sortDesc(Number value) {
        int firstIndex = 0;
        for (int i = 0; i < size; i++) {
            if (Objects.equals(mathSet[i], value)) {
                firstIndex = i;
                break;
            }
        }
        sortDesc(firstIndex, size);
    }

    public void sortAsc() {
        sort(mathSet);
    }

    public void sortAsc(int firstIndex, int lastIndex) {
        Number[] array = toArray(firstIndex, lastIndex);
        sort(array);
        for (int i = 0; i < array.length; i++) {
            mathSet[i + firstIndex] = array[i];
        }
    }

    public void sortAsc(Number value) {
        int firstIndex = 0;
        for (int i = 0; i < size; i++) {
            if (Objects.equals(mathSet[i], value)) {
                firstIndex = i;
                break;
            }
        }
        sortAsc(firstIndex, size);
    }

    public Number get(int index) {
        return mathSet[index];
    }

    public Number getMax() {
        Number max = Long.MIN_VALUE;
        for (int i = 0; i < mathSet.length; i++) {
            if (mathSet[i] != null)
                if (isBigger(mathSet[i], max)) max = mathSet[i];
        }
        return max;
    }

    public Number getMin() {
        Number min = Long.MAX_VALUE;
        for (int i = 0; i < mathSet.length; i++) {
            if (mathSet[i] != null)
                if (isBigger(min, mathSet[i])) min = mathSet[i];
        }
        return min;
    }

    public Number getAverage() {
        Number sum = 0;
        int count = 0;
        for (int i = 0; i < mathSet.length; i++) {
            if (mathSet[i] == null) break;
            sum = sum(sum, mathSet[i]);
            count++;
        }
        return sum.doubleValue() / count;
    }

    public Number getMedian() {
        Number[] ms = new Number[getSize()];
        for (int i = 0; i < size; i++) {
            ms[i] = getMathSet()[i];
        }
        sort(ms);
        if (ms.length % 2 == 1) {
            return ms[ms.length / 2];
        } else return (sum(ms[ms.length / 2 - 1], ms[ms.length / 2]).doubleValue() / 2);
    }

    public Number[] toArray() {
        return mathSet;
    }

    public Number[] toArray(int firstIndex, int lastIndex) {
        Number[] result = new Number[lastIndex - firstIndex + 1];
        for (int i = 0; i < result.length; i++) {
            result[i] = mathSet[i + firstIndex];
        }
        return result;
    }

    public MathSet cut(int firstIndex, int lastIndex) {
        lastIndex++;
        for (int i = firstIndex; i < lastIndex; i++) {
            mathSet[i] = mathSet[i + lastIndex - firstIndex];
            mathSet[i + lastIndex - firstIndex] = null;
        }
        size -= (lastIndex - firstIndex);
        return new MathSet(mathSet);
    }

    public void clear() {
        for (int i = 0; i < mathSet.length; i++) {
            mathSet[i] = null;
        }
        size = 0;
    }

    public void clear(Number[] numbers) {
        for (Number number : numbers) {
            for (int i = 0; i < mathSet.length; i++) {
                if (mathSet[i] != null && (Objects.equals(mathSet[i], number))) {
                    mathSet[i] = null;
                }
            }
        }
    }

    void sort(Number arr[]) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            if (arr[i] == null) break;
            int min_idx = i;
            for (int j = i + 1; j < n; j++)
                if (arr[j] != null && isBigger(arr[min_idx], arr[j]))
                    min_idx = j;
            Number temp = arr[min_idx];
            arr[min_idx] = arr[i];
            arr[i] = temp;
        }
    }

    void sortDesc(Number[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int maxElementIndex = i;
            if (arr[i] == null) break;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] == null) break;
                if (isBigger(arr[j], arr[maxElementIndex])) {
                    maxElementIndex = j;
                }
            }
            if (maxElementIndex != i) {
                Number temp = arr[i];
                arr[i] = arr[maxElementIndex];
                arr[maxElementIndex] = temp;
            }
        }
    }

    public Number sum(Number n1, Number n2) {
        BigDecimal b1 = new BigDecimal(n1.doubleValue());
        BigDecimal b2 = new BigDecimal(n2.doubleValue());
        return b1.add(b2);
    }

    boolean isBigger(Number n1, Number n2) {
        BigDecimal b1 = new BigDecimal(n1.doubleValue());
        BigDecimal b2 = new BigDecimal(n2.doubleValue());
        return b1.compareTo(b2) > 0;
    }

    public boolean contains(Number n) {
        if (mathSet != null) {
            for (int i = 0; i < mathSet.length; i++) {
                if (n == null) break;
                if (n.equals(mathSet[i])) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    public void resize() {
        Number[] bufferarray = new Number[mathSet.length * 2];
        if (size > 0) {
            for (int i = 0; i < size; i++) {
                bufferarray[i] = mathSet[i];
            }
        }
        mathSet = bufferarray;
        //capacity = capacity * 2;

    }

    private void addArrayToMathSet(Number[] numbers) {
        for (int i = 0; i < numbers.length; i++) {
            add(numbers[i]);
        }
    }

    private Number[] mergeArrays(Number[]... numbers) {
        int length = 0;
        int count = 0;
        for (Number[] n : numbers) {
            length += n.length;
        }
        Number[] result = new Number[length];
        for (Number[] n : numbers) {
            for (Number num : n) {
                result[count] = num;
                count++;
            }
        }
        return result;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < size; i++) {
            stringBuilder.append(mathSet[i]).append(" ");
        }
        return stringBuilder.toString();
    }

}
package Lesson3;

/* Создать массив с набором слов (10-20 слов, должны встречаться повторяющиеся).
    Найти и вывести список уникальных слов, из которых состоит массив (дубликаты не считаем).
    Посчитать сколько раз встречается каждое слово.
 */
import java.util.*;

public class CollectionsLearning {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            list.add("Number"+i);
        }
        list.addAll(Arrays.asList("Number2", "Number3", "Number4", "Number7"));
        Set<String> set = new LinkedHashSet<>(list);
    Iterator<String> iteratorSet = set.iterator();

    while (iteratorSet.hasNext()) {
        int count = 0;
        String setSample = iteratorSet.next();
        for (String listSample : list) {
            if(setSample.equals(listSample)) { count++; }
        }
        String s = "";
        System.out.println("The word - \"" + setSample + "\" was found in the list " +
                            count + " time" + (count > 1 ? "s" : "") + ".");
        }
    }
}

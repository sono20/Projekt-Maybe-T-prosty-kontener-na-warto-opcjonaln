package zad2;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;

public class XList<T> extends ArrayList<T> {
    public XList() {
        super();
    }

    public XList(Collection<? extends T> collection) {
        super(collection);
    }

    public XList(int... nums) {
        super();
        for (int num : nums) {
            this.add((T) Integer.valueOf(num));
        }
    }

    public XList(Integer[] nums) {
        super();
        this.addAll((Collection<? extends T>) Arrays.asList(nums));
    }

    public static <T> XList<T> of(T... elements) {
        XList<T> list = new XList<>();
        Collections.addAll(list, elements);
        return list;
    }


    public static <T> XList<T> of(Collection<? extends T> collection) {
        return new XList<>(collection);
    }

    public static XList<Character> charsOf(String napis) {
        XList<Character> list = new XList<>();
        for (Character c : napis.toCharArray()) {
            list.add(c);
        }
        return list;
    }

    public static XList<String> tokensOf(String napis) {
        return tokensOf(napis, "\\s+");
    }

    public static XList<String> tokensOf(String napis, String sep) {
        XList<String> list = new XList<>();
        String[] tokens = napis.split(sep);
        Collections.addAll(list, tokens);
        return list;
    }

    public XList<T> union(Collection<? extends T> collection) {
        XList<T> result = new XList<>();
        result.addAll(this);
        result.addAll(collection);
        return result;
    }

    public final XList<T> union(T[] elements) {
        XList<T> result = new XList<>();
        result.addAll(this);
        Collections.addAll(result, elements);
        return result;
    }


    public XList<T> diff(Collection<? extends T> collection) {
        XList<T> result = new XList<>(this);
        result.removeAll(collection);
        return result;
    }

    public XList<T> unique() {
        return new XList<>(new LinkedHashSet<>(this));
    }

    public XList<XList<T>> combine() {
        XList<XList<T>> result = new XList<>();

        if (this.isEmpty()) {
            return result;
        }

        List<List<T>> lists = new ArrayList<>();
        for (Object obj : this) {
            if (obj instanceof Collection) {
                lists.add(new ArrayList<>((Collection<T>) obj));
            }
        }
        if (lists.isEmpty()) {
            return result;
        }
        combine(lists, result, new XList<>(), 0);
        return result;
    }


    private void combine(List<List<T>> lists, XList<XList<T>> result, XList<T> current, int depth) {
        if (depth == lists.size()) {
            result.add(new XList<>(current));
            return;
        }

        for (T item : lists.get(depth)) {
            current.add(item);
            combine(lists, result, current, depth + 1);
            current.remove(current.size() - 1);
        }
    }

    public <R> XList<R> collect(Function<T, R> mapper) {
        XList<R> result = new XList<>();
        for (T element : this) {
            result.add(mapper.apply(element));
        }
        return result;
    }
    public String join() {
        return join("");
    }

    public String join(String sep) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.size(); i++) {
            sb.append(this.get(i));
            if (i < this.size() - 1) {
                sb.append(sep);
            }
        }
        return sb.toString();
    }
    public void forEachWithIndex(BiConsumer<T, Integer> consumer){
        for(int i=0;i<this.size();i++){
            consumer.accept(this.get(i),i);
        }
    }
}



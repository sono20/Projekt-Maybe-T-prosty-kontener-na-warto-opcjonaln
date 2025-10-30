package zad1;

import java.util.NoSuchElementException;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class Maybe<T> {
    private final T value;

    public Maybe(T value) {
        this.value = value;
    }

    public static <T> Maybe<T> of(T x) {
        return new Maybe<>(x);
    }

    public void ifPresent(Consumer<T> cons) {
        if (value != null) {
            cons.accept(value);
        }
    }

    public <R> Maybe<R> map(Function<T, R> func) {
        if (value == null) {
            return Maybe.of(null);
        }
        R result = func.apply(value);
        return Maybe.of(result);
    }

    public T get() {
        if (value == null) {
            throw new NoSuchElementException("maybe is empty");
        } else {
            return value;
        }

    }

    public boolean isPresent() {
        if (value == null) {
            return false;
        }
        return true;
    }

    public T orElse(T defVal) {
        if (value == null) {
            return defVal;
        }
        return value;

    }
    public Maybe<T> filter(Predicate<T> pred){
        if(value == null){
            return this;
        }
        if(pred.test(value)){
            return this;
        }
        return null;
    }
    @Override
    public String toString() {
        if(value == null){
            return "Maybe is empty";
        }
        return "Maybe has value " + value;
    }
}

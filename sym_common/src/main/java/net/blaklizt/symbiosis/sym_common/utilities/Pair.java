package net.blaklizt.symbiosis.sym_common.utilities;

import java.io.Serializable;
import java.util.*;

import static java.util.stream.Collectors.toList;


public class Pair<T, K> implements Serializable {

    private T car;
    private K cdr;

    public Pair() {
    }

    public Pair(T car, K cdr) {
        this.car = car;
        this.cdr = cdr;
    }

    @SuppressWarnings({"unchecked"})
    public static <T, K> Pair<T, K> cons(T car, K cdr) {
        return new Pair(car, cdr);
    }

    public static <T, K> Pair<T, K> p(T car, K cdr) {
        return cons(car, cdr);
    }



    @SuppressWarnings({"unchecked"})
    public static <T> Pair<T, ?>[] filter(Pair<T, ?>... pairs) {
        List<Pair<T, ?>> pairList = filterList(pairs);
        return pairList.toArray(new Pair[pairList.size()]);
    }

    @SafeVarargs
    public static <T> List<Pair<T, ?>> filterList(Pair<T, ?>... pairs) {
        return Arrays.stream(pairs)
                .filter((pair) -> pair.getRight() != null)
                .collect(toList());
    }

    public T getCar() {
        return car;
    }

    public K getCdr() {
        return cdr;
    }

    public T getLeft() {
        return getCar();
    }

    public K getRight() {
        return getCdr();
    }

    @Override
    public String toString() {
        return "(" + car + '=' + cdr + ')';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Pair)) {
            return false;
        }

        Pair pair = (Pair) o;

        return !(car != null ? !car.equals(pair.car) : pair.car != null) && !(cdr != null ? !cdr.equals(pair.cdr) : pair.cdr != null);

    }

    @Override
    public int hashCode() {
        int result = car != null ? car.hashCode() : 0;
        result = 31 * result + (cdr != null ? cdr.hashCode() : 0);
        return result;
    }

    @SafeVarargs
    public static <T, K> Map<T, K> map(Pair<T, K>... pairs) {
        LinkedHashMap<T, K> map = new LinkedHashMap<>();
        for (Pair<T, K> pair : pairs) {
            map.put(pair.car, pair.cdr);
        }
        return map;
    }

    public static <T,K> Pair<T,K>[] fromMap(Map<T,K> map) {
        Pair<T,K>[] pairs = new Pair[map.size()];
        Set<T> keys = map.keySet();
        int cnt=0;
        for (T key : keys) {
            pairs[cnt++] = p(key, map.get(key));
        }
        return pairs;
    }

    public String join(String delimiter) {
        return safeToString(car) + delimiter + safeToString(cdr);
    }

    public String join() {
        return join(" ");
    }

    private String safeToString(Object value) {
        return (value != null) ? value.toString() : "empty";
    }


}

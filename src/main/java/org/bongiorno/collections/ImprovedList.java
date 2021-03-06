package org.bongiorno.collections;

import java.util.*;
import java.util.function.Function;

import static java.util.stream.Collectors.toCollection;

/**
 * @author chribong
 */
public class ImprovedList<T> extends QuickCollection<T> implements List<T>{

    public ImprovedList() {
        super(new ArrayList<>());
    }

    public ImprovedList(List<T> interiorDelegate) {
        super(interiorDelegate);
    }

    public static <T> ImprovedList<T> improve(List<T> inferiorList) {
        return new ImprovedList<>(inferiorList);
    }

    public static <T> ImprovedList<T> of(List<T> inferiorList) {
        return new ImprovedList<>(inferiorList);
    }


    @SafeVarargs
    public static <T> ImprovedList<T> of(T ... stuff) {
        return new ImprovedList<>(new ArrayList<>(Arrays.asList(stuff)));
    }
    public <O> ImprovedList<O> transform(Function<? super T, ? extends O> f) {
        return this.stream().map(f).collect(toCollection(ImprovedList::new));
    }


    @Override
    public void add(int index, T element) {
        ((List<T>)super.delegate).add(index,element);
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        return ((List<T>)super.delegate).addAll(index,c);
    }

    @Override
    public T get(int index) {
        return ((List<T>)super.delegate).get(index);
    }

    @Override
    public T set(int index, T element) {
        return ((List<T>)super.delegate).set(index,element);
    }

    @Override
    public T remove(int index) {
        return ((List<T>)super.delegate).remove(index);
    }

    @Override
    public int indexOf(Object o) {
        return ((List<T>)super.delegate).indexOf(o);
    }

    @Override
    public int lastIndexOf(Object o) {
        return ((List<T>)super.delegate).lastIndexOf(o);
    }

    @Override
    public ListIterator<T> listIterator() {
        return ((List<T>)super.delegate).listIterator();
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        return ((List<T>)super.delegate).listIterator(index);
    }

    @Override
    public ImprovedList<T> subList(int fromIndex, int toIndex) {
        return new ImprovedList<> ((List<T>)super.delegate).subList(fromIndex,toIndex);
    }

    public ImprovedList<T> subList(int fromIndex) {
        return new ImprovedList<> (((List<T>)super.delegate).subList(fromIndex,this.size()));
    }

    public static class ImprovedListStream<T> extends ImprovedStream<T> {
        public ImprovedListStream(ImprovedStream<T> delegate) {
            super(delegate);
        }

        public <O> ImprovedList<O> transform(Function<? super T, ? extends O> f) {
            return delegate.map(f).collect(toCollection(ImprovedList::new));
        }

        public  ImprovedList<T> collect() {
            return delegate.collect(toCollection(ImprovedList::new));
        }


    }
}

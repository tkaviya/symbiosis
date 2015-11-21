package net.blaklizt.symbiosis.sym_common.utilities;


@FunctionalInterface
public interface TypeTransformer<T, K> {

    K transform(T instance);

}

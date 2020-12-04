package it.FRED.GuidaTv.framework.data;

/*
 * @param <KT> the key type
 */
public interface DataItem<KT> {

    KT getKey();

    long getVersion();

    void setKey(KT key);

    void setVersion(long version);

}

package org.qaasiems.pcStore.repositories;

import java.util.List;

/**
 * Created by qaasiems on 2017/10/05.
 */
public interface RestAPI<S, ID> {
    S get(ID id);

    String post(S entity);

    String put(S entity);

    String delete(S entity);

    List<S> getAll();
}

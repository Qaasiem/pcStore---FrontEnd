package org.qaasiems.pcStore.services;

import java.util.List;

/**
 * Created by qaasiems on 2017/10/09.
 */
public interface Services<S, ID> {

    public S findById(ID id);

    public String save(S entity);

    public String update(S entity);

    public String delete(S entity);

    public List<S> findAll();
}

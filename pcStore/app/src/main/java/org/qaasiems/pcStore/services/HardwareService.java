package org.qaasiems.pcStore.services;

import org.jabaar91.pcStore.model.Hardware;

import java.util.List;

/**
 * Created by qaasiems on 2017/10/09.
 */
public interface HardwareService {

    Hardware getHardwareItem(Long id);
    List getAllHardwareItems();
    String insertHardwareItem(Hardware hardware);
    String deleteHardwareItem(Hardware hardware);
}

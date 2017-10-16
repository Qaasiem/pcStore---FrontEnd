package org.qaasiems.pcStore.repositories;

import org.qaasiems.pcStore.model.Hardware;

import java.util.List;

/**
 * Created by qaasiems on 2017/10/05.
 */
public interface RestAPIHardware {

    Hardware getHardwareItem(Long id);
    List<Hardware> getAllHardwareItems();
    String insertHardwareItem(Hardware hardware);
    String deleteHardwareItem(Hardware hardware);
}

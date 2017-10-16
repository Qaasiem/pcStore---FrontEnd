package org.qaasiems.pcStore.services.implementation;

import org.jabaar91.pcStore.model.Hardware;
import org.jabaar91.pcStore.repositories.RestAPIHardware;
import org.jabaar91.pcStore.repositories.rest.RestHardwareAPI;
import org.jabaar91.pcStore.services.HardwareService;

import java.util.List;

/**
 * Created by qaasiems on 2017/10/09.
 */
public class HardwareServiceImpl implements HardwareService {

    final RestAPIHardware rest = new RestHardwareAPI();

    public Hardware getHardwareItem(Long id) {

        return rest.getHardwareItem(id);
    }

    public List<Hardware> getAllHardwareItems() {

        return rest.getAllHardwareItems();
    }

    public String insertHardwareItem(Hardware hardware) {

        return rest.insertHardwareItem(hardware);
    }

    public String deleteHardwareItem(Hardware hardware) {

        return rest.deleteHardwareItem(hardware);
    }
}

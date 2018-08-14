package com.z0976190100.restingnashorn.service;

import com.z0976190100.restingnashorn.persistence.entity.ClientScript;
import org.springframework.stereotype.Service;

import static com.z0976190100.restingnashorn.util.AppVariables.scriptsToProceed;

@Service
public class UploadService {

    public ClientScript buildScript(String ascript) {
        return new ClientScript(ascript);
    }

    // LILO variant of
    public ClientScript registerScript(ClientScript clientScript) {

        scriptsToProceed.add(clientScript);
        int id = scriptsToProceed.size();
        clientScript.setId(id);

        return clientScript;
    }
}

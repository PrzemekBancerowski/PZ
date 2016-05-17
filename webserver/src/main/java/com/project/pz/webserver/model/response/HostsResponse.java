package com.project.pz.webserver.model.response;

import java.util.List;
import java.util.Map;

/**
 * Created by Piotr Sołtysiak on 2016-05-17.
 * Contact: piotrek.soltysiak@gmail.com
 */
public class HostsResponse {

    private Map<String, List<String>> hosts;

    public HostsResponse() {
    }

    public Map<String, List<String>> getHosts() {
        return hosts;
    }

    public void setHosts(Map<String, List<String>> hosts) {
        this.hosts = hosts;
    }
}



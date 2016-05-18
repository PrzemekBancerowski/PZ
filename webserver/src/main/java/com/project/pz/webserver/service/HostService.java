package com.project.pz.webserver.service;

import com.project.pz.webserver.exception.MonitorNotFoundException;
import com.project.pz.webserver.exception.MonitorNotUniqueException;
import com.project.pz.webserver.model.response.HostResponse;

/**
 * Created by Piotr Sołtysiak on 2016-05-17.
 * Contact: piotrek.soltysiak@gmail.com
 */
public interface HostService {

    HostResponse getHostsForMonitor(Integer monitorId) throws MonitorNotFoundException, MonitorNotUniqueException;



}

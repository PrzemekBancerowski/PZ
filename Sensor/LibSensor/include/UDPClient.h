//
// Created by pSolT on 05.05.16.
//

#ifndef SENSOR_UDPCLIENT_H
#define SENSOR_UDPCLIENT_H

#include <iostream>
#include <boost/asio.hpp>
#include <boost/array.hpp>

using boost::asio::ip::udp;

class UDPClient {
public:
    UDPClient(boost::asio::io_service& ioService,
              const std::string& host,
              const std::string& port);
    ~UDPClient();
    void Send(std::string message);

private:
    boost::asio::io_service& _ioService;
    udp::endpoint _endpoint;
    udp::socket _socket;
};


#endif //SENSOR_UDPCLIENT_H

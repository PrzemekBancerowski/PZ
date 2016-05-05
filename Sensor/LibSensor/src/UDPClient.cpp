//
// Created by pSolT on 05.05.16.
//

#include "../include/UDPClient.h"

UDPClient::UDPClient(boost::asio::io_service &ioService, const std::string &host, const std::string &port)
: _ioService(ioService), _socket(ioService, udp::endpoint(udp::v4(), 0))
{
    udp::resolver resolver(_ioService);
    udp::resolver::query query(udp::v4(), host, port);
    udp::resolver::iterator iter = resolver.resolve(query);
    _endpoint = *iter;
}

UDPClient::~UDPClient()
{
    _socket.close();
}

void UDPClient::Send(std::string message)
{
    _socket.send_to(boost::asio::buffer(message, message.size()),_endpoint);
}
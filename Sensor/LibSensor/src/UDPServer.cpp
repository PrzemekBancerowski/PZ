//
// Created by pSolT on 07.05.16.
//

#include "../include/UDPServer.h"

UDPServer::UDPServer(boost::asio::io_service &io_service, unsigned short targetPort)
:  _socket(io_service, udp::endpoint(udp::v4(), targetPort))
{
    StartReceive();
}

void UDPServer::StartReceive()
{
    _socket.async_receive_from(
            boost::asio::buffer(_recv_buffer), _remote_endpoint,
            boost::bind(&UDPServer::HandleReceive, this,
                        boost::asio::placeholders::error,
                        boost::asio::placeholders::bytes_transferred));
}

void UDPServer::HandleReceive(const boost::system::error_code& error,
                    std::size_t bytesTransferred)
{
    std::cout << "RECEIVED " << bytesTransferred << " B of data!" << std::endl;
    if (!error || error == boost::asio::error::message_size)
    {
        std::string dataReceived;
        std::copy(_recv_buffer.begin(), _recv_buffer.begin()+bytesTransferred, std::back_inserter(dataReceived));
        std::cout << dataReceived << std::endl;
        StartReceive();
    }
    else
    {
        std::cout << error << std::endl;
    }
}
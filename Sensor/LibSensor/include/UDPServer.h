//
// Created by pSolT on 07.05.16.
//

#ifndef PZ_UDPSERVER_H
#define PZ_UDPSERVER_H


#include <boost/asio.hpp>
#include <boost/array.hpp>
#include <boost/bind.hpp>
#include <boost/shared_ptr.hpp>

using boost::asio::ip::udp;
class UDPServer {
public:
    UDPServer(boost::asio::io_service& io_service, unsigned short targetPort);
private:
    void StartReceive();
    void HandleReceive(const boost::system::error_code& error, std::size_t bytesTransferred);
    udp::socket _socket;
    udp::endpoint _remote_endpoint;
    boost::array<char, 4096> _recv_buffer;
};


#endif //PZ_UDPSERVER_H

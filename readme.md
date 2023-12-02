# TCP_UDP_Chat Project

This project 

## Project Structure

- `UDPServer`: UDP server implementation.
- `UDPClient`: UDP client implementation.
- `TCPServer`: TCP server implementation.
- `TCPClient`: TCP client implementation.
- `ThreadTest`: A test program for multithreading.
- `ConnectionThread`: A thread class used by TCPMultiServer.
- `TCPMultiServer`: Implementation of TCP server that supports multiple clients.
- `Makefile`: Makefile for building and running the project.

## Getting Started

1. **Clone the Repository:**
    ```bash
    git clone https://github.com/ouarzazi/JAVA_UDP_TCP_Chat
    cd JAVA_UDP_TCP_Chat
    ```

2. **Compile the code:**

    ```bash
    make compile
    ```
3. **Makefile Commands:**

    - **make sudp:** For UDP Server
    - **make cudp:** for UDP Client
    - **make stcp:** For TCP Server
    - **make ctcp:** for TCP Client
    - **make mtcp:** for TCPMultiserver

4. **Follow the instructions:**

    - You will choose a username
    - To disconnect, type _bye_ in the console. 

5. **Notes**

    - This project was developed with _openjdk 11.0.21 2023-10-17_
    - Ensure that ports are not in use.


compile: 
	@javac UDPServer.java UDPClient.java TCPServer.java TCPClient.java ThreadTest.java ConnectionThread.java TCPMultiServer.java 
sudp: compile cl 
	@java UDPServer 9988
cl:
	@clear
stcp: compile cl
	@java TCPServer 9988
cudp: compile cl
	@java UDPClient localhost 9988
ctcp: compile cl
	@java TCPClient localhost 9988
mtcp: compile cl
	@java TCPMultiServer 9988

# to start UDP Server, run the command: make sudp
# to start UDP Client, run the command: make cudp
# to start TCP Server, run the command: make stcp
# to start TCP Client, run the command: make ctcp
# to start TCP MultiServer, run the command: make mtcp


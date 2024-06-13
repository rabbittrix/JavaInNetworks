# ClientChannelNB is a non-blocking version of ClientChannel.

## I will demonstrate the communication with this server. As with the examples linked to threads, more than one client can be simultaneously communicating with the server. The biggest difference here is the time the program will "sleep" before sending a new number to the server. This time is set randomly, on line 41. Note that this client program does not have any configuration related to non-blocking input/output operation. After all, this type of configuration is necessary in the element that will handle multiple connections

# The server (ServerChannelNB TCP) will receive a number from the client, and will return the square of this.

## Using Java NIO, a new way of performing input/output operations is possible. In addition to blocking operations, non-blocking operations are also possible. Using this feature, a server can handle more than one connection without the use of threads, since the normal flow of execution is not "paused" when an input/output operation is requested. The example presents a TCP server that uses this feature.
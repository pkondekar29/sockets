# Sockets
An application with server and client using sockets to transfer data with each other

# Requirements
1. Java - 1.8
2. Maven - 3.x

# How to run

1. Server
  - Run command 
  > mvn exec:java -Dexec.mainClass=com.signzy.ServerApp -f server
2. Client
  - Get the ip of the server application and run command 
  > mvn exec:java -Dexec.mainClass=com.signzy.ClientApp -f client -Dexec.args="<ip_address> 5000"

# Project structure

There are 2 applications of server and client. 

## 1. Client

The client application has the following components -
  - ClientSocket: This picks up random message from "messages.txt" file and pushes it the server
    - The client socket follows a template pattern. The template process is as follows -
      a. Pick a random message from file
      b. Get a Message object from string using a MessageFactory
      c. Convert the it to JSON
      d. Encrypt it
      e. Send to to server
      f. Wait for acknowledgment
      <br>
  - Message Interface: The message interface is a template for the messages supported by client
    - The type of messages supported are - TEXT, COMMAND
    - The API in message interface are getMessageType(), getMessage()
    - So, the implementation of Message interface are - AbstractMessage, TextMessage, CommandMessage
      - AbstractMessage: This is abstract implementation of Message interface. This stores the messageType of the message
      - TextMessage: This stored the plain text message
      - CommandMessage: This stored the command which needs to be run on server
    <br>
  - MessageFactory: The factory takes an input messge string(from messages.txt) and returns command or text message. 
    - The return objects are encapsulated in Message interface making use of Bridge design.
    <br>
  - JSONUtils: This utility class is used to convert the Message obj to a JSON string. 
    - Gson is used to convert the Message Obj to corresponding 
    <br>
  - RSAEncrypterDecrypter: Utility class to encrypt the outgoing json strings using RSA algorithm
  
<br>
  
## 2. Server

The server application has the following components:
 - SocketServer: This server is initialised on application start and listens to client messages
  - The server by default listens to port 5000
  - This also follows the template design as follows:
    a. Listens for client messages.
    b. Establishes a connection with client
    c. Reads the message
    d. Decrypts the message to JSON string
    e. Generates a Message obj using MessageFactory
    f. Pushes the message to a priority queue
    g. Polls the queue to get the message
    h. Delegates the task to process the message to MessageHandler
    g. Returns an acknowledgments if the handler is able to handle the message, else returns a not acknowledged message
    <br>
- MessageHandler: This is interface for handling the Message obj. The interface has only 1 API, i.e, to handle.
  - The implementations of this are TextMessageHandler, CommandMessageHandler
    - TextMessageHandler: This reads the message from Message obj and returns true.
    - CommandMessageHandler: This runs the command from message obj and return true if the command is successfully executed, else false.
  <br>
- MessageHandlerFactory: This factory returns a MessageHandler implmentation based on the message type

- MessageQueue: A queue to store/poll the incoming messages

- Message: Same as the client application interface with exact same implmentations

- RSAEncrypterDecrypter: Decrypts the messages sent by clients.

package Connection;

import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public class ConnectionManager {

    private final List<Connection> connections = new ArrayList<Connection>();
    private final ReentrantLock connectionManagerLock = new ReentrantLock();
    static ConnectionManager instance;

    static ConnectionManager getInstance()
    {
        return instance;
    }

    public Connection CreateConnection() {

        connectionManagerLock.lock();

        Connection newConnection = null;
        try {
            newConnection = new Connection();
            connections.add(newConnection);
        } finally {
            connectionManagerLock.unlock();
        }

        return newConnection;
    }

    public void releaseConnection(Connection connection) {
        connectionManagerLock.lock();
        try {
            connections.remove(connection);
        } finally {
            connectionManagerLock.unlock();
        }
    }

    public void closeAllConnections() {
        connectionManagerLock.lock();
        try {
            connections.forEach(connection -> {
                try {

                    Socket socket = connection.getSocket();

                    socket.shutdownOutput();
                    socket.shutdownInput();
                    socket.close();

                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            });
            connections.clear();
        } finally {
            connectionManagerLock.unlock();
        }
    }
}

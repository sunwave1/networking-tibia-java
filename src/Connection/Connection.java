package Connection;

import Network.NetworkBuffer;
import Network.NetworkOutput;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

enum ConnectionState {
    CONNECTION_STATE_DISCONNECTED,
    CONNECTION_STATE_REQUEST_CHARLIST,
    CONNECTION_STATE_GAMEWORLD_AUTH,
    CONNECTION_STATE_GAME,
    CONNECTION_STATE_PENDING
}

public class Connection {

    private Socket socket;
    private NetworkBuffer msg;
    private ConnectionState state = ConnectionState.CONNECTION_STATE_PENDING;
    private final ReentrantLock connectionLock = new ReentrantLock();
    private final List<NetworkOutput> messages = new ArrayList<>();
    private final ScheduledExecutorService readTimer = Executors.newSingleThreadScheduledExecutor();
    private final ScheduledExecutorService writeTimer = Executors.newSingleThreadScheduledExecutor();
    private boolean receivedFirst = false;
    private boolean receivedName = false;
    private boolean receivedLastChar = false;

    private static int CONNECTION_READ_TIMEOUT = 30;

    public void close(boolean force) {

        ConnectionManager.getInstance().releaseConnection(this);

        connectionLock.lock();

        try {

            state = ConnectionState.CONNECTION_STATE_DISCONNECTED;

            if (messages.isEmpty() || force) {
                closeSocket();
            }

        } finally {
            connectionLock.unlock();
        }

    }

    public void close() {
        close(false);
    }

    private void parseHeader() {
        connectionLock.lock();
        readTimer.close();

        try {


        } finally {
            connectionLock.unlock();
        }
    }

    private void parsePacket() {

        connectionLock.lock();
        readTimer.close();

        try {

            // Read potential checksum bytes
            msg.readInt();

            if (!receivedFirst) {
                receivedFirst = true;
            } else {

            }

            readTimer.schedule(() -> {

            }, CONNECTION_READ_TIMEOUT, TimeUnit.SECONDS);

        } finally {
            connectionLock.unlock();
        }
    }

    public static ConnectionManager getInstance() {
        return new ConnectionManager();
    }

    private void closeSocket() {
        if (socket != null && socket.isConnected()) {
            try {
                socket.shutdownOutput();
                socket.shutdownInput();
                socket.close();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    public Socket getSocket() {
        return socket;
    }

}

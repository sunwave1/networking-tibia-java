package Network;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

public class NetworkBufferTeste {

    @Test
    @DisplayName("Buffer int")
    public void teste_buffer_int() {
        NetworkBuffer msg = new NetworkBuffer();
        msg.putInt(2160);
        assert(msg.readInt() == 20);
    }

    @Test
    @DisplayName("Buffer string")
    public void teste_buffer_string() {
        NetworkBuffer msg = new NetworkBuffer();
        msg.putStringWithSize("Hello World");
        assert(msg.readString().equals("Hello World"));
    }

    @Test
    @DisplayName("Buffer short")
    public void teste_buffer_short() {
        NetworkBuffer msg = new NetworkBuffer();
        msg.putShort((short)25);
        assert(msg.readShort() == 25);
    }

    @Test
    @DisplayName("Buffer double")
    public void teste_buffer_double() {
        NetworkBuffer msg = new NetworkBuffer();
        msg.putDouble(0.00025);
        assert(msg.readDouble() == 0.00025);
    }

    @Test
    @DisplayName("Buffer float")
    public void teste_buffer_float() {
        NetworkBuffer msg = new NetworkBuffer();
        msg.putFloat(0.25f);
        assert(msg.readFloat() == 0.25f);
    }

    @Test
    @DisplayName("Buffer byte")
    public void teste_buffer_byte() {
        NetworkBuffer msg = new NetworkBuffer();
        msg.putByte((byte)12);
        assert(msg.readByte() == (byte)12);
    }
}

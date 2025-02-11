package Network;

import java.nio.ByteBuffer;

public class NetworkBuffer {

    static int NETWORKMESSAGE_MAXSIZE = 24590;
    static int INITIAL_BUFFER_POSITION = 8;

    protected final ByteBuffer buffer;
    protected int position = INITIAL_BUFFER_POSITION;
    protected int length = 0;

    public NetworkBuffer() {
        buffer = ByteBuffer.allocate(NETWORKMESSAGE_MAXSIZE);
        buffer.position(INITIAL_BUFFER_POSITION);
    }

    public int getLength() {
        return length;
    }

    public ByteBuffer putString(String string) {
        return buffer.put(string.getBytes());
    }

    public ByteBuffer putStringWithSize(String string) {
        length += Character.BYTES * string.length();
        buffer.putInt(string.length());
        return buffer.put(string.getBytes());
    }

    public ByteBuffer putString(int index, String string) {
        length += Character.BYTES * string.length();
        return buffer.put(index, string.getBytes());
    }

    public byte get(int index) {
        return buffer.get(index);
    }

    public void skipBytes(int skip) { position += skip; }

    public ByteBuffer get(byte[] dst, int offset, int length) {
        return buffer.get(dst, offset, length);
    }

    public ByteBuffer put(int index, byte value) {
        length += 1;
        return buffer.put(index, value);
    }

    public ByteBuffer putInt(int value) {
        length += Integer.BYTES;
        return buffer.putInt(value);
    }

    public ByteBuffer putInt(int index, int value) {
        length += Integer.BYTES;
        return buffer.putInt(index, value);
    }

    public ByteBuffer putDouble(double value) {
        length += Double.BYTES;
        return buffer.putDouble(value);
    }

    public ByteBuffer putDouble(int index, double value) {
        length += Double.BYTES;
        return buffer.putDouble(index, value);
    }

    public ByteBuffer putFloat(float value) {
        length += Float.BYTES;
        return buffer.putFloat(value);
    }

    public ByteBuffer putFloat(int index, float value) {
        length += Float.BYTES;
        return buffer.putFloat(index, value);
    }

    public ByteBuffer putByte(byte value) {
        length += 1;
        return buffer.put(value);
    }

    public ByteBuffer putByte(int index, byte value) {
        length += 1;
        return buffer.put(index, value);
    }

    public ByteBuffer putShort(short value) {
        length += Short.BYTES;
        return buffer.putShort(value);
    }

    public ByteBuffer putShort(int index, short value) {
        length += Short.BYTES;
        return buffer.putShort(index, value);
    }

    public ByteBuffer position(int position) {
        return buffer.position(position);
    }

    public int getInt(int index) {
        return buffer.getInt(index);
    }

    public byte[] array() {
        return buffer.array();
    }

    public ByteBuffer getByteBuffer() {
        return buffer;
    }

    public String readString(int strLen) {

        if (strLen <= 0) {
            strLen = readInt();
        }

        position += strLen;

        return new String(buffer.array(), (position - strLen), strLen);
    }

    public String readString() {
        return readString(0);
    }

    public int readInt() {
        position += Integer.BYTES;
        return buffer.getInt(position - Integer.BYTES);
    }

    public double readDouble() {
        position += Double.BYTES;
        return buffer.getDouble(position - Double.BYTES);
    }

    public float readFloat() {
        position += Float.BYTES;
        return buffer.getFloat(position - Float.BYTES);
    }

    public byte readByte() {
        position += 1;
        return buffer.get(position - 1);
    }

    public short readShort() {
        position += Short.BYTES;
        return buffer.getShort(position - Short.BYTES);
    }
}

package Network;

import java.nio.ByteBuffer;

enum ChecksumMode
{
    CHECKSUM_DISABLED,
    CHECKSUM_ADLER,
    CHECKSUM_SEQUENCE
};

public class NetworkOutput extends NetworkBuffer {

    private int outputBufferStart = INITIAL_BUFFER_POSITION;
    private int sequenceId = 0;

    public int getSequenceId() {
        return sequenceId;
    }

    public void setSequenceId(int sequenceId) {
        this.sequenceId = sequenceId;
    }

    public void writeMessageLength() {
        addHeaderInteger(length);
    }

    public void addCryptoHeader(ChecksumMode mode) {
        if (mode == ChecksumMode.CHECKSUM_ADLER) {
//            addHeaderInteger(adlerChecksum(buffer.get(outputBufferStart), length));
        } else if (mode == ChecksumMode.CHECKSUM_SEQUENCE) {
            addHeaderInteger(getSequenceId());
        }
        writeMessageLength();
    }

    public ByteBuffer getOutputBuffer() {
        return buffer.position(outputBufferStart);
    }

    public void append(NetworkBuffer msg) {
        var len = msg.getLength();

        buffer.put(position, msg.getByteBuffer(), 0, len);

        position += len;
        length += len;
    }

    public void append(NetworkOutput msg) {
        var len = msg.getLength();

        buffer.put(position, msg.getByteBuffer(), 0, len);

        position += len;
        length += len;
    }

    private void addHeaderInteger(int value) {

        assert(outputBufferStart >= Integer.BYTES);

        outputBufferStart -= Integer.BYTES;

        buffer.putInt(outputBufferStart, value);
    }
}

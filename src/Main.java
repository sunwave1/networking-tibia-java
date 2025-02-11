import Network.NetworkBuffer;

public class Main {
    public static void main(String[] args) {

        NetworkBuffer buffer = new NetworkBuffer();

        buffer.putShort((short)500);
        buffer.putStringWithSize("Hello World");
        buffer.putInt(24000);
        buffer.putInt(24000);
        buffer.putDouble(24000);
        buffer.putFloat(0.4f);

        System.out.println(buffer.readShort());
        System.out.println(buffer.readString());
        System.out.println(buffer.readInt());
    }

}
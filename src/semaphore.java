import java.io.FileWriter;
import java.io.IOException;

class semaphore {
    protected int value;

    protected semaphore(int val) {
        value = val;
    }

    public synchronized void acquire(Device device, FileWriter f) throws IOException {
        value--;
        if (value < 0)
            try {
                f.append (device.getDeviceName() + " (" + device.getType() + ") arrived and waiting \n");
                wait();
            } catch (InterruptedException e) {}
        else
            f.append (device.getDeviceName() + " (" + device.getType() + ") arrived \n");
    }

    public synchronized void release() {
        value++;
        if (value <= 0)
            notify();
    }
}
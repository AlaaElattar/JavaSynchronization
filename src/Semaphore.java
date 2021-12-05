class semaphore {
    protected int value;

    protected semaphore(int val) {
        value = val;
    }

    public synchronized void acquire(Device device) {
        value--;
        if (value < 0)
            try {
                System.out.println(device.getDeviceName() + " (" + device.getType() + ") arrived and waiting");
                wait();
            } catch (InterruptedException e) {}
        else
            System.out.println(device.getDeviceName() + " (" + device.getType() + ") arrived");
    }

    public synchronized void release(Device device) {
        value++;
        if (value <= 0)
            notify();
    }
}
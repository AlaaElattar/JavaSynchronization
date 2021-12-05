enum Status{
    connected, waiting, disconnected
}

class Device extends Thread {
    private String type;
    private String deviceName;
    private semaphore s;
    public static Router router;

    public Device(String n, String t, semaphore s, Router r){
        deviceName = n;
        type = t;
        this.s = s;
        router = r;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public String getType() {
        return type;
    }

    private void connect(Router router){
        s.acquire(this);
        router.addDevice(this);
    }

    private void disconnect(Router router){
        router.removeDevice(this);
        s.release(this);
    }

    @Override
    public void run(){
        this.connect(router);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        router.performActivity(this);


        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.disconnect(router);
    }
}

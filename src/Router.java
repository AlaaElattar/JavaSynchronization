import java.util.ArrayList;

class Router {
    ArrayList<Device> devices;
    int numDevices;
    public Router(int num){
        devices = new ArrayList<>(num);
        for (int x = 0; x<5; x++){
            devices.add(null);
        }
        numDevices = num;
    }


    public void performActivity(Device device){
        int i = devices.indexOf(device);
        System.out.println("connection " + i +": " + device.getDeviceName() + " performing activity");
    }
    public void addDevice(Device device){
        int i = 0;
        for (; i<numDevices; i++){
            if(devices.get(i) == null){
                devices.remove(i);
                devices.add(i, device);
                break;
            }
        }
        System.out.println("connection " + i +": " + device.getDeviceName() + " occupied");
    }

    public void removeDevice(Device device){
        int i = devices.indexOf(device);
        devices.remove(device);
        devices.add(i, null);
        System.out.println("connection " + i +": " + device.getDeviceName() + " logged out");
    }
}

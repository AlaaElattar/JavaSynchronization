class Network {
    public static void main(String[] args) throws IOException, InterruptedException {
        System.out.println("What is the number of WI-FI Connections?");
        Scanner in = new Scanner(System.in);
        int numCon = in.nextInt();
        System.out.println("What is the number of devices Clients want to connect?");
        int numDevices = in.nextInt();

        FileWriter file = new FileWriter("log.out");
        semaphore s = new semaphore(numCon);
        Router router = new Router(numCon);

        ArrayList<Thread> devices = new ArrayList<>();
        for (int i = 0; i < numDevices; i++) {
            String name = in.next();
            String type = in.next();
            Thread t = new Thread(new Device(name, type, s, router, file));
            devices.add(t);
        }

        for (int i = 0; i < numDevices; i++) {
            devices.get(i).start();
            devices.get(i).setPriority(numDevices - i);
        }
        for (int i = 0; i < numDevices; i++) {
            devices.get(i).join();
        }
        file.close();
        /ArrayList<Integer> x =new ArrayList<>();
        x.add(1);
        x.add(2);
        x.add(3);
        for (int i = 0; i<x.size(); i++){
            if(i == 1){
                x.remove(i);
                x.add(i, 5);
            }
        }
        for (int i = 0; i<x.size(); i++){
            System.out.println(x.get(i));
        }
    }/
}
}
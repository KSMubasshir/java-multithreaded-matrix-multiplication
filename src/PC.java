class PC {

    int i = 0, j = 0;
    WorkItem[] workitem;
    boolean isready = false;

    PC(int length) {
        workitem = new WorkItem[length];
    }

    synchronized void put(WorkItem workitem) {
        try {
            this.workitem[i] = workitem;
            i++;

        } catch (Exception e) {
            System.out.println("Exception @ PC.put : " + e);
        }
        isready = true;

        notify();
    }

    synchronized WorkItem get() {
        while (workitem[j] == null) {
            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println("Interrupted @PC.get :" + e);
            }
        }
        j++;
        isready = false;
        notify();
        return workitem[j - 1];

    }
}
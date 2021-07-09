class worker implements Runnable {

    WorkItem wk;
    int[][] resultant_Matrix;
    Thread t;
    PC pc;

    worker(int[][] resultant_Matrix, PC pc) {

        this.resultant_Matrix = resultant_Matrix;
        this.pc = pc;
        t = new Thread(this);
        t.start();
    }

    @Override
    public void run() {
        wk = pc.get();
        for (int k = 0; k < wk.MatA.length; k++) {
            resultant_Matrix[wk.i][wk.j] += (wk.MatA[k] * wk.MatB[k]);
        }
    }
}

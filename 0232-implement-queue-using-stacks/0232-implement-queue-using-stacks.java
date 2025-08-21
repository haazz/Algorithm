class MyQueue {
    Stack<Integer> filo = new Stack<>();
    Stack<Integer> fifo = new Stack<>();
    
    public MyQueue() {

    }
    
    public void push(int x) {
        filo.add(x);
    }
    
    public int pop() {
        if (!fifo.isEmpty()) {
            return fifo.pop();
        }
        while (!filo.isEmpty()) {
            fifo.add(filo.pop());
        }
        return fifo.pop();
    }
    
    public int peek() {
        if (!fifo.isEmpty()) {
            return fifo.peek();
        }
        while (!filo.isEmpty()) {
            fifo.add(filo.pop());
        }
        return fifo.peek();
    }
    
    public boolean empty() {
        return filo.isEmpty() && fifo.isEmpty();
    }
}

/**
 * Your MyQueue object will be instantiated and called as such:
 * MyQueue obj = new MyQueue();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.peek();
 * boolean param_4 = obj.empty();
 */
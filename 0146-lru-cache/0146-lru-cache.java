class LRUCache {
    HashMap<Integer, int[]> data = new HashMap<>();
    Queue<Integer> lt = new LinkedList<>();
    int capacity;

    public LRUCache(int capacity) {
        this.capacity = capacity;
    }
    
    public int get(int key) {
        if (!data.containsKey(key)) {
            return -1;
        }
        int[] elem = data.get(key);
        elem[1]++;
        lt.add(key);
        return elem[0];
    }
    
    public void put(int key, int value) {
        if (data.containsKey(key)) {
            int[] elem = data.get(key);
            elem[0] = value;
            elem[1]++;
            lt.add(key);
            return;
        }
        if (data.size() < capacity) {
            data.put(key, new int[] {value, 1});
            lt.add(key);
            return;
        }
        while (!lt.isEmpty()) {
            int k = lt.poll();
            int[] elem = data.get(k);
            System.out.println(k + " " + elem);
            if (elem[1] <= 1) {
                data.remove(k);
                break;
            }
            elem[1]--;
        }
        data.put(key, new int[] {value, 1});
        lt.add(key);
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
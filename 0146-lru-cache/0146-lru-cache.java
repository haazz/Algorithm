// 양방향 링크드 리스트를 통해 해결?

class LRUCache {
    class Node {
        int key;
        int value;
        Node prev;
        Node next;

        public Node(int key, int value, Node prev, Node next) {
            this.key = key;
            this.value = value;
            this.prev = prev;
            this.next = next;
        }
    }
    Node root;
    Node last;
    HashMap<Integer, Node> data = new HashMap<>();
    int cap;

    public LRUCache(int capacity) {
        this.cap = capacity;
        root = new Node(-1, -1, null, null);
        last = new Node(-1, -1, null, null);
        root.next = last;
        last.prev = root;
    }
    
    public int get(int key) {
        if (!data.containsKey(key)) {
            return -1;
        }
        Node node = data.get(key);
        node.prev.next = node.next;
        node.next.prev = node.prev;
        last.prev.next = node;
        node.prev = last.prev;
        last.prev = node;
        node.next = last;
        return node.value;
    }
    
    public void put(int key, int value) {
        if (data.containsKey(key)) {
            Node node = data.get(key);
            node.value = value;
            node.prev.next = node.next;
            node.next.prev = node.prev;
            last.prev.next = node;
            node.prev = last.prev;
            last.prev = node;
            node.next = last;
            return;
        }
        if (data.size() >= cap) {
            data.remove(root.next.key);
            root.next = root.next.next;
            root.next.prev = root;
        }
        Node node = new Node(key, value, last.prev, last);
        last.prev.next = node;
        last.prev = node;
        data.put(key, node);
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
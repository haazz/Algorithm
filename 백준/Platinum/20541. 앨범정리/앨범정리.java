import java.util.*;
import java.util.Map.Entry;
import java.io.*;

class Main {
    static class Album {
        String name;
        TreeMap<String, Album> albums;
        TreeSet<String> photos;
        Album prevAlubm;

        public Album(String name, Album prevAlbum) {
            this.name = name;
            this.prevAlubm = prevAlbum;
            albums = new TreeMap<>();
            photos = new TreeSet<>();
        }
    }

    static Album root;
    static Album curAlbum;
    static StringBuilder sb;

    public static class AlbumOperator {
        public static void mk(String name) {
            if (curAlbum.albums.containsKey(name)) {
                sb.append("duplicated album name\n");
                return;
            }
            Album album = new Album(name, curAlbum);
            curAlbum.albums.put(name, album);
        }

        public static void rm(String name) {
            if (!curAlbum.albums.containsKey(name)) {
                sb.append("0 0\n");
                return;
            }
            int[] count = countAlbum(curAlbum.albums.get(name));
            sb.append(count[0] + 1).append(" ").append(count[1]).append("\n");
            curAlbum.albums.remove(name);
        }

        public static void rm(int var) {
            if (var == 0) {
                int[] count = countAlbum(curAlbum);
                sb.append(count[0]).append(" ").append(count[1] - curAlbum.photos.size()).append("\n");
                curAlbum.albums.clear();
                return;
            }
            if (curAlbum.albums.size() == 0) {
                sb.append("0 0\n");
                return;
            }
            Entry<String, Album> entry;
            if (var == -1) {
                entry = curAlbum.albums.pollFirstEntry();
            } else {
                entry = curAlbum.albums.pollLastEntry();
            }
            int[] count = countAlbum(entry.getValue());
            sb.append(count[0] + 1).append(" ").append(count[1]).append("\n");
        }

        private static int[] countAlbum(Album album) {
            int[] count = new int[2];
            count[0] += album.albums.size();
            count[1] += album.photos.size();

            for (Entry<String, Album> nAlbum : album.albums.entrySet()) {
                int[] nCount = countAlbum(nAlbum.getValue());
                count[0] += nCount[0];
                count[1] += nCount[1];
            }
            return count;
        }

        public static void ca(String var) {
            if (var.equals("..")) {
                if (curAlbum.prevAlubm != null) {
                    curAlbum = curAlbum.prevAlubm;
                }
            } else if (var.equals("/")) {
                curAlbum = root;
            } else if (curAlbum.albums.containsKey(var)) {
                curAlbum = curAlbum.albums.get(var);
            }
            sb.append(curAlbum.name).append("\n");
        }
    }

    public static class PhotoOperator {
        public static void insert(String name) {
            if (curAlbum.photos.contains(name)) {
                sb.append("duplicated photo name\n");
                return;
            }
            curAlbum.photos.add(name);
        }

        public static void delete(String name) {
            if (!curAlbum.photos.contains(name)) {
                sb.append("0\n");
                return;
            }
            curAlbum.photos.remove(name);
            sb.append("1\n");
        }

        public static void delete(int var) {
            if (var == 0) {
                sb.append(curAlbum.photos.size()).append("\n");
                curAlbum.photos.clear();
                return;
            }
            if (curAlbum.photos.size() == 0) {
                sb.append("0\n");
                return;
            }
            if (var == -1) {
                curAlbum.photos.pollFirst();
            } else {
                curAlbum.photos.pollLast();
            }
            sb.append("1\n");
        }

    }

    public static boolean isNum(String s) {
        if (s.equals("-1") || s.equals("0") || s.equals("1")) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        root = new Album("album", null);
        curAlbum = root;
        sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            String op = st.nextToken();
            String var = st.nextToken();

            switch (op) {
                case "mkalb":
                    AlbumOperator.mk(var);
                    break;
                case "rmalb":
                    if (isNum(var)) {
                        AlbumOperator.rm(Integer.parseInt(var));
                    } else {
                        AlbumOperator.rm(var);
                    }
                    break;
                case "insert":
                    PhotoOperator.insert(var);
                    break;
                case "delete":
                    if (isNum(var)) {
                        PhotoOperator.delete(Integer.parseInt(var));
                    } else {
                        PhotoOperator.delete(var);
                    }
                    break;
                case "ca":
                    AlbumOperator.ca(var);
                    break;
            }
        }

        System.out.println(sb.toString());
    }
}
package 백준.gold.폴더_정리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        String[][] arr = new String[n+m][3];
        for(int i=0; i<n+m; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<3; j++) {
                arr[i][j] = st.nextToken();
            }
        }

        int k = Integer.parseInt(br.readLine());
        String[][] move = new String[k][2];
        for(int i=0; i<k; i++) {
            st = new StringTokenizer(br.readLine());
            move[i][0] = st.nextToken();
            move[i][1] = st.nextToken();
        }

        int q = Integer.parseInt(br.readLine());
        String[] query = new String[q];
        for(int i=0; i<q; i++) {
            query[i] = br.readLine();
        }

        new Main().solution(n, m, arr, move, query);
    }

    static class Folder {
        String name;
        Folder parent;
        Set<Folder> folders = new HashSet<>();
        Set<File> files = new HashSet<>();
        Count result;

        public Folder(String name) {
            this.name = name;
        }

        public void addFile(File file) {
            file.parent = this;
            files.add(file);
        }

        public void addFolder(Folder folder) {
            folder.parent = this;
            folders.add(folder);
        }

        public void move(Folder move) {
            move.parent.remove(move);
            move.files.forEach(this::addFile);
            move.folders.forEach(this::addFolder);
        }

        public void remove(Folder folder) {
            folders.remove(folder);
        }

        public void print(int level) {
            StringBuilder sb = new StringBuilder();
            for(int i=0; i<level; i++) {
                sb.append("-");
            }
            System.out.println(sb + " " + name);
            sb.append("-");
            for (Folder folder : folders) {
                folder.print(level+1);
            }
            for (File file : files) {
                System.out.println(sb + " " + file.name);
            }
        }

        public Count findCount() {
            Set<File> subFiles = new HashSet<>();
            int fileCount = 0;
            for (Folder folder : folders) {
                Count count = folder.findCount();
                fileCount += count.fileCount;
                subFiles.addAll(count.files);
            }
            fileCount += files.size();
            Count count = new Count(fileCount);
            count.addAllFile(files);
            count.addAllFile(subFiles);
            result = count;
            return count;
        }
    }

    static class File {
        Folder parent;
        String name;

        public File(String name) {
            this.name = name;
        }
    }

    static class Count {
        int fileCount;
        Set<File> files = new HashSet<>();

        public Count(int fileCount) {
            this.fileCount = fileCount;
        }

        public void addAllFile(Set<File> files) {
            this.files.addAll(files);
        }

        public void print() {
            System.out.print(files.size());
            System.out.print(" ");
            System.out.println(fileCount);
        }
    }

    private void solution(int n, int m, String[][] arr, String[][] move, String[] query) {
        Map<String, Folder> folders = new HashMap<>();
        Map<String, File> files = new HashMap<>();
        for (String[] strings : arr) {
            String parent = strings[0];
            String child = strings[1];
            String dir = strings[2];
            Folder parentFolder = folders.getOrDefault(parent, new Folder(parent));
            if(dir.equals("0")) {
                File childFile = files.getOrDefault(child, new File(child));
                parentFolder.addFile(childFile);
                files.put(child, childFile);
            } else {
                Folder childFolder = folders.getOrDefault(child, new Folder(child));
                parentFolder.addFolder(childFolder);
                folders.put(child, childFolder);
            }
            folders.put(parent, parentFolder);
        }

        for (String[] strings : move) {
            String[] splitA = strings[0].split("/");
            String a = splitA[splitA.length-1];
            String[] splitB = strings[1].split("/");
            String b = splitB[splitB.length-1];
            Folder folderA = folders.get(a);
            Folder folderB = folders.get(b);
            folderB.move(folderA);
        }

        Folder main = folders.get("main");
        main.findCount();

        for (String q : query) {
            String[] split = q.split("/");
            String name = split[split.length - 1];
            Folder folder = folders.get(name);
            Count result = folder.result;
            result.print();
        }
    }
}

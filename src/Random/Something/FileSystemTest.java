package Random.Something;

import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Partial exam II 2016/2017
 */
public class FileSystemTest {
    public static void main(String[] args) {
        FileSystem fileSystem = new FileSystem();
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < n; i++) {
            String line = scanner.nextLine();
            String[] parts = line.split(":");
            fileSystem.addFile(parts[0].charAt(0), parts[1],
                    Integer.parseInt(parts[2]),
                    LocalDateTime.of(2016, 12, 29, 0, 0, 0).minusDays(Integer.parseInt(parts[3]))
            );
        }
        int action = scanner.nextInt();
        if (action == 0) {
            scanner.nextLine();
            int size = scanner.nextInt();
            System.out.println("== Find all hidden files with size less then " + size);
            List<File> files = fileSystem.findAllHiddenFilesWithSizeLessThen(size);
            files.forEach(System.out::println);
        } else if (action == 1) {
            scanner.nextLine();
            String[] parts = scanner.nextLine().split(":");
            System.out.println("== Total size of files from folders: " + Arrays.toString(parts));
            int totalSize = fileSystem.totalSizeOfFilesFromFolders(Arrays.stream(parts)
                    .map(s -> s.charAt(0))
                    .collect(Collectors.toList()));
            System.out.println(totalSize);
        } else if (action == 2) {
            System.out.println("== Files by year");
            Map<Integer, Set<File>> byYear = fileSystem.byYear();
            byYear.keySet().stream().sorted()
                    .forEach(key -> {
                        System.out.printf("Year: %d\n", key);
                        Set<File> files = byYear.get(key);
                        files.stream()
                                .sorted()
                                .forEach(System.out::println);
                    });
        } else if (action == 3) {
            System.out.println("== Size by month and day");
            Map<String, Long> byMonthAndDay = fileSystem.sizeByMonthAndDay();
            byMonthAndDay.keySet().stream().sorted()
                    .forEach(key -> System.out.printf("%s -> %d\n", key, byMonthAndDay.get(key)));
        }
        scanner.close();
    }
    public static class FileSystem
    {
        Hashtable<Character,ArrayList<File>> fs = new Hashtable<>();
        public void addFile(char folder, String name, int size, LocalDateTime createdAt)
        {
            ArrayList<File> temp;
            if(!fs.containsKey(folder))
            {
                temp = new ArrayList<>();

            }
            else
            {
                temp = fs.get(folder);
            }
            temp.add(new File(name, size, createdAt));
            fs.put(folder,temp);
        }
        public List<File> findAllHiddenFilesWithSizeLessThen(int size)
        {
            ArrayList<File> hidden=fs.get('.');
            List<File> result = new ArrayList<>();
            for(int i=0;i< hidden.size();i++)
            {
                if(hidden.get(0).size<size)
                {
                    result.add(hidden.get(i));
                }
                hidden.remove(0);
            }
            return result;
        }
        public int totalSizeOfFilesFromFolders(List<Character> folders)
        {
            int sum=0;
            while(!folders.isEmpty())
            {
                ArrayList<File> temp = fs.get(folders.get(0));
                folders.remove(0);
                while(!temp.isEmpty())
                {
                    sum+=temp.get(0).size;
                    temp.remove(0);
                }
            }
            return sum;
        }
        public Map<Integer, Set<File>> byYear()
        {

            Enumeration<ArrayList<File>> elements = fs.elements();
            Map<Integer,Set<File>> result = new HashMap<>();

            while (elements.hasMoreElements())
            {
                ArrayList<File> list = elements.nextElement();
                while(!list.isEmpty()) {
                    Set<File> temp;
                    if(!result.containsKey(list.get(0).createdAt.getYear()))
                    {
                        temp= new TreeSet<>();
                    }
                    else
                    {
                        temp=result.get(list.get(0).createdAt.getYear());
                    }
                    temp.add(new File(list.get(0).name,list.get(0).size,list.get(0).createdAt));
                    result.put(list.get(0).createdAt.getYear(),temp);
                    list.remove(0);
                }
            }
            return result;
        }
        public Map<String, Long> sizeByMonthAndDay()
        {
            Enumeration<ArrayList<File>> elements = fs.elements();
            Map<String, Long> result = new HashMap<>();
            while (elements.hasMoreElements())
            {
                ArrayList<File> list = elements.nextElement();
                Long temp= 0L;
                while(!list.isEmpty()) {
                    if (!result.containsKey(list.get(0).createdAt.getMonth().name() + "-" + list.get(0).createdAt.getDayOfMonth())) {
                        result.put(list.get(0).createdAt.getMonth().name() + "-" + list.get(0).createdAt.getDayOfMonth(), 0L);
                    }
                    temp = result.get(list.get(0).createdAt.getMonth().name() + "-" + list.get(0).createdAt.getDayOfMonth());
                    temp += list.get(0).size;
                    result.put(list.get(0).createdAt.getMonth().name() + "-" + list.get(0).createdAt.getDayOfMonth(), temp); // key value
                    list.remove(0);
                }
            }
            return result;
        }
    }
    public static class File implements Comparator<File>
    {
        String name;
        int size;
        LocalDateTime createdAt;
        File(String name,int size,LocalDateTime createdAt)
        {
            this.name=name;
            this.size=size;
            this.createdAt=createdAt;
        }

        @Override
        public int compare(File o1, File o2) {
            if(o1.size-o2.size==0)
            {
                return o1.name.compareToIgnoreCase(o2.name);
            }
            else return o1.size-o2.size;
        }
    }
}

// Your code here


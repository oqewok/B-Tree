package Structures;

public class FileNode implements Comparable<FileNode> {
    private String fullPath; //полный путь
    private int fileSize; //размер конкретного файла
    private boolean isDirectory; //является ли директорией (папкой)

    /**
     * Конструктор для папок
     * @param fullPath путь к файлу (папке).
     * @variable fileSize = -1
     * @variable isDirectory = false
     */
    public FileNode(String fullPath){
        this.fullPath = fullPath;
        this.fileSize = -1;
        this.isDirectory = true;
    }

    /**
     * Конструктор для файлов
     * @param filepath путь к файлу.
     * @param fullPath размер файла.
     * @variable isDirectory = false
     */
    public FileNode(String filepath, int fullPath){
        this.fullPath = filepath;
        this.fileSize = fullPath;
        this.isDirectory = false;
    }

    /**
     * Конструктор для файлов или папок
     * с заданием размера и свойства, является ли структура папкой
     * @param fullPath путь к файлу.
     * @param filesize размер файла.
     * @param isDirectory является ли директорией.
     */
    public FileNode(String fullPath, int filesize, boolean isDirectory){
        this.fullPath = fullPath;
        this.fileSize = filesize;
        this.isDirectory = isDirectory;
    }

    public String getFullPath(){
        return fullPath;
    }

    public int getFileSize(){
        return fileSize;
    }

    public boolean isDirectory(){
        return isDirectory==true;
    }

    public String toString(){
        //return fullPath + " " + fileSize;
        return fullPath;
    }

    @Override
    public int compareTo(FileNode o) {
        int result;
        result = this.fullPath.compareTo(o.fullPath);
        if (result != 0) return result;
        result = this.fullPath.length() - o.fullPath.length();
        return result;

//        int result;
//        result = this.fullPath.length() - o.fullPath.length();
//        if (result != 0) return result;
//        result = this.fullPath.compareTo(o.fullPath);
//        return result;

//        return this.fullPath.compareTo(o.fullPath);

//        Collections.sort(copy, new Comparator<Product>() {
//            @Override
//            public int compare(Product o1, Product o2) {
//                return o2.getQuantity()-o1.getQuantity();
//            }
//        });
//
//        Collections.sort(prod_v, new Comparator<Product>() {
//            @Override
//            public int compare(Product o1, Product o2) {
//                return (o1.getCode()).compareTo(o2.getCode());
//            }
//        });
    }
}

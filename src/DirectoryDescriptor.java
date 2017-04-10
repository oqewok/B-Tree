import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class DirectoryDescriptor {

    //region Data
    private String fullPath;
    private int order;

    private ArrayList<String> list;
    //endregion

    //region .Ctor
    public DirectoryDescriptor() throws FileNotFoundException {
        fullPath = "DirectoryDescription.txt";
        order = 1;
    }

    public  DirectoryDescriptor(String path) throws FileNotFoundException {
        fullPath = path;

        try {
            readDescription();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    //endregion

    //region SettersGetters
    public void setFullPath(String path){
        fullPath = path.toLowerCase();
    }

    public String getFullPath(){
        return fullPath;
    }

    public int getOrder(){
        return order;
    }

    public void setOrder(int order){
        this.order = order;
    }
    //endregion

    //region Methods
    public void readDescription() throws FileNotFoundException {
        try{
            list = new ArrayList<>();
            Scanner in = new Scanner(new File(fullPath));

            if (in.hasNext())
                order = Integer.parseInt(in.nextLine());

            list.clear();

            while (in.hasNextLine())
                list.add(in.nextLine());
        }
        catch (Exception ex){
            System.out.println("fail: " + ex.getMessage());
        }
    }

    public void readDescription(String path) throws FileNotFoundException{
        setFullPath(path);
        readDescription();
    }

    public void readDescription(File F) throws  FileNotFoundException{
        setFullPath(F.getAbsolutePath());
        readDescription();
    }

    //возвращает список всех директорий и файлов
    public ArrayList <String> getList(){
        return list;
    }

    //удаляет элемент их списка
    public void remove(String value){
        list.remove(value.toString());
    }
    //endregion

}

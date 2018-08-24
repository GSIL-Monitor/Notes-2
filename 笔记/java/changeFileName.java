import java.io.File;
import java.io.IOException;

/**
 * 改文件名字（不跟该目录名，无视目录层级）
 */


public class changeFileName {
    //仅对该目录下的文件进行检测
    String filePath="d:Test_File/txt";


    public void changeFilename() throws IOException {
        File file = new File(filePath);
        File[] listFile = file.listFiles();
        for (File f:listFile){
            isDir(f);
        }
    }

    //判断是否是目录,区分进行操作
    public void isDir(File file) throws IOException {
        if (file.isDirectory()){
            File[] nextFile = file.listFiles();
            for (File f:nextFile) {
                isDir(f);
            }
        }else {
            String name=file.getName();
            file.renameTo(new File(addPathName(file) +"yes "+name));
        }
    }


    //在路径中剔除掉当前文件名以后返回路径
    public String addPathName(File file) throws IOException {

        String path=file.getCanonicalPath();
        String Apath = file.getAbsolutePath();
        System.out.println("C_Path: " + path);
        System.out.println("A_Path: " + Apath);
        int begin = path.indexOf(file.getName());
//        int last = path.length();
        System.out.println(path.substring(0,begin));
        return path.substring(0,begin);
    }


    public static void main(String args[]){
        changeFileName c1 = new changeFileName();
        try {
            c1.changeFilename();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

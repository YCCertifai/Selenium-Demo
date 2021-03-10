package ai.certifai.Utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CSVHandler {
    private String path;
    private File file;
    private Boolean appendMode;

    public CSVHandler(String path, Boolean appendMode)
    {
        this.path = path;
        this.file = new File(path);
        this.appendMode = appendMode;
        if(!appendMode)
        {
            init();
        }
    }

    private void init()
    {
        if (file.exists())
        {
            file.delete();
        }
    }

    public void addHeader(String header)
    {
        if (appendMode)
        {
            return;
        }
        try (FileWriter fw = new FileWriter(file))
        {
            fw.write(header);
            fw.append("\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void append(String data)
    {
        try (FileWriter fw = new FileWriter(file,true))
        {
            fw.append(data);
            fw.append("\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void append(List<String> dataList)
    {
        try (FileWriter fw = new FileWriter(file,true))
        {
            for (String data : dataList)
            {
                fw.append(data);
                fw.append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

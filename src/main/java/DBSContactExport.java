import java.io.IOException;

/**
 * Created by Hcl Hari Babu on 25/09/2019.
 */
public class DBSContactExport {

    public static void main(String[] args) throws IOException {

        String bbContactListFile = args[0];
        String bawagUserListFile = args[1];
        String insertScriptFile = args[2];
        String missedContactsFile = args[3];

        DBSContactExportImpl csvReaderWrite = new DBSContactExportImpl();

        csvReaderWrite.CSVReaderWriteImpl(bbContactListFile, bawagUserListFile, insertScriptFile, missedContactsFile);


    }

}


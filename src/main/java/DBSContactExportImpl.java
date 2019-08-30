import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Hcl Hari Babu on 25/09/2019.
 */
public class DBSContactExportImpl {

    ArrayList<DBSContact> DBSContacts = new ArrayList<DBSContact>();
    static Map<String, String> userEmailMap = null;


    public void CSVReaderWriteImpl(String bbContactListFile, String bawagUserListFile, String insertScriptFile, String missedContactsFile) throws IOException {

        try (
                Reader bawagUserListFileReader = Files.newBufferedReader(Paths.get(bawagUserListFile));
                Reader bbContactListFileReader = Files.newBufferedReader(Paths.get(bbContactListFile));
                CSVParser bawagUserListParser = new CSVParser(bawagUserListFileReader, CSVFormat.DEFAULT
                        .withFirstRecordAsHeader()
                        .withIgnoreHeaderCase()
                        .withTrim());
                CSVParser bbContactListParser = new CSVParser(bbContactListFileReader, CSVFormat.DEFAULT
                        .withFirstRecordAsHeader()
                        .withIgnoreHeaderCase()
                        .withTrim());

        ) {
            System.out.println("------Started---------\n\n");
            userEmailMap = new HashMap<>();

            for (CSVRecord UserRecord : bawagUserListParser) {
                String id = UserRecord.get("id");
                String email = UserRecord.get("email");
                userEmailMap.put(email, id);

            }

            for (CSVRecord bbContact : bbContactListParser) {

                String id = bbContact.get("id");
                String bb_id = bbContact.get("bb_id");
                String name = bbContact.get("name");
                String created_at = bbContact.get("created_at");
                String status = bbContact.get("status");
                String uuid = bbContact.get("uuid");
                String iban = bbContact.get("iban");

                devContactSetter(id, bb_id, name, created_at, status, uuid, iban);

            }

            scriptCreation(DBSContacts, insertScriptFile, missedContactsFile);
            System.out.println("------finished---------\n\n");

        }
    }

    public void devContactSetter(String id, String bb_id, String name, String created_at, String status, String uuid, String iban) {
        DBSContact devContact = new DBSContact();
        devContact.setId(id);
        devContact.setBb_id(bb_id);
        devContact.setName(name);
        devContact.setCreated_at(created_at);
        devContact.setStatus(status);
        devContact.setUuid(uuid);
        devContact.setIban(iban);
        DBSContacts.add(devContact);

    }

    public static void scriptCreation(ArrayList<DBSContact> devContacts, String insertScriptFile, String missedContactsFile) throws IOException {

        try {

            BufferedWriter insertScriptWriter = Files.newBufferedWriter(Paths.get(insertScriptFile));
            BufferedWriter missedContactsWriter = Files.newBufferedWriter(Paths.get(missedContactsFile));

            CSVPrinter insertScriptPrinter = new CSVPrinter(insertScriptWriter, CSVFormat.DEFAULT);
            CSVPrinter missedContactsPrinter = new CSVPrinter(missedContactsWriter, CSVFormat.DEFAULT);

            SimpleDateFormat formatter = new SimpleDateFormat("dd/mm/yyyy HH:mm");
            Date systemDate = new Date();
            {
                for (DBSContact devContact : devContacts) {

                    if (null != devContact.getBb_id()) {
                        AddressBook addressBook = new AddressBook();
                        addressBook.setID(devContact.getId());
                        addressBook.setNAME(devContact.getName());
                        addressBook.setIBAN(devContact.getIban());
                        String userId = userEmailMap.get(devContact.getBb_id());
                        if (userId != null) {
                            addressBook.setUSER_ID(userEmailMap.get(devContact.getBb_id()));
                        }
                        if (null != devContact.getCreated_at()) {
                            String date = devContact.getCreated_at();
                            if (!date.equalsIgnoreCase("null")) {
                                addressBook.setCREATED_AT(devContact.getCreated_at());
                            } else {
                                java.sql.Date currentDate = new java.sql.Date(systemDate.getTime());
                                addressBook.setCREATED_AT(currentDate.toString());
                            }
                        }

                        addressBook.setUPDATED_AT("NOW()");

                        if (userId != null) {
                            insertScriptPrinter.printRecord("insert into ADDRESSBOOKENTRIES VALUES ('" + addressBook.getID() + "'", "'" + addressBook.getNAME() + "'",
                                    "'" + addressBook.getIBAN() + "'",
                                    "'" + addressBook.getUSER_ID() + "'",
                                    "'" + addressBook.getCREATED_AT() + "'",
                                    "'" + addressBook.getUPDATED_AT() + "'" +
                                            ");");
                            insertScriptPrinter.flush();
                        } else {
                            missedContactsPrinter.printRecord(devContact.toString());
                            missedContactsPrinter.flush();
                        }


                    }
                }
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}


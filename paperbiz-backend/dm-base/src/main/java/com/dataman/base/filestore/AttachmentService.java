package com.dataman.base.filestore;

import com.dataman.base.exception.DmException;
import com.dataman.base.util.Strings;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Attach/remove files of a document row (legacy DmFileManagerMongo.saveFile / deleteFiles):
 * the file goes to GridFS (folder = rule code) and a row in LINKFILECOLLECTION links it to docId + sNo.
 */
@Service
public class AttachmentService {

    private final MongoFileStore store;
    private final FileLinkRepository links;

    public AttachmentService(MongoFileStore store, FileLinkRepository links) {
        this.store = store;
        this.links = links;
    }

    /** Uploads {@code source} and links it; returns the Mongo id. If linking fails the uploaded file is removed again. */
    @Transactional
    public String saveFile(String fileDb, String docId, String sNo, String ruleCode, String fileName,
                           Path source, String userName) {
        String doc = required(docId, "DocId not provided to Upload File.");
        String sn = required(sNo, "SNo. not provided to Upload File.");
        String rule = required(ruleCode, "RuleCode not provided to Upload File.");
        if (source == null || !Files.exists(source)) {
            throw new DmException("File not found to get Uploaded.");
        }
        String id;
        long size;
        try (InputStream in = Files.newInputStream(source)) {
            size = Files.size(source);
            id = store.store(fileDb, rule, null, fileName, in);
        } catch (IOException e) {
            throw new DmException(e.getMessage());
        }
        try {
            links.insert(id, doc, sn, rule, fileName, size, userName);
        } catch (RuntimeException e) {
            store.delete(fileDb, rule, id);
            throw e;
        }
        return id;
    }

    /** Removes every file linked to docId + sNo + rule (from GridFS and from the link table). */
    @Transactional
    public void deleteFiles(String fileDb, String docId, String sNo, String ruleCode) {
        String doc = required(docId, "DocId not provided to get Uploaded File Link.");
        String sn = required(sNo, "SNo. not provided to get Uploaded File Link.");
        String rule = required(ruleCode, "RuleCode not provided to get Uploaded File Link.");
        for (String link : links.fileLinks(doc, sn, rule)) {
            store.delete(fileDb, rule, Strings.xNull(link));
        }
        links.delete(doc, sn, rule);
    }

    private static String required(String value, String message) {
        String v = Strings.xNull(value).trim();
        if (v.isEmpty()) {
            throw new DmException(message);
        }
        return v;
    }
}

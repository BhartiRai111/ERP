package com.dataman.base.filestore;

import com.dataman.base.exception.DmException;
import com.mongodb.MongoNamespace;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSBuckets;
import com.mongodb.client.gridfs.model.GridFSFile;
import com.mongodb.client.model.Filters;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.bson.BsonObjectId;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Component;

/**
 * Files in MongoDB GridFS (replaces legacy DmFileManagerMongo, legacy driver API).
 * <ul>
 *   <li>one Mongo database per company ({@code fileDb}, the legacy centralFileData_Path)</li>
 *   <li>one "folder" = one GridFS bucket named by the file rule code ({@code <rule>.files / <rule>.chunks})</li>
 * </ul>
 */
@Component
public class MongoFileStore {

    private final ObjectProvider<MongoClient> clientProvider;

    public MongoFileStore(ObjectProvider<MongoClient> clientProvider) {
        this.clientProvider = clientProvider;
    }

    private MongoDatabase db(String fileDb) {
        MongoClient client = clientProvider.getIfAvailable();
        if (client == null) {
            throw new DmException("File database is not configured.");
        }
        if (fileDb == null || fileDb.isBlank()) {
            throw new DmException("File database not selected.");
        }
        return client.getDatabase(fileDb.trim());
    }

    private GridFSBucket bucket(String fileDb, String folder) {
        return GridFSBuckets.create(db(fileDb), folder);
    }

    // ---- folders ---------------------------------------------------------------------------

    public boolean folderExists(String fileDb, String folder) {
        List<String> names = collectionNames(fileDb);
        return names.contains(folder + ".files") && names.contains(folder + ".chunks");
    }

    /** Creates the folder (both GridFS collections) when it does not exist. */
    public void createFolder(String fileDb, String folder) {
        if (!folderExists(fileDb, folder)) {
            MongoDatabase d = db(fileDb);
            List<String> names = collectionNames(fileDb);
            if (!names.contains(folder + ".files")) {
                d.createCollection(folder + ".files");
            }
            if (!names.contains(folder + ".chunks")) {
                d.createCollection(folder + ".chunks");
            }
        }
    }

    public void renameFolder(String fileDb, String oldFolder, String newFolder) {
        MongoDatabase d = db(fileDb);
        d.getCollection(oldFolder + ".files").renameCollection(new MongoNamespace(d.getName(), newFolder + ".files"));
        d.getCollection(oldFolder + ".chunks").renameCollection(new MongoNamespace(d.getName(), newFolder + ".chunks"));
    }

    public void removeFolder(String fileDb, String folder) {
        bucket(fileDb, folder).drop();
    }

    // ---- files -----------------------------------------------------------------------------

    /** Stores a file; when {@code fileId} is given it is used as the Mongo id, otherwise a new id is generated. */
    public String store(String fileDb, String folder, String fileId, String fileName, InputStream content) {
        GridFSBucket b = bucket(fileDb, folder);
        if (fileId != null && !fileId.isBlank()) {
            b.uploadFromStream(new BsonObjectId(new ObjectId(fileId)), fileName, content);
            return fileId;
        }
        return b.uploadFromStream(fileName, content).toHexString();
    }

    public Optional<FileInfo> info(String fileDb, String folder, String fileId) {
        GridFSFile f = bucket(fileDb, folder).find(Filters.eq("_id", new ObjectId(fileId))).first();
        return Optional.ofNullable(f).map(x -> toInfo(x, folder));
    }

    /** Searches every folder of the company database (legacy getFile(fileId)). */
    public Optional<FileInfo> findAnywhere(String fileDb, String fileId) {
        for (String name : collectionNames(fileDb)) {
            if (name.endsWith(".files")) {
                String folder = name.substring(0, name.length() - ".files".length());
                Optional<FileInfo> f = info(fileDb, folder, fileId);
                if (f.isPresent()) {
                    return f;
                }
            }
        }
        return Optional.empty();
    }

    public void download(String fileDb, String folder, String fileId, OutputStream out) {
        bucket(fileDb, folder).downloadToStream(new ObjectId(fileId), out);
    }

    public boolean exists(String fileDb, String folder, String fileId) {
        return info(fileDb, folder, fileId).isPresent();
    }

    public void delete(String fileDb, String folder, String fileId) {
        bucket(fileDb, folder).delete(new ObjectId(fileId));
    }

    public void deleteAll(String fileDb, String folder, List<String> fileIds) {
        for (String id : fileIds) {
            delete(fileDb, folder, id);
        }
    }

    private List<String> collectionNames(String fileDb) {
        return db(fileDb).listCollectionNames().into(new ArrayList<>());
    }

    private static FileInfo toInfo(GridFSFile f, String folder) {
        return new FileInfo(f.getObjectId().toHexString(), f.getFilename(), f.getLength(), folder);
    }
}

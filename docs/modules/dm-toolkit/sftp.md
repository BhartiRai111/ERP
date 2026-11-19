# dm-toolkit / sftp

> Auto-extracted facts from legacy GWT source (`legacy-gwt/dataman_V2/dmToolkit/src/dataman/dmToolkit/sftp/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: -
- Update tables: -
- Delete cascade (in order): -
- Duplicate checks on save: none found
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: -
- Lookups/suggestions: -
- CSV export: no

## Files
| file | lines |
|---|---|
| server/DmCloneable.java | 9 |
| server/PfmsEnum.java | 141 |
| server/SftpDirectory.java | 371 |
| server/SftpEventDumpOnMongo.java | 69 |
| server/SftpEventType.java | 35 |
| server/SftpFile.java | 44 |
| server/SftpPollingDirectory_Mdl.java | 319 |
| server/SftpPollingMetaModel.java | 156 |
| server/SharedEnum.java | 843 |

## Server `DmCloneable.java` (9 lines)
## Server `SftpEventType.java` (35 lines)
### `getSftpEvent`
`public static SftpEventType getSftpEvent(String eventType)`

- Other calls: `FileAdded.toString`, `FileDeleted.toString`, `FileUpdated.toString`, `None.toString`

## Server `SharedEnum.java` (843 lines)
## Server `PfmsEnum.java` (141 lines)
## Server `SftpEventDumpOnMongo.java` (69 lines)
Helpers used: `DmServer.convertFromJson`, `DmShared.iNull`

### `run`
`public void run()`

- Other calls: `StringUtils.isEmpty`

## Server `SftpDirectory.java` (371 lines)
Helpers used: `DmBaseServer.publishEvent`, `DmServer.convertFromJson`, `DmShared.xNull`

### `createSftpDirectory`
`private boolean createSftpDirectory() throws Exception`


### `listFiles`
`public Set<FileElement> listFiles() throws IOException`


### `getEventQueue`
`public IBigQueue getEventQueue(String eventQueueKey)`


### `listRemoteFiles`
`private void listRemoteFiles(StatefulSFTPClient sftpClient, Set<FileElement> resultToStore, String directoryToList) throws IOException`


### `listRemoteFiles`
`private void listRemoteFiles(StatefulSFTPClient sftpClient, Set<FileElement> resultToStore, FileElement fileToBeAddedForMonitoring) throws IOException`


### `initializePolling`
`public void initializePolling() throws Exception`

- Other calls: `Executors.newFixedThreadPool`, `FileAdded.toString`, `FileDeleted.toString`, `FileUpdated.toString`, `SftpEventType.values`, `Thread.sleep`

### `close`
`public void close() throws IOException`


### `raiseEvent`
`private synchronized void raiseEvent(Map<String, String> eventsAttributes) throws Exception`

- Other calls: `SftpEventType.getSftpEvent`

## Server `SftpPollingDirectory_Mdl.java` (319 lines)
Helpers used: `DmServer.dCodify`, `DmServer.getCachedSftpPollingDirectoryModel`, `DmServer.getConnection`, `DmShared.iNull`, `DmShared.xNull`

### `putPfmsSsid`
`public SftpPollingMetaModel putPfmsSsid(String pfmsSsid) throws Exception`

- Errors: "PFMS SSID can't be left blank/null..."
- Other calls: `StringUtils.isBlank`, `StringUtils.stripToEmpty`

### `close`
`public void close() throws IOException`


### `getPfmsSsidDirectoryModel`
`public SftpPollingMetaModel getPfmsSsidDirectoryModel(String pfmsSsid) throws Exception`

- Errors: "PFMS SSID can't be left blank/null..."
- Other calls: `StringUtils.isBlank`, `StringUtils.stripToEmpty`

### `removePfmsSsidDirectoryModel`
`public SftpPollingMetaModel removePfmsSsidDirectoryModel(String pfmsSsid) throws Exception`

- Errors: "PFMS SSID can't be left blank/null..."
- Other calls: `StringUtils.isBlank`, `StringUtils.stripToEmpty`

### `hashCode`
`public int hashCode()`


### `equals`
`public boolean equals(Object obj)`


### `connectToSftpHost`
`private void connectToSftpHost() throws Exception`


### `sendFileToSftpServer`
`public boolean sendFileToSftpServer(String fullFilePath, String destinationFolder) throws Exception`


### `getCachedSftpPollingDirectoryModel`
`public static SftpPollingDirectory_Mdl getCachedSftpPollingDirectoryModel(String requestApiKey, String requestDmSsid) throws Exception`

- SQL 1: `Select sftpIp,sftpPort,sftpUserName,sftpPassword from applicationRegistration where (pfmsSourceId = :pfmsSourceId) and lower(apiKey)= lower(:apiKey)`
- Params: `:pfmsSourceId` ← `DmShared.xNull(requestDmSsid).trim()`; `:apiKey` ← `requestApiKey`

## Server `SftpPollingMetaModel.java` (156 lines)
### `ioErrorRaised`
`public void ioErrorRaised(IoErrorRaisedEvent event) throws InterruptedException`


### `initializePoller`
`public void initializePoller() throws Exception`

- Other calls: `DirectoryPoller.newBuilder`

### `close`
`public void close() throws IOException`

- Other calls: `Thread.sleep`

### `getPollableDirectory`
`public SftpDirectory getPollableDirectory(PfmsEnum.PfmsDataDirectories dataDirectory)`

- Other calls: `PfmsDataDirectories.convertActualPathFromTemplate`

### `hashCode`
`public int hashCode()`


### `equals`
`public boolean equals(Object obj)`


## Server `SftpFile.java` (44 lines)
## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.server`, `dataman.dmBase.dmEventbus.server`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.rdp.DirectoryPoller`, `dataman.dmToolkit.rdp.DirectoryPollerBuilder`, `dataman.dmToolkit.rdp.InitialContentEvent`, `dataman.dmToolkit.rdp.InitialContentListener`, `dataman.dmToolkit.rdp.IoErrorCeasedEvent`, `dataman.dmToolkit.rdp.IoErrorListener`, `dataman.dmToolkit.rdp.IoErrorRaisedEvent`, `dataman.dmToolkit.rdp.spi`, `dataman.queue.IBigQueue`

## Business rules (derived from code conditions)
No explicit rule/exception found beyond validator.

## Notes (hand-written)
**SFTP polling:** remote directory me nayi files par event (`getSftpEvent`, `listRemoteFiles`, `createSftpDirectory`, `initializePolling`, `run`); queue `dataman.queue`/`rdp` ke saath. Integration (PFMS jaisa source id `addPFMSSourceIdToSftpPolling`).

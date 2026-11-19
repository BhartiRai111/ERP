# dm-base / dataman_dmBase_dmSession

> Auto-extracted facts from legacy GWT source (`legacy-gwt/dataman_V2/dmBase/src/dataman_dmBase_dmSession/`). Hand-written notes are in the "Notes" section.

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
| client/DmRequestBuilder.java | 184 |
| server/DmAbstractRemoteServiceServlet.java | 111 |
| server/DmGarbageCleaner.java | 48 |
| server/DmRSS.java | 11 |
| server/DmRSSPrivate.java | 45 |
| server/DmRSSPublic.java | 27 |
| server/DmRemoteServiceServlet.java | 331 |
| server/DmRequestFilter.java | 80 |
| server/DmRequestWrapper.java | 106 |
| server/DmResponseWrapper.java | 39 |
| server/DmSerializationPolicyClient.java | 108 |
| server/DmServlet.java | 17 |
| server/DmServletPrivate.java | 73 |
| server/DmServletPublic.java | 61 |
| server/DmSession.java | 278 |
| server/DmSessionCleaner.java | 42 |
| server/DmSessionInner.java | 72 |
| server/DmSessionListener.java | 18 |
| server/DmSessionPropertyListener.java | 24 |
| server/DmSession_Cntx.java | 32 |
| server/IAppServiceResponseHandler.java | 11 |

## Server `DmSessionCleaner.java` (42 lines)
Helpers used: `DmBaseServer.deleteFilesAndDirectories`

### `run`
`public void run()`


### `cleanExpiredSessions`
`private void cleanExpiredSessions()`

- Other calls: `Collectors.toList`

## Server `DmSession_Cntx.java` (32 lines)
### `contextInitialized`
`public void contextInitialized(ServletContextEvent sc)`

- Other calls: `EnumSet.allOf`

## Server `DmRemoteServiceServlet.java` (331 lines)
### `getCodeServerPort`
`private int getCodeServerPort() throws ServletException`

- Errors: "Invalid value of gwt.codeserver.port system property;"

### `getSerializationPolicy`
`public final SerializationPolicy getSerializationPolicy(String moduleBaseURL, String strongName)`

- Other calls: `RPC.getDefaultSerializationPolicy`

### `processCall`
`public String processCall(String payload) throws SerializationException`

- Other calls: `RPC.decodeRequest`, `RPC.encodeResponseForFailure`, `RPC.invokeAndEncodeResponse`

### `checkPermutationStrongName`
`protected void checkPermutationStrongName() throws SecurityException`


### `doGetSerializationPolicy`
`protected SerializationPolicy doGetSerializationPolicy( HttpServletRequest request, String moduleBaseURL, String strongName)`

- Other calls: `DmRemoteServiceServlet.loadSerializationPolicy`

### `getCodeServerPolicyUrl`
`protected String getCodeServerPolicyUrl(String strongName)`


### `loadPolicyFromCodeServer`
`protected SerializationPolicy loadPolicyFromCodeServer(String url)`

- Other calls: `CODE_SERVER_CLIENT.loadPolicy`, `DmSerializationPolicyClient.Logger`

### `getCachedSerializationPolicy`
`private SerializationPolicy getCachedSerializationPolicy( String moduleBaseURL, String strongName)`


### `putCachedSerializationPolicy`
`private void putCachedSerializationPolicy(String moduleBaseURL, String strongName, SerializationPolicy serializationPolicy)`


### `writeResponse`
`private void writeResponse(HttpServletRequest request, HttpServletResponse response, String responsePayload) throws IOException`

- Other calls: `RPCServletUtils.acceptsGzipEncoding`, `RPCServletUtils.writeResponse`

## Server `IAppServiceResponseHandler.java` (11 lines)
## Server `DmAbstractRemoteServiceServlet.java` (111 lines)
### `doPost`
`public final void doPost(HttpServletRequest request, HttpServletResponse response)`


### `doUnexpectedFailure`
`protected void doUnexpectedFailure(Throwable e)`

- Errors: "Unable to report failure"
- Other calls: `RPCServletUtils.writeResponseForUnexpectedFailure`

### `getThreadLocalRequest`
`protected final HttpServletRequest getThreadLocalRequest()`


### `getThreadLocalResponse`
`protected final HttpServletResponse getThreadLocalResponse()`


### `validateThreadLocalData`
`private void validateThreadLocalData()`


## Server `DmSerializationPolicyClient.java` (108 lines)
### `readPolicy`
`private static SerializationPolicy readPolicy(InputStream in, String sourceName, Logger logger)`

- Other calls: `SerializationPolicyLoader.loadFromStream`

### `logMissingClasses`
`private static void logMissingClasses(Logger logger, List<ClassNotFoundException> errs)`


## Server `DmRSS.java` (11 lines)
## Server `DmGarbageCleaner.java` (48 lines)
Helpers used: `DmBaseServer.deleteExpiredLinkVerification`, `DmBaseServer.deleteFilesAndDirectories`, `DmBaseServer.deleteGlobalTemporaryCollectionsFromMongoUsingSessionId`

### `run`
`public void run()`


### `cleanInactiveSessionDirectories`
`private void cleanInactiveSessionDirectories()`


## Server `DmRSSPublic.java` (27 lines)
### `getLocalRequest`
`public HttpServletRequest getLocalRequest()`


## Server `DmSessionInner.java` (72 lines)
Helpers used: `DmBaseServer.deleteFilesAndDirectories`

### `checkSessionTasks`
`public static void checkSessionTasks()`


### `cleanExpiredSessions`
`private static void cleanExpiredSessions()`

- Other calls: `Collectors.toList`

### `lockSessions`
`private static void lockSessions()`


## Server `DmSessionPropertyListener.java` (24 lines)
## Server `DmServletPublic.java` (61 lines)
### `doGet`
`protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException`


### `doPost`
`protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException`


### `validate`
`private boolean validate(HttpServletRequest request, HttpServletResponse response) throws IOException`


## Server `DmServletPrivate.java` (73 lines)
### `doGet`
`protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException`


### `doPost`
`protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException`


### `validate`
`private boolean validate(HttpServletRequest request, HttpServletResponse response) throws IOException`


## Server `DmSessionListener.java` (18 lines)
## Server `DmRSSPrivate.java` (45 lines)
### `getLocalRequest`
`public HttpServletRequest getLocalRequest()`


## Server `DmResponseWrapper.java` (39 lines)
Helpers used: `DmBaseServer.getApplicationServiceHandler`

### `setDatamanSessionExpired`
`public void setDatamanSessionExpired(boolean isExpired)`


### `setDatamanSessionLock`
`public void setDatamanSessionLock(boolean isLock)`


## Server `DmSession.java` (278 lines)
### `getLastAccessDurationInSeconds`
`public synchronized long getLastAccessDurationInSeconds()`

- Other calls: `LocalDateTime.from`, `LocalDateTime.now`

### `getCreationTime`
`public long getCreationTime()`


### `getLastAccessedTime`
`public long getLastAccessedTime()`


### `getAttribute`
`public Object getAttribute(String name)`

- Other calls: `LocalDateTime.now`

### `getValueNames`
`public String[] getValueNames()`

- Other calls: `LocalDateTime.now`

### `setAttribute`
`public void setAttribute(String string, Object o)`

- Other calls: `LocalDateTime.now`

### `removeAttribute`
`public void removeAttribute(String string)`

- Other calls: `LocalDateTime.now`

### `validateSession`
`public static boolean validateSession(String sessionId, HttpServletRequest req)`

- Errors: "session does not exist." | "session not validated."

### `getEntryPointWiseAttributes`
`public Map<String, Object> getEntryPointWiseAttributes(String rpcEntryPointName)`


### `releaseAllAttributes`
`public void releaseAllAttributes() throws Exception`


## Server `DmRequestWrapper.java` (106 lines)
### `assignUniqueDatamanSessionId`
`public void assignUniqueDatamanSessionId()`

- Other calls: `UUID.randomUUID`

### `getDatamanRequest`
`public static DmRequestWrapper getDatamanRequest(HttpServletRequest instance) throws Exception`

- Errors: "not a valid dataman request"

### `getIsNew`
`public boolean getIsNew()`


### `getSession`
`public HttpSession getSession(boolean create)`


### `getDatamanSession`
`public DmSession getDatamanSession() throws Exception`

- Errors: "not a valid dataman request."

## Server `DmRequestFilter.java` (80 lines)
Helpers used: `DmBaseServer.createRepositorySsn`

### `doFilter`
`public void doFilter(ServletRequest req, ServletResponse res, FilterChain fc) throws IOException, ServletException`


## Server `DmServlet.java` (17 lines)
## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmBaseStatic.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.jvmSerialization.shared`, `dataman.dmBase.self.shared`

## Business rules (derived from code conditions)
No explicit rule/exception found beyond validator.

## Notes (hand-written)
_No hand-written notes yet._

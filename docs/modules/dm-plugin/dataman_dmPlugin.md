# dm-plugin / dataman_dmPlugin

> Auto-extracted facts from legacy GWT source (`legacy-gwt/dataman_V2/dmPlugin/src/dataman_dmPlugin/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: -
- Update tables: -
- Delete cascade (in order): -
- Duplicate checks on save: none found
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: -
- Lookups/suggestions: getHostname, getHeaders
- CSV export: no

## Files
| file | lines |
|---|---|
| CFunctions.java | 454 |
| ILocalSettingsRefreshCommander.java | 16 |
| localSettings/LocalSettings.java | 98 |
| localSettings/PrinterInfo.java | 30 |
| printing/DmFilePrintRequest.java | 257 |
| printing/DmPrintCommands.java | 87 |
| querySession/QueryToClient.java | 31 |
| resources/DmPlugInResources.java | 29 |
| routing/DmPluginBaseRouter.java | 30 |
| server/InternalRewrite.java | 31 |
| server/NanoHTTPD.java | 1868 |
| server/WebServerPlugin.java | 19 |
| server/WebServerPluginInfo.java | 14 |
| server/router/RouterNanoHTTPD.java | 496 |
| server/util/ServerRunner.java | 122 |

## Server `NanoHTTPD.java` (1868 lines)
### `mimeTypes`
`public static Map<String, String> mimeTypes()`

- Other calls: `LOG.log`, `MIME_TYPES.isEmpty`

### `loadMimeTypes`
`private static void loadMimeTypes(Map<String, String> result, String resourceName)`

- Other calls: `LOG.log`

### `makeSSLSocketFactory`
`public static SSLServerSocketFactory makeSSLSocketFactory(KeyStore loadedKeyStore, KeyManager[] keyManagers) throws IOException`

- Other calls: `SSLContext.getInstance`, `TrustManagerFactory.getDefaultAlgorithm`, `TrustManagerFactory.getInstance`

### `makeSSLSocketFactory`
`public static SSLServerSocketFactory makeSSLSocketFactory(KeyStore loadedKeyStore, KeyManagerFactory loadedKeyFactory) throws IOException`


### `makeSSLSocketFactory`
`public static SSLServerSocketFactory makeSSLSocketFactory(String keyAndTrustStoreClasspathPath, char[] passphrase) throws IOException`

- Errors: "Unable to load keystore from classpath: "
- Other calls: `KeyManagerFactory.getDefaultAlgorithm`, `KeyManagerFactory.getInstance`, `KeyStore.getDefaultType`, `KeyStore.getInstance`

### `getMimeTypeForFile`
`public static String getMimeTypeForFile(String uri)`


### `safeClose`
`private static final void safeClose(Object closeable)`

- Errors: "Unknown object to close"
- Other calls: `LOG.log`

### `decodeParameters`
`public static Map<String, List<String>> decodeParameters(String queryString)`


### `decodePercent`
`protected static String decodePercent(String str)`

- Other calls: `LOG.log`, `URLDecoder.decode`

### `useGzipWhenAccepted`
`protected boolean useGzipWhenAccepted(Response r)`


### `isAlive`
`public final boolean isAlive()`


### `makeSecure`
`public void makeSecure(SSLServerSocketFactory sslServerSocketFactory, String[] sslProtocols)`


### `newFixedLengthResponse`
`public static Response newFixedLengthResponse(IStatus status, String mimeType, String txt)`

- Other calls: `Charset.forName`, `LOG.log`

### `serve`
`public Response serve(IHTTPSession session)`

- Other calls: `POST.equals`, `PUT.equals`

### `serve`
`public Response serve(String uri, Method method, Map<String, String> headers, Map<String, String> parms, Map<String, String> files)`


### `start`
`public void start(final int timeout, boolean daemon) throws IOException`

- Other calls: `Thread.sleep`

### `stop`
`public void stop()`

- Other calls: `LOG.log`

## Server `WebServerPlugin.java` (19 lines)
## Server `InternalRewrite.java` (31 lines)
## Server `WebServerPluginInfo.java` (14 lines)
## Server `ServerRunner.java` (122 lines)
### `executeInstance`
`public static void executeInstance(NanoHTTPD server)`


### `setServer`
`public static <T extends DmPluginBaseRouter> void setServer(Class<T> serverClass)`


### `startServer`
`public static boolean startServer()`

- Other calls: `CFunctions.getAllAttachedLocalSettingsReloadedRefreshSubscriber`, `CFunctions.initializeDmPlugIn`, `LSE_StartMachines.toString`, `TRUE.toString`

### `stopServer`
`public static boolean stopServer()`

- Other calls: `CFunctions.getAllAttachedLocalSettingsReloadedRefreshSubscriber`

## Server `RouterNanoHTTPD.java` (496 lines)
### `normalizeUri`
`public static String normalizeUri(String value)`


### `addMappings`
`public void addMappings()`


## Depends on (imports from other packages)
`dataman.dmBase.jvmSerialization.shared`, `dataman.dmBase.self.shared`, `dataman.dmPlugin.CFunctions`, `dataman.dmPlugin.ILocalSettingsRefreshCommander`, `dataman.dmPlugin.localSettings.LocalSettings`, `dataman.dmPlugin.printing.DmPrintCommands`, `dataman.dmPlugin.querySession.QueryToClient`, `dataman.dmPlugin.routing.DmPluginBaseRouter`, `dataman.dmPlugin.server.NanoHTTPD`, `dataman.dmPlugin.server.router`, `dataman.libraryMenu.mi.shared`

## Business rules (derived from code conditions)
No explicit rule/exception found beyond validator.

## Notes (hand-written)
_No hand-written notes yet._

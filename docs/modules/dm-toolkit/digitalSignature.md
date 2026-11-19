# dm-toolkit / digitalSignature

> Auto-extracted facts from legacy GWT source (`legacy-gwt/dataman_V2/dmToolkit/src/dataman/dmToolkit/digitalSignature/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: -
- Update tables: -
- Delete cascade (in order): -
- Duplicate checks on save: none found
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: -
- Lookups/suggestions: getHeaderInfo
- CSV export: no

## Files
| file | lines |
|---|---|
| server/DscDocument_Server.java | 347 |
| server/DscSigningService.java | 173 |
| server/shared/DscDocument_Mdl.java | 116 |

## Server `DscSigningService.java` (173 lines)
Helpers used: `DmShared.xNull`

### `signPdf`
`public static boolean signPdf(String serialNo, String inputPdf, String outputPdf, String pfxPath, String pfxPassword, float signatureWidth, float signatureHeight, float x1, float y1, String reason, String location)`

- Errors: "No certificate found in PFX matching serial number: " | "Private key not found." | "Certificate chain not found."
- Other calls: `KeyStore.getInstance`, `MakeSignature.signDetached`, `PdfStamper.createSignature`, `Security.addProvider`, `Security.getProvider`

### `getKeyStore`
`public static KeyStore getKeyStore(final String pfxPath, final String pfxPassword) throws Exception`

- Other calls: `KeyStore.getInstance`

### `getHeaderInfo`
`public static List<KeyFieldExtended> getHeaderInfo(final String pfxPath, final String pfxPassword) throws Exception`


## Server `DscDocument_Server.java` (347 lines)
Helpers used: `DmServer.getMdbCollection`, `DmServer.getSsnId`, `DmServer.getSsnSiteCode`, `DmShared.iNull`, `DmShared.vNull`, `DmShared.xNull`

### `save`
`public static void save(DscDocument_Mdl mrTemp, DmRSS dmRSS) throws Exception`

- Errors: "Invalid docId" | "Invalid Data"

### `get`
`public static DscDocument_Mdl get(BasicDBObject filter, DmRSS dmRSS, boolean isConstructDocument) throws Exception`


### `genrateByte`
`private static byte[] genrateByte(String data) throws Exception`

- Other calls: `Base64.getDecoder`

### `getByteString`
`public static String getByteString(String filePath) throws Exception`

- Other calls: `Files.readAllBytes`, `Paths.get`

### `getByteString`
`private static String getByteString(byte[] data) throws Exception`

- Other calls: `Base64.getEncoder`

### `getByteArrayInputStream`
`public static ByteArrayInputStream getByteArrayInputStream(String bytStr) throws Exception`


### `getDocumentURL`
`private static String getDocumentURL(String documentName, String signedDocument, DmRSS dmRSS) throws Exception`

- Errors: "Unable to create session directory."
- Other calls: `Base64.getDecoder`, `Files.write`

### `getDocumentURLOld`
`public static String getDocumentURLOld(BasicDBObject filter, DmRSS dmRSS) throws Exception`

- Errors: "Unable to create session directory."
- Other calls: `Base64.getDecoder`, `Files.write`

### `isValidConfig`
`public static boolean isValidConfig(int documentType, String siteCode, Connection cnTemp) throws Exception`

- SQL 1: `SELECT COUNT(*) cnt FROM dscEnviro e WHERE e.site_Code = :siteCode AND e.documentType = :documentType`
- Params: `:siteCode` ← `DmShared.xNull(siteCode)`; `:documentType` ← `DmShared.iNull(documentType).intValue()`

### `getSignedDocument`
`public static String getSignedDocument(int documentType, String unsignedDocument, String reason, String location, Connection cnTemp, DmRSS dmRSS) throws Exception`

- SQL 1: `SELECT e.srNo, e.documentType, e.signWidth, e.signLength , e.locationX, e.locationY, m.password, m.serverFileName AS pfxFileName FROM dscEnviro e LEFT JOIN dscMast m ON m.srNo = e.srNo WHERE e.site_Code = :siteCode AND e.documentType = :documentType`
- Params: `:siteCode` ← `DmServer.getSsnSiteCode(dmRSS)`; `:documentType` ← `DmShared.iNull(documentType).intValue()`
- Errors: "Failed to sign PDF: " | "No DSC configuration found for document type: "
- Other calls: `DscSigningService.signPdf`

## Server `DscDocument_Mdl.java` (116 lines)
## Depends on (imports from other packages)
`dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.digitalSignature.server`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`

## Business rules (derived from code conditions)
No explicit rule/exception found beyond validator.

## Notes (hand-written)
**PDF digital signature:** `signPdf`, keystore (PFX, `dscMaster` ka certificate) se; `getKeyStore`, header info. e-invoice/PDF signing ke liye.

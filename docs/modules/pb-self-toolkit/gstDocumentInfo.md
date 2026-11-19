# pb-self-toolkit / gstDocumentInfo

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/selfToolkit/src/paperbiz/selfToolkit/gstDocumentInfo/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: -
- Update tables: -
- Delete cascade (in order): -
- Duplicate checks on save: none found
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: -
- Lookups/suggestions: getTxtHDocumentType, getTxtHDocumentNature, getTxtHGSTItemType, getTxtHGSTItemNature
- CSV export: no

## Files
| file | lines |
|---|---|
| client/GSTDocumentInfo.java | 325 |
| client/GSTDocumentInfo_Dsg.java | 154 |
| server/GSTDocumentInfo_SrvImpl.java | 282 |
| shared/DefaultValue_Mdl.java | 33 |
| shared/GSTDocumentInfoParam_Mdl.java | 30 |
| shared/GSTDocumentInfo_Srv.java | 19 |
| shared/GSTDocumentInfo_SrvAsync.java | 20 |
| shared/GSTDocumentInfo_Vld.java | 40 |
| shared/TxtHServiceDetail_Mdl.java | 213 |

## UI labels / columns (client)
`GST Document Type`, `GST Document Nature`, `GST Item Type`, `GST Item Nature`

## Models (shared DTOs)
### DefaultValue_Mdl
| type | field |
|---|---|
| String | documentTypeCSV |
| GstDocument_Mdl | gstdi_Mdl |

### GSTDocumentInfoParam_Mdl
| type | field |
|---|---|
| String | voucherCategory |
| String | mode |

### TxtHServiceDetail_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | smfTag |
| String | smfText |
| String | serviceEvalTypeCode |
| String | serviceEvalTypeName |
| String | serviceEvalRate |
| String | hsnCode |
| String | hsn |
| String | itemGSTNature |
| String | manualCode |
| String | uniqueId |
| String | itsBasedOn |
| String | gstUnitCode |
| String | skuCode |
| String | skuName |
| String | isubCode |
| String | balancingAc |
| String | purchaseAc |
| String | purchaseReturnAc |
| String | salesAc |
| String | salesReturnAc |
| String | tranferAc |
| String | transitAc |

## RPC interface
| method | params | returns |
|---|---|---|
| getDefaultValues | String voucherCategory | DmResult<DefaultValue_Mdl> |
| getTxtHDocumentType | String voucherCategory, DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHDocumentNature | String gstDocNatureShortName, DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHGSTItemType | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHGSTItemNature | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |

## Validator `GSTDocumentInfo_Vld`
```java
public class GSTDocumentInfo_Vld {
    public static enum ErrorType {
        GST_DOCUMENT_TYPE(999910), GST_DOCUMENT_NATURE(999920), ITEM_GST_TYPE(999930), ITEM_GST_NATURE(999940);
        private int errorCode;
        ErrorType(int errorCode) {
            this.errorCode = errorCode;
        }
        public int getErrorCode() {
            return errorCode;
        }
    }
    public static ErrorType getErrorTypeViaCode(int errorCode) {
        ErrorType errorCodeType = null;
        for (ErrorType type : ErrorType.values()) {
            if (DmShared.iNull(errorCode).intValue() == type.getErrorCode()) {
                return type;
            }
        }
        return errorCodeType;
    }
    public static void validate(GstDocument_Mdl mrTemp) {
        DmShared.isValidText(mrTemp.getGstDocTypeCode(), "GST Document Type", ErrorType.GST_DOCUMENT_TYPE.getErrorCode());
        DmShared.isValidText(mrTemp.getGstDocNatureCode(), "GST Document Nature", ErrorType.GST_DOCUMENT_NATURE.getErrorCode());
        DmShared.isValidText(mrTemp.getItemGstTypeCode(), "Item GST Type", ErrorType.ITEM_GST_TYPE.getErrorCode());
        DmShared.isValidText(mrTemp.getItemGSTNatureCode(), "Item GST Nature", ErrorType.ITEM_GST_NATURE.getErrorCode());
    }
}
```

## Server `GSTDocumentInfo_SrvImpl.java` (282 lines)
Helpers used: `DmServer.evaluateHelper`, `DmServer.getConnection`, `DmServer.getSsnDBName`, `DmShared.filterKeyFieldList`, `DmShared.xNull`

### `getDefaultValues`
`public DmResult<DefaultValue_Mdl> getDefaultValues(String voucherCategory)`


### `getDefaultValue`
`public DefaultValue_Mdl getDefaultValue(String voucherCategory, Connection cnTemp)`

- SQL 1: `SELECT vt.v_Type, vt.documentTypeCode, vt.documentNatureCode, vt.itemGstType , vt.itemGSTNature, vt.documentTypeCSV FROM voucher_Type vt WHERE vt.category = :category`
- Params: `:category` ← `DmShared.xNull(voucherCategory)`
- Errors: "'Voucher Type' is mandatory for 'GST Document Info'!"
- Other calls: `GstDocumentNature.getViaShortName`, `GstDocumentType.getViaShortName`

### `getTxtHDocumentType`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHDocumentType(String voucherCategory, DmHelper helper, boolean blnToEndOfFile)`

- Other calls: `GstDocumentType.getViaShortName`, `GstDocumentType.values`

### `getTxtHDocumentNature`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHDocumentNature(String gstDocNatureShortName, DmHelper helper, boolean blnToEndOfFile)`

- Other calls: `GstDocumentNature.values`

### `getTxtHGSTItemType`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHGSTItemType(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT git.code , git.name , git.manualCode FROM gstItemType git`

### `getTxtHGSTItemNature`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHGSTItemNature(DmHelper helper, boolean blnToEndOfFile)`

- Other calls: `GSTNature.values`

## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.gst.shared`, `paperbiz.selfToolkit.gstDocumentInfo.shared`

## Business rules (derived from code conditions)
| method | when | error message |
|---|---|---|
| getDefaultValue | `if (DmShared.xNull(voucherCategory).trim().equalsIgnoreCase(""))` | "'Voucher Type' is mandatory for 'GST Document Info'!" |

## Notes (hand-written)
_No hand-written notes yet._

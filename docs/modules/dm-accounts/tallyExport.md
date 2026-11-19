# dm-accounts / tallyExport

> Auto-extracted facts from legacy GWT source (`legacy-gwt/dataman_V2/accounts/src/tallyExport/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: -
- Update tables: saleBill, pBill, itemCat, voucher_Type, sg, subGroup, ledgerM, serviceGroup, departmentMast, acGroup, patientServiceCatMast, patientServiceMast, itemGroup, patientReceipt, itemMast, unitMast
- Delete cascade (in order): -
- Duplicate checks on save: none found
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: -
- Lookups/suggestions: getTxtHAccountOpeningType
- CSV export: no

## Screen spec (auto-extracted from client)
### Screen `AsOnDatePopUp`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | As On Date | DmTextBox | `txtAsOnDate` | `11, true` |
| 2 | Account Master | DmTextHelp | `txtHAccountOpeningType` | `KeyField_PrpAcs, 3, true, false` |

**Grid / popup columns** (header, width, db field):
- `txtHAccountOpeningType`: Opening Type [Text, w0, Name]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHAccountOpeningType` → `getTxtHAccountOpeningType`

**Event handlers (condensed code, CRUD flow and field callbacks):**

`txtHAccountOpeningType.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHAccountOpeningType(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHAccountOpeningType).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHAccountOpeningType.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHAccountOpeningType).show();
```

`txtHAccountOpeningType.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHAccountOpeningType.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHAccountOpeningType).show();
```

`txtHAccountOpeningType.addTextHelpCallBack` → `getSelectedRow()`
```java
if (blnStatus && tRow != null) {
```
- Alert: "As on date is mandatory."

### Screen `CurrentRunningProcessPopUp`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | - | DmTextArea | `txtTallyProcessRunning` | `500, false` |

### Screen `TallyExport`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|

Other widgets (not in tab list): `grdTallyDetails` (DmGrid), `grdTallyProcess` (DmGrid)

**Grid / popup columns** (header, width, db field):
- `grdTallyDetails`: Description [Text, w170]; Values [Text, w312]
- `grdTallyProcess`: Masters / Transactions [Text, w225]; Transferred [Text, w100]; Transferring [Text, w422]

**Lookup / suggestion / find sources (server RPC called):**
- `currentRunningProcessPopUp` → `onlyOpeningProcessGrid`

**Event handlers (condensed code, CRUD flow and field callbacks):**

`asOnDatePopUp.addCallBack` → `onSave()`
```java
defaultValue.setAsOnDate(asOnDate);
if (!DmShared.xNull(acOpeningTypeCode).equalsIgnoreCase("")) {
defaultValue.setAcOpeningTypeCode(acOpeningTypeCode);
defaultValue.setAcOpeningTypeName(acOpeningTypeName);
} else {
defaultValue.setAcOpeningTypeCode(DmShared.iNull(AcEnum.TALLY_ACCOUNT_OPENING_TYPE.TRANSFER_WITHOUT_OPENING.getCode()).t
defaultValue.setAcOpeningTypeName(DmShared.xNull(AcEnum.TALLY_ACCOUNT_OPENING_TYPE.TRANSFER_WITHOUT_OPENING.getName()));
setGridTallyDetails();
if (DmShared.iNull(acOpeningTypeCode).intValue() == DmShared.iNull(AcEnum.TALLY_ACCOUNT_OPENING_TYPE.TRANSFER_ONLY_OPENI
transferOnlyOpeningProcess();
...
```
- Confirm: "Are you sure? You want to start the process."
- Confirm: "Are you sure? You want to close."
- Alert: "As on date is mandatory."

## Files
| file | lines |
|---|---|
| client/AsOnDatePopUp.java | 168 |
| client/AsOnDatePopUp_Dsg.java | 144 |
| client/CurrentRunningProcessPopUp.java | 121 |
| client/CurrentRunningProcessPopUp_Dsg.java | 104 |
| client/TallyExport.java | 293 |
| client/TallyExport_Dsg.java | 282 |
| server/AcGroup.java | 180 |
| server/AcMaster.java | 344 |
| server/BankPayment.java | 411 |
| server/BankReceipt.java | 393 |
| server/CashPayment.java | 299 |
| server/CashReceipt.java | 310 |
| server/EodPosting.java | 312 |
| server/GodownMaster.java | 162 |
| server/HospitalReceiptRefundPosting.java | 313 |
| server/ItemCategory.java | 171 |
| server/ItemGroup.java | 178 |
| server/ItemMaster.java | 251 |
| server/Journal.java | 309 |
| server/OrganizationReceipt.java | 312 |
| server/PurchaseBillItemized.java | 560 |
| server/PurchaseBillReturnItemized.java | 577 |
| server/PurchaseBillVoucher.java | 322 |
| server/SaleBillItemized.java | 602 |
| server/SaleBillReturnItemized.java | 591 |
| server/SaleBillService.java | 599 |
| server/SaleBillVoucher.java | 324 |
| server/ServiceCategory.java | 171 |
| server/ServiceGroup.java | 179 |
| server/ServiceMaster.java | 248 |
| server/TallyExport_Run.java | 290 |
| server/TallyExport_Server.java | 153 |
| server/TallyExport_SrvImpl.java | 834 |
| server/UnitMaster.java | 190 |
| server/VoucherType.java | 215 |
| shared/DefaultValue_Mdl.java | 215 |
| shared/TallyExport_Srv.java | 26 |
| shared/TallyExport_SrvAsync.java | 26 |
| shared/TallyResponse_Mdl.java | 124 |

## UI labels / columns (client)
`As On Date`, `Account Master`

## Models (shared DTOs)
### TallyResponse_Mdl
| type | field |
|---|---|
| String | lineError |
| int | created |
| int | altered |
| int | deleted |
| int | lastVchid |
| int | lastMid |
| int | combined |
| int | ignored |
| int | errors |
| int | cancelled |
| int | exceptions |

### DefaultValue_Mdl
| type | field |
|---|---|
| String | batchId |
| String | gateWayIP |
| String | gateWayPort |
| String | tallyComp |
| String | tallyState |
| String | tallyGSTType |
| String | asOnDate |
| String | siteCode |
| String | aarogyaSiteNames |
| String | siteCodeLst |
| String | siteGSTIN |
| String | pltCode |
| String | fileDbName |
| String | trnDbName |
| String | v_Prefix |
| String | startDate |
| String | userName |
| String | tallyIntegrationTypeCode |
| String | tallyIntegrationTypeName |
| String | acOpeningTypeCode |
| String | acOpeningTypeName |

## RPC interface
| method | params | returns |
|---|---|---|
| getTallySync | DefaultValue_Mdl mdlParameter | DmResult<String> |
| getDefaultValues |  | DmResult<DefaultValue_Mdl> |
| refreshCurrentRunningProcessPopUp |  | DmResult<String> |
| refreshProcessGrid | String isItemized, String onlyOpeningCode | DmResult<List<TallyProcess_Mdl>> |
| onlyOpeningProcessGrid | String onlyOpeningCode | DmResult<List<TallyProcess_Mdl>> |
| getTxtHAccountOpeningType | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |

## Server `SaleBillReturnItemized.java` (591 lines)
Helpers used: `DmServer.getParamList`, `DmServer.getServerTimestamp`, `DmShared.iNull`, `DmShared.xNull`

### `startExport`
`public void startExport(Connection cnTemp, DefaultValue_Mdl tmpMdlParameter) throws Exception`

- SQL 1: `select sb.docid,isNull(sg.add1,'') as partyAddress, isNull(CONVERT(CHAR(8), sb.v_date, 112),'') as voucherDt ,case when sg.partyType='R' then 'Regular' when sg.partyType='U' then 'Un Registered' when sg.partyType='C' then 'Composition' end as partyGstType ,isNull(sm.name,'') as partyState,isNull(sg.gsTin,'') as partyGSTin, sg.name+'('+cast(sg.subCode as varchar(20))+')' as partyName,sb.recId, vt.description as voucherType,sbd.rate as sRate, um.unit,sbd.oh_Amt_Gross as itemValue, sbd.oh_at_SGST as sgstRate, isNull(sbd.oh_at_SGST,0)+isNull(sbd.oh_at_CGST,0) as igstRate ,0-round(isNull(sbd.oh_Amt_Gross,0)-isNull(sbd.oh_Amt_Discount,0)-isNull(sbd.oh_Amt_Discount2,0) -(isNull(sbd.oh_Amt_CGST,0)+isNull(sbd.oh_Amt_SGST,0)+isNull(sbd.oh_Amt_IGST,0)),2) as itemTaxableAmount,sbd.skuQuantity as qty,isNull(sbd.batchNo,'') as batchNo ,sbd.oh_at_CGST as cgstRate, sgd.name as departmentName,sgs.name +'('+cast(sgs.subCode as varchar(20))+')' as salesAc,sbd.isFooter, im.name as ItemName,hsn.hsn ,itmcnt.maxSno,sbd.v_sno from saleBill sb left join voucher_Type vt on sb.v_Type=vt.v_Type left join saleBilldetail sbd on sb.docID=sbd.docID left join subGroup sg on sb.subCode=sg.subCode left join subGroup sgd on sgd.subCode=sb.departmentCode left join subGroup sgs on sgs.subCode=sbd.oh_Ac_Sales left join itemMAst im on sbd.itemCode=im.code left join hsnMast hsn on im.hsnCode=hsn.code left join unitMast um on im.purchaseUnit=um.code left join city c on sg.cityCode=c.cityCode left join stateMast sm on c.stateCode=sm.code left join stateMast pos on pos.numericCode=sb.placeOfSupply left join ( select sb.docid,max(sbd.v_Sno) as maxSno from saleBill sb left join saleBillDetail sbd on sb.docid = sbd.docId left join voucher_Type vt on sb.v_Type = vt.v_Type where vt.category in(' {…} ') and sbd.isFooter=0 and sb.site_code in ( {…} :siteCodeLst {…} ) and sb.v_Prefix= :vPrefix and sb.v_Date<= :asOnDate and sb.tallySyncDt is null group by sb.docid )itmcnt on sb.docid=itmcnt.docid where vt.category in(' {…} ') and sbd.isFooter=0 and sb.site_code in ( {…} :siteCodeLst {…} ) and sb.v_Prefix= :vPrefix and sb.v_Date<= :asOnDate and sb.tallySyncDt is null order by sb.v_Date,sb.docid,sb.v_no`
- SQL 2: `select ld.docid,ld.ledgerAmount,sg.name +'('+cast(sg.subCode as varchar(20))+')' as ledgerPartyName from ( select sbd.docid,sum(oh_Amt_Net) as ledgerAmount,(select pharmacyControlAc from enviro where site_Code=:siteCode) as account from salebill sbd where docid=:docId group by sbd.docid union all select sbd.docid,0-sum(oh_amt_sgst) as ledgerAmount,sbd.oh_Ac_SGST as account from salebillDetail sbd where docid=:docId group by sbd.docid,sbd.oh_Ac_SGST union all select sbd.docid,0-sum(oh_amt_cgst) as ledgerAmount,sbd.oh_Ac_CGST as account from salebillDetail sbd where docid=:docId group by sbd.docid,sbd.oh_Ac_CGST union all select sbd.docid,0-sum(oh_Amt_RoundOff) as ledgerAmount,(select serviceAc from patientServiceCatMast where shortName='RO') as account from salebill sbd where docid=:docId group by sbd.docid ) as ld left join subGroup sg on ld.account=sg.subCode`
- Params: `:asOnDate` ← `DmShared.xNull(tmpMdlParameter.getAsOnDate())`; `:vPrefix` ← `DmShared.xNull(tmpMdlParameter.getV_Prefix())`; `:docId` ← `DmShared.iNull(prevDocid).longValue()`; `:siteCode` ← `DmShared.iNull(tmpMdlParameter.getSiteCode()).intValue()`
- Other calls: `DocumentBuilderFactory.newInstance`, `FAILED.getCode`, `PROCESSING.getCode`, `TallyExport_Server.tallySyncLogInsert`, `XPathFactory.newInstance`

### `generateXml`
`private void generateXml(Document xmlDocument, String fileDbName, String mongoId, String docId, String recId, Connection cnTemp, String gateWayIP, String gateWayPort, String u_Name, TallyProcess_Mdl tallyprocessMdl) throws Exception`

- SQL 1: `Update saleBill set tallyKeyName = :tallyKeyName, tallySyncDt = :tallySyncDt where docId = :docId ;`
- Params: `:docId` ← `DmShared.xNull(docId)`; `:tallyKeyName` ← `DmShared.xNull(recId)`; `:tallySyncDt` ← `DmServer.getServerTimestamp()`
- Errors: "Exception Occured."
- Other calls: `TallyExport_Server.sendToTally`, `TallyExport_Server.tallySyncLogUpdate`, `TransformerFactory.newInstance`

## Server `PurchaseBillItemized.java` (560 lines)
Helpers used: `DmServer.getParamList`, `DmServer.getServerTimestamp`, `DmShared.iNull`, `DmShared.xNull`

### `startExport`
`public void startExport(Connection cnTemp, DefaultValue_Mdl tmpMdlParameter) throws Exception`

- SQL 1: `select pb.docid, pb.recId, sg.add1 as partyAddress, isNull(CONVERT(CHAR(8), pb.v_date, 112),'') as voucherDt, isNull(CONVERT(CHAR(8), pb.partyBillDate, 112),'') as reffDate ,case when sg.partyType='R' then 'Regular' when sg.partyType='U' then 'Unregistered/Consumer' when sg.partyType='C' then 'Composition' else 'Unknown' end as partyGstType ,sm.name as partyState,sg.gsTin as partyGSTin, pos.name as placeOfSupply,sg.name+'('+cast(sg.subCode as varchar(20))+')' as partyName,pb.recId, pb.partyBillNo as reffNo,vt.description as voucherType,pbd.rate as pRate, um.unit,pbd.oh_Amt_Net as itemValue, pbd.oh_at_SGST as sgstRate, isNull(pbd.oh_at_SGST,0)+isNull(pbd.oh_at_CGST,0) as igstRate ,0-pbd.oh_Amt_Gross as itemGrossAmount,pbd.acceptQty as qty,isNull(pbd.batchNo,'') as batchNo ,pbd.oh_at_CGST as cgstRate, sgd.name as departmentName,sg.name+'('+cast(sg.subCode as varchar(20))+')' as purchaseAC, im.name as ItemName ,itmcnt.maxSno,pbd.v_sno from pBill pb left join voucher_Type vt on pb.v_Type=vt.v_Type left join pBilldetail pbd on pb.docID=pbd.docID left join subGroup sg on pb.subCode=sg.subCode left join subGroup sgd on sgd.subCode=pb.departmentCode left join itemMAst im on pbd.itemCode=im.code left join unitMast um on im.purchaseUnit=um.code left join city c on sg.cityCode=c.cityCode left join stateMast sm on c.stateCode=sm.code left join stateMast pos on pos.numericCode=pb.placeOfSupply left join ( select pb.docid,max(pbd.v_Sno) as maxSno from pBill pb left join pBillDetail pbd on pb.docid=pbd.docId left join voucher_Type vt on pb.v_Type=vt.v_Type where vt.category in (' {…} ', ' {…} ', ' {…} ', ' {…} ', ' {…} ', ' {…} ', ' {…} ', ' {…} ') and pb.site_code in ( {…} :siteCodeLst {…} ) and isNull(pb.approvedBy,'')<>'' and pb.v_Prefix= :vPrefix and pb.v_Date<= :asOnDate and pb.tallySyncDt is null group by pb.docid) itmcnt on pb.docid=itmcnt.docid where vt.category in (' {…} ', ' {…} ', ' {…} ', ' {…} ', ' {…} ', ' {…} ', ' {…} ', ' {…} ') and pb.site_code in ( {…} :siteCodeLst {…} ) and isNull(pb.approvedBy,'')<>'' and pb.v_Prefix= :vPrefix and pb.v_Date<= :asOnDate and pb.tallySyncDt is null order by pb.v_Date,pb.docid,pb.v_no`
- SQL 2: `select sg.name+'('+cast(sg.subCode as varchar(20))+')' as ledgerPartyName,case when l.amtCr>0 then l.amtCr when l.amtDr>0 then 0-l.amtDr end as ledgerAmount ,l.* from ledger l left join subGroup sg on l.subCode=sg.subCode where l.docid = :docId and l.subcode not in(select isNull(subCode,'') from overHeadLine where docid=:docId and overHeadMCode='TV' group by subCode)`
- Params: `:asOnDate` ← `DmShared.xNull(tmpMdlParameter.getAsOnDate())`; `:vPrefix` ← `DmShared.xNull(tmpMdlParameter.getV_Prefix())`; `:docId` ← `DmShared.iNull(prevDocid).longValue()`
- Other calls: `DocumentBuilderFactory.newInstance`, `FAILED.getCode`, `PROCESSING.getCode`, `TallyExport_Server.tallySyncLogInsert`, `XPathFactory.newInstance`

### `generateXml`
`private void generateXml(Document xmlDocument, String fileDbName, String mongoId, String docId, String recId, Connection cnTemp, String gateWayIP, String gateWayPort, String u_Name, TallyProcess_Mdl tallyprocessMdl) throws Exception`

- SQL 1: `Update pBill set tallyKeyName = :tallyKeyName, tallySyncDt = :tallySyncDt where docId = :docId ;`
- Params: `:docId` ← `DmShared.xNull(docId)`; `:tallyKeyName` ← `DmShared.xNull(recId)`; `:tallySyncDt` ← `DmServer.getServerTimestamp()`
- Errors: "Exception Occured."
- Other calls: `TallyExport_Server.sendToTally`, `TallyExport_Server.tallySyncLogUpdate`, `TransformerFactory.newInstance`

## Server `ItemCategory.java` (171 lines)
Helpers used: `DmServer.getServerTimestamp`, `DmShared.iNull`, `DmShared.xNull`

### `startExport`
`public void startExport(Connection cnTemp, DefaultValue_Mdl tmpMdlParameter) throws Exception`

- SQL 1: `select ic.code,ic.name as categoryName from itemCat ic where ic.tallySyncdt is null`
- SQL 2: `Update itemCat set tallyKeyName = :tallyKeyName, tallySyncDt = :tallySyncDt where code = :code ;`
- Params: `:code` ← `DmShared.xNull(rsTemp.getString("code"))`; `:tallyKeyName` ← `DmShared.xNull(rsTemp.getString("categoryName"))`; `:tallySyncDt` ← `DmServer.getServerTimestamp()`
- Errors: "Exception Occured."
- Other calls: `DocumentBuilderFactory.newInstance`, `FAILED.getCode`, `PROCESSING.getCode`, `TallyExport_Server.sendToTally`, `TallyExport_Server.tallySyncLogInsert`, `TallyExport_Server.tallySyncLogUpdate`, `TransformerFactory.newInstance`, `XPathFactory.newInstance`

## Server `VoucherType.java` (215 lines)
Helpers used: `DmServer.getServerTimestamp`, `DmShared.iNull`, `DmShared.xNull`

### `startExport`
`public void startExport(Connection cnTemp, DefaultValue_Mdl tmpMdlParameter) throws Exception`

- SQL 1: `select 'Receipt' as voucher_Type, 9998 as v_Type,'Receipt' as parentVoucher,1 as sno union all select 'Payment' as voucher_Type, 9999 as v_Type,'Payment' as parentVoucher,1 as sno union all select distinct vt.description as voucher_Type, vt.v_Type , case when vt.short_Name in('CPV','PMT') then 'Payment' when vt.short_Name in('CRV','RCT') then 'Receipt' else 'Journal' end as parentVoucher,2 as sno from ledger l left join voucher_Type vt on l.v_Type=vt.v_Type where vt.tallySyncDt is null order by sno`
- SQL 2: `select distinct vt.description as voucher_Type, vt.v_Type , case when vt.short_Name in('CPV','PMT') then 'Payment' when vt.short_Name in('CRV','RCT') then 'Receipt' when vt.short_Name in('CNTPB','CNPBU','PHPBL','PHPBU','PHPBD','PHPDU','PBIL','PBILU','PBILD','PBLDU','WB','WBU','PHPBR','PHPRU','PRTN','PRTNU') then 'purchase' when vt.short_Name in('DCRBL','EMRBL','IPDBL','PHMSB','OBOPD','POPBL','DCRBL','CNTSB','PHBIL','PHWBL','PHBRT') then 'sales' else 'Journal' end as parentVoucher from voucher_Type vt where vt.tallySyncDt is null`
- SQL 3: `Update voucher_Type set tallyKeyName = :tallyKeyName, tallySyncDt = :tallySyncDt where v_Type = :v_Type ;`
- Params: `:v_Type` ← `DmShared.xNull(rsTemp.getString("v_Type"))`; `:tallyKeyName` ← `DmShared.xNull(rsTemp.getString("voucher_Type"))`; `:tallySyncDt` ← `DmServer.getServerTimestamp()`
- Errors: "Exception Occured."
- Other calls: `DocumentBuilderFactory.newInstance`, `FAILED.getCode`, `PROCESSING.getCode`, `TallyExport_Server.sendToTally`, `TallyExport_Server.tallySyncLogInsert`, `TallyExport_Server.tallySyncLogUpdate`, `TransformerFactory.newInstance`, `XPathFactory.newInstance`

## Server `AcMaster.java` (344 lines)
Helpers used: `DmServer.getParamList`, `DmServer.getServerTimestamp`, `DmShared.iNull`, `DmShared.xNull`

### `startExport`
`public void startExport(Connection cnTemp, DefaultValue_Mdl tmpMdlParameter) throws Exception`

- SQL 1: `update sg set tallyAcOpn =op.opAmt,tallySyncDt=null from subGroup sg left join ( select l.subCode,sum(isNull(l.amtCr,0)-isNull(l.amtDr,0)) as opAmt from ledger l where l.v_Date < :yearStartDate and l.site_Code IN( {…} :siteCodeLst {…} ) and l.isPosted=1 and l.subCode in( select l.subCode from ledgerM lm left join ledger l on lm.docID=l.docid where l.v_Date < :yearStartDate and l.site_Code IN( {…} :siteCodeLst {…} ) and l.isPosted=1 and lm.tallySyncDt is null union all select l.subCode from pBill lm left join ledger l on lm.docID=l.docid where l.v_Date < :yearStartDate and l.site_Code IN( {…} :siteCodeLst {…} ) and l.isPosted=1 and lm.tallySyncDt is null union all select l.subCode from saleBill lm left join ledger l on lm.docID=l.docid where l.v_Date < :yearStartDate and l.site_Code IN( {…} :siteCodeLst {…} ) and l.isPosted=1 and lm.tallySyncDt is null union all select sg.subCode from subGroup sg where sg.tallySyncDt is null ) group by subCode ) op on sg.subCode=op.subCode where op.subcode is not null`
- SQL 2: `Select sg.name+'('+cast(sg.subCode as varchar(20))+')' as accountName, sg.subCode, sg.manualCode, sg.pan, isnull(CONVERT(CHAR(8), sg.gstinDate, 112),'') as gstinDate, isNull(sg.gsTin,'') as gsTin,case when sg.partyType='R' then 'Regular' when sg.partyType='C' then 'Composition' else 'Unregistered/Consumer' end as RegistrationType,isNull(sg.add1,'') as Address1, isNull(sg.add2,'') as Address2,isNull(c.cityName,'') as cityName,isNull(stm.name,'') as partyState, isNull(sg.pin,'') as pinCode,isNull(sg.mobile,'') as mobile,isNull(CONVERT(CHAR(8), sg.preparedDt, 112),'') as PreparedDate, ag.groupName as accountGroup, cast(isNull(sg.tallyAcOpn ,0) as numeric(19,2)) as tallyAcOpn , case when isNull(itd.subCode,'')<>'' then 'GST' else 'Others' end as taxType , case when itd.manualCode='CGST' then 'CGST' when itd.manualCode='SGST' then 'SGST/UTGST' when itd.manualCode='IGST' then 'IGST' end as gstdutyhead from subGroup sg left join acGroup ag on sg.groupCode=ag.groupCode left join city c on sg.cityCode=c.cityCode left join stateMast stm on c.stateCode=stm.code left join ( select itd.subCode,max(sg.name) as name,max(ohm.manualCode) as manualCode from itsDetail itd left join overHeadMast ohm on itd.overHeadCode=ohm.code left join subGroup sg on itd.subCode=sg.subCode where ohm.manualCode in('SGST','UGST','CGST','IGST') group by itd.subCode ) as itd on sg.subCode=itd.subcode where sg.tallySyncDt is null`
- SQL 3: `Update subGroup set tallyKeyName = :tallyKeyName, tallySyncDt = :tallySyncDt where subCode = :subCode ;`
- Params: `:yearStartDate` ← `DmShared.xNull(tmpMdlParameter.getStartDate())`; `:subCode` ← `DmShared.xNull(rsTemp.getString("subCode"))`; `:tallyKeyName` ← `DmShared.xNull(rsTemp.getString("accountName"))`; `:tallySyncDt` ← `DmServer.getServerTimestamp()`
- Errors: "Exception Occured."
- Other calls: `DocumentBuilderFactory.newInstance`, `FAILED.getCode`, `PROCESSING.getCode`, `TRANSFER_ONLY_OPENING.getCode`, `TRANSFER_WITHOUT_OPENING.getCode`, `TRANSFER_WITH_OPENING.getCode`, `TallyExport_Server.sendToTally`, `TallyExport_Server.tallySyncLogInsert`, `TallyExport_Server.tallySyncLogUpdate`, `TransformerFactory.newInstance`, `XPathFactory.newInstance`

### `getAddressList`
`private String getAddressList(int index, String add1, String add2, String city)`

- Errors: "Invalid Index."

## Server `CashPayment.java` (299 lines)
Helpers used: `DmServer.getParamList`, `DmServer.getServerTimestamp`, `DmShared.iNull`, `DmShared.xNull`

### `startExport`
`public void startExport(Connection cnTemp, DefaultValue_Mdl tmpMdlParameter) throws Exception`

- SQL 1: `Select case when l.v_Sno=1 then sg.name+'('+cast(sg.subCode as varchar(20))+')' end as firstDebitParty,l.RecID,l.docId,isNull(CONVERT(CHAR(8), l.v_Date, 112),'') as voucherDate,lm.remark as commonNarration ,sg.name+'('+cast(sg.subCode as varchar(20))+')' as partyName ,case when l.amtDr>0 then 0-l.amtDr else l.amtCr end as amount,l.remark from ledgerM lm left join ledger l on l.docID=lm.docID left join voucher_Type vt on lm.v_Type=vt.v_Type left join subGroup sg on l.subCode=sg.subCode where vt.short_Name= 'CPV' and l.site_code in ( {…} :siteCodeLst {…} ) and l.isPosted=1 and l.v_Prefix= :vPrefix and l.v_Date<= :asOnDate and lm.tallySyncDt is null order by l.v_Date,l.docid,l.v_Sno`
- Params: `:asOnDate` ← `DmShared.xNull(tmpMdlParameter.getAsOnDate())`; `:vPrefix` ← `DmShared.xNull(tmpMdlParameter.getV_Prefix())`
- Other calls: `DocumentBuilderFactory.newInstance`, `FAILED.getCode`, `PROCESSING.getCode`, `TallyExport_Server.tallySyncLogInsert`, `XPathFactory.newInstance`

### `generateXml`
`private void generateXml(Document xmlDocument, String fileDbName, String mongoId, String docId, String recId, Connection cnTemp, String gateWayIP, String gateWayPort, String u_Name, TallyProcess_Mdl tallyprocessMdl) throws Exception`

- SQL 1: `Update ledgerM set tallyKeyName = :tallyKeyName, tallySyncDt = :tallySyncDt where docId = :docId ;`
- Params: `:docId` ← `DmShared.xNull(docId)`; `:tallyKeyName` ← `DmShared.xNull(recId)`; `:tallySyncDt` ← `DmServer.getServerTimestamp()`
- Errors: "Exception Occured."
- Other calls: `TallyExport_Server.sendToTally`, `TallyExport_Server.tallySyncLogUpdate`, `TransformerFactory.newInstance`

## Server `PurchaseBillReturnItemized.java` (577 lines)
Helpers used: `DmServer.getParamList`, `DmServer.getServerTimestamp`, `DmShared.iNull`, `DmShared.xNull`

### `startExport`
`public void startExport(Connection cnTemp, DefaultValue_Mdl tmpMdlParameter) throws Exception`

- SQL 1: `select pb.docid, pb.recId, sg.add1 as partyAddress, isNull(CONVERT(CHAR(8), pb.v_date, 112),'') as voucherDt, isNull(CONVERT(CHAR(8), pb.partyBillDate, 112),'') as reffDate ,case when sg.partyType='R' then 'Regular' when sg.partyType='U' then 'Un Registered' when sg.partyType='C' then 'Composition' end as partyGstType ,sm.name as partyState,sg.gsTin as partyGSTin, pos.name as placeOfSupply,sg.name+'('+cast(sg.subCode as varchar(20))+')' as partyName,pb.recId, pb.partyBillNo as reffNo,vt.description as voucherType,pbd.rate as pRate, um.unit,pbd.oh_Amt_Net as itemValue,pbd.oh_at_CGST as cgstRate, pbd.oh_at_SGST as sgstRate, isNull(pbd.oh_at_SGST,0)+isNull(pbd.oh_at_CGST,0) as igstRate ,pbd.oh_Amt_Gross as itemGrossAmount,pbd.acceptQty as qty,isNull(pbd.batchNo,'') as batchNo ,sgd.name as departmentName,sg.name+'('+cast(sg.subCode as varchar(20))+')' as purchaseAC, im.name as ItemName,hsn.hsn as hsnCode,hsn.name as hsnDescription ,itmcnt.maxSno,pbd.v_sno from pBill pb left join voucher_Type vt on pb.v_Type=vt.v_Type left join pBilldetail pbd on pb.docID=pbd.docID left join subGroup sg on pb.subCode=sg.subCode left join subGroup sgd on sgd.subCode=pb.departmentCode left join itemMAst im on pbd.itemCode=im.code left join hsnMast hsn on im.hsnCode=hsn.code left join unitmast um on im.sku=um.code left join city c on sg.cityCode=c.cityCode left join stateMast sm on c.stateCode=sm.code left join stateMast pos on pos.numericCode=pb.placeOfSupply left join ( select pb.docid,max(pbd.v_Sno) as maxSno from pBill pb left join pBillDetail pbd on pb.docid=pbd.docId left join voucher_Type vt on pb.v_Type=vt.v_Type where vt.category in (' {…} ', ' {…} ', ' {…} ', ' {…} ') and pb.site_code in ( {…} :siteCodeLst {…} ) and isNull(pb.approvedBy,'')<>'' and pb.v_Prefix= :vPrefix and pb.v_Date<= :asOnDate and pb.tallySyncDt is null group by pb.docid)itmcnt on pb.docid=itmcnt.docid where vt.category in (' {…} ', ' {…} ', ' {…} ', ' {…} ') and pb.site_code in ( {…} :siteCodeLst {…} ) and isNull(pb.approvedBy,'')<>'' and pb.v_Prefix= :vPrefix and pb.v_Date<= :asOnDate and pb.tallySyncDt is null order by pb.v_Date,pb.docid,pb.v_no`
- SQL 2: `select sg.name+'('+cast(sg.subCode as varchar(20))+')' as ledgerPartyName,case when l.amtCr>0 then l.amtCr when l.amtDr>0 then 0-l.amtDr end as ledgerAmount ,l.* from ledger l left join subGroup sg on l.subCode=sg.subCode where l.docid = :docId and l.subcode not in(select isNull(subCode,'') from overHeadLine where docid=:docId and overHeadMCode='TV' group by subCode)`
- Params: `:asOnDate` ← `DmShared.xNull(tmpMdlParameter.getAsOnDate())`; `:vPrefix` ← `DmShared.xNull(tmpMdlParameter.getV_Prefix())`; `:docId` ← `DmShared.iNull(prevDocid).longValue()`
- Other calls: `DocumentBuilderFactory.newInstance`, `FAILED.getCode`, `PROCESSING.getCode`, `TallyExport_Server.tallySyncLogInsert`, `XPathFactory.newInstance`

### `generateXml`
`private void generateXml(Document xmlDocument, String fileDbName, String mongoId, String docId, String recId, Connection cnTemp, String gateWayIP, String gateWayPort, String u_Name, TallyProcess_Mdl tallyprocessMdl) throws Exception`

- SQL 1: `Update pBill set tallyKeyName = :tallyKeyName, tallySyncDt = :tallySyncDt where docId = :docId ;`
- Params: `:docId` ← `DmShared.xNull(docId)`; `:tallyKeyName` ← `DmShared.xNull(recId)`; `:tallySyncDt` ← `DmServer.getServerTimestamp()`
- Errors: "Exception Occured."
- Other calls: `TallyExport_Server.sendToTally`, `TallyExport_Server.tallySyncLogUpdate`, `TransformerFactory.newInstance`

## Server `CashReceipt.java` (310 lines)
Helpers used: `DmServer.getParamList`, `DmServer.getServerTimestamp`, `DmShared.iNull`, `DmShared.xNull`

### `startExport`
`public void startExport(Connection cnTemp, DefaultValue_Mdl tmpMdlParameter) throws Exception`

- SQL 1: `select l.recId,case when l.v_sno=1 then sg.name+'('+cast(sg.subCode as varchar(20))+')' end as firstCreditParty,l.docid,isNull(CONVERT(CHAR(8), l.v_Date, 112),'') as voucherDate,l.narration as commonNarration ,sg.name+'('+cast(sg.subCode as varchar(20))+')' as partyName ,case when l.amtDr>0 then 0-l.amtDr else l.amtCr end as amount from ledgerM lm left join ledger l on l.docID=lm.docID left join subGroup sg on l.subCode=sg.subCode left join voucher_Type vt on lm.v_Type=vt.v_Type where vt.short_Name= 'CRV' and l.site_code in ( {…} :siteCodeLst {…} ) and l.isPosted=1 and l.v_Prefix= :vPrefix and l.v_Date<= :asOnDate and lm.tallySyncDt is null order by l.v_Date,l.docid,l.v_Sno`
- Params: `:asOnDate` ← `DmShared.xNull(tmpMdlParameter.getAsOnDate())`; `:vPrefix` ← `DmShared.xNull(tmpMdlParameter.getV_Prefix())`
- Other calls: `DocumentBuilderFactory.newInstance`, `FAILED.getCode`, `PROCESSING.getCode`, `TallyExport_Server.tallySyncLogInsert`, `XPathFactory.newInstance`

### `generateXml`
`private void generateXml(Document xmlDocument, String fileDbName, String mongoId, String docId, String recId, Connection cnTemp, String gateWayIP, String gateWayPort, String u_Name, TallyProcess_Mdl tallyprocessMdl) throws Exception`

- SQL 1: `Update ledgerM set tallyKeyName = :tallyKeyName, tallySyncDt = :tallySyncDt where docId = :docId ;`
- Params: `:docId` ← `DmShared.xNull(docId)`; `:tallyKeyName` ← `DmShared.xNull(recId)`; `:tallySyncDt` ← `DmServer.getServerTimestamp()`
- Errors: "Exception Occured."
- Other calls: `TallyExport_Server.sendToTally`, `TallyExport_Server.tallySyncLogUpdate`, `TransformerFactory.newInstance`

## Server `ServiceGroup.java` (179 lines)
Helpers used: `DmServer.getServerTimestamp`, `DmShared.iNull`, `DmShared.xNull`

### `startExport`
`public void startExport(Connection cnTemp, DefaultValue_Mdl tmpMdlParameter) throws Exception`

- SQL 1: `select sg.code,sg.name as groupName,'20240401' as startDate from serviceGroup sg where sg.tallySyncdt is null`
- SQL 2: `Update serviceGroup set tallyKeyName = :tallyKeyName, tallySyncDt = :tallySyncDt where code = :code ;`
- Params: `:code` ← `DmShared.xNull(rsTemp.getString("code"))`; `:tallyKeyName` ← `DmShared.xNull(rsTemp.getString("groupName"))`; `:tallySyncDt` ← `DmServer.getServerTimestamp()`
- Errors: "Exception Occured."
- Other calls: `DocumentBuilderFactory.newInstance`, `FAILED.getCode`, `PROCESSING.getCode`, `TallyExport_Server.sendToTally`, `TallyExport_Server.tallySyncLogInsert`, `TallyExport_Server.tallySyncLogUpdate`, `TransformerFactory.newInstance`, `XPathFactory.newInstance`

## Server `Journal.java` (309 lines)
Helpers used: `DmServer.getParamList`, `DmServer.getServerTimestamp`, `DmShared.iNull`, `DmShared.xNull`

### `startExport`
`public void startExport(Connection cnTemp, DefaultValue_Mdl tmpMdlParameter) throws Exception`

- SQL 1: `select l.docid,l.recId,case when l.v_sno=1 then sg.name+'('+cast(sg.subCode as varchar(20))+')' end as firstCreditParty,l.docid,isNull(CONVERT(CHAR(8), l.v_Date, 112),'') as voucherDate,l.narration as commonNarration ,sg.name+'('+cast(sg.subCode as varchar(20))+')' as partyName ,case when l.amtDr>0 then 0-l.amtDr else l.amtCr end as amount from ledger l left join ledgerM lm on l.docID=lm.docID left join subGroup sg on l.subCode=sg.subCode left join voucher_Type vt on l.v_Type=vt.v_Type where vt.category in('JV','CNGST','DNGST','CIGST','DIGST','CNTRA') and l.site_code in ( {…} :siteCodeLst {…} ) and l.isPosted=1 and l.v_Prefix= :vPrefix and l.v_Date<= :asOnDate and lm.tallySyncDt is null order by l.v_Date,l.docId,l.v_Sno`
- Params: `:asOnDate` ← `DmShared.xNull(tmpMdlParameter.getAsOnDate())`; `:vPrefix` ← `DmShared.xNull(tmpMdlParameter.getV_Prefix())`
- Other calls: `DocumentBuilderFactory.newInstance`, `FAILED.getCode`, `PROCESSING.getCode`, `TallyExport_Server.tallySyncLogInsert`, `XPathFactory.newInstance`

### `generateXml`
`private void generateXml(Document xmlDocument, String fileDbName, String mongoId, String docId, String recId, Connection cnTemp, String gateWayIP, String gateWayPort, String u_Name, TallyProcess_Mdl tallyprocessMdl) throws Exception`

- SQL 1: `Update ledgerM set tallyKeyName = :tallyKeyName, tallySyncDt = :tallySyncDt where docId = :docId ;`
- Params: `:docId` ← `DmShared.xNull(docId)`; `:tallyKeyName` ← `DmShared.xNull(recId)`; `:tallySyncDt` ← `DmServer.getServerTimestamp()`
- Errors: "Exception Occured."
- Other calls: `TallyExport_Server.sendToTally`, `TallyExport_Server.tallySyncLogUpdate`, `TransformerFactory.newInstance`

## Server `GodownMaster.java` (162 lines)
Helpers used: `DmServer.getServerTimestamp`, `DmShared.iNull`, `DmShared.xNull`

### `startExport`
`public void startExport(Connection cnTemp, DefaultValue_Mdl tmpMdlParameter) throws Exception`

- SQL 1: `Select dm.subCode as godownCode,sg.name as godownName from departmentMast dm left join subGroup sg on dm.subCode=sg.subCode where dm.tallySyncDt is null`
- SQL 2: `Update departmentMast set tallyKeyName = :tallyKeyName, tallySyncDt = :tallySyncDt where subCode = :godownCode ;`
- Params: `:godownCode` ← `DmShared.xNull(rsTemp.getString("godownCode"))`; `:tallyKeyName` ← `DmShared.xNull(rsTemp.getString("godownName"))`; `:tallySyncDt` ← `DmServer.getServerTimestamp()`
- Errors: "Exception Occured."
- Other calls: `DocumentBuilderFactory.newInstance`, `FAILED.getCode`, `PROCESSING.getCode`, `TallyExport_Server.sendToTally`, `TallyExport_Server.tallySyncLogInsert`, `TallyExport_Server.tallySyncLogUpdate`, `TransformerFactory.newInstance`, `XPathFactory.newInstance`

## Server `SaleBillService.java` (599 lines)
Helpers used: `DmServer.getParamList`, `DmServer.getServerTimestamp`, `DmShared.iNull`, `DmShared.xNull`

### `startExport`
`public void startExport(Connection cnTemp, DefaultValue_Mdl tmpMdlParameter) throws Exception`

- SQL 1: `select sb.docid,isNull(sg.add1,'') as partyAddress, isNull(CONVERT(CHAR(8), sb.v_date, 112),'') as voucherDt ,case when sg.partyType='R' then 'Regular' when sg.partyType='U' then 'Un Registered' when sg.partyType='C' then 'Composition' end as partyGstType ,isNull(sm.name,'') as partyState,isNull(sg.gsTin,'') as partyGSTin, sg.name+'('+cast(sg.subCode as varchar(20))+')' as partyName,sb.recId, vt.description as voucherType,sbd.rate as sRate, 'No' as unit,sbd.oh_Amt_Gross as itemValue, sbd.oh_at_SGST as sgstRate, isNull(sbd.oh_at_SGST,0)+isNull(sbd.oh_at_CGST,0) as igstRate ,round(isNull(sbd.oh_Amt_Gross,0)-isNull(sbd.oh_Amt_Discount,0)-isNull(sbd.oh_Amt_Discount2,0) -(isNull(sbd.oh_Amt_CGST,0)+isNull(sbd.oh_Amt_SGST,0)+isNull(sbd.oh_Amt_IGST,0)),2) as itemTaxableAmount,sbd.quantity as qty ,'' as batchNo ,sbd.oh_at_CGST as cgstRate, sgd.name as departmentName,sgs.name +'('+cast(sgs.subCode as varchar(20))+')' as salesAc,sbd.isFooter , im.name as serviceName,hsn.hsn,itmcnt.maxSno,sbd.regDocID from saleBill sb left join voucher_Type vt on sb.v_Type=vt.v_Type left join patientServiceDetail sbd on sb.docID=sbd.sbDocID left join subGroup sg on sb.subCode=sg.subCode left join subGroup sgd on sgd.subCode=sb.departmentCode left join patientServiceMast im on sbd.serviceCode=im.code left join patientServiceCatMast pscm on im.categoryCode=pscm.code left join subGroup sgs on sgs.subCode=pscm.serviceAc left join hsnMast hsn on im.hsnCode=hsn.code left join city c on sg.cityCode=c.cityCode left join stateMast sm on c.stateCode=sm.code left join stateMast pos on pos.numericCode=sb.placeOfSupply left join ( select sb.docid,count(sbd.docid) as maxSno from saleBill sb left join patientServiceDetail sbd on sb.docID=sbd.sbDocID left join voucher_Type vt on sb.v_Type=vt.v_Type where vt.category in(' {…} ',' {…} ',' {…} ',' {…} ',' {…} ',' {…} ',' {…} ',' {…} ') and sbd.isFooter = 0 and sb.site_code in ( {…} :siteCodeLst {…} ) and sb.v_Prefix= :vPrefix and sb.v_Date<= :asOnDate and sb.tallySyncDt is null and sb.approvedBy is not null group by sb.docid )itmcnt on sb.docid=itmcnt.docid where vt.category in(' {…} ',' {…} ',' {…} ',' {…} ',' {…} ',' {…} ',' {…} ',' {…} ') and sbd.isFooter=0 and sb.site_code in ( {…} :siteCodeLst {…} ) and sb.v_Prefix= :vPrefix and sb.v_Date<= :asOnDate and sb.tallySyncDt is null and sb.approvedBy is not nullorder by sb.v_Date,sb.docid,sb.v_no`
- SQL 2: `select ld.docid,ld.ledgerAmount,sg.name +'('+cast(sg.subCode as varchar(20))+')' as ledgerPartyName from ( select sbd.docid,0-sum(oh_Amt_Net) as ledgerAmount,(select organizationCode from patientRegistration where regDocID=:regDocID) as account from salebill sbd where docid = :docId group by sbd.docid union all select sbd.docid,sum(oh_amt_sgst) as ledgerAmount,sbd.oh_Ac_SGST as account from salebillDetail sbd where docid = :docId group by sbd.docid,sbd.oh_Ac_SGST union all select sbd.docid,sum(oh_amt_cgst) as ledgerAmount,sbd.oh_Ac_CGST as account from salebillDetail sbd where docid = :docId group by sbd.docid,sbd.oh_Ac_CGST union all select sbd.docid,sum(oh_Amt_RoundOff) as ledgerAmount,(select serviceAc from patientServiceCatMast where shortName='RO') as account from salebill sbd where docid = :docId group by sbd.docid ) as ld left join subGroup sg on ld.account=sg.subCode where ld.ledgerAmount<>0`
- Params: `:asOnDate` ← `DmShared.xNull(tmpMdlParameter.getAsOnDate())`; `:vPrefix` ← `DmShared.xNull(tmpMdlParameter.getV_Prefix())`; `:docId` ← `DmShared.iNull(prevDocid).longValue()`; `:regDocID` ← `DmShared.iNull(rsTemp.getString("regDocID")).longValue()`
- Other calls: `DocumentBuilderFactory.newInstance`, `FAILED.getCode`, `PROCESSING.getCode`, `TallyExport_Server.tallySyncLogInsert`, `XPathFactory.newInstance`

### `generateXml`
`private void generateXml(Document xmlDocument, String fileDbName, String mongoId, String docId, String recId, Connection cnTemp, String gateWayIP, String gateWayPort, String u_Name, TallyProcess_Mdl tallyprocessMdl) throws Exception`

- SQL 1: `Update saleBill set tallyKeyName = :tallyKeyName, tallySyncDt = :tallySyncDt where docId = :docId ;`
- Params: `:docId` ← `DmShared.xNull(docId)`; `:tallyKeyName` ← `DmShared.xNull(recId)`; `:tallySyncDt` ← `DmServer.getServerTimestamp()`
- Errors: "Exception Occured."
- Other calls: `TallyExport_Server.sendToTally`, `TallyExport_Server.tallySyncLogUpdate`, `TransformerFactory.newInstance`

## Server `AcGroup.java` (180 lines)
Helpers used: `DmServer.getServerTimestamp`, `DmShared.iNull`, `DmShared.xNull`

### `startExport`
`public void startExport(Connection cnTemp, DefaultValue_Mdl tmpMdlParameter) throws Exception`

- SQL 1: `Select ag.groupCode,isnull(ag.tallyKeyName ,ag.groupName) as groupName,agu.groupUnder as underGroupCode,agu.groupName as underGroupName,ag.groupName as manualCode from acGroup ag left join acGroup agu on ag.groupUnder = agu.groupCode where ag.tallySyncDt is null order By ag.groupCode`
- SQL 2: `Update acGroup set tallyKeyName = :tallyKeyName, tallySyncDt = :tallySyncDt where groupCode = :groupCode ;`
- Params: `:groupCode` ← `DmShared.xNull(rsTemp.getString("groupCode"))`; `:tallyKeyName` ← `DmShared.xNull(rsTemp.getString("groupName"))`; `:tallySyncDt` ← `DmServer.getServerTimestamp()`
- Errors: "Exception Occured."
- Other calls: `DocumentBuilderFactory.newInstance`, `FAILED.getCode`, `PROCESSING.getCode`, `TallyExport_Server.sendToTally`, `TallyExport_Server.tallySyncLogInsert`, `TallyExport_Server.tallySyncLogUpdate`, `TransformerFactory.newInstance`, `XPathFactory.newInstance`

## Server `EodPosting.java` (312 lines)
Helpers used: `DmServer.getParamList`, `DmServer.getServerTimestamp`, `DmShared.iNull`, `DmShared.xNull`

### `startExport`
`public void startExport(Connection cnTemp, DefaultValue_Mdl tmpMdlParameter) throws Exception`

- SQL 1: `select l.recId, isNull(CONVERT(CHAR(8), l.v_Date, 112),'') as voucherDate, case when l.v_sno=1 then sg.name+'('+cast(sg.subCode as varchar(20))+')' end as firstCreditParty,l.docid,isNull(CONVERT(CHAR(8), l.v_Date, 112),'') as voucherDate,l.narration as commonNarration ,sg.name+'('+cast(sg.subCode as varchar(20))+')' as partyName ,cast(case when l.amtDr>0 then 0-l.amtDr else l.amtCr end as numeric(19,2)) as amount from saleBill lm left join ledger l on l.docID=lm.docID left join subGroup sg on l.subCode = sg.subCode left join voucher_Type vt on l.v_Type=vt.v_Type where vt.category in(' {…} ',' {…} ',' {…} ',' {…} ',' {…} ',' {…} ',' {…} ',' {…} ',' {…} ',' {…} ',' {…} ') and l.site_code in ( {…} :siteCodeLst {…} ) and l.v_Prefix= :vPrefix and l.v_Date<= :asOnDate and lm.tallySyncDt is null order by l.v_Date,l.docId,l.v_Sno ;`
- Params: `:asOnDate` ← `DmShared.xNull(tmpMdlParameter.getAsOnDate())`; `:vPrefix` ← `DmShared.xNull(tmpMdlParameter.getV_Prefix())`
- Other calls: `DocumentBuilderFactory.newInstance`, `FAILED.getCode`, `PROCESSING.getCode`, `TallyExport_Server.tallySyncLogInsert`, `XPathFactory.newInstance`

### `generateXml`
`private void generateXml(Document xmlDocument, String fileDbName, String mongoId, String docId, String recId, Connection cnTemp, String gateWayIP, String gateWayPort, String u_Name, TallyProcess_Mdl tallyprocessMdl) throws Exception`

- SQL 1: `Update saleBill set tallyKeyName = :tallyKeyName, tallySyncDt = :tallySyncDt where docId = :docId ;`
- Params: `:docId` ← `DmShared.xNull(docId)`; `:tallyKeyName` ← `DmShared.xNull(recId)`; `:tallySyncDt` ← `DmServer.getServerTimestamp()`
- Errors: "Exception Occured."
- Other calls: `TallyExport_Server.sendToTally`, `TallyExport_Server.tallySyncLogUpdate`, `TransformerFactory.newInstance`

## Server `ServiceCategory.java` (171 lines)
Helpers used: `DmServer.getServerTimestamp`, `DmShared.iNull`, `DmShared.xNull`

### `startExport`
`public void startExport(Connection cnTemp, DefaultValue_Mdl tmpMdlParameter) throws Exception`

- SQL 1: `select scm.code,scm.name as categoryName from patientServiceCatMast scm where scm.tallySyncdt is null`
- SQL 2: `Update patientServiceCatMast set tallyKeyName = :tallyKeyName, tallySyncDt = :tallySyncDt where code = :code ;`
- Params: `:code` ← `DmShared.xNull(rsTemp.getString("code"))`; `:tallyKeyName` ← `DmShared.xNull(rsTemp.getString("categoryName"))`; `:tallySyncDt` ← `DmServer.getServerTimestamp()`
- Errors: "Exception Occured."
- Other calls: `DocumentBuilderFactory.newInstance`, `FAILED.getCode`, `PROCESSING.getCode`, `TallyExport_Server.sendToTally`, `TallyExport_Server.tallySyncLogInsert`, `TallyExport_Server.tallySyncLogUpdate`, `TransformerFactory.newInstance`, `XPathFactory.newInstance`

## Server `ServiceMaster.java` (248 lines)
Helpers used: `DmServer.getServerTimestamp`, `DmShared.iNull`, `DmShared.xNull`

### `startExport`
`public void startExport(Connection cnTemp, DefaultValue_Mdl tmpMdlParameter) throws Exception`

- SQL 1: `select pcm.code,pcm.name as serviceName,ic.name as serviceCategory,sg.name as serviceGroup,hm.hsn as hsnCode,hm.name as hsnDescription ,isNull(isd.atRate,0)/2 as CGSTRate,isNull(isd.atRate,0)/2 as SGSTRate,isNull(isd.atRate,0) as IGSTRate,'No' as unitName from patientServiceMast pcm left join patientServiceCatMast ic on pcm.categoryCode=ic.code left join serviceGroup sg on pcm.groupCode=sg.code left join hsnMast hm on pcm.hsnCode=hm.code left join hsnITSDetail hisd on hm.code=hisd.code left join ( select code,max(atRate) as atRate from itsDetail isd group by code ) isd on hisd.its_Code_Purchase=isd.code where pcm.tallySyncdt is null`
- SQL 2: `Update patientServiceMast set tallyKeyName = :tallyKeyName, tallySyncDt = :tallySyncDt where code = :code ;`
- Params: `:code` ← `DmShared.xNull(rsTemp.getString("code"))`; `:tallyKeyName` ← `DmShared.xNull(rsTemp.getString("serviceName"))`; `:tallySyncDt` ← `DmServer.getServerTimestamp()`
- Errors: "Exception Occured."
- Other calls: `DocumentBuilderFactory.newInstance`, `FAILED.getCode`, `PROCESSING.getCode`, `TallyExport_Server.sendToTally`, `TallyExport_Server.tallySyncLogInsert`, `TallyExport_Server.tallySyncLogUpdate`, `TransformerFactory.newInstance`, `XPathFactory.newInstance`

## Server `ItemGroup.java` (178 lines)
Helpers used: `DmServer.getServerTimestamp`, `DmShared.iNull`, `DmShared.xNull`

### `startExport`
`public void startExport(Connection cnTemp, DefaultValue_Mdl tmpMdlParameter) throws Exception`

- SQL 1: `select ig.code,ig.name as groupName,'20240401' as startDate from itemGroup ig where ig.tallySyncdt is null`
- SQL 2: `Update itemGroup set tallyKeyName = :tallyKeyName, tallySyncDt = :tallySyncDt where code = :code ;`
- Params: `:code` ← `DmShared.xNull(rsTemp.getString("code"))`; `:tallyKeyName` ← `DmShared.xNull(rsTemp.getString("groupName"))`; `:tallySyncDt` ← `DmServer.getServerTimestamp()`
- Errors: "Exception Occured."
- Other calls: `DocumentBuilderFactory.newInstance`, `FAILED.getCode`, `PROCESSING.getCode`, `TallyExport_Server.sendToTally`, `TallyExport_Server.tallySyncLogInsert`, `TallyExport_Server.tallySyncLogUpdate`, `TransformerFactory.newInstance`, `XPathFactory.newInstance`

## Server `OrganizationReceipt.java` (312 lines)
Helpers used: `DmServer.getParamList`, `DmServer.getServerTimestamp`, `DmShared.iNull`, `DmShared.xNull`

### `startExport`
`public void startExport(Connection cnTemp, DefaultValue_Mdl tmpMdlParameter) throws Exception`

- SQL 1: `select l.recId,case when l.v_sno=1 then sg.name+'('+cast(sg.subCode as varchar(20))+')' end as firstCreditParty,l.docid,isNull(CONVERT(CHAR(8), l.v_Date, 112),'') as voucherDate,l.narration as commonNarration ,sg.name+'('+cast(sg.subCode as varchar(20))+')' as partyName ,cast(case when l.amtDr>0 then 0-l.amtDr else l.amtCr end as numeric(19,2)) as amount from ledgerM lm left join ledger l on l.docID=lm.docID left join subGroup sg on l.subCode=sg.subCode left join voucher_Type vt on l.v_Type=vt.v_Type where vt.category in(' {…} ') and l.site_code in ( {…} :siteCodeLst {…} ) and l.v_Prefix= :vPrefix and l.v_Date<= :asOnDate and lm.tallySyncDt is null order by l.v_Date,l.docId,l.v_Sno ;`
- Params: `:asOnDate` ← `DmShared.xNull(tmpMdlParameter.getAsOnDate())`; `:vPrefix` ← `DmShared.xNull(tmpMdlParameter.getV_Prefix())`
- Other calls: `DocumentBuilderFactory.newInstance`, `FAILED.getCode`, `PROCESSING.getCode`, `TallyExport_Server.tallySyncLogInsert`, `XPathFactory.newInstance`

### `generateXml`
`private void generateXml(Document xmlDocument, String fileDbName, String mongoId, String docId, String recId, Connection cnTemp, String gateWayIP, String gateWayPort, String u_Name, TallyProcess_Mdl tallyprocessMdl) throws Exception`

- SQL 1: `Update ledgerM set tallyKeyName = :tallyKeyName, tallySyncDt = :tallySyncDt where docId = :docId ;`
- Params: `:docId` ← `DmShared.xNull(docId)`; `:tallyKeyName` ← `DmShared.xNull(recId)`; `:tallySyncDt` ← `DmServer.getServerTimestamp()`
- Errors: "Exception Occured."
- Other calls: `TallyExport_Server.sendToTally`, `TallyExport_Server.tallySyncLogUpdate`, `TransformerFactory.newInstance`

## Server `HospitalReceiptRefundPosting.java` (313 lines)
Helpers used: `DmServer.getParamList`, `DmServer.getServerTimestamp`, `DmShared.iNull`, `DmShared.xNull`

### `startExport`
`public void startExport(Connection cnTemp, DefaultValue_Mdl tmpMdlParameter) throws Exception`

- SQL 1: `select lm.docId, l.recId,case when l.v_sno=1 then sg.name+'('+cast(sg.subCode as varchar(20))+')' end as firstCreditParty,l.docid,isNull(CONVERT(CHAR(8), l.v_Date, 112),'') as voucherDate,l.narration as commonNarration ,sg.name+'('+cast(sg.subCode as varchar(20))+')' as partyName ,cast(case when l.amtDr>0 then 0-l.amtDr else l.amtCr end as numeric(19,2)) as amount from patientReceipt lm left join ledger l on l.docID=lm.docID left join subGroup sg on l.subCode=sg.subCode left join voucher_Type vt on l.v_Type=vt.v_Type where vt.category in(' {…} ',' {…} ',' {…} ',' {…} ',' {…} ',' {…} ',' {…} ',' {…} ',' {…} ',' {…} ',' {…} ',' {…} ',' {…} ',' {…} ',' {…} ',' {…} ',' {…} ' ,' {…} ',' {…} ',' {…} ',' {…} ',' {…} ',' {…} ',' {…} ',' {…} ',' {…} ',' {…} ') and l.site_code in ( {…} :siteCodeLst {…} ) and l.v_Prefix= :vPrefix and l.v_Date<= :asOnDate and lm.tallySyncDt is null order by l.v_Date,l.docId,l.v_Sno ;`
- Params: `:asOnDate` ← `DmShared.xNull(tmpMdlParameter.getAsOnDate())`; `:vPrefix` ← `DmShared.xNull(tmpMdlParameter.getV_Prefix())`
- Other calls: `DocumentBuilderFactory.newInstance`, `FAILED.getCode`, `PROCESSING.getCode`, `TallyExport_Server.tallySyncLogInsert`, `XPathFactory.newInstance`

### `generateXml`
`private void generateXml(Document xmlDocument, String fileDbName, String mongoId, String docId, String recId, Connection cnTemp, String gateWayIP, String gateWayPort, String u_Name, TallyProcess_Mdl tallyprocessMdl) throws Exception`

- SQL 1: `Update patientReceipt set tallyKeyName = :tallyKeyName, tallySyncDt = :tallySyncDt where docId = :docId ;`
- Params: `:docId` ← `DmShared.xNull(docId)`; `:tallyKeyName` ← `DmShared.xNull(recId)`; `:tallySyncDt` ← `DmServer.getServerTimestamp()`
- Errors: "Exception Occured."
- Other calls: `TallyExport_Server.sendToTally`, `TallyExport_Server.tallySyncLogUpdate`, `TransformerFactory.newInstance`

## Server `TallyExport_SrvImpl.java` (834 lines)
Helpers used: `DmServer.addActiveProcess`, `DmServer.getConnection`, `DmServer.getSsnDBName`, `DmServer.getSsnFileDBName`, `DmServer.getSsnPLTCode`, `DmServer.getSsnSiteCode`, `DmServer.getSsnStartDate`, `DmServer.getSsnUserName`, `DmServer.getSsnVoucherPrefix`, `DmServer.isActiveProcessRunning`, `DmShared.filterKeyFieldList`, `DmShared.iNull`, `DmShared.xNull`

### `getTallySync`
`public DmResult<String> getTallySync(DefaultValue_Mdl defaultValueMdl)`

- Errors: "One Process Is Running." | "Tally gateWay ip not found." | "Tally gateWay port not found." | "Tally gateWay company name not found." | "Tally gateWay state name not found." | "Tally gateWay GST Type not found." | "As On Date not found."
- Other calls: `Executors.newFixedThreadPool`, `NONE.getCode`

### `getDefaultValues`
`public DmResult<DefaultValue_Mdl> getDefaultValues()`

- SQL 1: `Select tallyIntegrationType, tallyGateWayCompanyName , tallyGateWayIP , tallyGateWayport, stateName as tallyGateWayStateName ,case when registrationType ='R' then 'Regular' else 'Composition' end as tallyGateWayGSTType,gstin from {…} .dbo.siteMast where code = :siteCode`
- SQL 2: `Without Item {…} With Item`
- SQL 3: `Select code,manualCode from {…} .dbo.siteMast where tallyGateWayCompanyName = :tallyGateWayCompanyName`
- Params: `:siteCode` ← `DmShared.iNull(DmServer.getSsnSiteCode(this)).intValue()`; `:tallyGateWayCompanyName` ← `DmShared.xNull(node.getTallyComp())`
- Other calls: `NONE.getCode`, `TRANSFER_WITHOUT_OPENING.getCode`, `TRANSFER_WITHOUT_OPENING.getName`

### `refreshProcessGrid`
`public DmResult<List<TallyProcess_Mdl>> refreshProcessGrid(String isItemized, String onlyOpeningCode)`

- Other calls: `TRANSFER_ONLY_OPENING.getCode`

### `refreshCurrentRunningProcessPopUp`
`public DmResult<String> refreshCurrentRunningProcessPopUp()`


### `getTxtHAccountOpeningType`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHAccountOpeningType(DmHelper helper, boolean blnToEndOfFile)`

- Other calls: `TRANSFER_ONLY_OPENING.getCode`, `TRANSFER_ONLY_OPENING.getName`, `TRANSFER_WITHOUT_OPENING.getCode`, `TRANSFER_WITHOUT_OPENING.getName`, `TRANSFER_WITH_OPENING.getCode`, `TRANSFER_WITH_OPENING.getName`

### `onlyOpeningProcessGrid`
`public DmResult<List<TallyProcess_Mdl>> onlyOpeningProcessGrid(String onlyOpening)`

- Other calls: `NONE.getCode`, `TRANSFER_ONLY_OPENING.getCode`

## Server `BankReceipt.java` (393 lines)
Helpers used: `DmServer.getParamList`, `DmServer.getServerTimestamp`, `DmShared.iNull`, `DmShared.xNull`

### `startExport`
`public void startExport(Connection cnTemp, DefaultValue_Mdl tmpMdlParameter) throws Exception`

- SQL 1: `select l.recId,case when l.v_sno=1 then sg.name+'('+cast(sg.subCode as varchar(20))+')' end as firstCreditParty,l.docid,isNull(CONVERT(CHAR(8), l.v_Date, 112),'') as voucherDate,l.narration as commonNarration, case when l.amtDr>0 then sg.name+'('+cast(sg.subCode as varchar(20))+')' end as debitPartyName ,case when l.amtCr>0 then sg.name+'('+cast(sg.subCode as varchar(20))+')' end as creditPartyName ,case when l.amtDr>0 then 0-l.amtDr end as amtDr,case when l.amtCr>0 then l.amtCr end as amtCr ,case when isNull(l.chq_No,'')<>'' then l.chq_No end as chq_No ,case when l.chq_Date is not null then isNull(CONVERT(CHAR(8), l.chq_Date, 112),'') end as chq_Date ,'' as favourOf ,0-(l.amtDr) as chqAmount ,case when isNull(l.clg_Remark,'')<>'' then l.clg_Remark end as chqRemark ,case when (isNull(l.chq_No,'')<>'' ) then sg.name+'('+cast(sg.subCode as varchar(20))+')' end as linkPartyName,l.docid from ledgerM lm left join ledger l on l.docID=lm.docID left join subGroup sg on l.subCode=sg.subCode left join voucher_Type vt on lm.v_Type=vt.v_Type where vt.short_Name= 'RCT' and l.site_code in ( {…} :siteCodeLst {…} ) and l.isPosted=1 and l.v_Prefix= :vPrefix and l.v_Date<= :asOnDate and lm.tallySyncDt is null order by l.v_Date,l.docid,l.v_Sno`
- SQL 2: `select l.amtCr as chqAmount,l.chq_No as chqNo,isNull(CONVERT(CHAR(8), l.chq_Date, 112),'') as chqDate,l.clg_Remark as clgRemark,l.favourOf,sg.name+'('+cast(sg.subCode as varchar(20))+')' as linkCreditParty ,l.accountNo from ledger l left join ledgerM lm on l.docID=lm.docID left join subGroup sg on l.subCode=sg.subCode where l.docid= :docId and l.amtcr>0 and (isNull(l.chq_No,'')<>'' or l.chq_Date is not null) order by l.v_Sno`
- Params: `:asOnDate` ← `DmShared.xNull(tmpMdlParameter.getAsOnDate())`; `:vPrefix` ← `DmShared.xNull(tmpMdlParameter.getV_Prefix())`; `:docId` ← `DmShared.iNull(prevDocid).longValue()`
- Other calls: `DocumentBuilderFactory.newInstance`, `FAILED.getCode`, `PROCESSING.getCode`, `TallyExport_Server.tallySyncLogInsert`, `XPathFactory.newInstance`

### `generateXml`
`private void generateXml(Document xmlDocument, String fileDbName, String mongoId, String docId, String recId, Connection cnTemp, String gateWayIP, String gateWayPort, String u_Name, TallyProcess_Mdl tallyprocessMdl) throws Exception`

- SQL 1: `Update ledgerM set tallyKeyName = :tallyKeyName, tallySyncDt = :tallySyncDt where docId = :docId ;`
- Params: `:docId` ← `DmShared.xNull(docId)`; `:tallyKeyName` ← `DmShared.xNull(recId)`; `:tallySyncDt` ← `DmServer.getServerTimestamp()`
- Errors: "Exception Occured."
- Other calls: `TallyExport_Server.sendToTally`, `TallyExport_Server.tallySyncLogUpdate`, `TransformerFactory.newInstance`

## Server `BankPayment.java` (411 lines)
Helpers used: `DmServer.getParamList`, `DmServer.getServerTimestamp`, `DmShared.iNull`, `DmShared.xNull`

### `startExport`
`public void startExport(Connection cnTemp, DefaultValue_Mdl tmpMdlParameter) throws Exception`

- SQL 1: `Select l.recId, lm.u_entDt,case when l.v_sno=1 then sg.name+'('+cast(sg.subCode as varchar(20))+')' end as firstCreditParty,l.docid,isNull(CONVERT(CHAR(8), l.v_Date, 112),'') as voucherDate,l.narration as commonNarration, case when l.amtDr>0 then sg.name+'('+cast(sg.subCode as varchar(20))+')' end as debitPartyName ,case when l.amtCr>0 then sg.name+'('+cast(sg.subCode as varchar(20))+')' end as creditPartyName ,case when l.amtDr>0 then 0-l.amtDr end as amtDr,case when l.amtCr>0 then l.amtCr end as amtCr ,case when isNull(l.chq_No,'')<>'' then l.chq_No end as chq_No ,case when l.chq_Date is not null then isNull(CONVERT(CHAR(8), l.chq_Date, 112),'') end as chq_Date ,'' as favourOf ,0-(l.amtDr) as chqAmount ,case when isNull(l.clg_Remark,'')<>'' then l.clg_Remark end as chqRemark ,case when (isNull(l.chq_No,'')<>'' ) then sg.name+'('+cast(sg.subCode as varchar(20))+')' end as linkPartyName,l.docid from ledgerM lm left join ledger l on l.docID=lm.docID left join voucher_Type vt on l.v_Type=vt.v_Type left join subGroup sg on l.subCode=sg.subCode where vt.short_Name = 'PMT' and l.site_code in ( {…} :siteCodeLst {…} ) and l.isPosted=1 and l.v_Prefix= :vPrefix and l.v_Date<= :asOnDate and lm.tallySyncDt is null order by l.v_Date,l.docid,l.v_Sno`
- SQL 2: `Select l.amtDr as chqAmount,l.chq_No as chqNo,isNull(CONVERT(CHAR(8), l.chq_Date, 112),'') as chqDate,l.clg_Remark as clgRemark,l.favourOf,sg.name+'('+cast(sg.subCode as varchar(20))+')' as linkCreditParty ,l.accountNo from ledger l left join ledgerM lm on l.docID=lm.docID left join subGroup sg on l.subCode=sg.subCode where l.docid = :docId and l.amtdr>0 and (isNull(l.chq_No,'')<>'' or l.chq_Date is not null) order by l.v_Sno`
- Params: `:asOnDate` ← `DmShared.xNull(tmpMdlParameter.getAsOnDate())`; `:vPrefix` ← `DmShared.xNull(tmpMdlParameter.getV_Prefix())`; `:docId` ← `DmShared.iNull(prevDocid).longValue()`
- Other calls: `DocumentBuilderFactory.newInstance`, `FAILED.getCode`, `PROCESSING.getCode`, `TallyExport_Server.tallySyncLogInsert`, `XPathFactory.newInstance`

### `generateXml`
`private void generateXml(Document xmlDocument, String fileDbName, String mongoId, String docId, String recId, Connection cnTemp, String gateWayIP, String gateWayPort, String u_Name, TallyProcess_Mdl tallyprocessMdl) throws Exception`

- SQL 1: `Update ledgerM set tallyKeyName = :tallyKeyName, tallySyncDt = :tallySyncDt where docId = :docId ;`
- Params: `:docId` ← `DmShared.xNull(docId)`; `:tallyKeyName` ← `DmShared.xNull(recId)`; `:tallySyncDt` ← `DmServer.getServerTimestamp()`
- Errors: "Exception Occured."
- Other calls: `TallyExport_Server.sendToTally`, `TallyExport_Server.tallySyncLogUpdate`, `TransformerFactory.newInstance`

## Server `TallyExport_Run.java` (290 lines)
Helpers used: `DmServer.getConnection`, `DmServer.getServerTimestamp`, `DmServer.removeActiveProcess`, `DmShared.iNull`

### `run`
`public void run()`

- Other calls: `SUCCESS.getCode`, `TRANSFER_ONLY_OPENING.getCode`, `Thread.currentThread`

### `updateProcessCounter`
`private void updateProcessCounter(String currCount, String processName)`

- Other calls: `TRANSFER_ONLY_OPENING.getCode`

## Server `ItemMaster.java` (251 lines)
Helpers used: `DmServer.getServerTimestamp`, `DmShared.iNull`, `DmShared.xNull`

### `startExport`
`public void startExport(Connection cnTemp, DefaultValue_Mdl tmpMdlParameter) throws Exception`

- SQL 1: `select im.code,im.name as itemName,ic.name as itemCategory,ig.name as itemGroup, hm.hsn as hsnCode,hm.name as hsnDescription,um.unit as unitName ,isNull(isd.atRate,0)/2 as CGSTRate,isNull(isd.atRate,0)/2 as SGSTRate,isNull(isd.atRate,0) as IGSTRate from itemMast im left join itemCat ic on im.itemCategoryCode=ic.code left join itemGroup ig on im.itemGroup=ig.code left join hsnMast hm on im.hsnCode=hm.code left join hsnITSDetail hisd on hm.code=hisd.code left join unitmast um on im.sku=um.code left join ( select code,max(atRate) as atRate from itsDetail isd group by code ) isd on hisd.its_Code_Purchase=isd.code where im.tallySyncdt is null`
- SQL 2: `Update itemMast set tallyKeyName = :tallyKeyName, tallySyncDt = :tallySyncDt where code = :code ;`
- Params: `:code` ← `DmShared.xNull(rsTemp.getString("code"))`; `:tallyKeyName` ← `DmShared.xNull(rsTemp.getString("itemName"))`; `:tallySyncDt` ← `DmServer.getServerTimestamp()`
- Errors: "Exception Occured."
- Other calls: `DocumentBuilderFactory.newInstance`, `FAILED.getCode`, `PROCESSING.getCode`, `TallyExport_Server.sendToTally`, `TallyExport_Server.tallySyncLogInsert`, `TallyExport_Server.tallySyncLogUpdate`, `TransformerFactory.newInstance`, `XPathFactory.newInstance`

## Server `SaleBillVoucher.java` (324 lines)
Helpers used: `DmServer.getParamList`, `DmServer.getServerTimestamp`, `DmShared.iNull`, `DmShared.xNull`

### `startExport`
`public void startExport(Connection cnTemp, DefaultValue_Mdl tmpMdlParameter) throws Exception`

- SQL 1: `select l.recId,case when l.v_sno=1 then sg.name+'('+cast(sg.subCode as varchar(20))+')' end as firstCreditParty,l.docid,isNull(CONVERT(CHAR(8), l.v_Date, 112),'') as voucherDate ,l.narration as commonNarration,sg.name+'('+cast(sg.subCode as varchar(20))+')' as partyName,cast(case when l.amtDr>0 then 0-l.amtDr else l.amtCr end as numeric(19,2)) as amount ,vt.description as voucherType from saleBill sb left join ledger l on sb.docId=l.docId left join ledgerM lm on l.docID=lm.docID left join subGroup sg on l.subCode=sg.subCode left join voucher_Type vt on l.v_Type=vt.v_Type where sb.site_code in ( {…} :siteCodeLst {…} ) and l.isPosted=1 and sb.v_Prefix= :vPrefix and sb.v_Date<= :asOnDate and sb.tallySyncDt is null and (isNull(l.amtDr,0)+isNull(l.amtCr,0))<>0 order by l.v_Date,l.docId,l.v_Sno`
- Params: `:vPrefix` ← `DmShared.xNull(tmpMdlParameter.getV_Prefix())`; `:asOnDate` ← `DmShared.xNull(tmpMdlParameter.getAsOnDate())`
- Other calls: `DocumentBuilderFactory.newInstance`, `FAILED.getCode`, `PROCESSING.getCode`, `TallyExport_Server.tallySyncLogInsert`, `XPathFactory.newInstance`

### `generateXml`
`private void generateXml(Document xmlDocument, String fileDbName, String mongoId, String docId, String recId, Connection cnTemp, String gateWayIP, String gateWayPort, String u_Name, TallyProcess_Mdl tallyprocessMdl) throws Exception`

- SQL 1: `Update saleBill set tallyKeyName = :tallyKeyName, tallySyncDt = :tallySyncDt where docId = :docId ;`
- Params: `:docId` ← `DmShared.xNull(docId)`; `:tallyKeyName` ← `DmShared.xNull(recId)`; `:tallySyncDt` ← `DmServer.getServerTimestamp()`
- Errors: "Exception Occured."
- Other calls: `TallyExport_Server.sendToTally`, `TallyExport_Server.tallySyncLogUpdate`, `TransformerFactory.newInstance`

## Server `PurchaseBillVoucher.java` (322 lines)
Helpers used: `DmServer.getParamList`, `DmServer.getServerTimestamp`, `DmShared.iNull`, `DmShared.xNull`

### `startExport`
`public void startExport(Connection cnTemp, DefaultValue_Mdl tmpMdlParameter) throws Exception`

- SQL 1: `select l.recId,case when l.v_sno=1 then sg.name+'('+cast(sg.subCode as varchar(20))+')' end as firstCreditParty,l.docid,isNull(CONVERT(CHAR(8), l.v_Date, 112),'') as voucherDate ,l.narration as commonNarration,sg.name+'('+cast(sg.subCode as varchar(20))+')' as partyName,cast(case when l.amtDr>0 then 0-l.amtDr else l.amtCr end as numeric(19,2)) as amount ,vt.description as voucherType from pBill pb left join ledger l on pb.docId=l.docId left join ledgerM lm on l.docID=lm.docID left join subGroup sg on l.subCode=sg.subCode left join voucher_Type vt on l.v_Type=vt.v_Type where pb.site_code in ( {…} :siteCodeLst {…} ) and l.isPosted = 1 and pb.v_Prefix= :vPrefix and pb.v_Date<= :asOnDate and pb.tallySyncDt is null order by l.v_Date,l.docId,l.v_Sno`
- Params: `:vPrefix` ← `DmShared.xNull(tmpMdlParameter.getV_Prefix())`; `:asOnDate` ← `DmShared.xNull(tmpMdlParameter.getAsOnDate())`
- Other calls: `DocumentBuilderFactory.newInstance`, `FAILED.getCode`, `PROCESSING.getCode`, `TallyExport_Server.tallySyncLogInsert`, `XPathFactory.newInstance`

### `generateXml`
`private void generateXml(Document xmlDocument, String fileDbName, String mongoId, String docId, String recId, Connection cnTemp, String gateWayIP, String gateWayPort, String u_Name, TallyProcess_Mdl tallyprocessMdl) throws Exception`

- SQL 1: `Update pBill set tallyKeyName = :tallyKeyName, tallySyncDt = :tallySyncDt where docId = :docId ;`
- Params: `:docId` ← `DmShared.xNull(docId)`; `:tallyKeyName` ← `DmShared.xNull(recId)`; `:tallySyncDt` ← `DmServer.getServerTimestamp()`
- Errors: "Exception Occured."
- Other calls: `TallyExport_Server.sendToTally`, `TallyExport_Server.tallySyncLogUpdate`, `TransformerFactory.newInstance`

## Server `TallyExport_Server.java` (153 lines)
Helpers used: `DmBaseServer.getMdbCollection`, `DmShared.iNull`, `DmShared.xNull`

### `sendToTally`
`public static DmResult<String> sendToTally(String transformedStringXml, String gateWayIP, String gateWayPort) throws Exception`

- Other calls: `EntityUtils.toString`, `HttpClients.createDefault`, `JAXBContext.newInstance`

### `tallySyncLogInsert`
`public static String tallySyncLogInsert(String batchId, String trnType, String trn_keyCode, String trn_keyName, String fileDbName) throws Exception`


### `tallySyncLogUpdate`
`public static void tallySyncLogUpdate(String mongoId, String trn_statusRemark, int trn_status, String u_name, String fileDbName) throws Exception`


## Server `UnitMaster.java` (190 lines)
Helpers used: `DmServer.getServerTimestamp`, `DmShared.iNull`, `DmShared.xNull`

### `startExport`
`public void startExport(Connection cnTemp, DefaultValue_Mdl tmpMdlParameter) throws Exception`

- SQL 1: `select um.code,um.unit as unitName,um.decimalPlaces ,sm.tallyGateWayCompanyName as companyName,sm.stateName from unitMast um left join siteMast sm on sm.code=1 where sm.code = :siteCode and um.unit NOT LIKE '%[^a-zA-Z]%' and um.tallySyncdt is null`
- SQL 2: `Update unitMast set tallyKeyName = :tallyKeyName, tallySyncDt = :tallySyncDt where code = :code ;`
- Params: `:siteCode` ← `DmShared.xNull(tmpMdlParameter.getSiteCode())`; `:code` ← `DmShared.xNull(rsTemp.getString("code"))`; `:tallyKeyName` ← `DmShared.xNull(rsTemp.getString("unitName"))`; `:tallySyncDt` ← `DmServer.getServerTimestamp()`
- Errors: "Exception Occured."
- Other calls: `DocumentBuilderFactory.newInstance`, `FAILED.getCode`, `PROCESSING.getCode`, `TallyExport_Server.sendToTally`, `TallyExport_Server.tallySyncLogInsert`, `TallyExport_Server.tallySyncLogUpdate`, `TransformerFactory.newInstance`, `XPathFactory.newInstance`

## Server `SaleBillItemized.java` (602 lines)
Helpers used: `DmServer.getParamList`, `DmServer.getServerTimestamp`, `DmShared.iNull`, `DmShared.xNull`

### `startExport`
`public void startExport(Connection cnTemp, DefaultValue_Mdl tmpMdlParameter) throws Exception`

- SQL 1: `select sb.docid,isNull(sg.add1,'') as partyAddress, isNull(CONVERT(CHAR(8), sb.v_date, 112),'') as voucherDt ,case when sg.partyType='R' then 'Regular' when sg.partyType='C' then 'Composition' else 'Unregistered/Consumer' end as partyGstType ,isNull(sm.name,'') as partyState,isNull(sg.gsTin,'') as partyGSTin, sg.name+'('+cast(sg.subCode as varchar(20))+')' as partyName,sb.recId, vt.description as voucherType,sbd.rate as sRate, um.unit,sbd.oh_Amt_Gross as itemValue, sbd.oh_at_SGST as sgstRate, isNull(sbd.oh_at_SGST,0)+isNull(sbd.oh_at_CGST,0) as igstRate ,round(isNull(sbd.oh_Amt_Gross,0)-isNull(sbd.oh_Amt_Discount,0)-isNull(sbd.oh_Amt_Discount2,0) -(isNull(sbd.oh_Amt_CGST,0)+isNull(sbd.oh_Amt_SGST,0)+isNull(sbd.oh_Amt_IGST,0)),2) as itemTaxableAmount,cast(sbd.skuQuantity as numeric(8,2)) as qty,isNull(sbd.batchNo,'') as batchNo ,sbd.oh_at_CGST as cgstRate, sgd.name as departmentName,sgs.name +'('+cast(sgs.subCode as varchar(20))+')' as salesAc,sbd.isFooter, im.name as ItemName,hsn.hsn ,itmcnt.maxSno,sbd.v_sno from saleBill sb left join voucher_Type vt on sb.v_Type=vt.v_Type left join saleBilldetail sbd on sb.docID=sbd.docID left join ( select pharmacyControlAc from enviro where site_Code=1 ) pty on 1=1 left join subGroup sg on pty.pharmacyControlAc=sg.subCode left join subGroup sgd on sgd.subCode=sb.departmentCode left join subGroup sgs on sgs.subCode=sbd.oh_Ac_Sales left join itemMAst im on sbd.itemCode=im.code left join hsnMast hsn on im.hsnCode=hsn.code left join unitMast um on im.sku=um.code left join city c on sg.cityCode=c.cityCode left join stateMast sm on c.stateCode=sm.code left join stateMast pos on pos.numericCode=sb.placeOfSupply left join ( select sb.docid,max(sbd.v_Sno) as maxSno from saleBill sb left join saleBillDetail sbd on sb.docid = sbd.docId left join voucher_Type vt on sb.v_Type = vt.v_Type where vt.category in(' {…} ') and sbd.isFooter=0 and sb.isCancelled =0 and sb.site_code in ( {…} :siteCodeLst {…} ) and sb.v_Prefix= :vPrefix and sb.v_Date<= :asOnDate and sb.tallySyncDt is null group by sb.docid )itmcnt on sb.docid=itmcnt.docid where vt.category in(' {…} ') and sbd.isFooter=0 and sb.isCancelled =0 and sb.site_code in ( {…} :siteCodeLst {…} ) and sb.v_Prefix= :vPrefix and sb.v_Date<= :asOnDate and sb.tallySyncDt is null order by sb.v_Date,sb.docid,sb.v_no`
- SQL 2: `select ld.docid,ld.ledgerAmount,sg.name +'('+cast(sg.subCode as varchar(20))+')' as ledgerPartyName from ( select sbd.docid,0-sum(oh_Amt_Net) as ledgerAmount,(select pharmacyControlAc from enviro where site_Code=:siteCode) as account from salebill sbd where docid=:docId group by sbd.docid union all select sbd.docid,sum(oh_amt_sgst) as ledgerAmount,sbd.oh_Ac_SGST as account from salebillDetail sbd where docid=:docId group by sbd.docid,sbd.oh_Ac_SGST union all select sbd.docid,sum(oh_amt_cgst) as ledgerAmount,sbd.oh_Ac_CGST as account from salebillDetail sbd where docid=:docId group by sbd.docid,sbd.oh_Ac_CGST union all select sbd.docid,sum(oh_Amt_RoundOff) as ledgerAmount,(select serviceAc from patientServiceCatMast where shortName='RO') as account from salebill sbd where docid=:docId group by sbd.docid ) as ld left join subGroup sg on ld.account=sg.subCode`
- Params: `:asOnDate` ← `DmShared.xNull(tmpMdlParameter.getAsOnDate())`; `:vPrefix` ← `DmShared.xNull(tmpMdlParameter.getV_Prefix())`; `:docId` ← `DmShared.iNull(prevDocid).longValue()`; `:siteCode` ← `DmShared.iNull(tmpMdlParameter.getSiteCode()).intValue()`
- Other calls: `DocumentBuilderFactory.newInstance`, `FAILED.getCode`, `PROCESSING.getCode`, `TallyExport_Server.tallySyncLogInsert`, `XPathFactory.newInstance`

### `generateXml`
`private void generateXml(Document xmlDocument, String fileDbName, String mongoId, String docId, String recId, Connection cnTemp, String gateWayIP, String gateWayPort, String u_Name, TallyProcess_Mdl tallyprocessMdl) throws Exception`

- SQL 1: `Update saleBill set tallyKeyName = :tallyKeyName, tallySyncDt = :tallySyncDt where docId = :docId ;`
- Params: `:docId` ← `DmShared.xNull(docId)`; `:tallyKeyName` ← `DmShared.xNull(recId)`; `:tallySyncDt` ← `DmServer.getServerTimestamp()`
- Errors: "Exception Occured."
- Other calls: `TallyExport_Server.sendToTally`, `TallyExport_Server.tallySyncLogUpdate`, `TransformerFactory.newInstance`

## Depends on (imports from other packages)
`dataman.accounts.self.shared`, `dataman.accounts.tallyExport.shared`, `dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmBaseStatic.server`, `dataman.dmBase.dmBaseStatic.shared`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmGrid.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextArea.client`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmToolContainer.client`, `dataman.dmToolkit.isWidgetExtended.client`, `dataman.libraryMenu.self.shared`

## Business rules (derived from code conditions)
| method | when | error message |
|---|---|---|
| getTallySync | `if (DmServer.isActiveProcessRunning("TallyExport_SrvImpl"))` | "One Process Is Running." |
| getTallySync | `if (DmShared.xNull(defaultValueMdl.getGateWayIP()).equalsIgnoreCase(""))` | "Tally gateWay ip not found." |
| getTallySync | `if (DmShared.xNull(defaultValueMdl.getGateWayPort()).equalsIgnoreCase(""))` | "Tally gateWay port not found." |
| getTallySync | `if (DmShared.xNull(defaultValueMdl.getTallyComp()).equalsIgnoreCase(""))` | "Tally gateWay company name not found." |
| getTallySync | `if (DmShared.xNull(defaultValueMdl.getTallyState()).equalsIgnoreCase(""))` | "Tally gateWay state name not found." |
| getTallySync | `if (DmShared.xNull(defaultValueMdl.getTallyGSTType()).equalsIgnoreCase(""))` | "Tally gateWay GST Type not found." |
| getTallySync | `if (DmShared.xNull(defaultValueMdl.getAsOnDate()).equalsIgnoreCase(""))` | "As On Date not found." |

## Notes (hand-written)
_No hand-written notes yet._

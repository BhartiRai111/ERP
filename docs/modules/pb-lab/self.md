# pb-lab / self

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/lab/src/self/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: Voucher_Type
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
| client/Lab.java | 253 |
| server/LabDataStructure.java | 467 |
| server/Lab_Cntx.java | 81 |
| shared/Lab_Model.java | 88 |

## Server `Lab_Cntx.java` (81 lines)
### `contextInitialized`
`public void contextInitialized(ServletContextEvent sce)`


## Server `LabDataStructure.java` (467 lines)
Helpers used: `DmServer.executeSQL`, `DmServer.executeScalar`, `DmServer.getConnection`, `DmServer.vNull`, `DmShared.vNull`

### `enviro`
`private void enviro(String tableName) throws Exception`


### `impurityMast`
`private void impurityMast(String tableName) throws Exception`


### `testTemplateMast`
`private void testTemplateMast(String tableName) throws Exception`


### `testTemplateDetail`
`private void testTemplateDetail(String tableName) throws Exception`


### `itemImpurityMast`
`private void itemImpurityMast(String tableName) throws Exception`


### `itemImpurityDetail`
`private void itemImpurityDetail(String tableName) throws Exception`


### `labSample`
`private void labSample(String tableName) throws Exception`


### `lab`
`private void lab(String tableName) throws Exception`


### `labDetail`
`private void labDetail(String tableName) throws Exception`


### `initilizer`
`public void initilizer() throws Exception`


### `iniVoucherType`
`private void iniVoucherType(Connection cnTemp) throws Exception`

- SQL 1: `Select Count(*) As Cnt From Voucher_Type VT Where VT.Short_Name = ' {…} ' {…} Select isNull(max(v_type),0) + 1 As Cnt From Voucher_Type VT {…} 0`
- SQL 2: `INSERT INTO Voucher_Type (NCat, Category, V_Type, Description, Short_Name, isSystemDefine, u_Name, PreparedBy, U_EntDt, MnuText,MnuAttachedInModule) VALUES (' {…} ', ' {…} ', ' {…} ', 'Lab Sample', ' {…} ', 1, 'SA', 'SA', getdate(), ' {…} ',' {…} ')`
- SQL 3: `Select Count(*) As Cnt From Voucher_Type VT Where VT.Short_Name = ' {…} ' {…} Select isNull(max(v_type),0) + 1 As Cnt From Voucher_Type VT {…} 0`
- SQL 4: `INSERT INTO Voucher_Type (NCat, Category, V_Type, Description, Short_Name, isSystemDefine, u_Name, PreparedBy, U_EntDt, MnuText,MnuAttachedInModule) VALUES (' {…} ', ' {…} ', ' {…} ', ' {…} ', ' {…} ', 1, 'SA', 'SA', getdate(), 'Lab',' {…} ')`

### `iniProcedure`
`private void iniProcedure(Connection cnTemp) throws Exception`

- SQL 1: `select count(*) as cnt from sysObjects where Name = 'getLabRefrence' and xType = 'P' {…} CREATE procedure [dbo].[getLabRefrence] (@vType smallint, @site_Code varchar(4), @docId varchar(21)) As begin declare @vCat varchar(20) declare @rtnValue varchar(20) declare @tblName varchar(50) declare @tblNameDet varchar(50) declare @approvalCondition varchar(max), @labSampleCondition varchar(max) declare @srQuery varchar(max) declare @joinLabSample varchar(max) select @vCat =category from voucher_Type where v_Type = @vType; print @vCat IF ISNULL(CAST(@docId AS BIGINT),0) > 0 BEGIN SET @joinLabSample = 'LEFT JOIN ( select ls.refDocId, ls.refV_Sno, ls.refSno from labSample ls where ls.docId <> ' + @docId + ' ) as vls ON vls.refDocId = ht.docID ' SET @labSampleCondition = ' AND vls.refDocId IS NULL ' END ELSE BEGIN SET @joinLabSample ='' SET @labSampleCondition ='' END If @vCat='UNLOD' Begin Set @tblName='unLoading' Set @tblNameDet='unLoadingDetail' Set @approvalCondition = '' IF ISNULL(CAST(@docId AS BIGINT),0) > 0 BEGIN SET @joinLabSample = @joinLabSample + ' AND vls.refV_Sno = dt.v_Sno ' END end else If @vCat='SO' begin Set @tblName='sOrder' Set @tblNameDet='sOrderDetail' Set @approvalCondition = ' AND ISNULL(ht.approvedBy,'''')<>'''' ' end set @srQuery='Select ht.docID,ht.recID,ht.v_Date,dt.v_SNO,dt.itemCode,im.name as itemName,im.manualCode,im.sku as unitCode, um.unit as unitName, um.decimalPlaces from '+@tblName+' ht LEFT JOIN '+@tblNameDet+' dt ON dt.docId = ht.docId LEFT JOIN itemMast im ON im.code = dt.itemCode LEFT JOIN unitMast um ON um.code = im.sku ' + @joinLabSample + ' where ht.site_Code = ' + @site_Code + ' ' + @approvalCondition + @labSampleCondition print(@srQuery) exec (@srQuery) end`

## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.shared`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmRefreshDataStructure.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmMenuBar.client`, `dataman.dmToolkit.dmMenuItem.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `paperbiz.lab.itemWiseQualityParameter.client`, `paperbiz.lab.itemWiseQualityParameter.server`, `paperbiz.lab.labEntry.client`, `paperbiz.lab.labEntry.server`, `paperbiz.lab.qualityParameterMaster.client`, `paperbiz.lab.qualityParameterMaster.server`, `paperbiz.lab.reports.client`, `paperbiz.lab.reports.server`, `paperbiz.lab.sampleEntry.client`, `paperbiz.lab.sampleEntry.server`, `paperbiz.lab.sampleLabel.client`, `paperbiz.lab.sampleLabel.server`, `paperbiz.lab.sampleNoGeneration.client`, `paperbiz.lab.sampleNoGeneration.server`, `paperbiz.lab.testTemplateMaster.client`, `paperbiz.lab.testTemplateMaster.server`, `paperbiz.projectMenu.self.shared`, `paperbiz.selfToolkit.labApproval.client`

## Business rules (derived from code conditions)
No explicit rule/exception found beyond validator.

## Notes (hand-written)
_No hand-written notes yet._

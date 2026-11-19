# pb-budget / self

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/budget/src/self/`). Hand-written notes are in the "Notes" section.

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
| client/Budget.java | 142 |
| server/BudgetDataStructure.java | 160 |
| server/Budget_Cntx.java | 46 |
| shared/Budget_Model.java | 33 |

## Server `Budget_Cntx.java` (46 lines)
### `contextInitialized`
`public void contextInitialized(ServletContextEvent sce)`


## Server `BudgetDataStructure.java` (160 lines)
Helpers used: `DmServer.executeSQL`, `DmServer.executeScalar`, `DmServer.getConnection`, `DmServer.vNull`, `DmShared.vNull`

### `budgetTypeMast`
`private void budgetTypeMast(String tableName) throws Exception`


### `budgetLedger`
`private void budgetLedger(String tableName) throws Exception`


### `budgetPlanningHeadMast`
`private void budgetPlanningHeadMast(String tableName) throws Exception`


### `budgetPlanning`
`private void budgetPlanning(String tableName) throws Exception`


### `initilizer`
`public void initilizer() throws Exception`

- SQL 1: `Select Count(*) As Cnt From Voucher_Type VT Where VT.Short_Name = ' {…} ' {…} Select isNull(max(v_type),0) + 1 As Cnt From Voucher_Type VT {…} 0`
- SQL 2: `INSERT INTO Voucher_Type (NCat, Category, V_Type, Description, Short_Name, isSystemDefine, u_Name, PreparedBy, U_EntDt, MnuText,MnuAttachedInModule) VALUES (' {…} ', ' {…} ', ' {…} ', 'Budget Planning', ' {…} ', 1, 'SA', 'SA', getdate(), ' {…} ',' {…} ')`

## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.shared`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmRefreshDataStructure.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmMenuBar.client`, `dataman.dmToolkit.dmMenuItem.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `paperbiz.budget.budgetPlanning.client`, `paperbiz.budget.budgetPlanning.server`, `paperbiz.budget.budgetPlanningHeaderMaster.client`, `paperbiz.budget.budgetPlanningHeaderMaster.server`, `paperbiz.budget.budgetTypeMaster.client`, `paperbiz.budget.budgetTypeMaster.server`, `paperbiz.projectMenu.self.shared`

## Business rules (derived from code conditions)
No explicit rule/exception found beyond validator.

## Notes (hand-written)
_No hand-written notes yet._

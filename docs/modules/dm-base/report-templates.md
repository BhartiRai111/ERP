# dm-base: reports as HTML templates (main way) + optional Jasper

Decision (user): reports are **HTML/PDF templates** (Thymeleaf + OpenHTMLtoPDF). Jasper stays only as an *optional library* for places that really need a Jasper print.

## Flow
1. Data: `ReportDataService.rows/resultSets(procedure, args...)` runs the report stored procedure (about 90, e.g. `GPurchase_Enquiry @docId`); `resultSetsWithFilter(procedure, filter, args...)` for procedures that take `@fieldUDT dbo.fieldUDT`. Every result set -> list of rows (case-insensitive map: `row.Name` = `row.name`).
2. Template: `HtmlReportService.render(name, folder, model)` -> HTML. `renderPdf(...)` -> PDF bytes. `toPdf(html)` for HTML built elsewhere.
3. Controller returns the HTML (screen preview/print) or the PDF.

## Template files
- Folder `${dataman.reports.dir}` (default `reports`): `system/<Name>.html`, `systemCustomized/<Name>.html` (a customer's own version, wins over `system` same as before), `osr/<Name>.html`.
- Names: letters, digits, `_`, `-`, space only.
- Thymeleaf with Spring expressions: `th:text` escapes user data by default; use `th:utext` only for trusted markup.

## In a template
| Need | How |
|---|---|
| Amount `1,234.50` | `${dm.amount(row.amt)}` / `${dm.amount(row.amt, 3)}` |
| Quantity | `${dm.qty(row.qty, 3)}` (decimals only when needed) |
| Amount in words | `${dm.words(total)}` (same Lakh/Million rules and wording as legacy) |
| Date | `${dm.date(row.v_Date)}` (dd-MMM-yyyy) ; `${dm.now()}` current date time |
| Page size, margins | CSS `@page { size: A4 landscape; margin: 15mm }` |
| Page x of y | `@page { @bottom-center { content: "Page " counter(page) " of " counter(pages) } }` |
| Repeat table heading | `thead { display: table-header-group }` |
| Keep a row together | `tr { page-break-inside: avoid }` |
| Watermark | a `position: fixed` element with `transform: rotate(-50deg)` and light grey text |
| Group header / totals | `th:each` over a list prepared in the controller (grouping/totals are done in Java or SQL, not in the template) |

## Fonts (PDF)
Only the 14 standard PDF fonts exist by default (no rupee sign, no Hindi/regional text). Put TrueType/OpenType files in `${dataman.reports.fonts-dir}`; they are registered under their own family name (bold/italic detected from the font name) and used in CSS `font-family`.

## Lists (Find screen export)
- Excel: `GridExcelExporter` (POI, same layout as legacy `RptFind`).
- PDF: `GridPdfExporter` -> default `HtmlGridPdfExporter`; `dataman.reports.grid-pdf=jasper` switches to `JasperGridPdfExporter` (needs the Jasper jars).
- Input: `GridReport` (title, sub title, columns caption/db field/width, page setup, header lines, footer text, odd-row shading) + a `ResultSet`.

## Config
| Property | Meaning |
|---|---|
| `dataman.reports.dir` | template folder (default `reports`) |
| `dataman.reports.fonts-dir` | fonts folder (optional) |
| `dataman.reports.template-cache` | cache compiled templates (true in production; false while editing) |
| `dataman.reports.grid-pdf` | `html` (default) or `jasper` |

## Jasper (optional)
- All Jasper dependencies are `optional` in `dm-base`; `ReportCompiler`, `ReportService`, `JasperGridPdfExporter` exist only when the Jasper jars are on the classpath. To use Jasper add the six artifacts (`jasperreports`, `-pdf`, `-excel-poi`, `-fonts`, `-jdt`, `-groovy`, version 7.1.1) to the module that needs them (see `dm-base/pom.xml` for the needed excludes/overrides).
- Jasper 7.1.1 reads only the new JRXML format; the 72 legacy reports are in the old format, so they will not load without conversion. That is why they move to HTML templates.

## Moving the legacy reports
Per report (done when its module is converted): take the stored procedure it uses (`queryString`), write the HTML template from its layout (bands -> table/sections; groups/variables -> prepared in Java or SQL), compare the printed output with the legacy PDF.

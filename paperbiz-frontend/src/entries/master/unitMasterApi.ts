import { createEntryApi } from "@/lib/entry/entryApi";
import type { ModifyReason } from "@/lib/entry/entryApi";

/** Same as the backend `UnitMasterDto`. */
export interface UnitMaster {
  code: string;
  name: string;
  decimalPlaces: string;
  gstUnitCode: string;
  gstUnitName: string;
  unitNatureCode: string;
  unitNatureName: string;
  isSystemGenerated: string;
  preparedBy: string;
  preparedDate: string;
  preparedIp: string;
  modifiedBy: string;
  modifyDate: string;
  modifyReason?: ModifyReason | null;
}

export interface UnitFindRow extends Record<string, unknown> {
  code: string;
  name: string;
  decimalPlaces: string;
  gstUnitName: string;
  unitNatureName: string;
}

export const unitMasterApi = createEntryApi<UnitMaster, UnitFindRow, { code: string; name: string; manualCode?: string }>("/api/master/unit-master");

export const emptyUnit = (): UnitMaster => ({
  code: "", name: "", decimalPlaces: "", gstUnitCode: "", gstUnitName: "", unitNatureCode: "", unitNatureName: "",
  isSystemGenerated: "", preparedBy: "", preparedDate: "", preparedIp: "", modifiedBy: "", modifyDate: "",
});

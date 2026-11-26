/** Same shapes as the backend (`SessionModels`, dm-base `MenuPermission`, `UserPermissions`). */
export interface Plt {
  code: string;
  name: string;
  linkedAccountCode: string;
}
export interface Company {
  code: string;
  name: string;
  currentYear: string;
  voucherPrefix: string;
  startDate: string;
  endDate: string;
}
export interface Site {
  code: string;
  name: string;
  branchName: string;
}

/** Answer of login / select / me. `*Selected` is null until chosen (a single choice is chosen automatically). */
export interface Selection {
  token: string;
  userName: string;
  userDescription: string;
  loginDate: string;
  plts: Plt[];
  pltSelected: Plt | null;
  companies: Company[];
  companySelected: Company | null;
  sites: Site[];
  siteSelected: Site | null;
  regionalLanguage: string;
  localeCompanyCode: string;
  message: string;
}

export interface MenuPermission {
  moduleName: string;
  refModuleName: string;
  menuText: string;
  menuName: string;
  toolTip: string;
  /** Letters: A add, E edit, D delete, P print. */
  permission: string;
  saveDate: string | null;
  viewDate: string | null;
  deleteDate: string | null;
  allowValueUpto: number;
  controlPermissions: string[];
  children: MenuPermission[];
}

export interface VoucherPermission {
  category: string;
  voucherType: string | null;
  viewDate: string | null;
  deleteDate: string | null;
  saveDate: string | null;
  allowValueUpto: number;
}

export interface UserPermissions {
  menus: Record<string, MenuPermission[]>;
  vouchers: VoucherPermission[];
}

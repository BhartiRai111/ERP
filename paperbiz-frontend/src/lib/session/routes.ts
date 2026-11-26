/** Menu item (module + menu name) -> screen. Menu items without a screen yet stay plain text. */
const ROUTES: Record<string, string> = {
  "master|unit master": "/master/unit-master",
};

export function routeForMenu(moduleName: string, menuName: string): string | undefined {
  return ROUTES[`${moduleName.toLowerCase()}|${menuName.toLowerCase()}`];
}

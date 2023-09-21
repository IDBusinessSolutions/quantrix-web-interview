/**
 * A single item that can be ordered from the menu/
 */
interface MenuItem {
    name: string
}

/**
 * A section on the menu, like "starters", "mains", "dessert", etc.
 */
interface MenuSection {
    title: string,
    items: MenuItem[]
}

interface Menu {
    sections: MenuSection[]
}

interface MenuService {
    getSections(): Promise<Menu>
}

/**
 * A table in the restaurant. Orders are associated with tables.
 * Tables have names to distinguish them, e.g. "Table 1", "Table 2", etc.
 */
interface Table {
    name: string
}

interface SeatingService {
    getTables(): Promise<Table[]>
    getOrderForTable(table: Table): OrderService
}

/**
 * The current order for a given table. The order contains all items
 * ordered by all guests at the table. The same item can appear more
 * than once.
 */
interface Order {
    table: Table,
    items: MenuItem[]
}

/**
 * Service for modifying a table's order. Bound to a specific order.
 * Obtain instances using {@link SeatingService.getOrderForTable}.
 */
interface OrderService {
    addItem(item: MenuItem): Promise<Order>;
}

interface Api {
    createMenuService(): MenuService,
    createSeatingService(): SeatingService
}

let api: Api;

const setApiImpl = (foh: Api) => {
    api = foh;
}

const getApi = () => {
    if (!api)
        throw new Error('No API implementation registered.');

    return api;
};

export type {
    MenuItem,
    MenuSection,
    Menu,
    MenuService,
    Table,
    SeatingService,
    OrderService,
    Api as FohApi
};

export {
    getApi,
    setApiImpl
}
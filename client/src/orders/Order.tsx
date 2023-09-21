import React, { useEffect, useState } from 'react';
import styled from 'styled-components';
import { getApi, Menu, MenuItem, Table } from './api';

const Container = styled.div`
    margin: 2em;

    hr {
        border: none;
        border-bottom: 1px solid gray;
        margin: 1em 0;
    }

    select, button {
        font-size: 1.2em;
    }
`;

const Order = styled.ul`
    list-style: none;
    padding: 0;
`;

const OrderItem = styled.li`
    margin: 0.5em 0;
    padding: 0.5em;
    border: 1px solid gray;
    border-radius: 0.2em;
`;

const Orders: React.FC = () => {
    const [menu, setMenu] = useState<Menu>({ sections: [] });

    useEffect(() => {
        const populateMenu = async () => {
            const _menu = await getApi()
                            .createMenuService()
                            .getSections()

            setMenu(_menu);
        };

        populateMenu();
    }, []);

    const [tables, setTables] = useState<Table[]>([]);

    useEffect(() => {
        const populateTables = async () => {
            const _tables = await getApi()
                            .createSeatingService()
                            .getTables()

            setTables(_tables);
            setTable(_tables[0].name);
        };

        populateTables();
    }, []);

    const [table, setTable] = useState<string | null>(null);
    const [order, setOrder] = useState<MenuItem[]>([]);
    const [nextItem, setNextItem] = useState<string | null>(null);

    const addItemToOrder = async () => {
        if (nextItem) {
            const itemDescription = menu.sections
                .flatMap((section) => section.items)
                .find((i) => i.name === nextItem);

            if (!itemDescription) {
                console.warn('Menu item not found: %s', nextItem);
            } else if (table) {
                // TODO: your code here
            }
        }
    };

    return (
        <Container>
            <h2>Orders</h2>

            {/* The table dropdown */}
            <select
                id="table-picker"
                value={table || ''}
                onChange={(e) => {
                    setTable(e.target.value);
                }}
            >
                {
                    tables.map((t) => (
                        <option key={t.name} value={t.name}>{ t.name }</option>
                    ))
                }
            </select>

            <hr/>

            {/* The menu item dropdown */}
            <select
                id="item-picker"
                value={nextItem || ''}
                onChange={(e) => {
                    setNextItem(e.target.value);
                }}
                style={{
                    marginRight: '0.5em'
                }}
            >
                {/*
                  * Default empty option so the item dropdown will be initially empty,
                  * just for aesthetic purposes.
                  */}
                <option value="" disabled hidden />

                {
                    menu.sections.map(({ title, items }) => (
                        <optgroup key={title} label={title}>
                            {
                                items.map(({ name }) => (
                                    <option key={`${title}.${name}`} value={name}>{ name }</option>
                                ))
                            }
                        </optgroup>
                    ))
                }
            </select>
            <button
                type="button"
                disabled={!nextItem}
                onClick={addItemToOrder}
            >
                Add to Order
            </button>

            {/* The list of current items in the order */}
            <Order>
                {
                    order.map((menuItem, i) => (
                        <OrderItem key={i}>
                            { menuItem.name }
                        </OrderItem>
                    ))
                }
            </Order>
        </Container>
    )
};

export default Orders;
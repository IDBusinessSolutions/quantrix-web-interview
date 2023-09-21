import { FohApi, OrderService, Table } from './api';

const API_ROOT = 'http://localhost:8080/api'

const createMenuService: FohApi['createMenuService'] = () => ({
    async getSections() {
        const resp = await fetch(
            `${API_ROOT}/menu`,
            {
                headers: {
                    Accept: 'application/json'
                }
            }
        );

        if (!resp.ok)
            throw new Error('Failed to fetch menu');

        return await resp.json();
    }
});

const createOrderService = (table: Table): OrderService => {
    return {
        async addItem(item) {
            // TODO: implement this method

            // remove this default value
            return {
                table: { name: '' },
                items: []
            };
        }
    };
};

const createSeatingService: FohApi['createSeatingService'] = () => ({
    async getTables() {
        const resp = await fetch(
            `${API_ROOT}/tables`,
            {
                headers: {
                    Accept: 'application/json'
                }
            }
        );

        if (!resp.ok)
            throw new Error('Failed to fetch tables');

        return await resp.json();
    },

    getOrderForTable(table) {
        return createOrderService(table);
    }
});

const impl: FohApi = {
    createMenuService,
    createSeatingService
};

export default impl;
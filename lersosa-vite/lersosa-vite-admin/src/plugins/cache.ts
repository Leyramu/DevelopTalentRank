/*
 * Copyright (c) 2024 Leyramu. All rights reserved.
 * This project (Lersosa), including its source code, documentation, and any associated materials, is the intellectual property of Leyramu. No part of this software may be reproduced, distributed, or transmitted in any form or by any means, including photocopying, recording, or other electronic or mechanical methods, without the prior written permission of the copyright owner, Miraitowa_zcx, except in the case of brief quotations embodied in critical reviews and certain other noncommercial uses permitted by copyright law.
 * For inquiries related to licensing or usage outside the scope of this notice, please contact the copyright holder at 2038322151@qq.com.
 * The author disclaims all warranties, express or implied, including but not limited to the warranties of merchantability and fitness for a particular purpose. Under no circumstances shall the author be liable for any special, incidental, indirect, or consequential damages arising from the use of this software.
 * By using this project, users acknowledge and agree to abide by these terms and conditions.
 */

interface Cache {
    set(key: string, value: string): void;

    get(key: string): string | null;

    setJSON(key: string, jsonValue: unknown): void;

    getJSON<T>(key: string): T | null;

    remove(key: string): void;
}

const sessionCache: Cache = {
    set(key: string, value: string) {
        if (!sessionStorage) {
            return;
        }
        if (key != null && value != null) {
            sessionStorage.setItem(key, value);
        }
    },
    get(key: string) {
        if (!sessionStorage) {
            return null;
        }
        if (key == null) {
            return null;
        }
        return sessionStorage.getItem(key);
    },
    setJSON(key: string, jsonValue: unknown) {
        if (jsonValue != null) {
            this.set(key, JSON.stringify(jsonValue));
        }
    },
    getJSON<T>(key: string) {
        const value = this.get(key);
        if (value != null) {
            return JSON.parse(value) as T;
        }
        return null;
    },
    remove(key: string) {
        sessionStorage.removeItem(key);
    }
};

const localCache: Cache = {
    set(key: string, value: string) {
        if (!localStorage) {
            return;
        }
        if (key != null && value != null) {
            localStorage.setItem(key, value);
        }
    },
    get(key: string) {
        if (!localStorage) {
            return null;
        }
        if (key == null) {
            return null;
        }
        return localStorage.getItem(key);
    },
    setJSON(key: string, jsonValue: unknown) {
        if (jsonValue != null) {
            this.set(key, JSON.stringify(jsonValue));
        }
    },
    getJSON<T>(key: string) {
        const value = this.get(key);
        if (value != null) {
            return JSON.parse(value) as T;
        }
        return null;
    },
    remove(key: string) {
        localStorage.removeItem(key);
    }
};

export default {
    /**
     * 会话级缓存
     */
    session: sessionCache,
    /**
     * 本地缓存
     */
    local: localCache
};


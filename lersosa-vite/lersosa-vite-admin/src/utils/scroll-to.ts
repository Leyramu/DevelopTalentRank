/*
 * Copyright (c) 2024 Leyramu. All rights reserved.
 * This project (Lersosa), including its source code, documentation, and any associated materials, is the intellectual property of Leyramu. No part of this software may be reproduced, distributed, or transmitted in any form or by any means, including photocopying, recording, or other electronic or mechanical methods, without the prior written permission of the copyright owner, Miraitowa_zcx, except in the case of brief quotations embodied in critical reviews and certain other noncommercial uses permitted by copyright law.
 * For inquiries related to licensing or usage outside the scope of this notice, please contact the copyright holder at 2038322151@qq.com.
 * The author disclaims all warranties, express or implied, including but not limited to the warranties of merchantability and fitness for a particular purpose. Under no circumstances shall the author be liable for any special, incidental, indirect, or consequential damages arising from the use of this software.
 * By using this project, users acknowledge and agree to abide by these terms and conditions.
 */

// 定义 easeInOutQuad 函数
declare global {
    interface Math {
        easeInOutQuad(t: number, b: number, c: number, d: number): number;
    }

    interface Window {
        webkitRequestAnimationFrame?: (callback: FrameRequestCallback) => number;
        mozRequestAnimationFrame?: (callback: FrameRequestCallback) => number;
    }
}

Math.easeInOutQuad = function (t: number, b: number, c: number, d: number): number {
    t /= d / 2;
    if (t < 1) {
        return c / 2 * t * t + b;
    }
    t--;
    return -c / 2 * (t * (t - 2) - 1) + b;
}

// requestAnimationFrame for Smart Animating http://goo.gl/sx5sts
const requestAnimFrame = (function (): ((callback: FrameRequestCallback) => number) {
    return window.requestAnimationFrame || window.webkitRequestAnimationFrame || window.mozRequestAnimationFrame || function (callback: FrameRequestCallback) {
        window.setTimeout(callback, 1000 / 60);
    };
})();

/**
 * Because it's so fucking difficult to detect the scrolling element, just move them all
 * @param {number} amount
 */
function move(amount: number): void {
    document.documentElement.scrollTop = amount;
    const bodyParentNode = document.body.parentNode as HTMLElement | null;
    if (bodyParentNode) {
        bodyParentNode.scrollTop = amount;
    }
    document.body.scrollTop = amount;
}


function position(): number {
    return document.documentElement.scrollTop || (document.body.parentNode as HTMLElement)?.scrollTop || document.body.scrollTop || 0;
}

/**
 * @param {number} to
 * @param {number} duration
 * @param {Function} callback
 */
export function scrollTo(to: number, duration?: number, callback?: () => void): void {
    const start = position();
    const change = to - start;
    const increment = 20;
    let currentTime = 0;
    duration = typeof duration === 'undefined' ? 500 : duration;

    const animateScroll = function (): void {
        // increment the time
        currentTime += increment;
        // find the value with the quadratic in-out easing function
        const val = Math.easeInOutQuad(currentTime, start, change, duration);
        // move the document.body
        move(val);
        // do the animation unless its over
        if (currentTime < duration) {
            requestAnimFrame(animateScroll);
        } else {
            if (callback && typeof callback === 'function') {
                // the animation is done so lets callback
                callback();
            }
        }
    };

    animateScroll();
}

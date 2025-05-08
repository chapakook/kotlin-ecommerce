import http from 'k6/http';
import {check} from 'k6';

export let options = {
    stages: [
        {duration: '5s', target: 10},
        {duration: '5s', target: 30},
        {duration: '5s', target: 50},
        {duration: '5s', target: 80},
        {duration: '10s', target: 100},
        {duration: '20s', target: 100},
        {duration: '10s', target: 0},
    ],
};

export default function () {
    const url = 'http://host.docker.internal:8080/coupon/issue';

    const payload = JSON.stringify({
        userId: Math.floor(Math.random() * 10000),
        couponId: 2,
    });

    const params = {
        headers: {'Content-Type': 'application/json'},
    };

    const res = http.post(url, payload, params);
    check(res, {'status was 200': (r) => r.status === 200});
}

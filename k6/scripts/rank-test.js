import http from 'k6/http';
import {check} from 'k6';

export let options = {
    vus: 100,
    duration: '60s',
};

export default function () {
    const url = 'http://host.docker.internal:8080/products/rank';

    const res = http.get(url);

    check(res, {
        'status is 200': (r) => r.status === 200,
        'body is not empty': (r) => r.body.length > 0,
    });

    // sleep(1);
}

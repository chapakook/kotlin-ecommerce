import http from 'k6/http';
import { sleep } from 'k6';

export let options = {
  stages: [
    { duration: '1m', target: 10 },
    { duration: '30s', target: 100 },
    { duration: '2m', target: 500 },
    { duration: '30s', target: 100 },
    { duration: '1m', target: 0 },
  ],
};

export default function () {
  const url = 'http://localhost:8088/coupon/issue';
  const payload = JSON.stringify({
    userId: Math.floor(Math.random() * 10000),
    couponId: 2,
  });

  const params = {
    headers: { 'Content-Type': 'application/json' },
  };

  http.post(url, payload, params);
  sleep(1);
}

import { WebSocket } from 'ws';

/* Generate random */

function randomNormal(mean, standardDeviation) {
  const u = Math.random();
  const v = Math.random();
  return mean + standardDeviation * Math.sqrt(-2 * Math.log(u)) * Math.cos(2 * Math.PI * v);
}


/* Sensor data */

const SERIAL_NUMBER = 'BRP001';

function getHeartRate() {
  return Math.round(Math.min(Math.max(randomNormal(75, 10), 40), 160));
}

function getBloodOxygen() {
  return parseFloat(Math.min(Math.max(randomNormal(96, 1), 85), 100).toFixed(2));
}

function getAccelerometer() {
    const random = () => randomNormal(0, 0.05);
    return {
        x: parseFloat(random().toFixed(2)),
        y: parseFloat(random().toFixed(2)),
        z: parseFloat((9.81 + random()).toFixed(2)), // gravity on z
    };
}


/* Send data */

function createSample(type, value) {
    return {
        braceletId: SERIAL_NUMBER,
        timestamp: new Date().toISOString(),
        type: type,
        value: value
    }
}

const run = async () => {
    const webSocket = new WebSocket(`ws:${process.env.IOT_GATEWAY_URL || 'localhost:8000'}`);

    let heartRateInterval = null;
    let bloodOxygenInterval = null;
    let accelerometerInterval = null;

    webSocket.on('open', () => {
        console.log('Connected to gateway');

        heartRateInterval = setInterval(
            () => { 
                webSocket.send(JSON.stringify(createSample('heartRate', getHeartRate())));
                console.log('send heart rate to gateway');
            }, 
            5000
        );

        bloodOxygenInterval = setInterval(
            () => { 
                webSocket.send(JSON.stringify(createSample('bloodOxygen', getBloodOxygen())));
                console.log('send blood oxygen to gateway');
            }, 
            10000
        );

        accelerometerInterval = setInterval(
            () => { 
                webSocket.send(JSON.stringify(createSample('accelerometer', getAccelerometer())));
                console.log('send accelerometer to gateway');
            }, 
            1000
        );
    });

    webSocket.on('close', () => {
        console.log('Disconnected from gateway, retrying in 5s...');
        clearInterval(heartRateInterval);
        clearInterval(bloodOxygenInterval);
        clearInterval(accelerometerInterval);
        setTimeout(run, 5000);
    });

    webSocket.on('error', webSocket.close);
}

run().catch(console.error);
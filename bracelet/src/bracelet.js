import { WebSocket } from 'ws';

function generateRandom(min, max) {
    return Math.floor(Math.random() * (max - min + 1)) + min
}


/* Sensor data */

const SERIAL_NUMBER = Array.from({length: 3}, () => Math.floor(Math.random() * 0x10000).toString(16).padStart(4, '0').toUpperCase()).join('-');

function getHeartRate() {
    return Math.floor(generateRandom(60, 100));
}

function getBloodOxygen() {
    return Math.floor(generateRandom(95, 100));
}

function getAccelerometer() {
    const random = () => generateRandom(-1, 1);
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
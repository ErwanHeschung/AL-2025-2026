import { Kafka } from 'kafkajs';
import { WebSocketServer } from 'ws';

const run = async () => {
    const kafka = new Kafka({
        clientId: 'iot_gateway',
        brokers: [process.env.KAFKA_URL || 'localhost:9092']
    });
    const kafkaProducer = kafka.producer();
    await kafkaProducer.connect(); 

    const webSocketServer = new WebSocketServer({ port: 8000 });
    webSocketServer.on('connection', webSocket => {
        console.log('Bracelet connected');

        webSocket.on('message', message => {
            const data = JSON.parse(message);
            kafkaProducer.send({ topic: data.type, messages: [{ key: null, value: message }] });
            console.log(`Transmitting data from bracelet ${data.braceletId} to topic ${data.type}`);
        });

        webSocket.on('close', () => {
            console.log('Bracelet disconnected');
        });
    });
};

run().catch(console.error);
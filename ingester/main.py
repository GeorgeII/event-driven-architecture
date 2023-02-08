import asyncio
import json
import os
import uuid

from kafka import KafkaProducer

messages_per_second = 10
data_path = os.path.normpath("data/ltc")

producer = KafkaProducer(bootstrap_servers='kafka:9092', value_serializer=lambda v: json.dumps(v).encode('utf-8'))


async def throttle_transactions_to_kafka(txs_filename: str, topic: str):
    txs_filepath = os.path.join(data_path, txs_filename)
    with open(txs_filepath) as f:
        for idx, line in enumerate(f):
            json_entity = json.loads(line)
            json_entity["correlation_id"] = str(uuid.uuid4())
            producer.send(topic=topic, value=json_entity)
            await asyncio.sleep(1 / messages_per_second)


async def main():
    print("Start sending messages into Kafka...")

    receivers_task = asyncio.create_task(throttle_transactions_to_kafka("ltc-receivers-april.json", "receivers"))
    senders_task = asyncio.create_task(throttle_transactions_to_kafka("ltc-senders-april.json", "senders"))

    await receivers_task
    await senders_task


asyncio.run(main())
